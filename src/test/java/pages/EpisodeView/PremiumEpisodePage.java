package pages.EpisodeView;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pages.ProfilePage.ProfilePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;

public class PremiumEpisodePage {

    private static final int TIME_TO_CHECK_PRE_ROLL_END = 2000;
    private static final int TIME_TO_CHECK_PRE_ROLL_END_GLOBAL = 32000; // Mseconds
    private static final int AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO = 120; // seconds

    private void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    private Boolean isWatchMorePageVisible() {
        try {
            $(By.id("tv.motorsport.mobile:id/close")).shouldBe(visible, Duration.ofSeconds(1));
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
    } // PremiumEpisodePage

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
        return new PremiumEpisodePage();
    }

    public void checkPremiumTiming() {
        SelenideElement premiumTriangle = $(By.id("tv.motorsport.mobile:id/premium_marker"));
        int timerForCheck = 0;
        while (timerForCheck < AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO
                & !isWatchMorePageVisible()) {
            try {
                premiumTriangle.shouldBe(visible, Duration.ofSeconds(0)); }
            catch (Error e) {
                System.out.println("Premium badge is not found");
            }

            if (!isWatchMorePageVisible()) {
                showPlayerControls();
                timerForCheck = convertStringTimeToSeconds(getTimeFromSeekBarTimer());
            }   else {
                isWatchMorePageVisible();
            }
        }
        pressExitButton();
        showPlayerControls();
        premiumTriangle.shouldBe(visible);
        Assert.assertTrue((convertStringTimeToSeconds(getTimeFromSeekBarTimer()) >= (AVAILABLE_TIME_TO_WATCH_PREMIUM_VIDEO)));
    }

}
