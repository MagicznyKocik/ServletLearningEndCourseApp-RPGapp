package pl.adamLupinski.ServletLearninng.RPGApp.service;

import pl.adamLupinski.ServletLearninng.RPGApp.dao.DAOFactory;
import pl.adamLupinski.ServletLearninng.RPGApp.dao.VoteDAO;
import pl.adamLupinski.ServletLearninng.RPGApp.model.Vote;
import pl.adamLupinski.ServletLearninng.RPGApp.model.VoteType;

import java.sql.Timestamp;
import java.util.Date;

public class VoteService {

    public Vote addVote(long ideaId, long userId, VoteType voteType) {
        Vote vote = new Vote();
        vote.setIdeaId(ideaId);
        vote.setUserId(userId);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        vote = voteDao.create(vote);
        return vote;
    }

    public Vote updateVote(long ideaId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote voteToUpdate = voteDao.getVoteByUserIdIdeaId(userId, ideaId);
        if(voteToUpdate != null) {
            voteToUpdate.setVoteType(voteType);
            voteDao.update(voteToUpdate);
        }
        return voteToUpdate;
    }

    public Vote addOrUpdateVote(long ideaId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdIdeaId(userId, ideaId);
        Vote resultVote = null;
        if(vote == null) {
            resultVote = addVote(ideaId, userId, voteType);
        } else {
            resultVote = updateVote(ideaId, userId, voteType);
        }
        return resultVote;
    }

    public Vote getVoteByIdeaUserId(long ideaId, long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdIdeaId(userId, ideaId);
        return vote;
    }

}
