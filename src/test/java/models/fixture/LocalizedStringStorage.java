package models.fixture;

import pages.LocaleInfo;

public class LocalizedStringStorage {
    private final LocaleInfo.Locale currentLocale;

    public LocalizedStringStorage(LocaleInfo.Locale locale) {
        currentLocale = locale;
    }

    public String getMale_gender() {
        return currentLocale == LocaleInfo.Locale.EN ? "Male" : "Мужской";
    }
    public String getSuccess_message() {
        return currentLocale == LocaleInfo.Locale.EN ? "You've successfully bought this purchase." : "Вы успешно совершили покупку.";
    }
}
