package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
@URLMapping(id = "users", pattern = "/users", viewId = "/WEB-INF/faces/users.xhtml")
public class UserController implements Serializable {
    @Inject
    private UserRepository userRepo;

    public Collection<User> getUsers() {
        return userRepo.findAll();
    }

    private User user;

    public void delete() {
        userRepo.delete(user);
    }

    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(@NotNull final String userId) {
        this.user = userRepo.findOne(userId);
    }

    public void attrListener(ActionEvent event){
        String userId = (String) event.getComponent().getAttributes().get("userId");
        setUser(userId);
    }
}
