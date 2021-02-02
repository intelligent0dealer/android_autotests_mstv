package test.subscription;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;
import setUp.SetupConfig;


public class Subscription {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();


    @Test
        public void buySubscription() {
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();
        androidProfile.clickSubscribe();
        androidProfile.buyMonthlySub();
        androidProfile.buyMonthlySubOutsideApp();
        androidProfile.checkSuccessBuy("message"); // TODO EN + RU TEXTS
        androidProfile.clickContinueSubButton();
        androidProfile.checkSubscribeAtProfile();
        testAPI.getSubscriptionInfo();
        dbUtils.deleteSubscribe(UserFixture.EMAIL_FOR_API_TEST.getValue());
    }
    @AfterClass
        public void tearDown() {
            setupConfig.driver.quit();
    }
}
