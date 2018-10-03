package ru.stegnin.javaee.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.jetbrains.annotations.NotNull;
import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
@URLMapping(id = "user-add", pattern = "/user-add", viewId = "/WEB-INF/faces/user-add.xhtml")
public class UserAddController implements Serializable {
    @Inject
    private UserRepository userRepo;

    @NotNull
    private User user = new User();

    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(@NotNull final User user) {
        this.user = user;
    }

    @NotNull
    public String save() {
        userRepo.save(user);
        return "users";
    }
}
