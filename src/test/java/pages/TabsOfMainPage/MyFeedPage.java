package pages.TabsOfMainPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.SignInPage;
import pages.SignUpPage;

import static com.codeborne.selenide.Selenide.$;

public class MyFeedPage extends PageObject {

    public MyFeedPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SignInPage signInButtonClickFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_simple")).click();
        return new SignInPage(driver);
    }

    public SignUpPage clickJoinNowFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_accent")).click();
        return new SignUpPage(driver);
    }
}
