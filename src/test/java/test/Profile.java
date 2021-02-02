package test;

import models.fixture.LocalizedStringStorage;
import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AndroidProfile;
import pages.DbUtils.DbUtils;
import pages.LocaleInfo;
import pages.TestAPI;
import setUp.SetupConfig;

public class Profile {
    SetupConfig setupConfig = new SetupConfig();
    AndroidProfile androidProfile = new AndroidProfile(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(androidProfile.driver).getInfo());
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();

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
            androidProfile.inputUserName(UserFixture.USERNAME_FOR_AUTO_TEST.getValue());

            androidProfile.checkUserName(UserFixture.USERNAME_FOR_AUTO_TEST.getValue());
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
            androidProfile.checkGender(stringStorage.getMale_gender());
            testAPI.postClearToOtherGender();
        }

    @Test
        public void changePassword() {
            androidProfile.openProfile();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_CHANGE_PASS_TEST.getValue(), UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.checkSubscribeAtProfile();

            androidProfile.changePasswordSuite(UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue());

            androidProfile.checkSubscribeAtProfile();

            androidProfile.scrollToBottom();
            androidProfile.signOut();
            androidProfile.openSignIn();
            androidProfile.inputLogPass(UserFixture.EMAIL_FOR_CHANGE_PASS_TEST.getValue(),UserFixture.PASSWORD_FOR_API_TEST.getValue());
            androidProfile.openSignIn();
            androidProfile.changePasswordSuite(UserFixture.PASSWORD_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue());
        }

    @AfterMethod
        public void resetApp () {
        setupConfig.driver.resetApp();
    }
    @AfterClass
        public void tearDown () {
        setupConfig.driver.quit();
    }
}
