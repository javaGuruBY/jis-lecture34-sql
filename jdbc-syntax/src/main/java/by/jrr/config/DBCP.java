package by.jrr.config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCP {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream("src/main/resources/jis4.properties")) {
            properties.load(in);

            ds.setUrl(properties.getProperty("URL"));
            ds.setUsername(properties.getProperty("USER"));
            ds.setPassword(properties.getProperty("PASS"));
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
        } catch (Exception ex) {
            System.out.println("not connected");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCP() {
    }
}
