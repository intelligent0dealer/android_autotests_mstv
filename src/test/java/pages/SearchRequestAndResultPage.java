package pages;

import models.fixture.UserFixture;
import org.openqa.selenium.By;
import pages.EpisodeView.PayPerViewEpisodePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchRequestAndResultPage {

        public PayPerViewEpisodePage tapOnEpisodePPV() {
            $(By.id("tv.motorsport.mobile:id/title")).shouldHave(text(UserFixture.NAME_OF_PPV_EPISODE.getValue())).click();
            return new PayPerViewEpisodePage();
        }
    }


