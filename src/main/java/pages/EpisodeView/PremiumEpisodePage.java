package pages.EpisodeView;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.PromoPlugPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;

public class PremiumEpisodePage extends PageObject {

    private static final int TIME_TO_CHECK_PRE_ROLL_END = 2000; // MS
    private static final int TIME_TO_CHECK_PRE_ROLL_END_GLOBAL = 32000; // MS
    private static final int AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO = 120; // Seconds

    public PremiumEpisodePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    private void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    private Boolean isWatchMorePageVisible() {
        try {
            $(By.id("tv.motorsport.mobile:id/close")).shouldBe(visible, Duration.ofSeconds(2));
            return true;
        } catch (Error e) {
            return false;
        }
    }

    private Boolean isSeekBarIsVisible() {
        try {
            $(By.id("tv.motorsport.mobile:id/exo_position")).shouldBe(visible, Duration.ofSeconds(1));
            return true;
        } catch (Error e) {
            return false;
        }
    }

    public void showPlayerControls() {
        if (!isSeekBarIsVisible()) {
            $(By.id("tv.motorsport.mobile:id/container_player")).click();
        }
    }

    private String getTimeFromSeekBarTimer() {
        return ($(By.id("tv.motorsport.mobile:id/exo_position")).getText());
    }


    private int convertStringTimeToSeconds(String time) {
        String[] units = time.split(":");
        System.out.println(time);
        int minutes = parseInt(units[0]);
        int seconds = parseInt(units[1]);
        return (60 * minutes + seconds);
    }

    public PremiumEpisodePage waitForAdsPreRollToFinish() {
        try {
            int timerForCheck = 0;
            SelenideElement seekBar = $(By.id("tv.motorsport.mobile:id/exo_position"));
            SelenideElement playerView = $(By.id("tv.motorsport.mobile:id/container_player"));
            while (playerView.isDisplayed() & !(isSeekBarIsVisible()) & timerForCheck < TIME_TO_CHECK_PRE_ROLL_END_GLOBAL) {
                showPlayerControls();
                Thread.sleep(TIME_TO_CHECK_PRE_ROLL_END);
                timerForCheck += TIME_TO_CHECK_PRE_ROLL_END;
            }
            seekBar.shouldBe(visible);
        } catch (InterruptedException e) {
            System.out.println("Impossible to wait for ads pre-roll to end (too long ads mb?)");
        }
        return this;
    }

    public PremiumEpisodePage checkPremiumTiming() {
        SelenideElement premiumTriangle = $(By.id("tv.motorsport.mobile:id/premium_marker"));
        int timerForCheck = 0;
        while (timerForCheck < AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO
                & !isWatchMorePageVisible()) {
            try {
                premiumTriangle.shouldBe(visible, Duration.ofSeconds(0));
            } catch (Error e) {
                System.out.println("Premium badge is not found");
            }

            if (!isWatchMorePageVisible()) {
                showPlayerControls();
                timerForCheck = convertStringTimeToSeconds(getTimeFromSeekBarTimer());
            } else {
                isWatchMorePageVisible();
            }
        }
        pressExitButton();
        showPlayerControls();
        premiumTriangle.shouldBe(visible);
        Assert.assertTrue((convertStringTimeToSeconds(getTimeFromSeekBarTimer()) >= (AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO)));
        return this;
    }

    public PromoPlugPage checkPremiumTimingForBuySubscription() {
        SelenideElement premiumTriangle = $(By.id("tv.motorsport.mobile:id/premium_marker"));
        int timerForCheck = 0;
        while (timerForCheck < AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO
                & !isWatchMorePageVisible()) {
            try {
                premiumTriangle.shouldBe(visible, Duration.ofSeconds(0));
            } catch (Error e) {
                System.out.println("Premium badge is not found");
            }

            if (!isWatchMorePageVisible()) {
                showPlayerControls();
                timerForCheck = convertStringTimeToSeconds(getTimeFromSeekBarTimer());
            } else {
                isWatchMorePageVisible();
            }
        }
        return new PromoPlugPage(driver);
    }
    public PremiumEpisodePage checkPremiumVideoNameInside() {
        $(By.className("android.widget.TextView")).$(By.id("tv.motorsport.mobile:id/title"))
                .shouldHave(text("1960 24 Hours of Le Mans"))
                .shouldBe(exist, Duration.ofSeconds(10));
        return this;
    }
    public PromoPlugPage getPromoPlugPageFromPremiumEpisode() {
        $(By.id("tv.motorsport.mobile:id/container_player")).click();
        return new PromoPlugPage(driver);
    }

}
