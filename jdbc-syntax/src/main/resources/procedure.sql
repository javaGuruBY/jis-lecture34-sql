mysql> delimiter //

mysql> CREATE PROCEDURE usercount (IN filter CHAR(16), OUT users INT)
BEGIN
    SELECT COUNT(*) INTO users FROM JIS4.user
    WHERE bio like filter;
END//
Query OK, 0 rows affected (0.01 sec)

mysql> delimiter ;

mysql> CALL usercount('%Dev%', @totalusers);
mysql> SELECT @totalusers;
