package ru.stegnin.javaee.servlet.other;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Error", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

}
