package pages.EpisodeView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.PromoPlugPage;
import pages.SignInPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PayPerViewEpisodePage extends PageObject {

    public PayPerViewEpisodePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SignInPage buyPPVByNotLoginUser() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionBuy")).click();
        return new SignInPage(driver);
    }

    public PayPerViewEpisodePage buyPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionBuy")).click();
        return this;
    }

    public PayPerViewEpisodePage rentPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionRent")).click();
        return this;
    }

    public PayPerViewEpisodePage checkThatAtPPVPage() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).shouldBe(exist.because("Sub button doesnt exist"));
        System.out.println("You're in PPV Episode Page");
        return this;
    }

    public void checkPurchasePPVView() {
        $(By.id("tv.motorsport.mobile:id/accessGrantedTitle")).shouldBe(exist);
    }

    public PayPerViewEpisodePage checkPPVCodeInfo() {
        $(By.id("tv.motorsport.mobile:id/activationDescription"))
                .shouldHave(text("You already have a code. You can activate it and start watching the content."))
                .shouldBe(exist, Duration.ofSeconds(5));
        return this;
    }

    public void clickPPVActivationButton() {
        $(By.id("tv.motorsport.mobile:id/activationBtn")).click();
    }

    public PromoPlugPage clickSubscribeAndWatchFromPPVPage() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).click();
        return new PromoPlugPage(driver);
    }

    public PromoPlugPage subscribeAndWatchButtonPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).click();
        return new PromoPlugPage(driver);
    }
}
