CREATE DATABASE JIS4
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

DROP DATABASE JIS4;

CREATE USER jis4@localhost IDENTIFIED BY '1234';
GRANT ALL ON JIS4.* TO jis4@localhost;
GRANT ALL PRIVILEGES ON JIS4.* TO jis4@localhost;
DROP USER jis4@localhost;

SELECT user FROM mysql.user;
SELECT * FROM mysql.user WHERE user='jis4';

# max@max:~$ mysql -h 127.0.0.1 -P 3306 -u jis4 -p JIS4
# max@max:~$ mysql -u jis4 -p

USE JIS4;
SHOW CREATE DATABASE JIS4;

# CREATE TABLE name;
SHOW TABLES;
DESCRIBE user;
SHOW CREATE TABLE user;

CREATE TABLE user (
    id INT AUTO_INCREMENT,
    login VARCHAR (32) NOT NULL UNIQUE,
    name VARCHAR (255),
    bio TEXT,
    image BLOB,
    birth DATE,
    registered DATETIME,
    updated TIMESTAMP,
    gender CHAR,
    likes INT,
    credit DEC (19,2),
    active BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id)
);

INSERT INTO user (`login`) VALUES ('maksim');
INSERT INTO `user` (login) VALUES ('vasim');

INSERT INTO user (`login`) VALUES ("It's an apple" );
INSERT INTO `user` (login) VALUES ('maksim');

INSERT INTO `user` (login) VALUES ('He sad "I am ok" he sad');
INSERT INTO `userSELECT id, name, bio, likes from user where likes in (SELECT MIN(likes) from user GROUP BY bio);` (login) VALUES ('He sad "I\'m ok" he sad');

SELECT * FROM user;

ALTER TABLE user ADD email VARCHAR(32);
ALTER TABLE user MODIFY email TEXT;
ALTER TABLE user CHANGE COLUMN email e_mail VARCHAR(32);
ALTER TABLE user DROP e_mail;
ALTER TABLE user RENAME abuser;

CREATE TABLE user (
    login VARCHAR (32) NOT NULL UNIQUE,
    name VARCHAR (255)
);

INSERT INTO user (login, name) VALUES ('maxBax', 'maksim');
INSERT INTO user (name, login) VALUES ('vladimir', 'vav4ig');
# INSERT INTO user VALUES ('eug');  //ERROR
INSERT INTO user VALUES ('eug', 'eugenij');

# DATE AND TIME
ALTER TABLE user ADD birth DATE;
ALTER TABLE user ADD registered DATETIME;

INSERT INTO user (login, birth) VALUES ('v1', '2020-05-29');
INSERT INTO user (login, registered) VALUES ('v2', '2020-05-29');
INSERT INTO user (login, registered) VALUES ('v3', '2020-05-20T23:27:38');
INSERT INTO user (login, registered) VALUES ('v4', '2020-05-20 23:27:38');

INSERT INTO user (login, birth) VALUES ('f', '06.06.20');
INSERT INTO user (login, birth) VALUES ('v', '06.06.2123');
# INSERT INTO user (login, birth) VALUES ('v1', 2020.05.29);  ERROR
INSERT INTO user (login, birth) VALUES ('v0', '2020.05.29');

INSERT INTO user (login, registered) VALUES ('v5', NOW());
INSERT INTO user (login, registered) VALUES ('v6', NOW() - INTERVAL 1 HOUR);

# SELECT RAND()*(b-a)+a;

DROP TABLE user;
DROP TABLE abuser;
# create user as in top;
# BATCH UPDATE
INSERT INTO user VALUES
(NULL, 'vadin', 'Vladimir Yakovlevitch', 'Директор завода', NULL, '2020-05-29', '2020-05-20 23:32:32', NULL, 'M', 150, 150.00, TRUE),
(NULL, 'mikas', 'Максим Шелкович', 'Директор ИТ Гуру', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'vikas', 'Евгений Абрамович', 'Промышленник со стажем', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'bikas', 'Владимир Ганц', 'Предприниматель', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'userr', 'Нетаньяху', 'Java Developer', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'myname', 'Соломон', 'Java Developer', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'login', 'Егор Барило', 'Предприниматель', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'passw', 'Авсеенков Владимир', 'Директор Курсов', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'noname', 'Никита Кожемяко', 'Промышленник без стажа', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'anon', 'Флипп Янцевич', 'Student', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'joker', 'Катерина Яцкевич', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'focker', 'Доктор Кто', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'mause', 'Терион Ланистер', 'Java Developer', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'mice', 'Бердд Питт', 'Java Developer', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'nice', 'Терминатор2', 'Student', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'goblin', 'Джон Смитт', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'bobin', 'Егор Добкин', 'Student', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'bob', 'Дмитрий Федовор', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'steave', 'Кац Шиллерман', 'Java Developer', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'jobs', 'Борис Нефедов', 'Student', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'rocket', 'Тамара Винникова', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'F', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND()),
(NULL, 'virus', 'Василий Сулейманович', 'Mentor', NULL, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NOW()-INTERVAL RAND()*(1000-5)+5 DAY, NULL, 'M', RAND()*(1000-5)+5, RAND()*(1000-5)+5, RAND())
;

# 23 - 44
SELECT id, login, active FROM user;
SELECT id, login, active FROM user LIMIT 2, 3;
SELECT id, login, active FROM user WHERE id=40;
SELECT id, login, active FROM user WHERE id<40;
SELECT id, login, active, birth FROM user WHERE birth BETWEEN '2019-01-01' AND '2020-05-01';
SELECT id, login, active, birth FROM user WHERE birth > '2020-05-01';
SELECT id, login, active FROM user WHERE id IN (40, 35, 25, 41, 37);
SELECT id, login, active FROM user WHERE id NOT IN (40, 35, 25, 41, 37);
SELECT id, login, active, bio FROM user WHERE bio LIKE '%dev%';

SELECT id, login, active, birth, bio FROM user WHERE birth > '2020-05-01'
UNION
SELECT id, login, active, birth, bio FROM user WHERE bio LIKE '%dev%';

SELECT id, login, active, birth, bio FROM user WHERE birth > '2020-05-01'
UNION ALL
SELECT id, login, active, birth, bio FROM user WHERE bio LIKE '%dev%';

SELECT id, login, active, birth, bio FROM user WHERE image IS NULL;
SELECT id, login, active, birth, bio FROM user WHERE image IS NOT NULL;
# SELECT id, login, active, birth, bio FROM user WHERE login = 0;  DON'T USE IT

SELECT id, login, active, gender, likes FROM user WHERE id=40;
UPDATE user SET
    gender = 'F',
    likes = 0
WHERE id=40;

# for our username and database - username in lowcase, database name in uppercase
# mysqldump JIS4 > jis4_2020-12-08T23.00.00.sql //root admin
max@max:~$ mysqldump -h 127.0.0.1 -u jis4 JIS4 > jis4.sql -p
max@max:~$ mysqldump -h 127.0.0.1 -u jis4 JIS12 JIS4 > severalJisDatabases.sql -p
max@max:~$ mysqldump -h 127.0.0.1 -u jis4 --all-databases > alldatabases.sql -p

max@max:~$ mysql -h 127.0.0.1 -u jis4 JIS4 < jis4.sql -p

max@max:~$ mysqldump -u username -ppassword database_name table_name > single_table_dump.sql
max@max:~$ mysqldump -u username -ppassword database_name table_name --where="date_created='2013-06-25'" > few_rows_dump.sql

# grant privileges to avoid
# Error: 'Access denied; you need (at least one of) the PROCESS privilege(s) for this operation'
# when trying to dump tablespaces
GRANT PROCESS ON *.* TO jis4@localhost;

SELECT id, login, active, gender, likes FROM user WHERE id=40;
START TRANSACTION;
UPDATE user SET
    gender = 'M',
    likes = 500
WHERE id<40;
ROLLBACK;

SELECT id, login, active, gender, likes FROM user WHERE id=40;
START TRANSACTION;
UPDATE user SET
    gender = 'M',
    likes = 500
WHERE id=40;
COMMIT;


SELECT RIGHT(bio, 2) FROM user;
SELECT login, SUBSTRING_INDEX(credit, '.', 1) FROM user;
SELECT UPPER(login), LOWER(name), REVERSE(bio) FROM user;
SELECT login, LENGTH(login) FROM user;
SELECT LENGTH(login), login FROM user WHERE LENGTH(login) >=5;

SELECT LTRIM(' dogfood ');
SELECT RTRIM(' catfood ');


ALTER TABLE user ADD login_policy VARCHAR (32);
UPDATE user SET login_policy =
                    CASE
                        WHEN LENGTH(login)<4
                            THEN 'too short'
                        WHEN LENGTH(login)>=4 AND LENGTH(login)<=5
                            THEN 'OK'
                        ELSE 'to long'
                    END;
SELECT LENGTH(login), login, login_policy FROM user;

select id, login, name, bio FROM user ORDER BY login ASC;
select id, login, name, bio FROM user ORDER BY bio ASC, name DESC;
select id, login, name, bio FROM user ORDER BY LENGTH(bio) ASC, name DESC;


SELECT DISTINCT bio FROM user;
SELECT DISTINCT LENGTH(login) FROM user;

SELECT SUM(credit) FROM user WHERE gender = 'M';
SELECT AVG(credit) FROM user WHERE gender = 'M';
SELECT MAX(likes) AS maxLikes, name FROM user GROUP BY name;


SELECT likes, name FROM user WHERE likes = (SELECT MAX(likes) FROM user);
SELECT likes, name FROM user ORDER BY likes DESC LIMIT 1;


SELECT bio, COUNT(bio) as total FROM user GROUP BY bio;
SELECT bio, COUNT(bio) as total FROM user WHERE bio LIKE '%Dev%' GROUP BY bio;
SELECT bio, COUNT(bio) as total FROM user GROUP BY bio ORDER BY total DESC;
SELECT bio, COUNT(bio) as total FROM user GROUP BY bio ORDER BY total DESC LIMIT 1,1;
SELECT bio, COUNT(bio) as total FROM user GROUP BY bio HAVING total > 3;
SELECT SUM(likes) AS total_likes, AVG(likes), COUNT(bio), bio FROM user GROUP BY bio;
SELECT MIN(likes), bio from user GROUP BY bio;
SELECT MIN(likes) from user;

SELECT id, name, likes from user where likes in (SELECT MIN(likes) from user);

SELECT MIN(likes), bio from user GROUP BY bio;
SELECT id, name, bio, likes from user where likes in (SELECT MIN(likes) from user GROUP BY bio);

# RELATIONS
CREATE TABLE `boys` (
    `boy_id` int(2) NOT NULL AUTO_INCREMENT,
    `boy` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`boy_id`)
);

CREATE TABLE `toys` (
    `toy_id` int(2) NOT NULL AUTO_INCREMENT,
    `toy` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`toy_id`)
);

INSERT INTO boys (boy) VALUES ('David'), ('Maks'), ('Vlad'), ('Anton');
INSERT INTO toys (toy) VALUES ('hula hoop'), ('solder'), ('boll'), ('bycicle'), ('stick');

SELECT t.toy, b.boy
FROM
    toys AS t
CROSS JOIN
    boys AS b;

SELECT b1.boy, b2.boy
FROM
    boys AS b1
CROSS JOIN
    boys AS b2;

CREATE TABLE `boysWithToys` (
    `boy_id` int(2) NOT NULL AUTO_INCREMENT,
    `boy` varchar(50) DEFAULT NULL,
    `toy_id` int(2) DEFAULT NULL,
    PRIMARY KEY (`boy_id`)
);
INSERT INTO boysWithToys (boy, toy_id) VALUES ('David', 2), ('Maks', 2), ('Vlad', 1), ('Anton', NULL);


SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
    NATURAL JOIN
     toys;

ALTER TABLE toys CHANGE toy_id id int(2) NOT NULL AUTO_INCREMENT;

SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
INNER JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
CROSS JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
LEFT OUTER JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM toys
LEFT OUTER JOIN
     boysWithToys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
RIGHT OUTER JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

-- Записи без подчиненных
SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
RIGHT OUTER JOIN
     toys
    ON boysWithToys.toy_id = toys.id
where boysWithToys.boy is null;

# FULL
SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
LEFT OUTER JOIN
     toys
    ON boysWithToys.toy_id = toys.id
UNION
SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
RIGHT OUTER JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

SELECT boysWithToys.boy, toys.toy
FROM toys
INNER JOIN
     boysWithToys
    ON boysWithToys.toy_id <> toys.id
ORDER BY boysWithToys.boy;

CREATE TABLE boysAndToysCache AS
SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

CREATE VIEW boysAndToysView AS
SELECT boysWithToys.boy, toys.toy
FROM boysWithToys
JOIN
     toys
    ON boysWithToys.toy_id = toys.id;

UPDATE boysWithToys SET toy_id = 4 WHERE boy = 'Anton';

show create table boysAndToysCache;
show create table boysAndToysView;

CREATE TABLE boyToToy AS
SELECT boy_id, toy_id FROM boysWithToys WHERE toy_id IS NOT NULL;
INSERT INTO boyToToy VALUES (1,3), (2,3), (2,1), (1,1);

SELECT boys.boy, toys.toy
FROM boys
JOIN boyToToy ON (boys.boy_id = boyToToy.boy_id)
JOIN toys ON (boyToToy.toy_id = toys.id);


CREATE TABLE `boysWithKeyToys` (
    `boy_id` int(2) NOT NULL AUTO_INCREMENT,
    `boy` varchar(50) DEFAULT NULL,
    `toy_id` int(2),
    PRIMARY KEY (`boy_id`),
CONSTRAINT constr FOREIGN KEY (toy_id) REFERENCES toys (id)
);

INSERT INTO boysWithKeyToys (boy, toy_id)
VALUES ('David', 2), ('Maks', 2), ('Vlad', 1), ('Anton', NULL);
INSERT INTO boysWithKeyToys (boy, toy_id)
VALUES ('Imposible', 99);

# tbd
CREATE TABLE `boysWithKeyToysSpecial` (
    `boy_id` int(2) NOT NULL AUTO_INCREMENT,
    `boy` varchar(50) DEFAULT NULL,
    `toy_id` int(2),
    PRIMARY KEY (`boy_id`),
CONSTRAINT constrSpecial FOREIGN KEY (toy_id) REFERENCES toys (id)
    ON UPDATE SET NULL
);
INSERT INTO boysWithKeyToysSpecial (boy, toy_id)
VALUES ('Imposible', 99);

SELECT boysWithKeyToys.boy, toys.toy
FROM boysWithKeyToys
         NATURAL JOIN
     toys;
