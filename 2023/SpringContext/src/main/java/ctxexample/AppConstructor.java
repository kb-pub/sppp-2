package ctxexample;

import ctxexample.bot.MotivatingBot;
import ctxexample.service.I18nServiceImpl;
import ctxexample.service.IOServiceImpl;
import ctxexample.service.LanguageServiceImpl;

public class AppConstructor {
    public static MotivatingBot construct() {
        var lang = new LanguageServiceImpl();
        var io = new IOServiceImpl(System.in, System.out);
        var i18n = new I18nServiceImpl(lang);
        return new MotivatingBot(i18n, io, lang);
    }
}
