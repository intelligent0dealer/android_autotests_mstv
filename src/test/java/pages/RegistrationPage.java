package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends PageObject {
    public RegistrationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
        return new ProfilePage(driver);
    }

    public SubscriptionPage chooseFreePlan() {
        $(By.id("tv.motorsport.mobile:id/subscription_scv_plan_free")).click();
        return new SubscriptionPage(driver);
    }
}
