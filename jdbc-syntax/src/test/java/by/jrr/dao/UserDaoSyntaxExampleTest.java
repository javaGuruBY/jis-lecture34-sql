package by.jrr.dao;

import by.jrr.bean.User;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoSyntaxExampleTest {

    @Test
    void saveAllBatchVsCommon() {
        UserDaoSyntaxExample dao = new UserDaoSyntaxExample();
        int usersN = 2000;

        LocalDateTime timeStamp1 = LocalDateTime.now();

        dao.saveAllBatch(getUsers(usersN, "r6"));

        LocalDateTime timeStamp2 = LocalDateTime.now();

        dao.saveAll(getUsers(usersN, "r7"));

        LocalDateTime timeStamp3 = LocalDateTime.now();

        dao.saveAllWithNewConnections(getUsers(usersN, "r8"));

        LocalDateTime timeStamp4 = LocalDateTime.now();

        long batchDuration = Duration.between(timeStamp1, timeStamp2).toSeconds();
        long commonDuration = Duration.between(timeStamp2, timeStamp3).toSeconds();
        long connectionDuration = Duration.between(timeStamp3, timeStamp4).toSeconds();

        double rate = batchDuration / (double) commonDuration * 100;

        System.out.println("Batch " + batchDuration);
        System.out.println("Common " + commonDuration);
        System.out.println("connectionDuration " + connectionDuration);
        System.out.println("pre\\comm " + rate);

    }

    private List<User> getUsers(int i, String prefix) {
        List<User> users = new ArrayList<>();
        while (i >0) {
            users.add(User.builder()
            .login("login"+i+prefix)
            .name("name"+i+prefix)
            .likes(i)
            .build());
            --i;
        }
        return users;
    }
}
