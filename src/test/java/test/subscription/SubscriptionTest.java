package test.subscription;

import models.fixture.LocalizedStringStorage;
import models.fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;


public class SubscriptionTest {
    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
        public void buySubscription() {
        homePage.performLoginProcess()
                .clickSubscribe()
                .buyMonthlySub()
                .buyMonthlySubOutsideApp()
                .checkSuccessBuy(stringStorage.getSuccess_message())
                .clickContinueSubButton()
                .checkProfilePageHasLoaded();

        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserConstants.EMAIL_FOR_API_TEST);
    }
    @AfterClass
        public void tearDown() {
            setupConfig.driver.quit();
    }
}
