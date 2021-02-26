package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.fixture.UserConstants;
import org.openqa.selenium.By;
import pages.EpisodeView.PayPerViewEpisodePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchRequestAndResultPage extends PageObject {


    public SearchRequestAndResultPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public PayPerViewEpisodePage tapOnEpisodePPV() {
            $(By.id("tv.motorsport.mobile:id/title")).shouldHave(text(UserConstants.NAME_OF_PPV_EPISODE)).click();
            return new PayPerViewEpisodePage(driver);
        }
    }


