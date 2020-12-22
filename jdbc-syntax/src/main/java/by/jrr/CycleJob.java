package by.jrr;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import static by.jrr.config.JisAtLocal.getConnection;

public class CycleJob {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i< 1000; i++) {
            cycleJob();
            Thread.sleep(1000);
        }
        Thread.sleep(100000);
    }
    public static void cycleJob() {
        try {
            Connection connection = getConnection();
            DatabaseMetaData dbMeta = connection.getMetaData();
            System.out.print("dbMeta.getDatabaseProductName() = " + dbMeta.getDatabaseProductName());
            System.out.print("dbMeta.getDriverName() = " + dbMeta.getDriverName());
            System.out.println("dbMeta.getMaxColumnsInGroupBy() = " + dbMeta.getMaxColumnsInGroupBy());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
