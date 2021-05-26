package pages.TabsOfMainPage;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;

import static com.codeborne.selenide.Selenide.$;

public class ProgramTabPage extends PageObject {
    public ProgramTabPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public ProgramTabPage checkThatElementsLoad() {
        $(By.id("tv.motorsport.mobile:id/channels")).shouldBe(Condition.visible.because("Channels carousel not found"));
        $(By.id("tv.motorsport.mobile:id/programs")).shouldBe(Condition.visible.because("Programs not found"));
        return this;
    }
    public MyFeedPage openFeedTab() {
        $(By.id("tv.motorsport.mobile:id/my_feed_dest")).click();
        return new MyFeedPage(driver);
    }


}
