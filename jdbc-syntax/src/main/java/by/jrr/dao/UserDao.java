package by.jrr.dao;

import by.jrr.bean.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.jrr.config.JisAtLocal.getConnection;
import static by.jrr.dao.Queries.*;

public class UserDao implements Dao<User, Integer> {

    @Override
    public void save(User user) {
        String id = null;
        String sql = null;
        if (user.getId() != null) {
            id = user.getId().toString();
            sql = String.format(UPDATE_NAME_AND_LIKES_BY_ID, user.getName(), user.getLikes(), id);
        } else {
            sql = INSERT_NEW_USER_LAZY; //todo: tbd
        }

        try (Connection connection = getConnection();
             Statement st = connection.createStatement()) {
            int rowsUpdated = st.executeUpdate(sql);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER)) {
            while (rs.next()) {
                User user = mapRsToUser(rs);
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(PREPARED_SELECT_USER_BY_ID)) {
            st.setInt(1, 24);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = mapRsToUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(User user) {
        deleteById(user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(PREPARED_DELETE_USER_BY_ID)) {
            st.setInt(1, 24);
            ResultSet rs = st.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private User mapRsToUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .login(rs.getString("login"))
                .name(rs.getString("name"))
                .bio(rs.getString("bio"))
                .image(rs.getBytes("image"))
                .birth(LocalDate.parse(rs.getDate("birth").toString()))
                .registered(rs.getObject("registered", LocalDateTime.class))
                .updated(rs.getObject("updated", LocalDateTime.class))
                .gender(rs.getString("gender").charAt(0))
                .likes(rs.getInt("likes"))
                .credit(rs.getDouble("credit"))
                .active(rs.getBoolean("active"))
                .build();
    }
}
