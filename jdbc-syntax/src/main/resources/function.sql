mysql> CREATE FUNCTION hello (s CHAR(20))
mysql> RETURNS CHAR(50) DETERMINISTIC
       RETURN CONCAT('Hello, ',s,'!');

Query OK, 0 rows affected (0.00 sec)
SELECT hello('world');
