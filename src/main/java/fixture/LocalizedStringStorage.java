package fixture;

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
        System.out.println(currentLocale);
        return currentLocale == LocaleInfo.Locale.EN ? "SIGN IN" : "ВОЙТИ";
    }
    public String getRegOkDescription() {
        return currentLocale == LocaleInfo.Locale.EN ? "Please send me Motorsport.tv newsletters, and occasional" +
                " communication from Motorsport Network featuring your favorite content, " +
                "exclusive offers and other information." : "Я согласен/согласна получать рассылку " +
                "от Моторспорт.тв и периодические сообщения от Motorsport Network об избранном контенте, эксклюзивных предложениях и др.";
    }
    public String mailField() {
        return currentLocale == LocaleInfo.Locale.EN ? "Email" : "Электронная почта";
    }
    public String passField() {
        return currentLocale == LocaleInfo.Locale.EN ? "Password" : "Пароль";
    }
    public String passRepeatField() {
        return currentLocale == LocaleInfo.Locale.EN ? "Confirm Password" : "Подтвердить пароль";
    }
    public String continueButton() {
        return currentLocale == LocaleInfo.Locale.EN ? "Continue" : "Продолжить";
    }


}
