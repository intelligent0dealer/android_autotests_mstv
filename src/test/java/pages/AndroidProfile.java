package pages;

import com.codeborne.selenide.Selectors;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.fixture.UserFixture;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class AndroidProfile {

    public AppiumDriver<MobileElement> driver;

    public AndroidProfile(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void openProfile() {
        $(By.id("tv.motorsport.mobile:id/action_settings")).click();
    }

    public void openSignIn() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).click();
    }

    public void openSignUp() {
        $(By.id("tv.motorsport.mobile:id/sign_up")).click();
    }

    public void inputLogPass(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/login_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/login_tiet_password")).sendKeys(password);
    }

    public void signOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
    }

    public void inputLogPassReg(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).sendKeys(password);
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).sendKeys(password);
    }

    public void doneReg() {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
    }

    public void clickSubscribe() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).click();
    }

    public void checkSubscribeAtProfile() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).$(By.id("tv.motorsport.mobile:id/tv_description")).waitUntil(exist, 10000);
        System.out.println("You're in user profile");

    }

    public void goToName() {
        $(By.id("tv.motorsport.mobile:id/item_name")).click();
    }

    public void inputNameLastDone(String name, String surname) {

        $(By.id("tv.motorsport.mobile:id/name")).sendKeys(name);

        $(By.id("tv.motorsport.mobile:id/surname")).sendKeys(surname);

        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public void checkNameLast(String text) {
        $(By.id("tv.motorsport.mobile:id/item_name")).$(By.id("tv.motorsport.mobile:id/tv_description")).shouldHave(exactText(text));
    }

    public void clickUsername() {
        MobileElement usernameBlock = this.driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameButton = usernameBlock.findElementById("tv.motorsport.mobile:id/title");
        usernameButton.click();

    }

    public void clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
    }

    public void buyMonthlySub() {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text("MONTHLY")).click();
    }

    public void buyMonthlySubOutsideApp() {
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
    }

    public void checkSuccessBuy(String message) {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text(message));
        System.out.println("Успех");
    }

    public void inputUserName(String username) {
        $(By.id("tv.motorsport.mobile:id/username")).sendKeys(username);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public void checkUserName(String username) {
        MobileElement usernameBlock = this.driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameName = usernameBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        Assert.assertEquals(usernameName.getText(), username);
    }

    public void clickGender() {
        $(By.id("tv.motorsport.mobile:id/item_gender")).click();
    }

    public void chooseGender() {
        $(By.id("tv.motorsport.mobile:id/gender_chooser_male")).click();
    }

    public void checkGender(String gender) {
     $(By.id("tv.motorsport.mobile:id/item_gender")).$(By.id("tv.motorsport.mobile:id/tv_description")).
             shouldHave(exactText(gender)).waitUntil(exist,5000);
    }

    public void changePassword() {
        $(By.id("tv.motorsport.mobile:id/item_password")).click();
    }

    public void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public void changePasswordSuite(String oldPassword, String newPassword) {
        changePassword();
        enterOldNewPass(oldPassword, newPassword);
        checkSubscribeAtProfile();
    }

    public void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    public void pressBackButton() {
        $(By.className("android.widget.ImageButton")).click();
    }

    public void pressSearchButton() {
        $(By.id("tv.motorsport.mobile:id/action_search")).click();
    }

    public void searchPPVEpisode() {
        $(By.id("tv.motorsport.mobile:id/search_src_text")).sendKeys(UserFixture.NAME_OF_PPV_EPISODE.getValue());
        this.driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    public void pressSignOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
    }

    public void unloginVerification() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).shouldBe(visible);
    }

    public void scrollToBottom() {
        new TouchAction(this.driver).press(PointOption.point(550, 1400))
                .waitAction().moveTo(PointOption.point(550, 605)).release().perform();
    }

    public void tapOnEpisodePPV() {
        $(By.id("tv.motorsport.mobile:id/title")).shouldHave(text(UserFixture.NAME_OF_PPV_EPISODE.getValue())).click();
    }

    public void buyPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionBuy")).click();
    }

    public void rentPPV() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionRent")).click();
    }

    public void buyPPVGoogle() {
        $(By.className("android.widget.Button")).$(By.id("com.android.vending:id/0_resource_name_obfuscated")).waitUntil(exist,5000).click();
    }

    public void checkThatAtPPVPage() {
        $(By.id("tv.motorsport.mobile:id/purchaseOptionSubscribe")).waitUntil(exist, 15000);
        System.out.println("You're in PPV Episode Page");
    }

    public void checkPurchasePPVView() {
        $(By.id("tv.motorsport.mobile:id/accessGrantedTitle")).waitUntil(exist,20000);
    }

    public void checkPPVCodeInfo() {
        $(By.id("tv.motorsport.mobile:id/activationDescription"))
                .shouldHave(text("You already have a code. You can activate it and start watching the content."))
                .waitUntil(exist,5000);
    }

    public void clickPPVActivationButton() {
        $(By.id("tv.motorsport.mobile:id/activationBtn")).click();
    }

    public void myFeedTabClick() {
        $(By.id("tv.motorsport.mobile:id/my_feed_dest")).click();
    }

    public void signInButtonClickFromFeedTab() {
        $(By.id("tv.motorsport.mobile:id/btn_action_simple")).click();
    }

    public void goToEpisodeUSregion() {
        $(By.className("android.view.ViewGroup")).$(By.id("tv.motorsport.mobile:id/title"))
                .shouldHave(text("6 Hours of Silverstone"))
                .waitUntil(exist, 5000)
                .click();
    }
    public void goToInfoInEpisodePage() {
        $(By.className("androidx.appcompat.app.ActionBar$Tab")).$(By.linkText("Info")).waitUntil(exist,5000).click();
    }
    public void addToMyFeedButtonClick() {
        $(By.id("tv.motorsport.mobile:id/follow")).click();
    }
    public void signInButtonFromRegistrationClick() {
        $(By.id("tv.motorsport.mobile:id/reg_tv_have_account")).click();
    }
    public void backAndroidButtonPressFourTimes() {
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }

}




