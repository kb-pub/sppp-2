package spring.utils.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;

@Component
@Getter
@ToString
public class Settings {
    @Value("${app.server.url:http://default.org}")
    private String serverUrl;

    @Value("${app.server.ip:127.0.0.1}")
    private String serverIp;

    @Value("${app.server.port:1234}")
    private int serverPort;

    @Value("${app.user.name}")
    private String userName;

    @Value("${app.user.password}")
    private String userPassword;
}
