package by.jrr.dao;

import by.jrr.bean.LightWeightUser;
import by.jrr.bean.User;
import by.jrr.config.JisAtLocal;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.jrr.config.JisAtLocal.getConnection;
import static by.jrr.dao.Queries.*;

public class UserDaoSyntaxExample {

    public List<User> jdbcSyntax() {
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

    public void updateNameAndLikesById(int id, String name, int likes) {
        try (Connection connection = getConnection();
             Statement st = connection.createStatement()) {
            int rowsUpdated = st.executeUpdate(
                    String.format(UPDATE_NAME_AND_LIKES_BY_ID, name, likes, id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllFromUser() throws SQLException, IOException {
        Connection connection = getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER);
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = mapRsToUser(rs);
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
            User user = mapRsToUser(rs);
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
            User user = mapRsToUser(rs);
            users.add(user);
        }
        return users;
    }


    public User findById(int id) {
        User user = null;
        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(String.format(SELECT_USER_BY_ID, id))) {
            while (rs.next()) {
                user = mapRsToUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User preparedFindById(int id) {
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

    public LightWeightUser findByIdLazy(int id) {
        LightWeightUser user = null;
        try (Connection connection = getConnection();
             CallableStatement st = connection.prepareCall(CALL_USER_BY_ID)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = mapRsToUserLazy(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    void movingCursor() {
        try (Connection connection = getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER)) {
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = mapRsToUser(rs);
                users.add(user);
            }
            rs.afterLast();
            while (rs.previous()) {
                User user = mapRsToUser(rs);
                users.add(user);
            }

            System.out.println("elems " + users.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void selectAndUpdate() {
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_ALL_FROM_USER,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER);

            rs.last();
            rs.updateString("name", "Elon Mask");
            rs.updateInt("likes", 9999);
            rs.updateRow();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void insertInTheMiddle() {
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(SELECT_ALL_FROM_USER,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = st.executeQuery(SELECT_ALL_FROM_USER);
            int i = 0;
            while (i < 8) {
                rs.next();
                i++;
            }
            rs.moveToInsertRow();
            rs.updateString("login", "Maks@Hacker.com");
            rs.updateString("name", "Maks Hacker");
            rs.updateInt("likes", 99999);
            rs.insertRow();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void saveAllBatch(List<User> users) {
        try (Connection connection = getConnection();
             PreparedStatement st = connection.prepareStatement(INSERT_NEW_USER_LAZY)) {
            for (User user : users) {
                st.setString(1, user.getLogin());
                st.setString(2, user.getName());
                st.setInt(3, user.getLikes());
                st.addBatch();
            }
            st.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void saveAll(List<User> users) {
        try (Connection connection = getConnection()) {
            for (User user : users) {
                try (PreparedStatement st = connection.prepareStatement(INSERT_NEW_USER_LAZY)) {
                    st.setString(1, user.getLogin());
                    st.setString(2, user.getName());
                    st.setInt(3, user.getLikes());
                    st.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void saveAllWithNewConnections(List<User> users) {
        for (User user : users) {
            try (Connection connection = getConnection();
                 PreparedStatement st = connection.prepareStatement(INSERT_NEW_USER_LAZY)) {
                st.setString(1, user.getLogin());
                st.setString(2, user.getName());
                st.setInt(3, user.getLikes());
                st.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    private LightWeightUser mapRsToUserLazy(ResultSet rs) throws SQLException {
        return LightWeightUser.builder()
                .id(rs.getInt("id"))
                .login(rs.getString("login"))
                .name(rs.getString("name"))
                .likes(rs.getInt("likes"))
                .build();
    }
}
