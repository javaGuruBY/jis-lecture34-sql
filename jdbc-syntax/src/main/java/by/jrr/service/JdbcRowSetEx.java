package by.jrr.service;

import by.jrr.bean.User;
import com.sun.rowset.JdbcRowSetImpl;
import lombok.SneakyThrows;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;
import java.time.LocalDate;


public class JdbcRowSetEx {

    public static final String URL = "jdbc:mysql://localhost:3306/JIS4";
    public static final String USER = "jis4";
    public static final String PASS = "1234";

    public void jdbcRsSyntax() {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            rowSet.setUrl(URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);

            rowSet.setCommand("SELECT * FROM user WHERE id in (?,?,?)");
            rowSet.setInt(1, 36);
            rowSet.setInt(2, 37);
            rowSet.setInt(3, 38);

            rowSet.execute();

            while (rowSet.next()) {
                System.out.println(mapRsToUser(rowSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void rowSetWithListener() {
        try (JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()) {
            this.addListener(rowSet);

            rowSet.setUrl(URL);
            rowSet.setUsername(USER);
            rowSet.setPassword(PASS);

            rowSet.setCommand("SELECT * FROM user WHERE id in (?,?,?)");
            rowSet.setInt(1, 28);
            rowSet.setInt(2, 39);
            rowSet.setInt(3, 30);

            rowSet.execute();

            while (rowSet.next()) {
                System.out.println(mapRsToUser(rowSet));
            }

            rowSet.moveToInsertRow();
            rowSet.updateInt("likes", 888);
            rowSet.updateString("birth", "2020-12-12");
            rowSet.updateString("login", "maksim4444ik");
            rowSet.insertRow();

            rowSet.afterLast();
            while (rowSet.previous()) {
                System.out.println(mapRsToUser(rowSet));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //compiles only with 1.8
    private void addListener(RowSet rs) {
        rs.addRowSetListener(new RowSetListener() {
            @SneakyThrows
            @Override
            public void rowSetChanged(RowSetEvent event) {
                System.out.println("\t\trowSetChanged");
                JdbcRowSetImpl rowSet = (JdbcRowSetImpl) event.getSource();
                System.out.println("\t\t[QUERY] = " + rowSet.getStatement());
            }

            @SneakyThrows
            @Override
            public void rowChanged(RowSetEvent event) {
                System.out.println("\t\trowChanged");
                JdbcRowSetImpl rowSet = (JdbcRowSetImpl) event.getSource();
                System.out.println("\t\t[QUERY] = " + rowSet.getStatement());
            }

            @SneakyThrows
            @Override
            public void cursorMoved(RowSetEvent event) {
                System.out.println("\t\tcursorMoved");
                JdbcRowSetImpl rowSet = (JdbcRowSetImpl) event.getSource();
                System.out.println("\t\t[QUERY] = " + rowSet.getStatement());
            }
        });
    }

    private User mapRsToUser(RowSet rs) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .login(rs.getString("login"))
                .name(rs.getString("name"))
                .bio(rs.getString("bio"))
                .image(rs.getBytes("image"))
                .birth(LocalDate.parse(rs.getDate("birth").toString()))
//                .registered(rs.getObject("registered", LocalDateTime.class))
//                .updated(rs.getObject("updated", LocalDateTime.class))
                .gender(rs.getString("gender").charAt(0))
                .likes(rs.getInt("likes"))
                .credit(rs.getDouble("credit"))
                .active(rs.getBoolean("active"))
                .build();
    }
}
