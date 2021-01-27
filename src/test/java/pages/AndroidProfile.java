package pages;

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
import static com.codeborne.selenide.Selenide.$$;

public class AndroidProfile {

    public AppiumDriver<MobileElement> driver;
    public AndroidProfile(AppiumDriver<MobileElement> driver) {
        this.driver=driver;
    }

    public void openProfile() {
        $(By.id("tv.motorsport.mobile:id/action_settings")).click();
    }

    public  void openSignIn() {
       $(By.id("tv.motorsport.mobile:id/sign_in")).click();
    }

    public  void openSignUp() {
        $(By.id("tv.motorsport.mobile:id/sign_up")).click();
    }

    public  void inputLogPass(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/login_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/login_tiet_password")).sendKeys(password);
    }

    public void signOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
    }

    public  void inputLogPassReg(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).sendKeys(password);
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).sendKeys(password);
    }

    public  void doneReg() {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
    }

    public void clickSubscribe() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).click();
    }

    public void checkSubscribeAtProfile() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).$(By.id("tv.motorsport.mobile:id/tv_description")).waitUntil(exist,10000);
        System.out.println("You're in user profile");

    }

    public  void goToName() {
        $(By.id("tv.motorsport.mobile:id/item_name")).click();
    }

    public  void inputNameLastDone(String name, String surname) {

        $(By.id("tv.motorsport.mobile:id/name")).sendKeys(name);

        $(By.id("tv.motorsport.mobile:id/surname")).sendKeys(surname);

        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public  void checkNameLast(String text){
        $$(By.id("tv.motorsport.mobile:id/item_name")).findBy(id("tv.motorsport.mobile:id/tv_description")).shouldHave(exactText(text));
    }

    public void clickUsername() {
        MobileElement usernameBlock = this.driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameButton = usernameBlock.findElementById("tv.motorsport.mobile:id/title");
        usernameButton.click();

    }

    public  void clickContinueSubButton() {
        $(By.id("tv.motorsport.mobile:id/continue_btn")).click();
    }

    public  void buyMonthlySub() {
        $(By.id("tv.motorsport.mobile:id/subscription_tv_name")).shouldHave(text("MONTHLY")).click();
    }

    public  void buyMonthlySubOutsideApp() {
        $(By.className("android.widget.Button")).shouldHave(text("Subscribe")).click();
    }

    public  void checkSuccessBuy() {
        $(By.id("tv.motorsport.mobile:id/description_tv")).shouldHave(text("Вы успешно совершили покупку."));
        System.out.println("Успех");
    }

    public  void inputUserName(String username) {
        $(By.id("tv.motorsport.mobile:id/username")).sendKeys(username);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done"));
    }

    public  void checkUserName(String username){
        MobileElement usernameBlock= this.driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameName = usernameBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        Assert.assertEquals(usernameName.getText(), username);
    }

    public void clickGender() {
        $(By.id("tv.motorsport.mobile:id/item_gender")).click();
    }

    public  void chooseGender(){
        $(By.id("tv.motorsport.mobile:id/gender_chooser_male"));
    }

    public  void checkGender (String gender){
        MobileElement genderBlock = this.driver.findElementById("tv.motorsport.mobile:id/item_gender");
        MobileElement genderName = genderBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        System.out.println(genderName.getText());
        Assert.assertTrue(genderName.getText().equals(gender));
    }

    public  void changePassword () {
        $(By.id("tv.motorsport.mobile:id/item_password"));
    }

    public  void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).click();
    }

    public  void changePassword(String oldPassword, String newPassword) {
        checkSubscribeAtProfile();
        changePassword();
        enterOldNewPass(oldPassword, newPassword);
        checkSubscribeAtProfile();
    }

    public  void pressExitButton() {
        $(By.id("tv.motorsport.mobile:id/close")).click();
    }

    public  void pressBackButton() {
       $(By.className("android.widget.ImageButton")).click();
    }

    public  void pressSearchButton(){
        $(By.id("tv.motorsport.mobile:id/action_search")).click();
    }

    public  void searchPPVEpisode(){
        $(By.id("tv.motorsport.mobile:id/search_src_text")).sendKeys(UserFixture.NAME_OF_PPV_EPISODE.getValue());
        this.driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    public void pressSignOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
    }

    public void unloginVerification(){
        $(By.id("tv.motorsport.mobile:id/sign_in")).shouldBe(visible);
    }

    public void scrollAtProfilePage() {
        new TouchAction(this.driver).press(PointOption.point(550, 640))
                .waitAction().moveTo(PointOption.point(550, 10)).release().perform();
    }
}



