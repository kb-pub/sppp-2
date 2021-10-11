package JDBC.and.Boot.ui;

import JDBC.and.Boot.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class IOTest {

    @Configuration
    @Import(IO.class)
    static class Config {
        @Bean
        byte[] byteArray() {
            return new byte[1024];
        }

        @Bean
        ByteArrayInputStream is() {
            return new ByteArrayInputStream(byteArray());
        }

        @Bean
        ByteArrayOutputStream os() {
            return new ByteArrayOutputStream();
        }
    }

    @Autowired
    IO io;

    @Autowired
    ByteArrayInputStream is;

    @Autowired
    ByteArrayOutputStream os;

    @Autowired
    byte[] byteArray;

    @MockBean
    MessageService messageService;

    @Test
    void inter() {
        String code = "some code";
        Object param = new Object();
        String answer = "right answer";
        when(messageService.localize(code, param))
                .thenReturn(answer);

        var result = io.inter(code, param);

        assertThat(result).isEqualTo(answer);
    }

    @Test
    void interPrintln() {
    }

    @Test
    void interPrint() {
    }

    @Test
    void println() {
    }

    @Test
    void print() {
    }

    @Test
    void readLine() {
    }
}