package pages.TabsOfMainPage;

import org.openqa.selenium.By;
import pages.SignInPage;
import pages.SignUpPage;

import static com.codeborne.selenide.Selenide.$;

public class MyFeedPage {

    public SignInPage signInButtonClickFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_simple")).click();
        return new SignInPage();
    }

    public SignUpPage clickJoinNowFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_accent")).click();
        return new SignUpPage();
    }
}
