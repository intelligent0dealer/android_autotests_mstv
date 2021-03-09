package fixture;

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
    public String getMonthlyNameOfPlan() {
        return currentLocale == LocaleInfo.Locale.EN ? "MONTHLY" : "МЕСЯЧНЫЙ";
    }
    public String getAnnualNameOfPlan() {
        return currentLocale == LocaleInfo.Locale.EN ? "ANNUAL" : "ГОДОВОЙ";
    }
    public String getGooglepayMessage() {
        return currentLocale == LocaleInfo.Locale.EN ? "Test card, always approves" : "Тестовая карта, всегда подтверждать";
    }
}
