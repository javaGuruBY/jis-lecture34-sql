package by.jrr.service;

import by.jrr.bean.User;
import by.jrr.dao.Dao;
import by.jrr.dao.UserDao;

import java.util.List;

// HW add tests
public class UserService {

    Dao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    void saveNewUser(User user) {
        userDao.save(user);
    }

    List<User> SelectAllUsers() {
        return userDao.findAll();
    }

    User findById(Integer id){
        return (User) userDao.findById(id);
    }
    void delete(User user) {
        if (userDao.findById(user.getId()) != null) {
            userDao.delete(user);
        }
    }
}
