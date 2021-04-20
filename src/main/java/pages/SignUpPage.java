package pages;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import api.TestAPI;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class SignUpPage extends PageObject {

    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public SignUpPage inputLogPassReg(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).sendKeys(password);
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).sendKeys(password);
        return this;
    }

    public SignInPage signInButtonFromRegistrationClick() {
        $(By.id("tv.motorsport.mobile:id/reg_tv_have_account")).click();
        return new SignInPage(driver);
    }

    public SubscriptionPage completeRegAndConfirmEmail(TestAPI testAPI) {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
        $(By.id("tv.motorsport.mobile:id/open_email_app_btn")).shouldBe(Condition.visible, Duration.ofSeconds(10));
        $(By.id("tv.motorsport.mobile:id/email_logout")).shouldBe(Condition.visible, Duration.ofSeconds(10));
        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();
        return new SubscriptionPage(driver);
    }
    public SignUpPage clickToCheckBox() {
        $(By.id("tv.motorsport.mobile:id/reg_cb_ok")).click();
        return this;
    }
    public SignUpPage checkDescription(String text) {
        $(By.id("tv.motorsport.mobile:id/reg_tv_ok_description")).shouldHave(Condition.text(text));
        return this;
    }
    public SignUpPage checkEmailField(String text) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).shouldHave(Condition.text(text));
        return this;
    }
    public SignUpPage checkPassField(String text) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).shouldHave(Condition.text(text));
        return this;
    }
    public SignUpPage checkConfirmPassField(String text) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).shouldHave(Condition.text(text));
        return this;
    }
    public SignUpPage checkContinueButton(String text) {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).shouldHave(Condition.text(text));
        return this;
    }

}
