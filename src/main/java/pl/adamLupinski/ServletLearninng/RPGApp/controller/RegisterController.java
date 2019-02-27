package pl.adamLupinski.ServletLearninng.RPGApp.controller;

import pl.adamLupinski.ServletLearninng.RPGApp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerController", value = "/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("inputUsername");
        String password = req.getParameter("inputPassword");
        String email = req.getParameter("inputEmail");
        UserService userService = new UserService();
        userService.addUser(username, email, password);
        resp.sendRedirect(req.getContextPath()+"/");

    }
}
