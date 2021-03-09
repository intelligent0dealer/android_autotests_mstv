package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.EpisodeView.PayPerViewEpisodePage;
import pages.EpisodeView.PremiumEpisodePage;
import pages.ProfilePage.ProfilePage;
import pages.TabsOfMainPage.MyFeedPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SubscriptionPage extends PageObject {
    public SubscriptionPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage clickContinueSubButtonFromProfile() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new ProfilePage(driver);
    }
    public MyFeedPage clickContinueSubButtonFromFeed() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new MyFeedPage(driver);
    }

    public PayPerViewEpisodePage clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new PayPerViewEpisodePage(driver);
    }

    public SubscriptionPage confirmBuyMonthlySubProcess(String nameOfPlan, String googlePayMessage, String message) {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text(nameOfPlan)).click();
        $$(By.className("android.widget.TextView")).findBy(text(googlePayMessage)).shouldBe(visible);
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        return this;
    }
    public SubscriptionPage confirmBuyAnnualSubProcess(String nameOfPlan, String googlePayMessage, String message) {
        $(By.id("tv.motorsport.mobile:id/subscription_scv_plan_annual"))
                .$(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text(nameOfPlan)).click();
        $$(By.className("android.widget.TextView")).findBy(text(googlePayMessage)).shouldBe(visible);
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        return this;
    }

    public SubscriptionPage buySubByGoogle(String googlePayMessage) {
        $$(By.className("android.widget.TextView")).findBy(text(googlePayMessage)).shouldBe(visible);
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
        return this;
    }

    public SubscriptionPage checkSuccessBuy(String message) {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        System.out.println("Успех");
        return this;
    }
    public SubscriptionPage chooseFreePlan() {
        $(By.id("tv.motorsport.mobile:id/subscription_scv_plan_free")).click();
        return this;
    }
    public PremiumEpisodePage clickContinueSubButtonFromPremiumEpisode() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
        return new PremiumEpisodePage(driver);
    }



}
