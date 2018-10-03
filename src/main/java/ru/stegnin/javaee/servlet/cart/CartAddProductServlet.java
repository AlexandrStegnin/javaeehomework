package ru.stegnin.javaee.servlet.cart;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.CartRepository;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddToCart", urlPatterns = "/add-to-cart")
public class CartAddProductServlet extends HttpServlet {
//    @Inject
    private CartRepository cartRepo;

    @Inject
    private ProductRepository productRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String productId = req.getParameter(Constants.ID);
        final Product product = productRepo.findOne(productId);
        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        cartRepo.addProduct(product);
        redirect(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("catalog");
    }
}
