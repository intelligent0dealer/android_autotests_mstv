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

    public String getNameOfRegionButton() {
        return currentLocale == LocaleInfo.Locale.EN ? "Region" : "Регион";
    }

    public String getNameOfProfilePage() {
        return currentLocale == LocaleInfo.Locale.EN ? "My Profile" : "Мой профиль";
    }

    public String getNameOfContactUsButton() {
        return currentLocale == LocaleInfo.Locale.EN ? "Contact Us" : "Связаться с нами";
    }

    public String getNameOfLegalInfo() {
        return currentLocale == LocaleInfo.Locale.EN ? "Legal" : "Юридическая информация";
    }

    public String getNameOfVersionView() {
        return currentLocale == LocaleInfo.Locale.EN ? "Version" : "Версия";
    }
    
    public String getNameOfSignUpButton() {
        return currentLocale == LocaleInfo.Locale.EN ? "SIGN UP" : "ЗАРЕГИСТРИРОВАТЬСЯ";
    }

    public String getNameOfSignInButton() {
        return currentLocale == LocaleInfo.Locale.EN ? "SIGN IN" : "ВОЙТИ";
    }


}
