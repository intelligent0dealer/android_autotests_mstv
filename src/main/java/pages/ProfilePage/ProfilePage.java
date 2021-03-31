package pages.ProfilePage;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.ChangeUserData.ChangeNamePage;
import pages.ChangeUserData.ChangeUserNamePage;
import pages.PageObject;
import pages.SignInPage;
import pages.SignUpPage;
import pages.SubscriptionPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public  class ProfilePage extends PageObject {
    public ProfilePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public ProfilePage signOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
        return this;
    }

    public SubscriptionPage clickSubscribe() {
        $(By.id("tv.motorsport.mobile:id/item_subscription")).click();
        return new SubscriptionPage(driver);
    }

    public ProfilePage checkProfilePageHasLoaded() {
        $(By.id("tv.motorsport.mobile:id/item_subscription"))
                .$(By.id("tv.motorsport.mobile:id/tv_description")).shouldBe(exist);
        System.out.println("You're in user profile");
        return this;
    }

    public ChangeNamePage goToName() {
        $(By.id("tv.motorsport.mobile:id/item_name")).click();
        return new ChangeNamePage(driver);
    }

    public ProfilePage checkNameLast(String text) {
        $(By.id("tv.motorsport.mobile:id/item_name")).$(By.id("tv.motorsport.mobile:id/tv_description"))
                .shouldHave(exactText(text),Duration.ofSeconds(5));
        return this;
    }

    public ChangeUserNamePage clickUsername() {
        $(By.id("tv.motorsport.mobile:id/item_username")).$(By.id(("tv.motorsport.mobile:id/title"))).click();
        return new ChangeUserNamePage(driver);
    }

    public void checkUserName(String username) {
        SelenideElement usernameName = $(By.id("tv.motorsport.mobile:id/item_username")).$(By.id(("tv.motorsport.mobile:id/tv_description")));
        Assert.assertEquals(usernameName.getText(), username);
    }

    public ProfilePage clickGender() {
        $(By.id("tv.motorsport.mobile:id/item_gender")).click();
        return this;
    }

    public ProfilePage chooseGender() {
        $(By.id("tv.motorsport.mobile:id/gender_chooser_male")).click();
        return this;
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
        return this;
    }

    private void enterOldNewPass(String currentpass, String newpass) {
        $(By.id("tv.motorsport.mobile:id/currentPassword")).sendKeys(currentpass);
        $(By.id("tv.motorsport.mobile:id/newPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/confirmPassword")).sendKeys(newpass);
        $(By.id("tv.motorsport.mobile:id/settings_menu_done")).shouldBe(exist,Duration.ofSeconds(10)).click();
    }

    public ProfilePage pressSignOut() {
        $(By.id("tv.motorsport.mobile:id/log_out")).click();
        return this;
    }

    public SignInPage openSignIn() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).click();
        return new SignInPage(driver);
    }

    public SignUpPage openSignUp() {
        $(By.id("tv.motorsport.mobile:id/sign_up")).click();
        return new SignUpPage(driver);
    }

    public void unloginVerification() {
        $(By.id("tv.motorsport.mobile:id/sign_in")).shouldBe(visible);
    }

    public ProfilePage checkSignIn(String text) {
        $(By.id("tv.motorsport.mobile:id/sign_in")).shouldHave(text(text));
        return this;
    }
    public ProfilePage checkSignUp(String text) {
        $(By.id("tv.motorsport.mobile:id/sign_up")).shouldHave(text(text));
        return this;
    }
    public ProfilePage checkThatElementOnPageAndTextInside(String text) {
        $$(By.className("android.widget.TextView")).findBy(text(text)).shouldBe(visible);
        return this;
    }





}
