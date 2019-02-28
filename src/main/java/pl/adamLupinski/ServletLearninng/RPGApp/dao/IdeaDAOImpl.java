package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.adamLupinski.ServletLearninng.RPGApp.model.Idea;
import pl.adamLupinski.ServletLearninng.RPGApp.util.ConnectionProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IdeaDAOImpl implements IdeaDAO {

    private static final String CREATE_IDEA = "insert into idea(name, description, url, user_id, date, up_vote, down_vote) " +
            "values(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";

    private NamedParameterJdbcTemplate template;

    public IdeaDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }


    @Override
    public Idea create(Idea idea) {
        Idea resultIdea = new Idea(idea);
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap =new HashMap<String, Object>();
        paramMap.put("name", idea.getName());
        paramMap.put("description", idea.getDescription());
        paramMap.put("url", idea.getUrl());
        paramMap.put("user_id", idea.getUser().getId());
        paramMap.put("date", idea.getTimestamp());
        paramMap.put("up_vote", idea.getUpVote());
        paramMap.put("down_vote", idea.getDownVote());
        SqlParameterSource parameterSource =  new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_IDEA, parameterSource, holder);
        if(update > 0){
            resultIdea.setId(Objects.requireNonNull(holder.getKey()).longValue());
        }
        return resultIdea;
    }

    @Override
    public Idea read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Idea updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<Idea> getAll() {
        return null;
    }
}
