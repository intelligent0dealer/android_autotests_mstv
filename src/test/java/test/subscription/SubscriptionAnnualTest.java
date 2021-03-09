package test.subscription;

import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import api.TestAPI;
import setUp.SetupConfig;

public class SubscriptionAnnualTest {
    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
    public void buySubscriptionFromProfileAnnual() {
        homePage.performLoginProcess()
                .clickSubscribe()
                .confirmBuyAnnualSubProcess(stringStorage.getAnnualNameOfPlan(), stringStorage.getGooglepayMessage(), stringStorage.getSuccess_message())
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();

        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserConstants.EMAIL_FOR_API_TEST);
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
