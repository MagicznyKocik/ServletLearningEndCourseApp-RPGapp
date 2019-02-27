package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import pl.adamLupinski.ServletLearninng.RPGApp.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
    Vote getVoteByUserIdIdeaId(long userId, long ideaid);
}
