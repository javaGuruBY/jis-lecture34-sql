package by.jrr.springjdbc.service;


import by.jrr.springjdbc.dao.HumanDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HumanServiceTest {

    @Autowired
    HumanDao humanDao;

    @Test
    public void countRows() {
        System.out.println(humanDao.countRows());
    }
}
