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
    public static final String UPDATE_NAME_AND_LIKES_BY_ID = "UPDATE `user` SET `name` = '%s', likes = '%s' WHERE id='%s';";
    public static final String INSERT_NEW_USER_LAZY = "insert into user (login, name, likes) values (?, ?, ?);";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE `id`='%s';";
    public static final String CALL_USER_BY_ID = "CALL getLightWeightUser();";
    public static final String PREPARED_DELETE_USER_BY_ID = "DELETE * FROM `user` WHERE `id`=?;";

    public static final String PREPARED_SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE `id`=?;";
}

