package tests;

import models.fixture.UserFixture;
import org.testng.annotations.*;
import pages.AndroidProfile;
import setUp.SetupConfig;

import java.net.MalformedURLException;

public class PPV {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);


    public PPV() throws MalformedURLException {
    }

    @BeforeClass
    public  void setUp() {
        setupConfig.openAppiumSession();
    }


    @Test
    public void buyPPV() {
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();

        androidProfile.pressBackButton();
        androidProfile.pressSearchButton();
        androidProfile.searchPPVEpisode();
        // buy
        // check
        // clear
    }
}
