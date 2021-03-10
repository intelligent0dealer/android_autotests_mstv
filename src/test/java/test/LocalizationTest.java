package test;

import fixture.LocalizedStringStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;

public class LocalizationTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
    public void checkProfileByUnloginUser() {
        homePage.openProfile()
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfProfilePage())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfRegionButton())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfContactUsButton())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfLegalInfo())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfVersionView())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfSignUpButton())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfSignInButton());
    }
    @AfterMethod
    public void resetApp() {
        setupConfig.driver.resetApp();
    }
}
