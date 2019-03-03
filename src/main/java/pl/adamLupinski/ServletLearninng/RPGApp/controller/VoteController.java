package pl.adamLupinski.ServletLearninng.RPGApp.controller;

import pl.adamLupinski.ServletLearninng.RPGApp.model.Idea;
import pl.adamLupinski.ServletLearninng.RPGApp.model.User;
import pl.adamLupinski.ServletLearninng.RPGApp.model.Vote;
import pl.adamLupinski.ServletLearninng.RPGApp.model.VoteType;
import pl.adamLupinski.ServletLearninng.RPGApp.service.IdeaService;
import pl.adamLupinski.ServletLearninng.RPGApp.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "voteController", urlPatterns = {"/vote"})
public class VoteController extends HttpServlet {

    private static final long serialVersionUID =1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("user");
        if(loggedUser !=null){
            VoteType voteType = VoteType.valueOf(req.getParameter("vote"));
            long userId = loggedUser.getId();
            long ideaId = Long.valueOf(req.getParameter("idea_id")) ;
            updateVote(userId, ideaId, voteType);
        }
        resp.sendRedirect(req.getContextPath()+"/");
    }

    private void updateVote(long userId, long ideaId, VoteType voteType){
        VoteService voteService = new VoteService();
        Vote existingVote =voteService.getVoteByIdeaUserId(ideaId,userId);
        Vote updatedVote =voteService.addOrUpdateVote(ideaId, userId, voteType);
        if(existingVote != updatedVote){
            updateIdea(ideaId, existingVote, updatedVote);
        }
    }

    private void updateIdea(long ideaId, Vote oldVote, Vote newVote) {
        IdeaService ideaService= new IdeaService();
        Idea ideaById= ideaService.getIdeaById(ideaId);
        Idea updatedIdea = null;
        if(oldVote == null && newVote != null) {
            updatedIdea = addIdeaVote(ideaById, newVote.getVoteType());
        } else if(oldVote != null && newVote != null) {
            updatedIdea = removeIdeaVote(ideaById, oldVote.getVoteType());
            updatedIdea = addIdeaVote(updatedIdea, newVote.getVoteType());
        }
        ideaService.updateIdea(updatedIdea);
    }

    private Idea addIdeaVote(Idea idea, VoteType voteType) {
        Idea ideaCopy = new Idea(idea);
        if (voteType == VoteType.VOTE_UP){
            ideaCopy.setUpVote(ideaCopy.getUpVote() + 1);
        } else if (voteType == VoteType.VOTE_DOWN){
            ideaCopy.setDownVote(ideaCopy.getDownVote() + 1);
        }
        return ideaCopy;
    }

    private Idea removeIdeaVote(Idea idea, VoteType voteType){
        Idea ideaCopy = new Idea(idea);
        if (voteType == VoteType.VOTE_UP){
            ideaCopy.setUpVote(ideaCopy.getUpVote() - 1);
        } else if (voteType == VoteType.VOTE_DOWN){
            ideaCopy.setDownVote(ideaCopy.getDownVote() - 1);
        }
        return ideaCopy;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
