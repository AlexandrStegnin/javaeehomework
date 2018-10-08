package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.User;

import javax.ws.rs.core.UriInfo;
import java.util.Collection;

public interface UserRepository {
    Collection<User> findAll();

    User findOne(String id);

    void delete(User user);

    User save(User user);

    User update(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    void init(String login, String email, String password);

    User delete(String userId);

    boolean isLoginUnique(String login);

    boolean isEmailUnique(String email);

    boolean check(String login, String password);

    String generateToken(String login, UriInfo uriInfo);
}
