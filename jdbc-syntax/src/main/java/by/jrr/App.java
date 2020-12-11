package by.jrr;

import by.jrr.dao.UserDao;

public class App {


    public static void main(String[] args) {

        UserDao dao = new UserDao();
        dao.jdbcSyntax().stream()
                .forEach(s -> System.out.println("\n" + s));

    }
}

