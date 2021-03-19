package pages;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import pages.TabsOfMainPage.HomePage;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static java.time.format.DateTimeFormatter.*;

public abstract class PageObject {
    public AppiumDriver<MobileElement> driver;
    public PageObject(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void scrollToBottom() {
        new TouchAction(this.driver).press(PointOption.point(550, 1400))
                .waitAction().moveTo(PointOption.point(550, 605)).release().perform();
    }

    public SearchRequestAndResultPage inputTextAndSearchByKeyButton(String search) {
        $(By.id("tv.motorsport.mobile:id/search_src_text")).sendKeys(search);
        this.driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        return new SearchRequestAndResultPage(driver);
    }

    public HomePage backAndroidButtonPressFourTimes() {
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        return new HomePage(driver);
    }
    public HomePage pressAndroidBackButton() {
        driver.navigate().back();
        return new HomePage(driver);
    }
    public HomePage tapBackButtonInSearch() {
        $(By.className("android.widget.ImageButton")).click();
        return new HomePage(driver);
    }

    public HomePage pressBackButton() {
        $(By.className("android.widget.ImageButton")).click();
        return new HomePage(driver);
    }
    public void buyPPVGoogle() {
        $(By.className("android.widget.Button")).$(By.id("com.android.vending:id/0_resource_name_obfuscated"))
                .shouldBe(exist.because("buying button from google not found"), Duration.ofSeconds(5))
                .click();
    }
    public String parseDateToOneFormat(String datetime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(datetime, ISO_DATE_TIME);
        ZonedDateTime newZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Yekaterinburg"));
        String formattedDateTime = newZonedDateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy, HH:mm").withLocale(Locale.US));
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
}
