package ru.stegnin.javaee.rest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@WebService
@Path("/api")
public class RestController {

    @GET
    @WebMethod
    @Path("/hello")
    public String hello() {
        return "HELLO";
    }
}
