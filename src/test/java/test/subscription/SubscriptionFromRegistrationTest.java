package test.subscription;

import api.TestAPI;
import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
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
                .confirmBuyMonthlySubProcess(stringStorage.getMonthlyNameOfPlan(),
                        stringStorage.getGooglepayMessage(), stringStorage.getSuccess_message())
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserConstants.EMAIL_FOR_API_TEST);
    }
}
