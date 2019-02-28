package pl.adamLupinski.ServletLearninng.RPGApp.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.adamLupinski.ServletLearninng.RPGApp.model.User;
import pl.adamLupinski.ServletLearninng.RPGApp.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private static final String CREATE_USER = "insert into user(username, email, password, is_active) values(:username, :email, :password, :active);";
    private static final String READ_USER = "select user_id, username, email, password, is_active form user where user_id = :id;";
    private static final String READ_USER_BY_USERNAME = "select user_id, username, email, password, is_active from user where username= :username;";

    private NamedParameterJdbcTemplate template;

    public UserDAOImpl(){
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }


    @Override
    public User create(User user) {
        User newUser = new User(user);
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        int update = template.update(CREATE_USER, parameterSource, holder);
        if (update > 0){
            newUser.setId(holder.getKey().longValue());
            setPrivileges(newUser);
        }
        return newUser;
    }

    private void setPrivileges(User user){
        final String userRoleQuery = "insert into user_role(username) values(:username);";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        template.update(userRoleQuery, parameterSource);
    }

    @Override
    public User read(Long primaryKey) {
        User resultUser = null;
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",primaryKey);
        resultUser = template.queryForObject(READ_USER, parameterSource, new UserRowMapper());
        return resultUser;
    }

    @Override
    public boolean update(User updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long primaryKey) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
       User resultUser = null;
       SqlParameterSource parameterSource = new MapSqlParameterSource("username", username);
       resultUser = template.queryForObject(READ_USER_BY_USERNAME, parameterSource, new UserRowMapper());
       return resultUser;
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }

    }

}
