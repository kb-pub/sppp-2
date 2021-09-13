package ctxexample.bot;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bot")
public class MotivatingBot {
    private final I18nService i18nService;
    private final IOService ioService;
    private final LanguageService languageService;

    public MotivatingBot(I18nService i18nService,
                         IOService ioService,
                         LanguageService languageService) {
        this.i18nService = i18nService;
        this.ioService = ioService;
        this.languageService = languageService;
    }

    public void conversation() {
        selectLanguage();
        sayHello();
        String username = askName();
        motivate(username);
    }

    private void selectLanguage() {
        ioService.print("Select language(ru/en): ");
        var language = ioService.readLine();
        languageService.setCurrentLanguage(language);
    }

    private void sayHello() {
        ioService.println(i18nService.getMessage("hello"));
    }

    private String askName() {
        ioService.println(i18nService.getMessage("what-is-your-name"));
        ioService.print("> ");
        return ioService.readLine();
    }

    private void motivate(String username) {
        ioService.println(i18nService.getMessage(
                "go-work", Map.of("username", username)
        ));
    }
}
