package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    public SignUpPage inputLogPassReg(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/register_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/register_tiet_password")).sendKeys(password);
        $(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password")).sendKeys(password);
        return new SignUpPage();
    }

    public SignInPage signInButtonFromRegistrationClick() {
        $(By.id("tv.motorsport.mobile:id/reg_tv_have_account")).click();
        return new SignInPage();
    }

    public RegistrationPage doneReg() {
        $(By.id("tv.motorsport.mobile:id/register_btn_done")).click();
        return new RegistrationPage();
    }
}
