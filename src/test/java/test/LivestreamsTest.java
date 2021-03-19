package test;

import api.TestAPI;
import fixture.UserConstants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
                .inputTextAndSearchByKeyButton(UserConstants.PROGRAM_FOR_LIVESTREAM_TESTS)
                .tapOnProgramWithLivestream()
                .checkTitleOfProgram();
        String statusApi = programViewPage.parseDateToOneFormat(testAPI.getInfoAboutLivestreamEpisode(UserConstants.ID_OF_FUTURE_LIVESTREAM));
        String statusApp = programViewPage.checkAndGetStatusOfFutureLivestream();
        Assert.assertEquals(statusApp, statusApi);
    }
    @Test
    public void livestreamNow() {
        String status =
        homePage.openLiveTab()
                .checkNameOfLive()
                .getStatusOfLive();
        testAPI.getCheckThatLivestreamIsLiveNow(UserConstants.ID_OF_PRESENT_LIVESTREAM);
        Assert.assertEquals(status, "Live Now");
    }
    @AfterMethod
    public void refresh() {
        setupConfig.driver.resetApp();
    }

    @AfterClass
    public void tearDown() {
        setupConfig.driver.quit();
    }
}
