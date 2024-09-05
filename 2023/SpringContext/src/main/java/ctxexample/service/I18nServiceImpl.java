package ctxexample.service;

import ctxexample.bot.I18nService;
import ctxexample.bot.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class I18nServiceImpl implements I18nService {
    private final Map<String, String> phraseMap = Map.of(
            "ru#hello", "Привет! Меня зовут Бот456546",
            "ru#what-is-your-name", "Как тебя зовут?",
            "ru#go-work", "Давай, иди работай, %username%",
            "ru#press-any-key", "[Нажмите любую клавишу для завершения...]",
            "en#hello", "Хелло! Май нэйм из Бот456546",
            "en#what-is-your-name", "Вхат из ёр нэйм?",
            "en#go-work", "Камон, гоу ворк, %username%",
            "en#press-any-key", "[Пресс эни кей то кантинью...]"
    );

    private final LanguageService languageService;

    public I18nServiceImpl(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, null);
    }

    @Override
    public String getMessage(String code, Map<String, Object> params) {
        var language = languageService.getCurrentLanguage();
        var key = language + "#" + code;
        var phrase = phraseMap.get(key);
        if (params != null)
            phrase = handlePlaceholders(phrase, params);
        return phrase;
    }

    private String handlePlaceholders(String phrase, Map<String, Object> params) {
        for (var e : params.entrySet())
            phrase = phrase.replace("%" + e.getKey() + "%", e.getValue().toString());
        return phrase;
    }
}
