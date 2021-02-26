package pages.TabsOfMainPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.fixture.UserConstants;
import org.openqa.selenium.By;
import pages.EpisodeView.EpisodePage;
import pages.EpisodeView.PremiumEpisodePage;
import pages.PageObject;
import pages.ProfilePage.ProfilePage;

import java.time.Duration;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends PageObject {

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage openProfile() {
       $(By.id("tv.motorsport.mobile:id/action_settings")).click();
       return new ProfilePage(driver);

    }

    public HomePage pressSearchButton() {
        $(By.id("tv.motorsport.mobile:id/action_search")).click();
        return this;
    }

    public MyFeedPage myFeedTabClick() {
        $(By.id("tv.motorsport.mobile:id/my_feed_dest")).click();
        return new MyFeedPage(driver);
    }

    public EpisodePage goToEpisodeRUregion() {
        $(By.id("tv.motorsport.mobile:id/items")).click();
        return new EpisodePage(driver);
    }
    private void checkPremiumVideoNameInside() {
        $(By.className("android.widget.TextView")).$(By.id("tv.motorsport.mobile:id/title"))
                .shouldHave(text("1960 24 Hours of Le Mans"))
                .shouldBe(exist, Duration.ofSeconds(10));
    }
    public PremiumEpisodePage clickPremiumEpisodeRuAndCheck() {
        $$(By.id("tv.motorsport.mobile:id/subtitle")).findBy(text("24H Le Mans: The Great History 24H"))
                .click();
        checkPremiumVideoNameInside();
        return new PremiumEpisodePage();
    }

    public ProfilePage performLoginProcess() {
        openProfile()
                .openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .checkProfilePageHasLoaded();
        return new ProfilePage(driver);
    }
    public ProfilePage performRegistrationProcess() {
        openProfile()
                .openSignUp()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .doneReg()
                .pressExitButton();
        return new ProfilePage(driver);
    }
}
