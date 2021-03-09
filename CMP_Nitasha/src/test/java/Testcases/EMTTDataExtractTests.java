package Testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import utilities.TestUtil;
import pages.LoginPage;
import java.util.HashMap;
import pages.EMTTHomePage;
import pages.EMTTDataExtractPage;
import org.apache.log4j.Logger;
import Base.TestBase;

public class EMTTDataExtractTests extends TestBase
{
    Logger log;
    EMTTDataExtractPage dataExtractPage;
    EMTTHomePage emttHomePage;
    HashMap<String, String> labelAccToMsgType;
    private final String MT = "MT";
    private final String MO = "MO";
    private final String BOTH = "BOTH";
    private final String TPOA_LABEL = "TPOA";
    private final String SERVICE_NUMBER_LABEL = "Service Number";
    private final String SERVICE_NUMBER_TPOA_LABEL = "Service Number/TPOA";
    
    public EMTTDataExtractTests() {
        this.log = Logger.getLogger((Class)EMTTDataExtractTests.class);
        this.labelAccToMsgType = new HashMap<String, String>();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        this.login = new LoginPage();
        this.emttHomePage = new EMTTHomePage();
        this.dataExtractPage = new EMTTDataExtractPage();
        this.login.login(TestUtil.EMTT_CUST_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
        this.emttHomePage.waitForThePageToBeLoaded();
        this.emttHomePage.clickOnDataExtractRequestLink();
        this.dataExtractPage.verifyPageIsLoaded();
    }
    
    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (2 == result.getStatus()) {
            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
            Screenshot.screenshot(result.getName());
        }
        EMTTDataExtractTests.driver.close();
        this.log.info((Object)"Browser Closed");
    }
    
    @Test
    public void ServiceNumberLabelBasedOnMessageType() {
        String value = null;
        this.dataExtractPage.selectMessageType("MT");
        value = this.dataExtractPage.getServiceNumberLabelValue();
        Assert.assertTrue(value.equals("TPOA"));
        this.dataExtractPage.selectMessageType("MO");
        value = this.dataExtractPage.getServiceNumberLabelValue();
        Assert.assertTrue(value.equals("Service Number"));
        this.dataExtractPage.selectMessageType("BOTH");
        value = this.dataExtractPage.getServiceNumberLabelValue();
        Assert.assertTrue(value.equals("Service Number/TPOA"));
    }
}


