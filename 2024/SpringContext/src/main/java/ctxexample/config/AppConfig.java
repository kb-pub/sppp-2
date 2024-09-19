package ctxexample.config;

import ctxexample.bot.I18nService;
import ctxexample.bot.IOService;
import ctxexample.bot.LanguageService;
import ctxexample.bot.MotivatingBot;
import ctxexample.service.I18nServiceImpl;
import ctxexample.service.IOServiceImpl;
import ctxexample.service.LanguageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration
public class AppConfig {
    @Bean
    public LanguageService languageService() {
        return new LanguageServiceImpl();
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    public I18nService i18nService() {
        return new I18nServiceImpl(languageService());
    }
//
//    @Bean(name = "bot")
//    public MotivatingBot motivatingBot() {
//        return new MotivatingBot(i18nService(), ioService(), languageService());
//    }

    @Bean(name = "bot")
    public MotivatingBot motivatingBot(I18nService i18n, IOService io, LanguageService languageService) {
        return new MotivatingBot(i18n, io, languageService);
    }
}
