package test;

import api.TestAPI;
import fixture.UserConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProgramViewPage;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;

public class LivestreamsTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    TestAPI testAPI = new TestAPI();

    @Test
    public void livestreamDates() {
        ProgramViewPage programViewPage =
        homePage.pressSearchButton()
                .inputTextAndSearchByKeyButton("Program for Autotests")
                .tapOnProgramWithLivestream()
                .checkTitleOfProgram();
        String statusApi = programViewPage.parseDateToOneFormat(testAPI.getInfoAboutLivestreamEpisode(UserConstants.ID_OF_FUTURE_LIVESTREAM));
        String statusApp = programViewPage.checkAndGetStatusOfFutureLivestream();
        Assert.assertEquals(statusApp, statusApi);
    }
}
