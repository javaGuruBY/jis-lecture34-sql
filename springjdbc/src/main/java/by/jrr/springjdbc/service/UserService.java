package by.jrr.springjdbc.service;

import by.jrr.springjdbc.bean.User;
import by.jrr.springjdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void findAndPrintUsers(Integer... id) {
        userDao.findUserInId(id)
                .forEach(User::printForGender);
    }
    public User findUser(Integer id) {
        List<User> result = userDao.findUserInId(id);
        if(result.size()>0) {
            return result.get(0);
        }
        return null;
    }

    public void updateGenderForUsers(Character gender, Integer... ids) throws Exception {
        for(int id : ids) {
            userDao.updateUserGender(User.builder().id(id).gender(gender).build());
            if (new Random().nextBoolean()) {
                throw new Exception("Fock you I'm out!");
            }
        }
    }

    @Transactional
    public void updateGenderForUsersTransactional(Character gender, Integer... ids) throws Exception {
        for(int id : ids) {
            userDao.updateUserGender(User.builder().id(id).gender(gender).build());
            if (new Random().nextBoolean()) {
                throw new Exception("Fock you I'm out!");
            }
        }
    }

    public void updateLoginForUsers(String login, Integer... ids) throws Exception {
        for(int id : ids) {
            userDao.updateUserLogin(User.builder().id(id).login(login).build());
        }
    }

    @Transactional
    public void updateLoginForUsersTransactional(String login, Integer... ids) throws Exception {
        for(int id : ids) {
            userDao.updateUserLogin(User.builder().id(id).login(login).build());
        }
    }
}
