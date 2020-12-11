package by.jrr.dao;

public class Queries {
    public static final String CREATE_TABLE_GIRLS = "" +
            "CREATE TABLE `j_girls` (" +
            "`girl_id` int(2) NOT NULL AUTO_INCREMENT," +
            "`girl` VARCHAR(50) DEFAULT NULL," +
            " PRIMARY KEY (`girl_id`)" +
            ");";
    public static final String DROP_TABLE_GIRLS = "DROP TABLE `j_girls`";
    public static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";
    public static final String SELECT_DEVELOPERS_FROM_USER = "SELECT * FROM user WHERE bio LIKE '%Dev%';";
    public static final String SELECT_MENTORS_FROM_USER = "SELECT * FROM user WHERE bio LIKE '%Men%';";
}
