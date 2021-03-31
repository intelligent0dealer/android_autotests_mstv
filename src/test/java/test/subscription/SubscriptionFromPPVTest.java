package test.subscription;

import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.EpisodeView.PayPerViewEpisodePage;
import fixture.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import api.TestAPI;
import setUp.SetupConfig;

public class SubscriptionFromPPVTest {
    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
    public void BuySubscriptionFromPPV() {
        PayPerViewEpisodePage payPerViewEpisodePage =
                homePage.performLoginProcess()
                        .pressBackButton()
                        .pressSearchButton()
                        .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                        .tapOnEpisodePPV()
                        .checkThatAtPPVPage();
        payPerViewEpisodePage.subscribeAndWatchButtonPPV()
                .clickWatchMore()
                .confirmBuyMonthlySubProcess(stringStorage.getMonthlyNameOfPlan(), stringStorage.getGooglepayMessage(), stringStorage.getSuccess_message())
                .clickContinueSubButton()
                .checkPPVCodeInfo();

        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserConstants.EMAIL_FOR_API_TEST);
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
