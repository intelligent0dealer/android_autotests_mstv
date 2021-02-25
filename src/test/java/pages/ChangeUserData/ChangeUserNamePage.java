package pages.ChangeUserData;

import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class ChangeUserNamePage {
    public ProfilePage inputUserName(String username) {
        $(By.id("tv.motorsport.mobile:id/username")).sendKeys(username);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
        return new ProfilePage();
    }
}
