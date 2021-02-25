package pages.EpisodeView;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PayPerViewEpisodePage {

    public void buyPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionBuy")).click();
    }

    public void rentPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionRent")).click();
    }

    public PayPerViewEpisodePage checkThatAtPPVPage() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).shouldBe(exist, Duration.ofSeconds(15));
        System.out.println("You're in PPV Episode Page");
        return new PayPerViewEpisodePage();
    }

    public void checkPurchasePPVView() {
        $(By.id("tv.motorsport.mobile:id/accessGrantedTitle")).shouldBe(exist);
    }

    public void checkPPVCodeInfo() {
        $(By.id("tv.motorsport.mobile:id/activationDescription"))
                .shouldHave(text("You already have a code. You can activate it and start watching the content."))
                .shouldBe(exist,Duration.ofSeconds(5));
    }

    public void clickPPVActivationButton() {
        $(By.id("tv.motorsport.mobile:id/activationBtn")).click();
    }

    public void clickSubscribeAndWatchFromPPVPage() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).click();
    }
}
