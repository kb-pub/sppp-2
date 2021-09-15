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

    @Value("${app.server.ip}")
    private String serverIp;

    @Value("${app.server.port}")
    private int serverPort;

    @Value("${app.user.name}")
    private String userName;

    @Value("${app.user.password}")
    private String userPassword;
}
