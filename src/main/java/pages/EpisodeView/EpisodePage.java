package pages.EpisodeView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.SignInPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class EpisodePage extends PageObject {

    public EpisodePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public EpisodePage goToInfoInEpisodePage() {
        $(By.className("androidx.appcompat.app.ActionBar$Tab")).$(By.linkText("Info")).shouldBe(exist, Duration.ofSeconds(15)).click();
        return this;
    }

    public SignInPage addToMyFeedButtonClick() {
        $(By.id("tv.motorsport.mobile:id/follow")).click();
        return new SignInPage(driver);
    }
}
