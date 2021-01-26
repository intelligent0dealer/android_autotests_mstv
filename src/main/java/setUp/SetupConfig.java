package setUp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetupConfig {
    public AppiumDriver<MobileElement> driver;
    public SetupConfig() {}

    public void openAppiumSession() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Mi A2");
        cap.setCapability("udid", "1ef20832");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "10");

        cap.setCapability("appPackage", "tv.motorsport.mobile");
        cap.setCapability("appActivity", "tv.motorsport.mobile.presentation.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        this.driver = new AppiumDriver<MobileElement>(url, cap);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Configuration.screenshots = false;
        Configuration.timeout = 10000;
    }
}