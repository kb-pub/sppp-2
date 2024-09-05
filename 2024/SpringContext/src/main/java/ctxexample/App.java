package ctxexample;

import ctxexample.bot.MotivatingBot;
import ctxexample.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
//@Configuration
public class App {
    public static void main(String[] args) {
//        AppConstructor.construct().conversation();

//        new ClassPathXmlApplicationContext("classpath:main-config.xml")
//                .getBean("bot", MotivatingBot.class)
//                .conversation();

//        new AnnotationConfigApplicationContext(AppConfig.class)
//                .getBean("bot", MotivatingBot.class)
//                .conversation();

        new AnnotationConfigApplicationContext(App.class)
                .getBean("bot", MotivatingBot.class)
                .conversation();
    }
}
