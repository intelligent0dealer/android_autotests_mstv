package tests;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import setUp.SetupConfig;

public class SignIn {
    AndroidProfile androidProfile = new AndroidProfile();
    SetupConfig setupConfig = new SetupConfig();

    @BeforeClass
    public void setUp () throws Exception {
        setupConfig.openAppiumSession();
    }
    @Test
    public void buySubscription() {
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();
        androidProfile.scrollAtProfilePage();
        androidProfile.pressSignOut();
        androidProfile.unloginVerification();
    }
    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
