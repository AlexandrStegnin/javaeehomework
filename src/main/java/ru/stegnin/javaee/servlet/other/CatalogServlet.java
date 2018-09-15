package ru.stegnin.javaee.servlet.other;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "Catalog", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Collection<Product> products = productRepo.findAll();
        req.setAttribute(Constants.PRODUCTS, products);
        req.getRequestDispatcher(Constants.DISPATCHER_PREFIX + "catalog.jsp").forward(req, resp);
    }
}
