package test.subscription;

import models.fixture.LocalizedStringStorage;
import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.CommonControls;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;


public class Subscription {
    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage();
    CommonControls commonControls = new CommonControls(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
        public void buySubscription() {
        homePage.performLoginProcess()
                .clickSubscribe()
                .buyMonthlySub();
        commonControls.buyMonthlySubOutsideApp()
                        .checkSuccessBuy(stringStorage.getSuccess_message())
                        .clickContinueSubButton()
                        .checkProfilePageHasLoaded();

        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserFixture.EMAIL_FOR_API_TEST.getValue());
    }
    @AfterClass
        public void tearDown() {
            setupConfig.driver.quit();
    }
}
