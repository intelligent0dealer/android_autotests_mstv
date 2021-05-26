package pages.TabsOfMainPage;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;

import static com.codeborne.selenide.Selenide.$;

public class RacingSeriesPage extends PageObject {
    public RacingSeriesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProgramTabPage openProgramTab() {
        $(By.id("tv.motorsport.mobile:id/programs_dest")).click();
        return new ProgramTabPage(driver);
    }
    public RacingSeriesPage checkThatElementsLoad() {
        $(By.id("tv.motorsport.mobile:id/racingSeries")).shouldBe(Condition.visible.because("Racing Series not found"));
        return this;
    }


}
