package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SubscriptionPage extends PageObject {
    public SubscriptionPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new ProfilePage(driver);
    }

    public SubscriptionPage buyMonthlySub() {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text("MONTHLY")).click();
        return this;
    }

    public SubscriptionPage buyMonthlySubOutsideApp() {
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
        return this;
    }

    public SubscriptionPage checkSuccessBuy(String message) {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        System.out.println("Успех");
        return this;
    }


}
