package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class HomePage {

    public HomePage () {}


    AppiumDriver<MobileElement> driver;
    MobileElement profileButton = driver.findElement(By.id("tv.motorsport.mobile:id/action_settings"));

    public void openProfile() {
        profileButton.click();
    }
}



