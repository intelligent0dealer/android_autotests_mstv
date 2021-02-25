package pages.ProfilePage;

import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.ChangeUserData.ChangeNamePage;
import pages.ChangeUserData.ChangeUserNamePage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.SubscriptionPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public  class ProfilePage {
    public ProfilePage signOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
        return new ProfilePage();
    }

    public SubscriptionPage clickSubscribe() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).click();
        return new SubscriptionPage();
    }

    public ProfilePage checkProfilePageHasLoaded() {
        $(By.id("tv.motorsport.mobile:id/item_subscription"))
                .$(By.id("tv.motorsport.mobile:id/tv_description")).shouldBe(exist);
        System.out.println("You're in user profile");
        return new ProfilePage();
    }

    public ChangeNamePage goToName() {
        $(By.id("tv.motorsport.mobile:id/item_name")).click();
        return new ChangeNamePage();
    }

    public ProfilePage checkNameLast(String text) {
        $(By.id("tv.motorsport.mobile:id/item_name")).$(By.id("tv.motorsport.mobile:id/tv_description"))
                .shouldHave(exactText(text),Duration.ofSeconds(5));
        return new ProfilePage();
    }

    public ChangeUserNamePage clickUsername() {
        $(By.id("tv.motorsport.mobile:id/item_username")).$(By.id(("tv.motorsport.mobile:id/title"))).click();
        return new ChangeUserNamePage();
    }

    public void checkUserName(String username) {
        SelenideElement usernameName = $(By.id("tv.motorsport.mobile:id/item_username")).$(By.id(("tv.motorsport.mobile:id/tv_description")));
        Assert.assertEquals(usernameName.getText(), username);
    }

    public ProfilePage clickGender() {
        $(By.id("tv.motorsport.mobile:id/item_gender")).click();
        return new ProfilePage();
    }

    public ProfilePage chooseGender() {
        $(By.id("tv.motorsport.mobile:id/gender_chooser_male")).click();
        return new ProfilePage ();
    }

    public void checkGender(String gender) {
        $(By.id("tv.motorsport.mobile:id/item_gender")).$(By.id("tv.motorsport.mobile:id/tv_description"))
                .shouldHave(exactText(gender))
                .shouldBe(exist,Duration.ofSeconds(10));
    }

    private void changePassword() {
        $(By.id("tv.motorsport.mobile:id/item_password")).click();
    }

    public ProfilePage changePasswordSuite(String oldPassword, String newPassword) {
        changePassword();
        enterOldNewPass(oldPassword, newPassword);
        checkProfilePageHasLoaded();
        return new ProfilePage();
    }
    private void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).shouldBe(exist,Duration.ofSeconds(10)).click();
    }

    public ProfilePage pressSignOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
        return new ProfilePage();
    }

    public SignInPage openSignIn() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).click();
        return new SignInPage();
    }

    public SignUpPage openSignUp() {
        $(By.id("tv.motorsport.mobile:id/sign_up")).click();
        return new SignUpPage();

    }
    public void unloginVerification() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).shouldBe(visible);
    }



}
