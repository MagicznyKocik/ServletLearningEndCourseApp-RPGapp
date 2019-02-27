package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import pl.adamLupinski.ServletLearninng.RPGApp.model.Idea;

import java.util.List;

public interface IdeaDAO extends GenericDAO<Idea, Long>{
    List<Idea> getAll();
}
