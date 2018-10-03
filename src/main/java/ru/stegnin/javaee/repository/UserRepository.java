package ru.stegnin.javaee.repository;

import ru.stegnin.javaee.model.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findAll();

    User findOne(String id);

    void delete(User user);

    void save(User user);

    void update(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    void init(String login, String email, String password);

    boolean isLoginUnique(String login);

    boolean isEmailUnique(String email);
}
