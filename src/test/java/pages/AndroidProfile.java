package pages;

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
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void scrollPage() {
        new TouchAction(driver).press(PointOption.point(550, 640))
                .waitAction().moveTo(PointOption.point(550, 10)).release().perform();
    }

    public static void openProfile() {
        MobileElement profileButton = driver.findElement(By.id("tv.motorsport.mobile:id/action_settings"));
        profileButton.click();

    }

    public static void openSignIn() {
        MobileElement signInButton = driver.findElement(By.id("tv.motorsport.mobile:id/sign_in"));
        signInButton.click();
    }

    public static void openSignUp() {
        MobileElement signUpButton = driver.findElement(By.id("tv.motorsport.mobile:id/sign_up"));
        signUpButton.click();
    }

    public static void inputLogPass(String login, String password) {
        MobileElement loginField = driver.findElement(By.id("tv.motorsport.mobile:id/login_tiet_email"));
        MobileElement passField = driver.findElement(By.id("tv.motorsport.mobile:id/login_tiet_password"));
        loginField.click();
        loginField.sendKeys(login);
        passField.click();
        passField.sendKeys(password);
    }

    public static void signOut() {
        MobileElement signOutButton = driver.findElement(By.id("tv.motorsport.mobile:id/log_out"));
        signOutButton.click();
    }

    public static void inputLogPassReg(String login, String password) {
        MobileElement loginFieldReg = driver.findElement(By.id("tv.motorsport.mobile:id/register_tiet_email"));
        MobileElement passFieldReg = driver.findElement(By.id("tv.motorsport.mobile:id/register_tiet_password"));
        MobileElement confirm = driver.findElement(By.id("tv.motorsport.mobile:id/register_tiet_confirm_password"));
        loginFieldReg.click();
        loginFieldReg.sendKeys(login);
        passFieldReg.click();
        passFieldReg.sendKeys(password);
        confirm.click();
        confirm.sendKeys(password);
    }

    public static void doneReg() {
        MobileElement continueReg = driver.findElement(By.id("tv.motorsport.mobile:id/register_btn_done"));
        continueReg.click();
    }

    public static void atPageProfile(String element) {
        MobileElement subscriptionBlock =  driver.findElement(By.id("tv.motorsport.mobile:id/item_subscription"));
        MobileElement subscriptionName = subscriptionBlock.findElementById("tv.motorsport.mobile:id/title");

        String subscriptionTitle = subscriptionName.getText();
        String str = "Failure: на странице Profile ожидался заголовок %s, получен %s";
        Assert.assertTrue(String.format(str, element, subscriptionTitle), subscriptionTitle.equals(element));
    }
    public static void clickSubscribe() {
        MobileElement subscriptionBlock =  driver.findElement(By.id("tv.motorsport.mobile:id/item_subscription"));
        MobileElement subscriptionName = subscriptionBlock.findElementById("tv.motorsport.mobile:id/title");
        subscriptionName.click();

    }

    public static void checkSubscribeAtProfile() throws Exception {
        MobileElement subscriptionBlock =  driver.findElement(By.id("tv.motorsport.mobile:id/item_subscription"));
        MobileElement subscriptionDescription = subscriptionBlock.findElement(By.id("tv.motorsport.mobile:id/tv_description"));

        int subscriptionDataAvailableTimer = 0;

        while (subscriptionDescription == null && (subscriptionDataAvailableTimer < 10000)) {
            sleep(100);
            subscriptionDataAvailableTimer += 100;
            subscriptionDescription = subscriptionBlock.findElement(By.id("tv.motorsport.mobile:id/tv_description"));
        }

        if (subscriptionDescription != null) {
            System.out.println("You're in user profile");
        } else {
            throw new Exception("Unvisible");
        }
    }

    public static void goToName() {
        MobileElement nameButton = driver.findElement(By.id("tv.motorsport.mobile:id/item_name"));
        nameButton.click();
    }

    public static void inputNameLastDone(String name, String surname) {
        MobileElement nameField = driver.findElement(By.id("tv.motorsport.mobile:id/name"));
        MobileElement lastNameField = driver.findElement(By.id("tv.motorsport.mobile:id/surname"));
        MobileElement doneButton = driver.findElement(By.id("tv.motorsport.mobile:id/settings_menu_done"));
        nameField.click();
        nameField.sendKeys(name);
        lastNameField.click();
        lastNameField.sendKeys(surname);
        doneButton.click();
    }

    public static void checkNameLast(String text){
        MobileElement textLastNameBlock = driver.findElementById("tv.motorsport.mobile:id/item_name");
        MobileElement textLastName = textLastNameBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        Assert.assertTrue(textLastName.getText().equals(text));
    }

    public static void clickUsername() {
        MobileElement usernameBlock = driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameButton = usernameBlock.findElementById("tv.motorsport.mobile:id/title");
        usernameButton.click();

    } public static void clickContinueSubButton() {
        MobileElement continueButton = driver.findElementById("tv.motorsport.mobile:id/continue_btn");
        continueButton.click();
    }
    public static void buyMonthlySub() {
        MobileElement monthlySubscriptionBlock = driver.findElementById("tv.motorsport.mobile:id/subscription_scv_plan_monthly");
        MobileElement monthlySubscriptionButton = monthlySubscriptionBlock.findElementById("tv.motorsport.mobile:id/subscription_tv_name");
        MobileElement subscribeButton = driver.findElementById("com.android.vending:id/0_resource_name_obfuscated");
        monthlySubscriptionButton.click();
        subscribeButton.click();
    }
    public static void checkSuccessBuy() {
        MobileElement successText = driver.findElementById("tv.motorsport.mobile:id/description_tv");
        Assert.assertTrue("Вы успешно совершили покупку.",true);
    }

    public static void inputUserName(String username) {
        MobileElement usernameField = driver.findElementById("tv.motorsport.mobile:id/username");
        MobileElement doneButton = driver.findElement(By.id("tv.motorsport.mobile:id/settings_menu_done"));
        usernameField.click();
        usernameField.sendKeys(username);
        doneButton.click();
    }

    public static void checkUserName(String username){
        MobileElement usernameBlock= driver.findElementById("tv.motorsport.mobile:id/item_username");
        MobileElement usernameName = usernameBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        Assert.assertTrue(usernameName.getText().equals(username));
    }

    public static void clickGender() {
        MobileElement genderButton = driver.findElementById("tv.motorsport.mobile:id/item_gender");
        genderButton.click();
    }

    public static void chooseGender(){
        MobileElement femaleButton = driver.findElementById("tv.motorsport.mobile:id/gender_chooser_male");
        femaleButton.click();
    }

    public static void checkGender (String gender){
        MobileElement genderBlock = driver.findElementById("tv.motorsport.mobile:id/item_gender");
        MobileElement genderName = genderBlock.findElementById("tv.motorsport.mobile:id/tv_description");
        System.out.println(genderName.getText());
        Assert.assertTrue(genderName.getText().equals(gender));
    }

    public static void changePassword () {
        MobileElement changePasswordButton = driver.findElementById("tv.motorsport.mobile:id/item_password");
        changePasswordButton.click();
    }

    public static void enterOldNewPass(String currentpass, String newpass) {
        MobileElement currentField = driver.findElementById("tv.motorsport.mobile:id/currentPassword");
        MobileElement newPassField = driver.findElementById("tv.motorsport.mobile:id/newPassword");
        MobileElement confirmPassField = driver.findElementById("tv.motorsport.mobile:id/confirmPassword");
        currentField.click();
        currentField.sendKeys(currentpass);
        newPassField.click();
        newPassField.sendKeys(newpass);
        confirmPassField.click();
        confirmPassField.sendKeys(newpass);
        MobileElement doneButton = driver.findElement(By.id("tv.motorsport.mobile:id/settings_menu_done"));
        doneButton.click();
    }

    public static void changePassword(String oldPassword, String newPassword) {
        atPageProfile("Подписка");
        changePassword();
        enterOldNewPass(oldPassword, newPassword);
        atPageProfile("Подписка");
    }

    public static void pressExitButton() {
        MobileElement crossButton = driver.findElementById("tv.motorsport.mobile:id/close");
        crossButton.click();

    }

}



