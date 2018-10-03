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

@Named
@RequestScoped
@URLMapping(id = "user-edit", pattern = "/user-edit", viewId = "/WEB-INF/faces/user-edit.xhtml")
public class UserEditController implements Serializable {
    @Inject
    private UserRepository userRepo;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(@NotNull String userId) {
        this.user = userRepo.findOne(userId);
    }

    @NotNull
    public String update(User user) {
        userRepo.update(user);
        return "users";
    }

    public void attrListener(ActionEvent event){
        String userId = (String) event.getComponent().getAttributes().get("userId");
        user = userRepo.findOne(userId);
    }

    public void delete() {
        userRepo.delete(user);
    }
}
