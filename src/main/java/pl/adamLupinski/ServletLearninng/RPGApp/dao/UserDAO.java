package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import pl.adamLupinski.ServletLearninng.RPGApp.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {
    List<User> getAll();
    User getUserByUsername(String username);
}
