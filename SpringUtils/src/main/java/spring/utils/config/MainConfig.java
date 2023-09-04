package spring.utils.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
public class MainConfig {
    @Getter
    @Value("#{ T(Math).random * T(Long).MAX_VALUE }")
    private long random;

    @Getter
    @Value("""
            #{
            new java.lang.ProcessBuilder()
                .command('/bin/bash', '-c', 'echo "Hello from SpEL!" > ~/test/SpEL_Hello.txt')
                .inheritIO()
                .start()
                .equals(null)
            }
            """)
    private String dangerousFiled;

    @SneakyThrows
    public void doSomeStuff() {
        new ProcessBuilder()
                .command("/bin/bash", "-c", "echo \"Hello from SpEL!\" > ~/test/SpEL_Hello.txt")
                .inheritIO()
                .start();
    }
}
