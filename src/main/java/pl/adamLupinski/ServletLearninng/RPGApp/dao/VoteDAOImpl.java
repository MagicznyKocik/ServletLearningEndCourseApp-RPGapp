package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.adamLupinski.ServletLearninng.RPGApp.model.Vote;
import pl.adamLupinski.ServletLearninng.RPGApp.model.VoteType;
import pl.adamLupinski.ServletLearninng.RPGApp.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VoteDAOImpl implements VoteDAO {


    private static final String CREATE_VOTE = "insert into vote(idea_id, user_id, date, type) " +
            "values(:idea_id, :user_id, :date, :type);";
    private static final String READ_VOTE = "select vote_id, idea_id, user_id, date, type from vote where vote_id = :vote_id;";
    private static final String READ_VOTE_BY_USER_ID_IDEA_ID = "select vote_id, idea_id, user_id, date, type from vote " +
            "where user_id = :user_id and idea_id= :idea_id";
    private static final String UPDATE_VOTE = "update vote set date = :date, type = :type where vote_id = :vote_id";

    NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Vote getVoteByUserIdIdeaId(long userId, long ideaId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("idea_id", ideaId);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        Vote vote = null;
        try {
            vote = template.queryForObject(READ_VOTE_BY_USER_ID_IDEA_ID, paramSource, new VoteRowMapper());
        } catch(EmptyResultDataAccessException e) {
            //vote not found
        }
        return vote;
    }

    @Override
    public Vote create(Vote vote) {
        Vote voteCopy = new Vote(vote);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idea_id", voteCopy.getIdeaId());
        paramMap.put("user_id", voteCopy.getUserId());
        paramMap.put("date", voteCopy.getDate());
        paramMap.put("type", voteCopy.getVoteType().toString());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_VOTE, paramSource, holder);
        if(update > 0) {
            voteCopy.setId(Objects.requireNonNull(holder.getKey()).longValue());
        }
        return voteCopy;
    }

    @Override
    public Vote read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("vote_id", primaryKey);
        Vote vote =template.queryForObject(READ_VOTE, parameterSource, new VoteRowMapper());
        return vote;
    }

    @Override
    public boolean update(Vote vote) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", vote.getDate());
        paramMap.put("type", vote.getVoteType().toString());
        paramMap.put("vote_id", vote.getId());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_VOTE, paramSource);
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
    public List<Vote> getAll() {
        return null;
    }


    public class VoteRowMapper implements RowMapper<Vote>{

        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setIdeaId(resultSet.getLong("idea_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
    }
}
