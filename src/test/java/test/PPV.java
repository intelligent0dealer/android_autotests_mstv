package test;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;
import setUp.SetupConfig;


public class PPV {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);
    DbUtils dbUtils = new DbUtils();
    TestAPI testAPI = new TestAPI();

    @Test
    public void buyPPV(){
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();
        androidProfile.pressBackButton();
        androidProfile.pressSearchButton();
        androidProfile.searchPPVEpisode();
        androidProfile.tapOnEpisodePPV();
        androidProfile.checkThatAtPPVPage();
        androidProfile.scrollToBottom();
        androidProfile.buyPPV();
        androidProfile.buyPPVGoogle();
        androidProfile.checkPurchasePPVView();
        testAPI.checkPPVAccessPermanent();
    }
    @Test
        public void rentPPV() {
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();
        androidProfile.pressBackButton();
        androidProfile.pressSearchButton();
        androidProfile.searchPPVEpisode();
        androidProfile.tapOnEpisodePPV();
        androidProfile.checkThatAtPPVPage();
        androidProfile.scrollToBottom();
        androidProfile.rentPPV();
        androidProfile.buyPPVGoogle();
        androidProfile.checkPPVCodeInfo();
        androidProfile.clickPPVActivationButton();
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
