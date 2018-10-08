package ru.stegnin.javaee.endpoints;

import ru.stegnin.javaee.filter.JWTTokenNeeded;
import ru.stegnin.javaee.model.Tag;
import ru.stegnin.javaee.repository.TagRepository;
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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path(Constants.API_TAGS)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
@JWTTokenNeeded
public class TagEndPoint {
    @Context
    private UriInfo uriInfo;
    private Logger logger = Logger.getLogger(UserEndpoint.class.getName());
    private GenericResponse message;

    @Inject
    private TagRepository tagRepo;

    /**
     * Создать тэг
     * @param tag - тэг в формате json
     * @return - response с сообщением
     */
    @POST
    public Response create(Tag tag) {
        tag = tagRepo.save(tag);
        message = new GenericResponse.Builder().withMessage("Tag : " + tag + " created").build();
        return Response.created(uriInfo.getAbsolutePathBuilder().path(tag.getId()).build()).entity(message).build();
    }

    /**
     * Найти тэг по id
     * @param tagId - id товара
     * @return - response с сообщением
     */
    @GET
    @Path(Constants.API_TAGS_TAG_ID)
    public Response findById(@PathParam(Constants.API_TAG_ID) String tagId) {
        Tag tag = tagRepo.findOne(tagId);

        if (tag == null) {
            message = new GenericResponse.Builder().withMessage("Tag with id : " + tagId + " not found").build();
            return Response.status(NOT_FOUND).entity(message).build();
        }
        return Response.ok(tag).build();
    }

    /**
     * Достать все тэги
     * @return - response со списком товаров
     */
    @GET
    public Response findAllTags() {
        List<Tag> allTags = new ArrayList<>(tagRepo.findAll());
        return Response.ok(allTags).build();
    }

    /**
     * Изменить тэг
     * @param tagId - id тэга
     * @param tag - данные тэга для изменения в формате json
     * @return - response с сообщением
     */
    @PUT
    @Path(Constants.API_TAGS_TAG_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(Constants.API_TAG_ID) String tagId, Tag tag) {
        tag.setId(tagId);
        tag = tagRepo.update(tag);
        if (tag != null) {
            return Response.ok().entity(tag).build();
        } else {
            message = new GenericResponse.Builder().withMessage("Tag with id " + tagId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }

    /**
     * Удалить тэг по id
     * @param tagId - id тэга
     * @return - response с сообщением
     */
    @DELETE
    @Path(Constants.API_TAGS_TAG_ID)
    public Response remove(@PathParam(Constants.API_TAG_ID) String tagId) {
        Tag tag = tagRepo.remove(tagId);
        if (tag != null) {
            message = new GenericResponse.Builder().withMessage("Tag with id " + tagId + " deleted successful.").build();
            return Response.ok().entity(message).build();
        } else {
            message = new GenericResponse.Builder().withMessage("Tag with id " + tagId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }
}
