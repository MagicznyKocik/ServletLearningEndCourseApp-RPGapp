package pl.adamLupinski.ServletLearninng.RPGApp.dao;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public IdeaDAO getIdeaDAO() {
        return new IdeaDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public VoteDAO getVoteDAO() {
        return new VoteDAOImpl();
    }
}
