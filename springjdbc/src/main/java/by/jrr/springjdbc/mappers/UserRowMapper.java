package by.jrr.springjdbc.mappers;

import by.jrr.springjdbc.bean.Human;
import by.jrr.springjdbc.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setName(rs.getString("name"));
        user.setBio(rs.getString("bio"));
        user.setImage(rs.getBytes("image"));
        if(rs.getDate("birth") != null) {
            user.setBirth(LocalDate.parse(rs.getDate("birth").toString()));
        }
        user.setRegistered(rs.getObject("registered", LocalDateTime.class));
        user.setUpdated(rs.getObject("updated", LocalDateTime.class));
        user.setGender(rs.getString("gender").charAt(0));
        user.setLikes(rs.getInt("likes"));
        user.setCredit(rs.getDouble("credit"));
        user.setActive(rs.getBoolean("active"));

        return user;
    }
}
