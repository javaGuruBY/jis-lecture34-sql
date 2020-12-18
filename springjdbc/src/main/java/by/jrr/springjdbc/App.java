package by.jrr.springjdbc;

import by.jrr.springjdbc.bean.Human;
import by.jrr.springjdbc.dao.HumanDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {

    static HumanDao humanDao;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        humanDao = ctx.getBean(HumanDao.class);
//        Arrays.stream(
//            ctx.getBeanDefinitionNames())
//                .forEach(System.out::println);


        batchUpdate();

    }

    private static void batchUpdate() {
        humanDao.save(
                new Human(null, "Max", "UpdatedBAtch"),
                new Human(null, "Max", "UpdatedBAtch"),
                new Human(null, "Max", "UpdatedBAtch")
        );
    }

    private static void deleteHuman() {
        Human human = new Human(13L, "Max", "Updated");
        humanDao.deleteHuman(human);
        humanDao.deleteHuman(14L);
    }

    private static void saveOrUpdatedHuman() {
        Human human = new Human(13L, "Max", "Updated");
        humanDao.saveOrUpdatedHuman(human);
    }

    private static void saveHuman() {
        Human human = new Human(13L, "Max", "maxesLastName");
                humanDao.saveHuman(human);

    }

    private static void findByCriteria() {
        Human human = humanDao.findHumanByCriteria(new Human(2L, "Max", null));
        System.out.println("human = " + human);
    }

    private static void updateHuman() {
        Human human = new Human();
        human.setName("updated@");
        human.setId(1L);
        humanDao.updateHuman(human);
        Human updatedHuman = humanDao.findHumanById(1L);
        System.out.println("human = " + updatedHuman);
    }

    private static void findHumanByIdWithNamedParameters() {
        Human human = humanDao.findHumanByIdWithNamedParameters(1L);
        System.out.println("human = " + human);
    }

    private static void findHumanByIdWithMyMapper() {
        Human human = humanDao.findHumanByIdWithMyMapper(1L);
        System.out.println("human = " + human);
    }

    private static void findHumanById() {
        Human human = humanDao.findHumanById(1L);
        System.out.println("human = " + human);
    }

    private static void countRows() {
        System.out.println(humanDao.countRows());
    }
}
