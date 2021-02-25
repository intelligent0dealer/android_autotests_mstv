package test;

import models.fixture.UserFixture;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;
import pages.DbUtils.DbUtils;
import pages.ProfilePage.ProfilePage;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;


public class Registration {

    SetupConfig setupConfig = new  SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage();
    ProfilePage profilePage = new ProfilePage();
    PromoPlug promoPlug =  new PromoPlug();
    RegistrationPage registrationPage = new RegistrationPage();
    CommonControls commonControls = new CommonControls(setupConfig.driver);

    @Test
    @Description("Full registration and login by created account")
        public void registrationFromProfilePage() {
            homePage.performRegistrationProcess();

            testAPI.postConfirmEmailForNewUser();
            testAPI.postCheckThatEmailConfirmed();

            profilePage.openSignIn()
                        .inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                        .pressSignInButton()
                        .checkProfilePageHasLoaded();
    }

    @Test
    @Description("Duplicate registration method")
        public void registrationAgain() {
            homePage.performRegistrationProcess();

            testAPI.postConfirmEmailForNewUser();
            testAPI.postCheckThatEmailConfirmed();

            profilePage.openSignIn()
                    .inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .pressSignInButton()
                    .checkProfilePageHasLoaded();
    }

    @Test
        public void registrationFromFeedTab() {
            homePage.myFeedTabClick()
                    .clickJoinNowFromFeedTab()
                    .inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .doneReg()
                    .pressExitButton();

            testAPI.postConfirmEmailForNewUser();
            testAPI.postCheckThatEmailConfirmed();

            profilePage.openSignIn()
                        .inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                        .pressSignInButton()
                        .checkProfilePageHasLoaded();
    }

    @Test
        public void registrationFromSignInPage() {
            homePage.openProfile()
                    .openSignIn()
                    .clickSignUpNowFromSignInPage()
                    .inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                    .doneReg()
                    .pressExitButton();
            testAPI.postConfirmEmailForNewUser();
            testAPI.postCheckThatEmailConfirmed();

            profilePage.openSignIn()
                        .inputLogPass(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
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


            homePage.pressSearchButton();
            commonControls.inputTextAndSearchByKeyButton(UserFixture.NAME_OF_PPV_EPISODE.getValue())
                    .tapOnEpisodePPV()
                    .checkThatAtPPVPage()
                    .clickSubscribeAndWatchFromPPVPage();

            promoPlug.clickJoinAndWatch()
                        .inputLogPassReg(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue(), UserFixture.PASSWORD_FOR_API_TEST.getValue())
                        .doneReg();

            testAPI.postConfirmEmailForNewUser();
            testAPI.postCheckThatEmailConfirmed();

            registrationPage.chooseFreePlan()
                            .clickContinueSubButton();
            commonControls.backAndroidButtonPressFourTimes();
            homePage.openProfile()
                    .checkProfilePageHasLoaded();
        }
    @AfterMethod
    @Description("If u want >1 registration")
        public void deleteNewUser() {
            dbUtils.deleteUser(UserFixture.EMAIL_FOR_REGISTRATION_TEST.getValue());
            setupConfig.driver.resetApp();
        }
    @AfterClass
        public  void tearDown() {
            setupConfig.driver.quit();
    }
}
