package test;

import models.fixture.UserFixture;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.CommonControls;
import pages.EpisodeView.PayPerViewEpisodePage;
import pages.ProfilePage.ProfilePage;
import pages.SignInPage;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;


public class SignInTest {
    SetupConfig setupConfig = new SetupConfig();
    PayPerViewEpisodePage payPerViewEpisodePage = new PayPerViewEpisodePage();
    HomePage homePage = new HomePage();
    ProfilePage profilePage = new ProfilePage();
    SignInPage signInPage = new SignInPage();
    CommonControls commonControls = new CommonControls(setupConfig.driver);

    @Test
       public void signInFromProfile() {
            homePage.performLoginProcess();
            commonControls.scrollToBottom();
            profilePage.pressSignOut()
                        .unloginVerification();
        }

    @Test
        public void signInFromFeedTab() {
            homePage.myFeedTabClick()
                    .signInButtonClickFromFeedTab()
                    .inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .pressSignInButton();
            homePage.openProfile()
                    .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromFeedButton() {
            homePage.goToEpisodeRUregion()
                    .goToInfoInEpisodePage()
                    .addToMyFeedButtonClick();
            signInPage.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                        .pressSignInButton()
                        .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromPPVPage() {
            homePage.pressSearchButton();
            commonControls.inputTextAndSearchByKeyButton(UserFixture.NAME_OF_PPV_EPISODE.getValue())
                    .tapOnEpisodePPV()
                    .checkThatAtPPVPage();
            commonControls.scrollToBottom();
            payPerViewEpisodePage.buyPPV();
            signInPage.inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .pressSignInButton();
            payPerViewEpisodePage.checkThatAtPPVPage();
            commonControls.backAndroidButtonPressFourTimes()
                    .openProfile()
                    .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromRegistrationPage() {
            homePage.openProfile()
                    .openSignUp()
                    .signInButtonFromRegistrationClick()
                    .inputLogPass(UserFixture.EMAIL_FOR_API_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .pressSignInButton()
                    .checkProfilePageHasLoaded();
    }

    @AfterMethod
        public void resetApp() {
        setupConfig.driver.resetApp();
    }
    @AfterClass
        public void tearDown() {
        setupConfig.driver.quit();
    }
}
