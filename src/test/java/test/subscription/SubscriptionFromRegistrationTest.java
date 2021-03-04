package test.subscription;

import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;

public class SubscriptionFromRegistrationTest {
    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
    public void BuySubscriptionFromRegistration() {
        homePage.performRegistrationProcess(testAPI)
                .buyMonthlySub()
                .buySubByGoogle()
                .checkSuccessBuy(stringStorage.getSuccess_message())
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserConstants.EMAIL_FOR_API_TEST);
    }
}
