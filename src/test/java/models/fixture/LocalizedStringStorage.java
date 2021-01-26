package models.fixture;

import pages.LocaleInfo;

public class LocalizedStringStorage {
    private LocaleInfo.Locale currentLocale;

    public LocalizedStringStorage(LocaleInfo.Locale locale) {
        currentLocale = locale;
    }

    public String male_gender;

    public String getMale_gender() {
        return currentLocale == LocaleInfo.Locale.EN ? "Male" : "Мужской";
    }
}
