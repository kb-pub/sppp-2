package spring.utils.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString(exclude = "birthday")
public class PersonData {
    private long id;
    private String fio;
    private LocalDate birthday;
}
