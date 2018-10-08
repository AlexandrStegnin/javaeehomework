package ru.stegnin.javaee.endpoints;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;
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

@Path(Constants.API_PRODUCTS)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class ProductEndpoint {
    @Context
    private UriInfo uriInfo;
    private Logger logger = Logger.getLogger(UserEndpoint.class.getName());
    private GenericResponse message;

    @Inject
    private ProductRepository productRepo;

    /**
     * Создать товар
     * @param product - товар в формате json
     * @return - response с сообщением
     */
    @POST
    public Response create(Product product) {
        product = productRepo.save(product);
        message = new GenericResponse.Builder().withMessage("Product : " + product + " created").build();
        return Response.created(uriInfo.getAbsolutePathBuilder().path(product.getId()).build()).entity(message).build();
    }

    /**
     * Найти товар по id
     * @param productId - id товара
     * @return - response с сообщением
     */
    @GET
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    public Response findById(@PathParam(Constants.API_PRODUCT_ID) String productId) {
        Product product = productRepo.findOne(productId);

        if (product == null) {
            message = new GenericResponse.Builder().withMessage("Product with id : " + productId + " not found").build();
            return Response.status(NOT_FOUND).entity(message).build();
        }
        return Response.ok(product).build();
    }

    /**
     * Найти товары по тэгу
     * @param tagId - id тэга
     * @return - response с сообщением
     */
    @GET
    @Path(Constants.API_TAGS + Constants.API_PRODUCTS_TAG)
    public Response findByTag(@PathParam(Constants.API_TAG_ID) String tagId) {
        List<Product> products = productRepo.findByTag(tagId);
        return Response.ok(products).build();
    }

    /**
     * Достать все товары
     * @return - response со списком товаров
     */
    @GET
    public Response findAllProducts() {
        List<Product> allProducts = new ArrayList<>(productRepo.findAll());
        return Response.ok(allProducts).build();
    }

    /**
     * Изменить товар
     * @param productId - id пользователя
     * @param product - данные товара для изменения в формате json
     * @return - response с сообщением
     */
    @PUT
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(Constants.API_PRODUCT_ID) String productId, Product product) {
        product.setId(productId);
        product = productRepo.update(product);
        if (product != null) {
            return Response.ok().entity(product).build();
        } else {
            message = new GenericResponse.Builder().withMessage("Product with id " + productId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }

    /**
     * Удалить товар по id
     * @param productId - id товара
     * @return - response с сообщением
     */
    @DELETE
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    public Response remove(@PathParam(Constants.API_PRODUCT_ID) String productId) {
        Product product = productRepo.remove(productId);
        if (product != null) {
            message = new GenericResponse.Builder().withMessage("Product with id " + productId + " deleted successful.").build();
            return Response.ok().entity(message).build();
        } else {
            message = new GenericResponse.Builder().withMessage("Product with id " + productId + " not found.").build();
            return Response.status(Response.Status.NOT_FOUND).entity(message).build();
        }
    }
}
