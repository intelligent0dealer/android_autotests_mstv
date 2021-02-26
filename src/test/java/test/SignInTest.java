package test;

import models.fixture.UserConstants;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EpisodeView.PayPerViewEpisodePage;
import pages.ProfilePage.ProfilePage;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;


public class SignInTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);


    @Test
       public void signInFromProfile() {
        ProfilePage profilePage = homePage.performLoginProcess();
                    profilePage.scrollToBottom();
                    profilePage.pressSignOut()
                        .unloginVerification();
        }

    @Test
        public void signInFromFeedTab() {
            homePage.myFeedTabClick()
                    .signInButtonClickFromFeedTab()
                    .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                    .pressSignInButton();
            homePage.openProfile()
                    .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromFeedButton() {
            homePage.goToEpisodeRUregion()
                    .goToInfoInEpisodePage()
                    .addToMyFeedButtonClick()
                    .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                    .pressSignInButton()
                    .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromPPVPageBuyButton() {
           PayPerViewEpisodePage payPerViewEpisodePage =
                   homePage.pressSearchButton()
                    .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                    .tapOnEpisodePPV()
                    .checkThatAtPPVPage();
           payPerViewEpisodePage.scrollToBottom();
           payPerViewEpisodePage.buyPPVByNotLoginUser()
                    .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                    .pressSignInButton();
           payPerViewEpisodePage.checkThatAtPPVPage()
                    .backAndroidButtonPressFourTimes()
                    .openProfile()
                    .checkProfilePageHasLoaded();
        }

    @Test
        public void signInFromPPVPageSubscribeAndWatchButton() {
           PayPerViewEpisodePage payPerViewEpisodePage =
                   homePage.pressSearchButton()
                    .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                    .tapOnEpisodePPV();

           payPerViewEpisodePage.checkThatAtPPVPage()
                    .subscribeAndWatchButtonPPV()
                    .alreadyHaveAnAccSignIn()
                    .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                    .pressSignInButton();

           payPerViewEpisodePage.checkThatAtPPVPage()
                    .backAndroidButtonPressFourTimes()
                    .openProfile()
                    .checkProfilePageHasLoaded();
        }


    @Test
        public void signInFromRegistrationPage() {
            homePage.openProfile()
                    .openSignUp()
                    .signInButtonFromRegistrationClick()
                    .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
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
