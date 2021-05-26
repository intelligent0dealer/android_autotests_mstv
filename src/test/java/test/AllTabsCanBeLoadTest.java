package test;

import org.testng.annotations.Test;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;

public class AllTabsCanBeLoadTest {
    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);

    @Test
    public void AllTabsShouldBeLoaded() {
        homePage.checkThatElementsLoad()// HomeTab проверка
                .openLiveTab()
                .checkThatElementsLoad()// LiveTab проверка
                .openRacingSeriesTab()
                .checkThatElementsLoad() // Racing series проверка
                .openProgramTab()
                .checkThatElementsLoad()// Program tab проверка (каналы и программы)
                .openFeedTab()
                .checkThatElementsLoad();// Feed tab проверка
    }
}
