package ctxexample.service;

import ctxexample.bot.LanguageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class LanguageServiceImpl implements LanguageService {
    private static final Collection<String> supportedLanguages = Set.of("ru", "en");

    private String currentLanguage = "ru";

    @Override
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    @Override
    public void setCurrentLanguage(String language) {
        language = language.strip().toLowerCase();
        if ( ! supportedLanguages.contains(language))
            throw new IllegalArgumentException("Not supported language: " + language);
        currentLanguage = language;
    }
}
