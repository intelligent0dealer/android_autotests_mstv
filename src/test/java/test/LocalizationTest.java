package test;

import fixture.LocalizedStringStorage;
import org.springframework.context.annotation.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import fixture.LocaleInfo;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;

public class LocalizationTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    LocalizedStringStorage stringStorage = new LocalizedStringStorage(new LocaleInfo(setupConfig.driver).getInfo());

    @Test
    @Description("Sign Up page too")
    public void checkProfileByUnloginUser() {
        homePage.openProfile()
      /*          .checkThatElementOnPageAndTextInside(stringStorage.getNameOfProfilePage())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfRegionButton())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfContactUsButton())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfLegalInfo())
                .checkThatElementOnPageAndTextInside(stringStorage.getNameOfVersionView()) */
                .checkSignIn(stringStorage.getNameOfSignInButton())
                .checkSignUp(stringStorage.getNameOfSignUpButton())
                .openSignUp()
                .checkDescription(stringStorage.getRegOkDescription())
                .checkEmailField(stringStorage.mailField())
                .checkPassField(stringStorage.passField())
                .checkConfirmPassField(stringStorage.passRepeatField())
                .checkContinueButton(stringStorage.continueButton());
    }
    @AfterMethod
    public void resetApp() {
        setupConfig.driver.resetApp();
    }

}
