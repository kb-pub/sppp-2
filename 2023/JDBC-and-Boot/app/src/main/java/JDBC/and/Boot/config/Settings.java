package JDBC.and.Boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Data
public class Settings {
    public Some some;

    @Data
    public static class Some {
        public String greeting;
        public long number;
    }
}
