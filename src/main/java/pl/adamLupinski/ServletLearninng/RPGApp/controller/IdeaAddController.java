package pl.adamLupinski.ServletLearninng.RPGApp.controller;

import pl.adamLupinski.ServletLearninng.RPGApp.model.User;
import pl.adamLupinski.ServletLearninng.RPGApp.service.IdeaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ideaAddController", value = ("/add"))
public class IdeaAddController extends HttpServlet {
    private static final long serialVersionUID =1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null){
            req.getRequestDispatcher("new.jsp").forward(req,resp);
        } else {
            resp.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("inputName");
        String description = req.getParameter("inputDescription");
        String url = req.getParameter("inputUrl");
        User authenticatedUser = (User) req.getSession().getAttribute("user");
        if(req.getUserPrincipal() != null) {
            IdeaService ideaService = new IdeaService();
            ideaService.addIdea(name, description, url, authenticatedUser);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendError(403);
        }
    }
}
