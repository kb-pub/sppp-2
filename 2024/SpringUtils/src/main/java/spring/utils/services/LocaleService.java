package spring.utils.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleService {
    @Getter @Setter
    private Locale current = Locale.forLanguageTag("ru");
}
