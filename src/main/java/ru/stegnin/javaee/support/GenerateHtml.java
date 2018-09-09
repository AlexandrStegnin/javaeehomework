package ru.stegnin.javaee.support;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenerateHtml {

    public static void generatePage(HttpServletResponse resp, String title) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<title>" + title + "</title>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>" + title + "</h1>");
        resp.getWriter().println("<a href='main'>Главная</a>");
        resp.getWriter().println("<a href='catalog'>Каталог</a>");
        resp.getWriter().println("<a href='product'>Товар</a>");
        resp.getWriter().println("<a href='cart'>Корзина</a>");
        resp.getWriter().println("<a href='order'>Заказ</a>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }

}
