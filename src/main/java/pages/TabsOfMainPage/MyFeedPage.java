package pages.TabsOfMainPage;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.SignInPage;
import pages.SignUpPage;

import static com.codeborne.selenide.Selenide.$;

public class MyFeedPage extends PageObject {

    public MyFeedPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SignInPage signInButtonClickFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_simple")).click();
        return new SignInPage(driver);
    }

    public SignUpPage clickJoinNowFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_accent")).click();
        return new SignUpPage(driver);
    }

    public void checkTextInEmptyFeedPage(String text) {
        $(By.id("tv.motorsport.mobile:id/tv_subtitle"))
                .shouldHave
                        (Condition.text(text)
                                .because("Text not correct or not found"));
        $(By.id("tv.motorsport.mobile:id/tv_title")).shouldBe(Condition.visible);
    }
    public String getNameOfProgramInFeed() {
        return $(By.id("tv.motorsport.mobile:id/subtitle")).getText();
    }
    public void checkThatElementsLoad() {
        $(By.id("tv.motorsport.mobile:id/emptyState")).shouldBe(Condition.visible.because("FeedTab not found"));
    }
}
