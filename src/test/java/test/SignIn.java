package test;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import setUp.SetupConfig;


public class SignIn {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);

    @Test
        public void signInFromProfile() {
            androidProfile.openProfile();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
            androidProfile.scrollToBottom();
            androidProfile.pressSignOut();
            androidProfile.unloginVerification();
        }

    @Test
        public void signInFromFeedTab() {
            androidProfile.myFeedTabClick();
            androidProfile.signInButtonClickFromFeedTab();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.openProfile();
            androidProfile.checkSubscribeAtProfile();
        }

    @Test
        public void signInFromFeedButton() {
            androidProfile.goToEpisodeUSregion();
            androidProfile.goToInfoInEpisodePage();
            androidProfile.addToMyFeedButtonClick();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
        }

    @Test
        public void signInFromPPVPage() {
            androidProfile.pressSearchButton();
            androidProfile.searchPPVEpisode();
            androidProfile.tapOnEpisodePPV();
            androidProfile.checkThatAtPPVPage();
            androidProfile.scrollToBottom();
            androidProfile.buyPPV();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkThatAtPPVPage();

            androidProfile.backAndroidButtonPressFourTimes();

            androidProfile.openProfile();
            androidProfile.checkSubscribeAtProfile();
        }

    @Test
        public void signInFromRegistrationPage() {
            androidProfile.openProfile();
            androidProfile.openSignUp();
            androidProfile.signInButtonFromRegistrationClick();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
    }

    @AfterMethod
        public void resetApp() {
        setupConfig.driver.resetApp();
    }
    @AfterClass
        public void tearDown() {
        setupConfig.driver.quit();
    }
}
