package test;

import api.TestAPI;
import fixture.UserConstants;

import org.springframework.context.annotation.Description;
import org.testng.annotations.*;
import org.testng.*;
import pages.EpisodeView.EpisodePage;
import pages.TabsOfMainPage.HomePage;
import pages.TabsOfMainPage.MyFeedPage;
import setUp.SetupConfig;

public class AddToMyFeedTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    TestAPI testAPI = new TestAPI();

    @Test
    @Description("blocker crash")
    public void addProgramInFeedAndCheck() {

        EpisodePage episodePage =
        homePage.goToEpisodeRUregion()
                .goToInfoInEpisodePage();
        String nameOfProgramOnEpisodePage =episodePage.getNameOfProgramInInfo();

       MyFeedPage myFeedPage =
       episodePage.addToMyFeedButtonClick()
                .inputLogPass(UserConstants.EMAIL_FOR_API_TEST, UserConstants.PASSWORD_FOR_API_TEST)
                .pressSignInButtonAfterPressFeedButton()
                .pressAndroidBackButton().pressAndroidBackButton()
                .goToMyFeed();

        String nameOfProgramInFeed = myFeedPage.getNameOfProgramInFeed();
        Assert.assertEquals(nameOfProgramOnEpisodePage,nameOfProgramInFeed);
        String nameOfProgramInApi = testAPI.getFeed();
        Assert.assertEquals(nameOfProgramInApi,nameOfProgramInFeed);
    }
    @AfterClass
    public void tearDown() {
        setupConfig.driver.closeApp();
    }
}
