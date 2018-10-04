package ru.stegnin.javaee.rest;

import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.repository.UserRepository;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@WebService
@Path("/api")
public class UserController {

    @Inject
    private UserRepository userRepo;

    @GET
    @WebMethod
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    @GET
    @WebMethod
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getOne(@PathParam("userId") String userId) {
        return userRepo.findOne(userId);
    }

    @DELETE
    @WebMethod
    @Path("users/{userId}")
    public String deleteUser(@PathParam("userId") String userId) {
        User user = userRepo.delete(userId);
        if (user != null) {
            return "Пользователь <b>" + user.getLogin() + "</b> успешно удалён";
        } else {
            return "Пользователь с ID = " + userId + " не найден!";
        }
    }

    @POST
    @WebMethod
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @PUT
    @WebMethod
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("userId") String userId, User user) {
        user.setId(userId);
        user = userRepo.update(user);
        if (user != null) {
            return "Пользователь " + user.getLogin() + " успешно обновлён";
        } else {
            return "Пользователь с ID " + userId + " не найден!";
        }
    }
}
