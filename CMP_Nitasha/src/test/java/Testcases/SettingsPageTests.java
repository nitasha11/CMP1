package Testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import utilities.TestUtil;
import pages.HomePage;
import pages.LoginPage;
import pages.SettingsPage;
import org.apache.log4j.Logger;
import Base.TestBase;

public class SettingsPageTests extends TestBase
{
    Logger log;
    SettingsPage settingsPage;
    
    public SettingsPageTests() {
        this.log = Logger.getLogger((Class)HomePageTests.class);
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        this.login = new LoginPage();
        this.homepage = new HomePage();
        this.settingsPage = new SettingsPage();
        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)"Logged In Successfully");
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.clickOnSettingsTab();
    }
    
    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (2 == result.getStatus()) {
            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
            Screenshot.screenshot(result.getName());
        }
        SettingsPageTests.driver.close();
        this.log.info((Object)"Browser Closed");
    }
    
    @Test
    public void checkLoadBalancerUrl() {
        final String loadBalancerUrl = this.settingsPage.getLoadBalancerUrl();
        Assert.assertEquals(loadBalancerUrl, TestUtil.LOAD_BALANCER_URL, "Load Balancer's url is not coming as expected");
        this.log.info((Object)"Proper Load Balancer's url is displaying");
    }
    
    @Test
    public void communityBoardLabelInSettingsTab() {
        this.settingsPage.clickOnExternalLinksTab();
        final String communityLabel = this.settingsPage.getCommunityBoardLabel();
        Assert.assertEquals(communityLabel, TestUtil.SAP_COMMUNITY_BOARD_LABEL, "Community Board label should come as 'SAP Digital Interconnect Community Board'");
        this.log.info((Object)"'Digital Interconnect' word is space seperated in 'SAP Digital Interconnect Community Board' title under External Tab of Settings Page.");
    }
}