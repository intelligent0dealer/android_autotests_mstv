package pages.ChangeUserData;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.ProfilePage.ProfilePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class ChangeNamePage extends PageObject {
    public ChangeNamePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage inputNameLastDone(String name, String surname) {

        $(By.id("tv.motorsport.mobile:id/name")).shouldBe(exist, Duration.ofSeconds(5)).sendKeys(name);

        $(By.id("tv.motorsport.mobile:id/surname")).sendKeys(surname);

        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
        return new ProfilePage(driver);
    }
}
