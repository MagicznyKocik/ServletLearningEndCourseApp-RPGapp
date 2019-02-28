package pl.adamLupinski.ServletLearninng.RPGApp.controller;

import pl.adamLupinski.ServletLearninng.RPGApp.model.Idea;
import pl.adamLupinski.ServletLearninng.RPGApp.service.IdeaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "homeController", urlPatterns = "")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID =1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveIdeasInRequest(req);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void saveIdeasInRequest(HttpServletRequest request){
        IdeaService ideaService = new IdeaService();
        List<Idea> allIdeas = ideaService.getAllIdeas(new Comparator<Idea>() {
//            więcej głosów wyżej na liscie
            @Override
            public int compare(Idea o1, Idea o2) {
                int o1Vote = o1.getUpVote() - o1.getDownVote();
                int o2Vote = o2.getUpVote() - o2.getDownVote();
                if (o1Vote < o2Vote){
                    return 1;
                } else if (o1Vote > o2Vote){
                    return -1;
                }
                return 0;
            }
        });
        request.setAttribute("ideas", allIdeas);
    }
}
