package ru.stegnin.javaee.servlet.product;

import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", urlPatterns = "/delete-product")
public class ProductDeleteServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String productId = req.getParameter(Constants.ID);
        if (productId == null || productId.isEmpty()) {
            redirect(resp);
            return;
        }
        productRepo.delete(productId);
        redirect(resp);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("catalog");
    }
}
