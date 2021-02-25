package pages.TabsOfMainPage;

import models.fixture.UserFixture;
import org.openqa.selenium.By;
import pages.CommonControls;
import pages.EpisodeView.EpisodePage;
import pages.EpisodeView.PremiumEpisodePage;
import pages.ProfilePage.ProfilePage;
import pages.SearchRequestAndResultPage;
import setUp.SetupConfig;

import java.time.Duration;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

   public ProfilePage openProfile() {
       $(By.id("tv.motorsport.mobile:id/action_settings")).click();
       return new ProfilePage();

    }

    public void pressSearchButton() {
        $(By.id("tv.motorsport.mobile:id/action_search")).click();
    }

    public MyFeedPage myFeedTabClick() {
        $(By.id("tv.motorsport.mobile:id/my_feed_dest")).click();
        return new MyFeedPage();
    }

    public EpisodePage goToEpisodeRUregion() {
        $(By.id("tv.motorsport.mobile:id/items")).click();
        return new EpisodePage();
    } // TODO : FIX TO RU
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
                .inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                .pressSignInButton()
                .checkProfilePageHasLoaded();
        return new ProfilePage();
    }
    public void performRegistrationProcess() {
        openProfile()
                .openSignUp()
                .inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                .doneReg()
                .pressExitButton();
    }
}
