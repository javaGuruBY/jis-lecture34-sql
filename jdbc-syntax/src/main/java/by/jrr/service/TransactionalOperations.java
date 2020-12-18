package by.jrr.service;

import by.jrr.config.JisAtLocal;
import by.jrr.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Arrays;
import java.util.Random;

public class TransactionalOperations {
    //36, 37
    public void riskyOperation(String gender, Integer... ids) throws Exception {
        Connection connection = JisAtLocal.getConnection();
        PreparedStatement updSt = connection.prepareStatement("UPDATE user SET gender=? WHERE id=?");

        updSt.setString(1, gender);
        updSt.setInt(2, ids[0]);
        updSt.executeUpdate();

        if (new Random().nextBoolean()) {
            throw new Exception("Fock you I'm out!");
        }

        updSt.setString(1, gender);
        updSt.setInt(2, ids[1]);
        updSt.executeUpdate();
    }

    public void transactionalOperation(String gender, Integer... ids) throws Exception {
        try (Connection connection = JisAtLocal.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement updSt = connection.prepareStatement("UPDATE user SET gender=? WHERE id=?")) {

                updSt.setString(1, gender);
                updSt.setInt(2, ids[0]);
                updSt.executeUpdate();

                if (new Random().nextBoolean()) {
                    throw new Exception("Fock you I'm out!");
                }

                updSt.setString(1, gender);
                updSt.setInt(2, ids[1]);
                updSt.executeUpdate();

                connection.commit();
            }
            catch (Exception ex) {
                System.out.println("[rollback]");
                connection.rollback();
            }
        }
        catch (Exception ex) {
            System.out.println("[unexpected]");
            ex.printStackTrace();
        }
    }

    public void savepointOperation(String gender, Integer... ids) throws Exception {
        try (Connection connection = JisAtLocal.getConnection()) {
            connection.setAutoCommit(false);
            Savepoint savepoint = null;
            try (PreparedStatement updSt = connection.prepareStatement("UPDATE user SET gender=? WHERE id=?")) {

                updSt.setString(1, gender);
                updSt.setInt(2, ids[0]);
                updSt.executeUpdate();

                savepoint = connection.setSavepoint("First row updated");

                updSt.setString(1, gender);
                updSt.setInt(2, ids[1]);
                updSt.executeUpdate();

                if (new Random().nextBoolean()) {
                    throw new Exception("Fock you I'm out!");
                }

                updSt.setString(1, gender);
                updSt.setInt(2, ids[2]);
                updSt.executeUpdate();

                connection.commit();
            }
            catch (Exception ex) {
                System.out.println("[rollback] to savepoint" + savepoint.getSavepointName());
                connection.rollback(savepoint);
                connection.commit();
            }
        }
        catch (Exception ex) {
            System.out.println("[unexpected]");
            ex.printStackTrace();
        }
    }

    public void printSelected(Integer... ids) throws Exception {
        UserService userService = new UserService(new UserDao());
        Arrays.stream(ids).forEach(id -> System.out.println(userService.findById(id)));
    }

}


