package test;

import api.TestAPI;
import org.testng.annotations.Test;
import pages.DbUtils.DbUtils;
import pages.TabsOfMainPage.HomePage;
import setUp.SetupConfig;

public class LivestreamsTest {

    SetupConfig setupConfig = new SetupConfig();
    HomePage homePage = new HomePage(setupConfig.driver);
    TestAPI testAPI = new TestAPI();

    @Test
    public void livestreamDates() {
        /*
        Open live tab
        Check that livestream is visible
        Make request in Episode API
        Parse Info of Livestream
        Compare dates and status between api and app
         */
    }
}
