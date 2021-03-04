package pages.ChangeUserData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class ChangeUserNamePage extends PageObject {
    public ChangeUserNamePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage inputUserName(String username) {
        $(By.id("tv.motorsport.mobile:id/username")).sendKeys(username);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
        return new ProfilePage(driver);
    }
}
