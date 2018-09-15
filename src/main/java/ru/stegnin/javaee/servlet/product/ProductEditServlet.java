package ru.stegnin.javaee.servlet.product;

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

@WebServlet(name = "EditProduct", urlPatterns = "/edit-product")
public class ProductEditServlet extends HttpServlet {
    @Inject
    private ProductRepository productRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String productId = req.getParameter(Constants.ID);
        final Product product = productRepo.findOne(productId);
        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute(Constants.PRODUCT, product);
        req.getRequestDispatcher(Constants.DISPATCHER_PREFIX + "edit-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
