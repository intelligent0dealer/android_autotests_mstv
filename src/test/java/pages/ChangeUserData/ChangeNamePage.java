package pages.ChangeUserData;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class ChangeNamePage {
    public ProfilePage inputNameLastDone(String name, String surname) {

        $(By.id("tv.motorsport.mobile:id/name")).shouldBe(exist, Duration.ofSeconds(5)).sendKeys(name);

        $(By.id("tv.motorsport.mobile:id/surname")).sendKeys(surname);

        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
        return new ProfilePage();
    }
}
