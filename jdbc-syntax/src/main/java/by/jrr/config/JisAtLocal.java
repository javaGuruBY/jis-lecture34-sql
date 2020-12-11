package by.jrr.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JisAtLocal {

    public static Connection getConnection() throws SQLException {
        Properties properties = getProperties();
        return DriverManager.getConnection(
                properties.getProperty("URL"),
                properties.getProperty("USER"),
                properties.getProperty("PASS"));
    }

    private static Properties getProperties() {
        try (InputStream in = new FileInputStream("src/main/resources/jis4.properties")){
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
}
