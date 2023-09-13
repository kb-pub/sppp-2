package spring.utils;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import spring.utils.config.MainConfig;
import spring.utils.config.Settings;
import spring.utils.dto.PersonData;
import spring.utils.dto.UserDto;
import spring.utils.services.ConversationService;
import spring.utils.services.LocaleService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Properties;

@Slf4j
@ComponentScan
public class App {
    public static void main(String[] args) {
        /**
         * Lombok
         * Logging
         * Properties
         * SpEL
         * I18n
         * Testing
         */

//        lombokTest();
//        test2();
        test3();

    }

    private static void lombokTest() {
        var user = new UserDto(1, "Vasya", LocalDate.now());
        log.info(user + "");

        var person = new PersonData();
        person.setId(1);
        person.setFio("Petya");
        person.setBirthday(LocalDate.now());

        log.info(person + "");
    }

    private static void test2() {
        val ctx = new AnnotationConfigApplicationContext(App.class);

        val settings = ctx.getBean(Settings.class);
        log.info("settings: " + settings);

        val config = ctx.getBean(MainConfig.class);
        log.info("num1: " + config.getRandom());
        log.info("num2: " + new MainConfig().getRandom());

//        config.doSomeStuff();

//        log.info("danger: " + config.getDangerousFiled());
    }

    private static void test3() {
        val ctx = new AnnotationConfigApplicationContext(App.class);

        var locale = ctx.getBean(LocaleService.class);

        locale.setCurrent(Locale.forLanguageTag("en-US"));
        log.info("locale: " + locale.getCurrent());
        ctx.getBean(ConversationService.class)
                .doConversation();

        locale.setCurrent(Locale.forLanguageTag("ru"));
        log.info("locale: " + locale.getCurrent());
        ctx.getBean(ConversationService.class)
                .doConversation();
    }


}
