package test;

import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LocaleInfo;
import pages.ProfilePage.ProfilePage;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;

public class ProfileTest {
    SetupConfig setupConfig = new SetupConfig();
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());
    HomePage homePage = new HomePage(setupConfig.driver);
    TestAPI testAPI = new TestAPI();

    @Test
    public void changeNameAndNickName() {
        homePage.performLoginProcess()
                .goToName()
                .inputNameLastDone("Artem", "Morozov")
                .checkNameLast("Artem Morozov")
                .checkProfilePageHasLoaded()
                .clickUsername()
                .inputUserName(UserConstants.USERNAME_FOR_AUTO_TEST)
                .checkUserName(UserConstants.USERNAME_FOR_AUTO_TEST);
    }

    @Test
    public void changeGender() {
        homePage.performLoginProcess()
                .clickGender()
                .chooseGender()
                .checkProfilePageHasLoaded()
                .checkGender(stringStorage.getMale_gender());
    }

    @Test
    public void changePassword() {
        ProfilePage profilePage =
                homePage.performLoginProcess()
                        .changePasswordSuite(UserConstants.PASSWORD_FOR_CHANGE_PASS_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                        .checkProfilePageHasLoaded();

        profilePage.scrollToBottom();
        profilePage.signOut()
                .openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_CHANGE_PASS_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .changePasswordSuite(UserConstants.PASSWORD_FOR_API_TEST, UserConstants.PASSWORD_FOR_CHANGE_PASS_TEST);
    }

    @AfterMethod
        public void resetApp () {
        setupConfig.driver.resetApp();
    }
    @AfterClass
        public void tearDown () {

        testAPI.postClearNameSurname();
        testAPI.postClearNickname();
        testAPI.postClearToOtherGender();

        setupConfig.driver.quit();
    }
}
