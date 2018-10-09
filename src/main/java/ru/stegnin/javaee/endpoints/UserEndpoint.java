package ru.stegnin.javaee.endpoints;

import ru.stegnin.javaee.annotation.JWTTokenNeeded;
import ru.stegnin.javaee.model.User;
import ru.stegnin.javaee.repository.UserRepository;
import ru.stegnin.javaee.support.Constants;
import ru.stegnin.javaee.support.GenericResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path(Constants.API_USERS)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@JWTTokenNeeded
public class UserEndpoint {

    @Context
    private UriInfo uriInfo;
    private Logger logger = Logger.getLogger(UserEndpoint.class.getName());
    private GenericResponse message;

    @Inject
    private UserRepository userRepo;

    /**
     * Аутентификация пользователя по логину и паролю
     * @param user - данные пользователя (логин/пароль) в формате json
     * @return - response с сообщением
     */
    @POST
    @Path(Constants.API_USERS_AUTH)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response authenticateUser(User user) {
        try {

            logger.info("#### try to login by ussr credentials ####");

            // Check user using the credentials provided
            boolean check = userRepo.check(user.getLogin(), user.getPassword());
            if (!check) throw new  SecurityException("login/password is incorrect");

            // Issue a token for the user
            String token = userRepo.generateToken(user.getLogin(), uriInfo);

            // Generate message body
            message = new GenericResponse.Builder().withMessage(Constants.MESSAGE_LOGIN_SUCCESSFUL).build();

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).entity(message).build();

        } catch (Exception e) {
            message = new GenericResponse.Builder().withMessage(Constants.MESSAGE_LOGIN_UNAUTHORIZED).build();
            return Response.status(UNAUTHORIZED).entity(message).build();
        }
    }

    /**
     * Создать пользователя
     * @param user - пользователь в формате json
     * @return - response с сообщением
     */
    @POST
    public Response create(User user) {
        user = userRepo.save(user);
        message = new GenericResponse.Builder().withMessage("User : " + user + " created").build();
        return Response.created(uriInfo.getAbsolutePathBuilder().path(user.getId()).build()).entity(message).build();
    }

    /**
     * Найти пользователя по id
     * @param userId - id пользователя
     * @return - response с сообщением
     */
    @GET
    @Path(Constants.API_USERS_USER_ID)
    public Response findById(@PathParam(Constants.API_USER_ID) String userId) {
        User user = userRepo.findOne(userId);

        if (user == null) {
            message = new GenericResponse.Builder().withMessage("User with id : " + userId + " not found").build();
            return Response.status(NOT_FOUND).entity(message).build();
        }
        return Response.ok(user).build();
    }

    /**
     * Достать всех пользователей
     * @return - response со списком пользователей
     */
    @GET
    public Response findAllUsers() {
        List<User> allUsers = new ArrayList<>(userRepo.findAll());
        return Response.ok(allUsers).build();
    }

    /**
     * Изменить пользователя
     * @param userId - id пользователя
     * @param user - данные пользователя для изменения в формате json
     * @return - response с сообщением
     */
    @PUT
    @Path(Constants.API_USERS_USER_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(Constants.API_USER_ID) String userId, User user) {
        user.setId(userId);
        user = userRepo.update(user);
        if (user != null) {
            return Response.ok().entity(user).build();
        } else {
            message = new GenericResponse.Builder().withMessage("User with id " + userId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }

    /**
     * Удалить пользователя по id
     * @param userId - id пользователя
     * @return - response с сообщением
     */
    @DELETE
    @Path(Constants.API_USERS_USER_ID)
    public Response remove(@PathParam(Constants.API_USER_ID) String userId) {
        User user = userRepo.delete(userId);
        if (user != null) {
            message = new GenericResponse.Builder().withMessage("User with id " + userId + " deleted succesfull.").build();
            return Response.ok().entity(message).build();
        } else {
            message = new GenericResponse.Builder().withMessage("User with id " + userId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }
}
