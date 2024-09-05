package rx.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Temperature {
    private final LocalDateTime timestamp;
    private final double value;
}
