package ru.stegnin.javaee.servlet.product;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.ProductRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveProduct", urlPatterns = "/save-product")
public class ProductSaveServlet extends HttpServlet {
    @Inject
    private ProductRepository productRepo;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String id = req.getParameter(Constants.ID);
        if (id == null || id.isEmpty()) {
            redirect(resp);
            return;
        }
        Product product = productRepo.findOne(id);
        if (product == null) {
            redirect(resp);
            return;
        }
        product.setName(req.getParameter(Constants.NAME));
        product.setDescription(req.getParameter(Constants.DESCRIPTION));
        product.setPrice(Double.parseDouble(req.getParameter(Constants.PRICE)));
        productRepo.update(product);
        redirect(resp);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("catalog");
    }
}
