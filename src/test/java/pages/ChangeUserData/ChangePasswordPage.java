package pages.ChangeUserData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;

import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordPage extends PageObject {

    public ChangePasswordPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }
}
