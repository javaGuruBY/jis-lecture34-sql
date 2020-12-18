package by.jrr.springjdbc.service;


import by.jrr.springjdbc.App;
import by.jrr.springjdbc.bean.Human;
import by.jrr.springjdbc.dao.HumanDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {App.class})
public class HumanDaoTests {

    @Autowired
    HumanDao humanDao;

    @Test
    public void batchUpdate() {
        humanDao.save(
                new Human(null, "Max", "UpdatedBAtch"),
                new Human(null, "Max", "UpdatedBAtch"),
                new Human(null, "Max", "UpdatedBAtch")
        );
    }

    @Test
    public void deleteHuman() {
        Human human = new Human(13L, "Max", "Updated");
        humanDao.deleteHuman(human);
        humanDao.deleteHuman(14L);
    }

    @Test
    public void saveOrUpdatedHuman() {
        Human human = new Human(13L, "Max", "Updated");
        humanDao.saveOrUpdatedHuman(human);
    }

    @Test
    public void saveHuman() {
        Human human = new Human(13L, "Max", "maxesLastName");
        humanDao.saveHuman(human);

    }

    @Test
    public void findByCriteria() {
        Human human = humanDao.findHumanByCriteria(new Human(2L, "Max", null));
        System.out.println("human = " + human);
    }

    @Test
    public void updateHuman() {
        Human human = new Human();
        human.setName("updated@");
        human.setId(1L);
        humanDao.updateHuman(human);
        Human updatedHuman = humanDao.findHumanById(1L);
        System.out.println("human = " + updatedHuman);
    }

    @Test
    public void findHumanByIdWithNamedParameters() {
        Human human = humanDao.findHumanByIdWithNamedParameters(1L);
        System.out.println("human = " + human);
    }

    @Test
    public void findHumanByIdWithMyMapper() {
        Human human = humanDao.findHumanByIdWithMyMapper(1L);
        System.out.println("human = " + human);
    }

    @Test
    public void findHumanById() {
        Human human = humanDao.findHumanById(1L);
        System.out.println("human = " + human);
    }


    @Test
    public void countRows() {
        System.out.println(humanDao.countRows());
    }
}
