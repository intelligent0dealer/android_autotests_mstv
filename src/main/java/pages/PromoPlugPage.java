package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PromoPlugPage extends PageObject{

    public PromoPlugPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SignUpPage clickJoinAndWatchByNotLoginUser() {
        $(By.id("tv.motorsport.mobile:id/promoJoinAndWatch")).click();
        return new SignUpPage(driver);
    }
    public SubscriptionPage clickWatchMore() {
        $(By.id("tv.motorsport.mobile:id/promoJoinAndWatch")).click();
        return new SubscriptionPage(driver);
    }

    public SignInPage alreadyHaveAnAccSignIn() {
        $(By.id("tv.motorsport.mobile:id/promoSignIn")).click();
        return new SignInPage(driver);
    }

}
