package ru.stegnin.javaee.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.model.User_;
import ru.stegnin.javaee.repository.AbstractRepository;
import ru.stegnin.javaee.repository.UserRepository;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class UserService extends AbstractRepository implements UserRepository {
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
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Nullable
    @Override
    public User findByLogin(String login) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Predicate predicate = builder.like(root.get(User_.login), login);
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
}
