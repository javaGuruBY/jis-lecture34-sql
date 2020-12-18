package by.jrr.springjdbc.dao;

import by.jrr.springjdbc.bean.Human;
import by.jrr.springjdbc.mappers.HumanRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HumanDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long countRows() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM human", Long.class);
    }

    // CRUD

    public Human findHumanById(Long id) {
        String query = "SELECT * FROM human WHERE id="+id;
        List<Human> result = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Human.class));
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Human findHumanByIdWithMyMapper(Long id) {
        String query = "SELECT * FROM human WHERE id="+id;
        List<Human> result = jdbcTemplate.query(query, new HumanRowMapper());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Human findHumanByIdWithNamedParameters(Long id) {
        String query = "SELECT * FROM human WHERE id=:idAsParameter";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idAsParameter", id);

        List<Human> result = namedParameterJdbcTemplate.query(query, namedParameters, new HumanRowMapper());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public Human findHumanByCriteria(Human human) {
        String query = "SELECT * FROM human WHERE id=:id and name=:name";

        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(human);

        List<Human> result = namedParameterJdbcTemplate.query(query, namedParameters, new HumanRowMapper());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public void updateHuman(Human human) {
        String query = "update human set name = :name where id = :id";

        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(human);

        int result = namedParameterJdbcTemplate.update(query, namedParameters);
    }


    public void saveHuman(Human human) {
        String query = "INSERT INTO human VALUES (:id, :name, :lastname)";

        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(human);

        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    public void saveOrUpdatedHuman(Human human) {
        String query;
        if (human.getId()== null) {
            query = "INSERT INTO human VALUES (:id, :name, :lastname)";
        } else {
            query = "update human set name = :name, lastname= :lastname where id = :id";
        }

        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(human);

        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    public void deleteHuman(Human human) {
        String query = "DELETE FROM human WHERE id = :id AND name = :name AND lastname= :lastname";

        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(human);

        namedParameterJdbcTemplate.update(query, namedParameters);
    }


    public void deleteHuman(Long id) {
        String query = "DELETE FROM human WHERE id = :id";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    public void save(Human... humans) {
        String query = "insert into human values (:id, :name, :lastname)";

        List<SqlParameterSource> namedParameters = new ArrayList<>();

        for(Human human : humans) {
            namedParameters.add(new BeanPropertySqlParameterSource(human));
        }

        BeanPropertySqlParameterSource[] batchParametes = namedParameters.toArray(new BeanPropertySqlParameterSource[namedParameters.size()]);

        namedParameterJdbcTemplate.batchUpdate(query, batchParametes);

    }
}
