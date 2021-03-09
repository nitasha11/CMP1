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
import pages.ExternalLinksPage;
import org.apache.log4j.Logger;
import Base.TestBase;

public class ExternalLinksTest extends TestBase
{
    Logger log;
    ExternalLinksPage externalLinks;
    
    public ExternalLinksTest() {
        this.log = Logger.getLogger((Class)AddUserTests.class);
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        this.login = new LoginPage();
        this.homepage = new HomePage();
        this.externalLinks = new ExternalLinksPage();
        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.clickOnExternalTabLink();
    }
    
    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (2 == result.getStatus()) {
            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
            Screenshot.screenshot(result.getName());
        }
        ExternalLinksTest.driver.close();
        this.log.info((Object)"Browser Closed");
    }
    
    @Test
    public void verifyCommunityBoardLabel() {
        Assert.assertEquals(this.externalLinks.getCommunityBoardLabel(), TestUtil.SAP_COMMUNITY_BOARD_LABEL);
        this.log.info((Object)"Community Board label is coming as 'SAP Digital Interconnect Community Board'");
    }
}


