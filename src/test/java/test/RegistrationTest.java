package test;

import models.fixture.UserConstants;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.ProfilePage.ProfilePage;
import pages.RegistrationPage;
import pages.TabsOfMainPage.HomePage;
import pages.TestAPI;
import setUp.SetupConfig;


public class RegistrationTest {

    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);


    @Test
    @Description("Full registration and login by created account")
    public void registrationFromProfilePage() {
        ProfilePage profilePage =
                homePage.performRegistrationProcess();

        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();

        profilePage.openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .checkProfilePageHasLoaded();
    }

    @Test
    @Description("Duplicate registration method")
    public void registrationAgain() {
        ProfilePage profilePage =
            homePage.performRegistrationProcess();

        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();

        profilePage.openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .checkProfilePageHasLoaded();
    }

    @Test
    public void registrationFromFeedTab() {
        ProfilePage profilePage =
        homePage.myFeedTabClick()
                .clickJoinNowFromFeedTab()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .doneReg()
                .pressExitButton();

        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();

        profilePage.openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .checkProfilePageHasLoaded();
    }

    @Test
    public void registrationFromSignInPage() {
        ProfilePage profilePage =
                homePage.openProfile()
                .openSignIn()
                .clickSignUpNowFromSignInPage()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .doneReg()
                .pressExitButton();
        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();

        profilePage.openSignIn()
                .inputLogPass(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButton()
                .checkProfilePageHasLoaded();
    }

    @Test
    @Description("Test not full. It's test like <premium video checker> ")
    public void registrationFromPremiumEpisode() {
        homePage.clickPremiumEpisodeRuAndCheck()
                .waitForAdsPreRollToFinish()
                .checkPremiumTiming();
    }

    @Test
    public void registrationFromPPVPage() {
        RegistrationPage registrationPage =
        homePage.pressSearchButton()
                .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                .tapOnEpisodePPV()
                .checkThatAtPPVPage()
                .clickSubscribeAndWatchFromPPVPage()
                .clickJoinAndWatch()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .doneReg();

        testAPI.postConfirmEmailForNewUser();
        testAPI.postCheckThatEmailConfirmed();

        registrationPage.chooseFreePlan()
                        .clickContinueSubButton()
                        .backAndroidButtonPressFourTimes()
                        .openProfile()
                        .checkProfilePageHasLoaded();
    }

    @AfterMethod
    @Description("If u want >1 registration")
    public void deleteNewUser() {
        dbUtils.deleteUser(UserConstants.EMAIL_FOR_REGISTRATION_TEST);
        setupConfig.driver.resetApp();
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
