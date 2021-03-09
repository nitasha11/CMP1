package Testcases;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import utilities.TestUtil;
import pages.LoginPage;
import pages.EMTTHomePage;
import org.apache.log4j.Logger;
import Base.TestBase;

public class EMTTHomePageTests extends TestBase
{
    Logger log;
    EMTTHomePage emttHomePage;
    private String expectedDataExtractLabel;
    
    public EMTTHomePageTests() {
        this.log = Logger.getLogger((Class)EMTTHomePageTests.class);
        this.expectedDataExtractLabel = "Data Extract Request";
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        this.login = new LoginPage();
        this.emttHomePage = new EMTTHomePage();
        this.login.login(TestUtil.EMTT_CUST_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
        this.emttHomePage.waitForThePageToBeLoaded();
    }
    
    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (2 == result.getStatus()) {
            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
            Screenshot.screenshot(result.getName());
        }
        EMTTHomePageTests.driver.close();
        this.log.info((Object)"Browser Closed");
    }
    
    @Test
    public void verifyDataExtractLabel() {
        final String label = this.emttHomePage.getDataExtractLinkLabel();
        Assert.assertTrue(!label.equals("Data Request Extract"), "Data Extract Link's label is coming as " + label);
        Assert.assertTrue(label.equals(this.expectedDataExtractLabel), "Data Extract Link's Label is coming as " + label);
        this.log.info((Object)"Data Extract Link's Label has been changed to 'Data Extract Request' from 'Data Request Extract'");
    }
}

