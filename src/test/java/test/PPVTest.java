package test;

import api.TestAPI;
import fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.EpisodeView.PayPerViewEpisodePage;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;


public class PPVTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    DbUtils dbUtils = new DbUtils();
    TestAPI testAPI = new TestAPI();


    @Test
    public void buyPPV() {
        PayPerViewEpisodePage payPerViewEpisodePage =
                homePage.performLoginProcess()
                        .pressBackButton()
                        .pressSearchButton()
                        .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                        .tapOnEpisodePPV();

        payPerViewEpisodePage.checkThatAtPPVPage().scrollToBottom();
        payPerViewEpisodePage.buyPPV().buyPPVGoogle();
        payPerViewEpisodePage.checkPurchasePPVView();

        testAPI.checkPPVAccessPermanent(UserConstants.ID_OF_PPV_EPISODE_FOR_AUTOTESTS);
    }

    @Test
    public void rentPPV() {
        PayPerViewEpisodePage payPerViewEpisodePage =
                homePage.performLoginProcess()
                        .pressBackButton()
                        .pressSearchButton()
                        .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                        .tapOnEpisodePPV()
                        .checkThatAtPPVPage();

        payPerViewEpisodePage.scrollToBottom();
        payPerViewEpisodePage.rentPPV()
                .buyPPVGoogle();
        payPerViewEpisodePage.checkPPVCodeInfo()
                .clickPPVActivationButton();

        testAPI.checkPPVAccessRent(UserConstants.ID_OF_PPV_EPISODE_FOR_AUTOTESTS);
    }

    @AfterMethod
    public void deletePPVAccess() {
        dbUtils.deletePPVCodeAccess(UserConstants.EMAIL_FOR_API_TEST);
        setupConfig.driver.resetApp();
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
