package ru.stegnin.javaee.rest;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@WebService
@Path(Constants.API)
public class ProductController {
    @Inject
    private ProductRepository productRepo;

    @GET
    @WebMethod
    @Path(Constants.API_PRODUCTS)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(productRepo.findAll());
    }

    @GET
    @WebMethod
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOneProduct(@PathParam(Constants.API_PRODUCT_ID) String productId) {
        return productRepo.findOne(productId);
    }

    @DELETE
    @WebMethod
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    public String deleteProduct(@PathParam(Constants.API_PRODUCT_ID) String productId) {
        Product product = productRepo.delete(productId);
        if (product != null) {
            return "Товар " + product.getName() + " успешно удалён";
        } else {
            return "Товар с ID = " + productId + " не найден!";
        }
    }

    @POST
    @WebMethod
    @Path(Constants.API_PRODUCTS)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @PUT
    @WebMethod
    @Path(Constants.API_PRODUCTS_PRODUCT_ID)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String updateUser(@PathParam(Constants.API_PRODUCT_ID) String productId, Product product) {
        product.setId(productId);
        product = productRepo.update(product);
        if (product != null) {
            return "Товар " + product.getName() + " успешно обновлён";
        } else {
            return "Товар с ID " + productId + " не найден!";
        }
    }
}
