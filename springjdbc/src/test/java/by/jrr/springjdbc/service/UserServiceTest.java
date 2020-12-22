package by.jrr.springjdbc.service;

import by.jrr.springjdbc.App;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {App.class})
class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    void findAndPrintUsers() {
        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateGenderForUsers() throws Exception {
        Character gender = 'M';
        if(userService.findUser(33).getGender().equals('M')) {
            gender = 'F';
        }

        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateGenderForUsers(gender,33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateGenderForUsersTransactional() throws Exception {
        Character gender = 'M';
        if(userService.findUser(33).getGender().equals('M')) {
            gender = 'F';
        }

        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateGenderForUsersTransactional(gender,33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateLoginForUsers() {
        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateLoginForUsers("new Login",33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateLoginForUsersTransactional() {
        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateLoginForUsersTransactional("Old Login",33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateGenderForUsersRTE() throws Exception {
        Character gender = 'M';
        if(userService.findUser(33).getGender().equals('M')) {
            gender = 'F';
        }

        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateGenderForUsersRTE(gender,33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }

    @Test
    void updateGenderForUsersGenderException() throws Exception {
        Character gender = 'M';
        if(userService.findUser(33).getGender().equals('M')) {
            gender = 'F';
        }

        userService.findAndPrintUsers(33, 34);

        try {
            userService.updateGenderForUsersGenderException(gender,33, 34);
        }catch (Exception ex) {
            System.out.println("ex.getMessage() = " + ex.getMessage());
        }

        userService.findAndPrintUsers(33, 34);
    }
}
