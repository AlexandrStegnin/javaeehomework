package ru.stegnin.javaee.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.model.User_;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.UserRepository;
import ru.stegnin.javaee.support.Constants;
import ru.stegnin.javaee.util.PasswordUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserService extends AbstractRepository implements UserRepository {
    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Override
    public Collection<User> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> from = cq.from(User.class);
        cq.select(from);
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public User findOne(String userId) {
        return em.find(User.class, userId);
    }

    @Override
    public void delete(User user) {
        em.remove(em.find(User.class, user.getId()));
    }

    @Override
    public User save(User user) {
        return em.merge(user);
    }

    @Override
    public User update(User user) {
        User oldUser = em.find(User.class, user.getId());
        if (user.getPassword().isEmpty()) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(PasswordUtils.digestPassword(user.getPassword()));
        }
        if (user.getEmail().isEmpty()) {
            user.setEmail(oldUser.getEmail());
        }
        return em.merge(user);
    }

    @Nullable
    @Override
    public User findByLogin(@NotNull String login) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicate = builder.equal(root.get(User_.LOGIN), login);
        criteriaQuery.where(predicate);
        criteriaQuery.select(root);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Nullable
    @Override
    public User findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Predicate predicate = cb.like(root.get(User_.email), email);
        cq.where(predicate);
        TypedQuery<User> query = em.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public void init(String login, String email, String password) {
        if (!isLoginUnique(login)) return;
        if (!isEmailUnique(email)) return;
        User user = new User.Builder().withLogin(login).withEmail(email).withPassword(password).build();
        save(user);
    }

    @Override
    public boolean isLoginUnique(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        return countByCondition(login, null) == 0;
    }

    @Override
    public boolean isEmailUnique(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        return countByCondition(null, email) == 0;
    }

    @NotNull
    private Long countByCondition(@Nullable String login, @Nullable String email) {
        List<Predicate> predicates = new ArrayList<>(2);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);
        if (login != null && !login.isEmpty()) {
            predicates.add(builder.like(root.get(User_.login), login));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(builder.like(root.get(User_.email), email));
        }
        criteriaQuery.select(builder.count(root));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    @Nullable
    public User delete(@NotNull String userId) {
        User user = em.find(User.class, userId);
        if (user != null) {
            em.remove(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean check(@NotNull final String login, @NotNull final String password) {
        if (login.isEmpty()) return false;
        if (password.isEmpty()) return false;
        final User user = findByLogin(login);
        return user != null && user.getPassword().equals(PasswordUtils.digestPassword(password));
    }

    @Override
    public String generateToken(@NotNull String login, @NotNull UriInfo uriInfo) {
        Key key = keyGenerator.generateKey(Constants.KEY_GENERATOR);
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        logger.info("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }
}
