package test;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CommonControls;
import pages.DbUtils.DbUtils;
import pages.EpisodeView.PayPerViewEpisodePage;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;


public class PPV {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage();
    DbUtils dbUtils = new DbUtils();
    TestAPI testAPI = new TestAPI();
    CommonControls commonControls = new CommonControls(setupConfig.driver);
    PayPerViewEpisodePage payPerViewEpisodePage = new PayPerViewEpisodePage();



    @Test
    public void buyPPV(){

        homePage.performLoginProcess();

        commonControls.pressBackButton();

        homePage.pressSearchButton();
        commonControls.inputTextAndSearchByKeyButton(UserFixture.NAME_OF_PPV_EPISODE.getValue())
                .tapOnEpisodePPV()
                .checkThatAtPPVPage();

        commonControls.scrollToBottom();
        payPerViewEpisodePage.buyPPV();
        commonControls.buyPPVGoogle();

        payPerViewEpisodePage.checkPurchasePPVView();
        testAPI.checkPPVAccessPermanent();
    }
    @Test
        public void rentPPV() {
        homePage.performLoginProcess();

        commonControls.pressBackButton();
        homePage.pressSearchButton();
        commonControls.inputTextAndSearchByKeyButton(UserFixture.NAME_OF_PPV_EPISODE.getValue())
                .tapOnEpisodePPV()
                .checkThatAtPPVPage();

        commonControls.scrollToBottom();
        payPerViewEpisodePage.rentPPV();
        commonControls.buyPPVGoogle();

        payPerViewEpisodePage.checkPPVCodeInfo();

        payPerViewEpisodePage.clickPPVActivationButton();

        testAPI.checkPPVAccessRent();
    }
    @AfterMethod
    public void deletePPVAccess() {
        dbUtils.deletePPVCodeAccess(UserFixture.EMAIL_FOR_API_TEST.getValue());
        setupConfig.driver.resetApp();
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
