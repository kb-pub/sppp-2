package spring.utils.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class I18nConfig {
    @Bean
    MessageSource messageSource() {
        var src = new ReloadableResourceBundleMessageSource();
        src.setBasename("classpath:bungle");
        src.setDefaultEncoding("UTF-8");
        src.setCacheSeconds(900);
        src.setUseCodeAsDefaultMessage(true);
        return src;
    }
}
