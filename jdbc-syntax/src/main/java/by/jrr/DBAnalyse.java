package by.jrr;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.jrr.config.DBCP.getConnection;

public class DBAnalyse {
    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        DatabaseMetaData dbMeta = connection.getMetaData();

        System.out.println("dbMeta.getDatabaseProductName() = " + dbMeta.getDatabaseProductName());
        System.out.println("dbMeta.getDriverName() = " + dbMeta.getDriverName());
        System.out.println("dbMeta.getMaxColumnsInGroupBy() = " + dbMeta.getMaxColumnsInGroupBy());

        ResultSet tables = dbMeta.getTables("JIS4", null, null, null);
        while (tables.next()) {
//                System.out.println("tables.getString(1) = " + tables.getString(1));
//                System.out.println("tables.getString(2) = " + tables.getString(2));
            System.out.println("\ttables.getString(3) = " + tables.getString(3));
//                System.out.println("tables.getString(4) = " + tables.getString(4));
//                System.out.println("tables.getString(5) = " + tables.getString(5));

            String tableName = tables.getString(3);
            ResultSet columns = dbMeta.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String col = columns.getString("COLUMN_NAME");
                System.out.println("\t\tcol = " + col);
            }
        }
    }
}
