package tests;

import models.fixture.UserFixture;
import org.springframework.context.annotation.Description;
import org.testng.annotations.*;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;
import setUp.SetupConfig;

import java.net.MalformedURLException;


public class Registration {

    SetupConfig setupConfig = new  SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();

    public Registration() throws MalformedURLException {
    }


    @BeforeClass
    public  void  setUp () throws Exception {
        setupConfig.openAppiumSession();
    }
    @Test
    @Description("Full registration and login by created account")
        public void registration() {
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

 /*   @Test
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
*/
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
