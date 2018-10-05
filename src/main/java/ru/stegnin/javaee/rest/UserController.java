package ru.stegnin.javaee.rest;

import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.repository.UserRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@WebService
@Path(Constants.API)
public class UserController {

    @Inject
    private UserRepository userRepo;

    @GET
    @WebMethod
    @Path(Constants.API_USERS)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    @GET
    @WebMethod
    @Path(Constants.API_USERS_USER_ID)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getOne(@PathParam(Constants.API_USER_ID) String userId) {
        return userRepo.findOne(userId);
    }

    @DELETE
    @WebMethod
    @Path(Constants.API_USERS_USER_ID)
    public String deleteUser(@PathParam(Constants.API_USER_ID) String userId) {
        User user = userRepo.delete(userId);
        if (user != null) {
            return "Пользователь " + user.getLogin() + " успешно удалён";
        } else {
            return "Пользователь с ID = " + userId + " не найден!";
        }
    }

    @POST
    @WebMethod
    @Path(Constants.API_USERS)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @PUT
    @WebMethod
    @Path(Constants.API_USERS_USER_ID)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String updateUser(@PathParam(Constants.API_USER_ID) String userId, User user) {
        user.setId(userId);
        user = userRepo.update(user);
        if (user != null) {
            return "Пользователь " + user.getLogin() + " успешно обновлён";
        } else {
            return "Пользователь с ID " + userId + " не найден!";
        }
    }
}
