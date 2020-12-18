package by.jrr.springjdbc.mappers;

import by.jrr.springjdbc.bean.Human;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HumanRowMapper implements RowMapper<Human> {

    @Override
    public Human mapRow(ResultSet rs, int rowNum) throws SQLException {
        Human human = new Human();

        human.setId(rs.getLong("id"));
        human.setName(rs.getString("name"));
        human.setLastname(rs.getString("lastname"));

        return human;
    }
}
