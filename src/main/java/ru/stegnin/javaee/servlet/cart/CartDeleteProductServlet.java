package ru.stegnin.javaee.servlet.cart;

import ru.stegnin.javaee.repository.CartRepository;
import ru.stegnin.javaee.support.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteFromCart", urlPatterns = "/delete-from-cart")
public class CartDeleteProductServlet extends HttpServlet {

//    @Inject
    private CartRepository cartRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String productId = req.getParameter(Constants.ID);
        if (productId == null || productId.isEmpty()) {
            redirect(resp);
            return;
        }
        cartRepo.deleteProduct(cartRepo.getAll().stream().filter(p -> p.getId().equalsIgnoreCase(productId)).findFirst().get());
        redirect(resp);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("cart");
    }
}
