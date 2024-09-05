package jpa.service;

import java.util.Locale;

public interface CurrentLocaleService {
    void set(String locale);
    Locale get();
}
