package test;

import api.TestAPI;
import fixture.LocaleInfo;
import fixture.LocalizedStringStorage;
import fixture.UserConstants;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;


public class RegistrationTest {

    SetupConfig setupConfig = new SetupConfig();
    TestAPI testAPI = new TestAPI();
    DbUtils dbUtils = new DbUtils();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());


    @Test(alwaysRun = true)
    @Description("Full registration and login by created account")
    public void registrationFromProfilePage() {
        homePage.performRegistrationProcess(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
    }

    @Test
    @Description("Duplicate registration method")
    public void registrationAgain() {
        homePage.performRegistrationProcess(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
    }

    @Test
    public void registrationFromFeedTab() {
        homePage.goToMyFeed()
                .clickJoinNowFromFeedTab()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .completeRegAndConfirmEmail(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromFeed()
                .checkTextInEmptyFeedPage(stringStorage.textFromEmptyFeedPage());
    }

    @Test
    public void registrationFromSignInPage() {
        homePage.openProfile()
                .openSignIn()
                .clickSignUpNowFromSignInPage()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .completeRegAndConfirmEmail(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
    }

    @Test
    public void registrationFromPremiumEpisode() {
        homePage.clickPremiumEpisodeRu()
                .checkPremiumVideoNameInside()
                .waitForAdsPreRollToFinish()
                .checkPremiumTiming()
                .getPromoPlugPageFromPremiumEpisode()
                .clickWatchMoreByNotLoginUser()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST,UserConstants.PASSWORD_FOR_API_TEST)
                .completeRegAndConfirmEmail(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromPremiumEpisode()
                .checkPremiumVideoNameInside()
                .pressAndroidBackButton()
                .openProfile()
                .checkProfilePageHasLoaded();
    }

    @Test
    public void registrationFromPPVPage() {
        homePage.pressSearchButton()
                .inputTextAndSearchByKeyButton(UserConstants.NAME_OF_PPV_EPISODE)
                .tapOnEpisodePPV()
                .checkThatAtPPVPage()
                .clickSubscribeAndWatchFromPPVPage()
                .clickJoinAndWatchByNotLoginUser()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .completeRegAndConfirmEmail(testAPI)
                .chooseFreePlan()
                .clickContinueSubButton()
                .backAndroidButtonPressFourTimes()
                .openProfile()
                .checkProfilePageHasLoaded();
    }
    @Test
    public void registrationWithActiveCheckBox() {
        homePage.openProfile()
                .openSignUp()
                .clickToCheckBox()
                .inputLogPassReg(UserConstants.EMAIL_FOR_REGISTRATION_TEST,UserConstants.PASSWORD_FOR_API_TEST)
                .completeRegAndConfirmEmail(testAPI)
                .chooseFreePlan()
                .clickContinueSubButtonFromProfile()
                .checkProfilePageHasLoaded();
    }

    @AfterMethod (alwaysRun = true)
    @Description("If u want >1 registration")
    public void deleteNewUser() {
        dbUtils.deleteUser(UserConstants.EMAIL_FOR_REGISTRATION_TEST);
        setupConfig.driver.resetApp();
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
        System.out.println("teardown");
    }
}
