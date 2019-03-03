package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.adamLupinski.ServletLearninng.RPGApp.model.Idea;
import pl.adamLupinski.ServletLearninng.RPGApp.model.User;
import pl.adamLupinski.ServletLearninng.RPGApp.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IdeaDAOImpl implements IdeaDAO {

    private static final String CREATE_IDEA = "insert into idea(name, description, url, user_id, date, up_vote, down_vote) " +
            "values(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";

    private static final String READ_ALL_IDEAS = "select user.user_id, username, email, is_active, password, idea_id, name, description, url, date, up_vote, down_vote " +
            "from idea left join user on idea.user_id = user.user_id;";

    private static final String READ_IDEA = "select user.user_id, username, email, is_active, password, idea_id, name, description, url, date, up_vote, down_vote " +
            "from idea left join user on idea.user_id=user.user_id WHERE idea_id=:idea_id;";
    private static final String UPDATE_IDEA =
            "UPDATE idea SET name=:name, description=:description, url=:url, user_id=:user_id, date=:date, up_vote=:up_vote, down_vote=:down_vote "
                    + "WHERE idea_id=:idea_id;";


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
        SqlParameterSource parameterSource = new MapSqlParameterSource("idea_id", primaryKey);
        Idea idea = template.queryForObject(READ_IDEA, parameterSource, new IdeaRowMapper());
        return idea;
    }

    @Override
    public boolean update(Idea idea) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("idea_id", idea.getId());
        paramMap.put("name", idea.getName());
        paramMap.put("description", idea.getDescription());
        paramMap.put("url", idea.getUrl());
        paramMap.put("user_id", idea.getUser().getId());
        paramMap.put("date", idea.getTimestamp());
        paramMap.put("up_vote", idea.getUpVote());
        paramMap.put("down_vote", idea.getDownVote());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_IDEA, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<Idea> getAll() {
        List<Idea> ideas = template.query(READ_ALL_IDEAS, new IdeaRowMapper());
        return ideas;
    }

    private class IdeaRowMapper implements RowMapper<Idea> {
        @Override
        public Idea mapRow(ResultSet resultSet, int row) throws SQLException {
            Idea idea = new Idea();
            idea.setId(resultSet.getLong("idea_id"));
            idea.setName(resultSet.getString("name"));
            idea.setDescription(resultSet.getString("description"));
            idea.setUrl(resultSet.getString("url"));
            idea.setUpVote(resultSet.getInt("up_vote"));
            idea.setDownVote(resultSet.getInt("down_vote"));
            idea.setTimestamp(resultSet.getTimestamp("date"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            idea.setUser(user);
            return idea;
        }

    }

}

