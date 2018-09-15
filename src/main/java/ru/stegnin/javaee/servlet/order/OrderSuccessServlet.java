package ru.stegnin.javaee.servlet.order;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.CartRepository;
import ru.stegnin.javaee.support.Constants;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "Order", urlPatterns = "/order")
public class OrderSuccessServlet extends HttpServlet {

    @Inject
    private CartRepository cartRepo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Product> cart = cartRepo.getAll();
        req.setAttribute(Constants.CART, cart);
        req.getRequestDispatcher(Constants.DISPATCHER_PREFIX + "order.jsp").forward(req, resp);
    }
}
