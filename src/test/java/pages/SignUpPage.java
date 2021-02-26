package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
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

    public RegistrationPage doneReg() {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
        return new RegistrationPage(driver);
    }
}
