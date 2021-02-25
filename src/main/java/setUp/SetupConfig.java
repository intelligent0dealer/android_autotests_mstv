package setUp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.google.common.util.concurrent.UncheckedExecutionException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SetupConfig {

    public AppiumDriver<MobileElement> driver;

    public SetupConfig()  {
        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to connect to remote device");
        }

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Mi A2");
        cap.setCapability("udid", "1ef20832");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "10");
        cap.setCapability("appPackage", "tv.motorsport.mobile");
        cap.setCapability("appActivity", "tv.motorsport.mobile.presentation.MainActivity");

        this.driver = new AppiumDriver<MobileElement>(url, cap);

        openAppiumSession();
    }

    private void openAppiumSession()  {
        WebDriverRunner.setWebDriver(this.driver);
        this.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        Configuration.screenshots = false;
        Configuration.timeout = 30_000;
    }
}
