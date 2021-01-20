package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Visible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sun.istack.NotNull;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.fixture.UserFixture;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.sun.jmx.snmp.ThreadContext.contains;
import static java.lang.Thread.*;
import static jdk.nashorn.internal.objects.Global.print;

public class AndroidProfile {
    static AppiumDriver<MobileElement> driver;

    public static void registrationInApp() {
        try {
            openApp();

        } catch (Exception exp) {
            System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
        finally {
            openProfile();
            openSignUp();
            inputLogPassReg(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            scrollPage();
            doneReg();
        }
    }

    public static void openApplication() {
        try {
            openApp();
        } catch (Exception exp) {
            System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
    }

    public static void logIn() {
        openProfile();
        openSignIn();
        inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        openSignIn();
    }

    public static void openApp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Mi A2");
        cap.setCapability("udid", "1ef20832");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "10");

        cap.setCapability("appPackage", "tv.motorsport.mobile");
        cap.setCapability("appActivity", "tv.motorsport.mobile.presentation.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver<MobileElement>(url, cap);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void scrollPage() {
        new TouchAction(driver).press(PointOption.point(550, 640))
                .waitAction().moveTo(PointOption.point(550, 10)).release().perform();
    }

    public static void openProfile() {
        $(By.id("tv.motorsport.mobile:id/action_settings")).click();
    }

    public static void openSignIn() {
       $(By.id("tv.motorsport.mobile:id/sign_in")).click();
    }

    public static void openSignUp() {
        $(By.id("tv.motorsport.mobile:id/sign_up")).click();
    }

    public static void inputLogPass(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/login_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/login_tiet_password")).sendKeys(password);
    }

    public static void signOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
    }

    public static void inputLogPassReg(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).sendKeys(password);
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).sendKeys(password);
    }

    public static void doneReg() {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
    }

    public static void clickSubscribe() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).click();
    }

    public static void checkSubscribeAtProfile() {
        MobileElement subscriptionBlock =  driver.findElement(By.id("tv.motorsport.mobile:id/item_subscription"));
        MobileElement subscriptionDescription = subscriptionBlock.findElement(By.id("tv.motorsport.mobile:id/tv_description"));
        $(subscriptionDescription).waitUntil(Condition.exist, 10000);
        System.out.println("You're in user profile");
    }

    public static void goToName() {
        $(By.id("tv.motorsport.mobile:id/item_name")).click();
    }

    public static void inputNameLastDone(String name, String surname) {
        $(By.id("tv.motorsport.mobile:id/name")).click();
        $(By.id("tv.motorsport.mobile:id/name")).sendKeys(name);

        $(By.id("tv.motorsport.mobile:id/surname")).click();
        $(By.id("tv.motorsport.mobile:id/surname")).sendKeys(surname);

        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public static void checkNameLast(String text){
        $$(By.id("tv.motorsport.mobile:id/item_name")).findBy(id("tv.motorsport.mobile:id/tv_description")).shouldHave(exactText(text));
    }

    public static void clickUsername() {
        MobileElement usernameBlock = driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameButton = usernameBlock.findElementById("tv.motorsport.mobile:id/title");
        usernameButton.click();

    }

    public static void clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
    }

    public static void buyMonthlySub() {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text("МЕСЯЧНЫЙ")).click();
    }

    public static void buyMonthlySubOutsideApp() {
        $(By.className("android.widget.Button")).shouldHave(text("Подписаться")).click();
    }

    public static void checkSuccessBuy() {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text("Вы успешно совершили покупку."));
        System.out.println("Успех");
    }

    public static void inputUserName(String username) {
        $(By.id("tv.motorsport.mobile:id/username")).sendKeys(username);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done"));
    }

    public static void checkUserName(String username){
        MobileElement usernameBlock= driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameName = usernameBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        Assert.assertTrue(usernameName.getText().equals(username));
    }

    public static void clickGender() {
        $(By.id("tv.motorsport.mobile:id/item_gender")).click();
    }

    public static void chooseGender(){
        $(By.id("tv.motorsport.mobile:id/gender_chooser_male"));
    }

    public static void checkGender (String gender){
        MobileElement genderBlock = driver.findElementById("tv.motorsport.mobile:id/item_gender");
        MobileElement genderName = genderBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        System.out.println(genderName.getText());
        Assert.assertTrue(genderName.getText().equals(gender));
    }

    public static void changePassword () {
        $(By.id("tv.motorsport.mobile:id/item_password"));
    }

    public static void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public static void changePassword(String oldPassword, String newPassword) {
        checkSubscribeAtProfile();
        changePassword();
        enterOldNewPass(oldPassword, newPassword);
        checkSubscribeAtProfile();
    }

    public static void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    public static void pressBackButton() {
       $(By.className("android.widget.ImageButton")).click();
    }

    public static void pressSearchButton(){
        $(By.id("tv.motorsport.mobile:id/action_search")).click();
    }

    public static void searchPPVEpisode(){
        $(By.id("tv.motorsport.mobile:id/search_src_text")).sendKeys(UserFixture.NAME_OF_PPV_EPISODE.getValue());
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    public static void waitVisibleSearchResultAndClick() throws Exception {

        MobileElement nameOfEpisode = driver.findElementById("tv.motorsport.mobile:id/title");

        int searchResultsAvailableTimer = 0;

        while (nameOfEpisode == null && (searchResultsAvailableTimer < 10000)) {
            sleep(100);
            searchResultsAvailableTimer += 100;
            nameOfEpisode = driver.findElementById("tv.motorsport.mobile:id/title");
        }

        if (nameOfEpisode != null) {
            $(nameOfEpisode).shouldHave(text(UserFixture.NAME_OF_PPV_EPISODE.getValue())).click();
            System.out.println("Episode found");
        } else {
            throw new Exception("Episode not found");
        }
    }

    public static void getInfo(){
        Map<String, Object> args = new HashMap<>();
        args.put("command", "getprop");
        args.put("args", Lists.newArrayList("persist.sys.locale"));
        String output = (String) driver.executeScript("mobile: shell", args);
        System.out.println(output);
    }

    public static void verificationPPVPageLoaded() throws Exception{
        MobileElement infoElement = driver.findElementById("tv.motorsport.mobile:id/tabs");

        int searchResultsAvailableTimer = 0;

        while (infoElement == null && (searchResultsAvailableTimer < 10000)) {
            sleep(100);
            searchResultsAvailableTimer += 100;
            infoElement = driver.findElementById("tv.motorsport.mobile:id/tabs");
        }

        if (infoElement != null) {
            $(infoElement).shouldHave(text("Информация"));
            System.out.println("You're on PPV episode page");
        } else {
            throw new Exception("Oops, something went wrong");
        }
    }

}



