package by.jrr;

import by.jrr.dao.UserDaoSyntaxExample;

public class App {


    public static void main(String[] args) {

        UserDaoSyntaxExample dao = new UserDaoSyntaxExample();
        dao.jdbcSyntax().stream()
                .forEach(s -> System.out.println("\n" + s));

    }
}

