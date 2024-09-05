package spring.utils.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserDto {
    long id;
    String fio;
    LocalDate birthday;
}
