package by.jrr.dao;

import by.jrr.bean.LightWeightUser;
import by.jrr.bean.User;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {

    @Test
    void updateNameAndLikesById() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();
        userDao.updateNameAndLikesById(24, "Vasiliy", 222);
        User user = userDao.findById(24);
        assertEquals("Vasiliy", user.getName());
        assertEquals(222, user.getLikes());
    }

    @Test
    void comparePreparedVsCommon() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();

        LocalDateTime timeStamp1 = LocalDateTime.now();
        for (int i = 0; i < 1000; i++) {
            userDao.findById(33);
        }
        LocalDateTime timeStamp2 = LocalDateTime.now();

        for (int i = 0; i < 1000; i++) {
            userDao.preparedFindById(33);
        }
        LocalDateTime timeStamp3 = LocalDateTime.now();

        long commonDuration = Duration.between(timeStamp1, timeStamp2).toSeconds();
        long preparedDuration = Duration.between(timeStamp2, timeStamp3).toSeconds();
        double rate = preparedDuration / (double) commonDuration * 100;
        System.out.println("Common " + commonDuration);
        System.out.println("Prepare " + preparedDuration);
        System.out.println("pre\\comm " + rate);
    }

    @Test
    void findUserWithCall() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();

        LightWeightUser user = userDao.findByIdLazy(33);
        System.out.println("user = " + user);
    }

    @Test
    void movingCursor() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();
        userDao.movingCursor(); //set mysql ver to 5.*.*
    }

    @Test
    void selectAndUpdate() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();
        userDao.selectAndUpdate();
    }

    @Test
    void insertInTheMiddle() {
        UserDaoSyntaxExample userDao = new UserDaoSyntaxExample();
        userDao.insertInTheMiddle();
    }
}
