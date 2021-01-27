package setUp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SetupConfig {

    public AppiumDriver<MobileElement> driver;
    private final URL url;
    private final DesiredCapabilities cap;

    public SetupConfig() throws MalformedURLException {
        this.url = new URL("http://127.0.0.1:4723/wd/hub");

        this.cap = new DesiredCapabilities();
        this.cap.setCapability("deviceName", "Mi A2");
        this.cap.setCapability("udid", "1ef20832");
        this.cap.setCapability("platformName", "Android");
        this.cap.setCapability("platformVersion", "10");
        this.cap.setCapability("appPackage", "tv.motorsport.mobile");
        this.cap.setCapability("appActivity", "tv.motorsport.mobile.presentation.MainActivity");

        this.driver = new AppiumDriver<MobileElement>(this.url, this.cap);
    }

    public void openAppiumSession()  {
        WebDriverRunner.setWebDriver(this.driver);
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Configuration.screenshots = false;
        Configuration.timeout = 10000;
    }
}
