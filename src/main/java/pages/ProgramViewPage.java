package pages;

import com.codeborne.selenide.Condition;
import fixture.UserConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;

public class ProgramViewPage extends PageObject {
    public ProgramViewPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProgramViewPage checkTitleOfProgram() {
        $(By.id("tv.motorsport.mobile:id/main_title"))
                .shouldHave(Condition.text(UserConstants.PROGRAM_FOR_LIVESTREAM_TESTS).because("It's another program"));
        return this;
    }
    public String checkAndGetStatusOfFutureLivestream() {
        return $(By.id("tv.motorsport.mobile:id/liveBadge"))
                .shouldBe(Condition.visible.because("Livestream status not found")).getText().replace("Live: ", "");
    }
}
