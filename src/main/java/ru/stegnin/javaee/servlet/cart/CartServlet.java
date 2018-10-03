package ru.stegnin.javaee.servlet.cart;

import ru.stegnin.javaee.model.Product;
import ru.stegnin.javaee.repository.CartRepository;
import ru.stegnin.javaee.service.CartService;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@WebServlet(name = "Cart", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION_KEY = "shoppingCart";

    public CartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        CartRepository cartBean = (CartRepository) request.getSession().getAttribute(CART_SESSION_KEY);

        if (cartBean == null) {
            try {
                cartBean = EJBFactory.createCartRepositoryBeanFromJNDI("ejb:");

                request.getSession().setAttribute(CART_SESSION_KEY, cartBean);

                System.out.println("shoppingCart created");

            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }

        String productName = request.getParameter("product");
        if (productName != null && productName.length() > 0) {

            Product product = new Product();
            product.setName(productName);
            cartBean.addProduct(product);

            System.out.println("product "+productName+" added");
        }

        String checkout = request.getParameter("checkout");
        if (checkout != null && checkout.equalsIgnoreCase("yes")) {
            cartBean.endPurchase();
            System.out.println("Shopping cart checked out ");
        }

    }

    private static class EJBFactory {
        private static CartRepository createCartRepositoryBeanFromJNDI
                (String namespace) throws NamingException {
            return lookupCartRepositoryBean(namespace);
        }

        private static CartRepository lookupCartRepositoryBean(String namespace) throws NamingException {
            Context ctx = createInitialContext();
            String appName = "";
            String moduleName = "javaeehomework";
            String distinctName = "";
            String beanName = CartService.class.getSimpleName();
            String viewClassName = CartRepository.class.getName();
            return (CartRepository) ctx.lookup(namespace
                    + appName + "/" + moduleName
                    + "/" + distinctName + "/" + beanName + "!" + viewClassName);

//            return (CartRepository) ctx.lookup("java:module/CartService");
        }

        private static Context createInitialContext() throws NamingException {
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put(Context.URL_PKG_PREFIXES,
                    "org.jboss.ejb.client.naming");
            jndiProperties.put(Context.PROVIDER_URL,
                    "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            return new InitialContext(jndiProperties);
        }
    }
}
