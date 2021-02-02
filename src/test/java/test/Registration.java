package test;

import models.fixture.UserFixture;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;
import setUp.SetupConfig;


public class Registration {

    SetupConfig setupConfig = new  SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();

    @Test
    @Description("Full registration and login by created account")
        public void registrationFromProfilePage() {
            androidProfile.openProfile();
            androidProfile.openSignUp();
            androidProfile.inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.doneReg();
            androidProfile.pressExitButton();

            testAPI.confirmationRegisterNewUser();
            testAPI.verificationEmailConfirmed();

            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
    }

   @Test
    @Description("Duplicate registration method")
        public void registrationAgain() {
            androidProfile.openProfile();
            androidProfile.openSignUp();
            androidProfile.inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.doneReg();
            androidProfile.pressExitButton();
            testAPI.confirmationRegisterNewUser();
            testAPI.verificationEmailConfirmed();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
    }
    @Test
        public void registrationFromFeedTab() {
    }

    @Test
        public void registrationFromSignInPage() {

    }
    @Test
        public void registrationFromPremiumEpisode () {
    }
    @Test
        public void registrationFromPPVPage() {

    }
    @AfterMethod
    @Description("If u want >1 registration")
        public void deleteNewUser() {
            dbUtils.deleteUser(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue());
            setupConfig.driver.resetApp();
    }
    @AfterClass
        public  void tearDown() {
            setupConfig.driver.quit();
    }
}
