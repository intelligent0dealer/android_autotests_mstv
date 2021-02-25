package pages.EpisodeView;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class EpisodePage {

    public EpisodePage goToInfoInEpisodePage() {
        $(By.className("androidx.appcompat.app.ActionBar$Tab")).$(By.linkText("Info")).shouldBe(exist, Duration.ofSeconds(15)).click();
        return new EpisodePage();
    }

    public void addToMyFeedButtonClick() {
        $(By.id("tv.motorsport.mobile:id/follow")).click();
    }
}
