package tests;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import setUp.SetupConfig;

import java.net.MalformedURLException;

public class SignIn {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);

    public SignIn() throws MalformedURLException {
    }


    @BeforeClass
    public void setUp () throws Exception {
        setupConfig.openAppiumSession();
    }
    @Test
    public void signInSignOut() {
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
