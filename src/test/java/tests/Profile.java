package tests;

import models.fixture.UserFixture;
import org.testng.annotations.*;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.TestAPI;
import setUp.SetupConfig;

public class Profile {

    AndroidProfile androidProfile = new AndroidProfile();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    SetupConfig setupConfig = new SetupConfig();

    @BeforeClass
    public  void  setUp () throws Exception {
        setupConfig.openAppiumSession();
    }
    @Test
    public void changeName() {
        androidProfile.openProfile();
        androidProfile.openSignIn();
        androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
        androidProfile.openSignIn();
        androidProfile.checkSubscribeAtProfile();

        androidProfile.goToName();
        androidProfile.inputNameLastDone("Artem", "Morozov");
        androidProfile.checkNameLast("Artem Morozov");
        androidProfile.checkSubscribeAtProfile();
        testAPI.postClearNameSurname();

        androidProfile.clickUsername();
        androidProfile.inputUserName("intdealer");
        androidProfile.checkUserName("intdealer");
        testAPI.postClearNickname();
        androidProfile.checkSubscribeAtProfile();
    }

    @Test
        public void changeGender() {
            androidProfile.openProfile();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();
            androidProfile.clickGender();
            androidProfile.chooseGender();

            androidProfile.checkSubscribeAtProfile();
            androidProfile.checkGender("Male");
            testAPI.postClearToOtherGender();
    }

    @Test
        public void changePassword() {
            androidProfile.openProfile();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();

            androidProfile.changePassword(UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());

            androidProfile.checkSubscribeAtProfile();


            androidProfile.signOut();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_CHANGE_PASS_TEST.getValue(),UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.changePassword(UserFixture.PASSWORD_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue());
    }
}
