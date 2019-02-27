package pl.adamLupinski.ServletLearninng.RPGApp.service;

import pl.adamLupinski.ServletLearninng.RPGApp.dao.DAOFactory;
import pl.adamLupinski.ServletLearninng.RPGApp.dao.UserDAO;
import pl.adamLupinski.ServletLearninng.RPGApp.model.User;

public class UserService {

    public void addUser(String username, String email, String password){
        User user = new User(username, email, password);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }
}
