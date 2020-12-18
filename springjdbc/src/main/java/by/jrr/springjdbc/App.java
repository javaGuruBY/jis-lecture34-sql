package by.jrr.springjdbc;

import by.jrr.springjdbc.dao.HumanDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class App {

    static HumanDao humanDao;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        humanDao = ctx.getBean(HumanDao.class);
        Arrays.stream(
            ctx.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}
