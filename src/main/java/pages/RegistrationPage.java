package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends PageObject {
    public RegistrationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

}
