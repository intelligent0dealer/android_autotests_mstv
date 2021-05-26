package pages.EpisodeView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pages.PageObject;
import pages.SignInPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EpisodePage extends PageObject {

    public EpisodePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public EpisodePage goToInfoInEpisodePage() {
        $$(By.className("androidx.appcompat.app.ActionBar$Tab")).last().click();
        return this;
    }

    public SignInPage addToMyFeedButtonClick() {
        $(By.id("tv.motorsport.mobile:id/follow")).click();
        return new SignInPage(driver);
    }
    public String getNameOfProgramInInfo() {
      String program = $(By.id("tv.motorsport.mobile:id/items_about"))
                .$(By.className("android.view.ViewGroup"))
                .$(By.id("tv.motorsport.mobile:id/title"))
                .getText();
      System.out.println(program);
      return program;
       // String[] units = program.split(": ");
       // return units[1];
    }

}
