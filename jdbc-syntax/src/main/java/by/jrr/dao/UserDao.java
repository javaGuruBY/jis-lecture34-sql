package by.jrr.dao;

import by.jrr.bean.User;
import by.jrr.config.JisAtLocal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.jrr.config.JisAtLocal.getConnection;
import static by.jrr.dao.Queries.*;

public class UserDao {

    public List<User> jdbcSyntax() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER)) {
            while (rs.next()) {
                User user = User.builder()
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
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<User> getAllFromUser() throws SQLException, IOException {
        Connection connection = getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = User.builder()
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
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return users;
    }

    public List<User> getDevelopers() throws SQLException, IOException {
        Connection connection = getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_DEVELOPERS_FROM_USER);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            System.out.println("\nrs.getRow() = " + rs.getRow());
            User user = User.builder()
                    .id(rs.getInt(1))
                    .login(rs.getString(2))
                    .name(rs.getString(3))
                    .bio(rs.getString(4))
                    .birth(LocalDate.parse(rs.getDate(6).toString()))
                    .registered(rs.getObject(7, LocalDateTime.class))
                    .updated(rs.getTimestamp("updated").toLocalDateTime())
                    .gender(rs.getString(9).charAt(0))
                    .likes(rs.getInt(10))
                    .credit(rs.getDouble(11))
                    .active(rs.getBoolean(12))
                    .build();
            users.add(user);
        }
        return users;
    }

    public List<User> getMentors() throws SQLException, IOException {
        Connection connection = getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_MENTORS_FROM_USER);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
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
            users.add(user);
        }
        return users;
    }


}
