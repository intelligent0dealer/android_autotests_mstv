package test;

import models.fixture.LocalizedStringStorage;
import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CommonControls;
import pages.LocaleInfo;
import pages.ProfilePage.ProfilePage;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;

public class Profile {
    SetupConfig setupConfig = new SetupConfig();
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());
    HomePage homePage = new HomePage();
    TestAPI testAPI = new TestAPI();
    ProfilePage profilePage = new ProfilePage();
    CommonControls commonControls = new CommonControls(setupConfig.driver);

    @Test
        public void changeNameAndNickName() {
            homePage.performLoginProcess()
                    .goToName()
                    .inputNameLastDone("Artem", "Morozov")
                    .checkNameLast("Artem Morozov")
                    .checkProfilePageHasLoaded()
                    .clickUsername()
                    .inputUserName(UserFixture.USERNAME_FOR_AUTO_TEST.getValue())
                    .checkUserName(UserFixture.USERNAME_FOR_AUTO_TEST.getValue());
            testAPI.postClearNameSurname();
            testAPI.postClearNickname();
        }

    @Test
        public void changeGender() {
            homePage.performLoginProcess()
                    .clickGender()
                    .chooseGender()
                    .checkProfilePageHasLoaded()
                    .checkGender(stringStorage.getMale_gender());

            testAPI.postClearToOtherGender();
        }

    @Test
        public void changePassword() {
            homePage.performLoginProcess()
                    .changePasswordSuite(UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .checkProfilePageHasLoaded();

            commonControls.scrollToBottom();

            profilePage.signOut()
                       .openSignIn()
                       .inputLogPass(UserFixture.EMAIL_FOR_CHANGE_PASS_TEST.getValue(),UserFixture.PASSWORD_FOR_API_TEST.getValue())
                       .pressSignInButton()
                       .changePasswordSuite(UserFixture.PASSWORD_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_CHANGE_PASS_TEST.getValue());
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
