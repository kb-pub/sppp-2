package app;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner helloRunner(PasswordEncoder encoder) {
        return args -> {
            System.out.println("Hello from Security Base!");
            System.out.println("user encoded password: " + encoder.encode("pass"));
        };
    }
}