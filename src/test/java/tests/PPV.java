package tests;

import models.fixture.UserFixture;
import org.testng.annotations.*;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;

public class PPV {

    AndroidProfile androidProfile = new AndroidProfile();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();

    @BeforeClass
    public static void setUp() {
    }


    @Test
    public void buyPPV() throws Exception {
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
