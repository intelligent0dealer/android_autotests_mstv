package pages.TabsOfMainPage;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.ProgramViewPage;

import static com.codeborne.selenide.Selenide.$;


public class LiveTabPage extends PageObject {
    public LiveTabPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public String getStatusOfLive() {
        return $(By.id("tv.motorsport.mobile:id/liveBadge")).getText();
    }
    public LiveTabPage checkNameOfLive() {
        $(By.className("androidx.recyclerview.widget.RecyclerView"))
                .$(By.id("tv.motorsport.mobile:id/title"))
                .shouldHave(Condition.text("Present (Autotests)").because("Episode not found"));
        return this;
    }
    public LiveTabPage checkThatElementsLoad() {
        $(By.id("tv.motorsport.mobile:id/liveBadge")).shouldBe(Condition.visible.because("Livebadge not found"));
        $(By.id("tv.motorsport.mobile:id/carousels")).shouldBe(Condition.visible.because("Carousels not found"));
        return this;
    }
    public RacingSeriesPage openRacingSeriesTab() {
        $(By.id("tv.motorsport.mobile:id/racing_series_dest")).click();
        return new RacingSeriesPage(driver);
    }

}
