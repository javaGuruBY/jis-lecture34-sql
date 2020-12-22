package by.jrr.springjdbc.dao;

import by.jrr.springjdbc.bean.Human;
import by.jrr.springjdbc.bean.User;
import by.jrr.springjdbc.mappers.HumanRowMapper;
import by.jrr.springjdbc.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> findUserInId(Integer... id) {
        String inIds = Arrays.stream(id)
                .map(a -> String.valueOf(a))
                .collect(Collectors.joining(","));

        String query = String.format("SELECT * FROM user WHERE id IN (%s)", inIds);
        List<User> result = jdbcTemplate.query(query, new UserRowMapper());
        if (!result.isEmpty()) {
            return result;
        }
        return new ArrayList<>();
    }

    public void updateUserGender(User user) {
        String query = String.format("update user set gender = '%s' where id = %s", user.getGender(), user.getId());
        jdbcTemplate.update(query);
    }

    public void updateUserLogin(User user) {
        String query = String.format("update user set login = '%s' where id = %s", user.getLogin(), user.getId());
        jdbcTemplate.update(query);
    }
}
