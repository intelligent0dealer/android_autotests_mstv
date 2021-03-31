package fixture;

import com.google.common.collect.Lists;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.HashMap;
import java.util.Map;

public class LocaleInfo {
    public enum Locale {
        EN, RU
    }

    private Locale currentLocale = null;
    private final AppiumDriver<MobileElement> driver;

    public LocaleInfo(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public Locale getInfo() {
        if (currentLocale == null) {
            Map<String, Object> args = new HashMap<>();
            args.put("command", "getprop");
            args.put("args", Lists.newArrayList("persist.sys.locale"));
            String output = (String) driver.executeScript("mobile: shell", args);
            switch (output) {
                case "ru_RU": {
                    currentLocale = Locale.RU;
                    break;
                }
                default: {
                    currentLocale = Locale.EN;
                    break;
                }
            }
        }
        return currentLocale;
    }
}
