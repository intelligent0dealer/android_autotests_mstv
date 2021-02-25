package pages;

import org.openqa.selenium.By;
import pages.ProfilePage.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    public SignInPage inputLogPass(String login, String password) {
        $(By.id("tv.motorsport.mobile:id/login_tiet_email")).sendKeys(login);
        $(By.id("tv.motorsport.mobile:id/login_tiet_password")).sendKeys(password);
        return new SignInPage();
    }

    public ProfilePage pressSignInButton() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).click();
        return new ProfilePage();
    }

    public SignUpPage clickSignUpNowFromSignInPage() {
        $(By.id("tv.motorsport.mobile:id/login_tv_register")).click();
        return new SignUpPage();
    }
}
