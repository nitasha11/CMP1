package Testcases;


import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.List;
import utilities.TestUtil;
import java.util.ArrayList;
import org.testng.annotations.AfterMethod;
import utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import pages.UserManagementPage;
import pages.HomePage;
import pages.LoginPage;
import java.util.HashMap;
import org.apache.log4j.Logger;
import Base.TestBase;

public class UserManagementPageTests extends TestBase
{
    Logger log;
    HashMap<String, String> recordsToSearchBy;
    private final String USER_IS_ACTIVATED_MSG = "Successfully Activated User(s) :";
    private final String USER_IS_ALREADY_ACTIVATED_MSG = "One or more users are already active. Please deselect those from the list.";
    private final String USER_IS_DEACTIVATED_MSG = "Successfully Deactivated User(s) :";
    private final String USER_IS_ALREADY_DEACTIVATED_MSG = "One or more users are already deactivated. Please deselect those from the list.";
    private final String USER_IS_DEACTIVATED_CNT_MODIFY_MSG = "This user is deactivated. Please activate it in order to modify it.";
    private final String USER_CNT_BE_MODIFY_MSG = "This user cannot be modified.";
    private final String USER_MODIFIED_MSG = "User role modified successfully";
    private final String NO_RECORD_FOUND = "No Records Found.";
    
    public UserManagementPageTests() {
        this.log = Logger.getLogger((Class)UserManagementPageTests.class);
        this.recordsToSearchBy = new HashMap<String, String>();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        this.login = new LoginPage();
        this.homepage = new HomePage();
        this.userManage = new UserManagementPage();
    }
    
    @AfterMethod
    public void tearDown(final ITestResult result) {
        if (2 == result.getStatus()) {
            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
            Screenshot.screenshot(result.getName());
        }
        UserManagementPageTests.driver.close();
        this.log.info((Object)"Browser Closed");
    }
    
    @DataProvider(name = "LogIn Credentials")
    public Object[] getCredFromDataprovider() {
        final List<String> logInCred = new ArrayList<String>();
        logInCred.add(TestUtil.CMP_ADMIN_EMAIL);
        logInCred.add(TestUtil.SALES_SPRT_USER_EMAIL);
        return logInCred.toArray(new Object[logInCred.size()]);
    }
    
    @DataProvider(name = "SalesForceAdmin")
    public Object[] getSalesForceCredFromDataprovider() {
        final List<String> logInCred = new ArrayList<String>();
        logInCred.add(TestUtil.CMP_ADMIN_EMAIL);
        logInCred.add(TestUtil.SALES_SPRT_USER_EMAIL);
        logInCred.add(TestUtil.SALESFORCE_ADMIN_EMAIL);
        return logInCred.toArray(new Object[logInCred.size()]);
    }
    
    @DataProvider(name = "UserToModify")
    public Object[] getUsersFromDataprovider() {
        final List<String> usersToModify = new ArrayList<String>();
        final String emttCustAdmin = "ramsay@adenyo.com";
        usersToModify.add(TestUtil.CUST_ADMIN_USER_EMAIL);
        usersToModify.add(emttCustAdmin);
        return usersToModify.toArray(new Object[usersToModify.size()]);
    }
    
    public String userToModify(final String userEmail) {
        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)"Logged In Successfully");
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        this.userManage.searchRecordByEmail(userEmail);
        final String status = this.userManage.modifyRole(userEmail);
        return status;
    }
    
    public String userToDeactivate(final String userNameToLogin, final String userToDeactivate) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        this.userManage.searchRecordByEmail(userToDeactivate);
        final String status = this.userManage.deActivateUser(userToDeactivate);
        return status;
    }
    
    public String userToActivate(final String userNameToLogin, final String userToActivate) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        this.userManage.searchRecordByEmail(userToActivate);
        final String status = this.userManage.activateUser(userToActivate);
        return status;
    }
    
    public String activateUserAfterDeactivation(final String userToActivate) {
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordByEmail(userToActivate);
        final String status = this.userManage.activateUser(userToActivate);
        return status;
    }
    
    @Test(priority = 1, dataProvider = "LogIn Credentials")
    public void deactivateButtonAndActivateButtonFunctionalityTestForSalesSupportUser(final String userNameToLogin) {
        final String salesSupportEmail = "ammygrover357@sap.com";
        String userStatus = this.userToDeactivate(userNameToLogin, salesSupportEmail);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(salesSupportEmail);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 2, dataProvider = "LogIn Credentials")
    public void deactivateButtonAndActivateButtonFunctionalityTestForEMTTServiceUser(final String userNameToLogin) {
        String userStatus = this.userToDeactivate(userNameToLogin, TestUtil.EMTT_SERVICE_DESK_USER);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(TestUtil.EMTT_SALES_USER_EMAIL);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 3, dataProvider = "SalesForceAdmin")
    public void deactivateButtonAndActivateButtonFunctionalityTestForCustomerAdmin(final String userNameToLogin) {
        final String custAdmin = "summar@sol.com";
        String userStatus = this.userToDeactivate(userNameToLogin, custAdmin);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(custAdmin);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 4, dataProvider = "LogIn Credentials")
    public void deactivateButtonAndActivateButtonFunctionalityTestForEMTTCustomerAdmin(final String userNameToLogin) {
        final String emttCustAdmin = "emcustadmin@xoriant.com";
        String userStatus = this.userToDeactivate(userNameToLogin, emttCustAdmin);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(emttCustAdmin);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 5, dataProvider = "SalesForceAdmin")
    public void deactivateButtonAndActivateButtonFunctionalityTestForCustomerSupportUser(final String userNameToLogin) {
        final String deactCustSupportUser = "lucen@salesforce.com";
        String userStatus = this.userToDeactivate(userNameToLogin, deactCustSupportUser);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is activated successfully");
        userStatus = this.activateUserAfterDeactivation(deactCustSupportUser);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
    }
    
    @Test(priority = 6, dataProvider = "LogIn Credentials")
    public void deactivateButtonNegativeTestForDeactivatedSalesUser(final String userNameToLogin) {
        final String deactSalesUser = "aaaaa@sap.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactSalesUser);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 7, dataProvider = "LogIn Credentials")
    public void deactivateButtonNegativeTestForDeactivatedEMTTSalesUser(final String userNameToLogin) {
        final String deactEMTTSalesUser = "Himanipathak2102@sap.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactEMTTSalesUser);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 8, dataProvider = "LogIn Credentials")
    public void deactivateButtonNegativeTestForDeactivatedEMTTCustAdminUser(final String userNameToLogin) {
        final String deactEMTTCustAdmin = "noahh@aerialink.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactEMTTCustAdmin);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 9, dataProvider = "SalesForceAdmin")
    public void deactivateButtonNegativeTestForDeactivatedCustAdminUser(final String userNameToLogin) {
        final String deactCustAdmin = "sal@sal.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactCustAdmin);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 10, dataProvider = "LogIn Credentials")
    public void deactivateButtonAndActivateButtonFunctionalityTestForEMTTCustomerSupport(final String userNameToLogin) {
        final String emttCustSupport = "bruno@adenyo.com";
        String userStatus = this.userToDeactivate(userNameToLogin, emttCustSupport);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(emttCustSupport);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 11, dataProvider = "LogIn Credentials")
    public void deactivateButtonAndActivateButtonFunctionalityTestForSDToolUser(final String userNameToLogin) {
        final String sdToolUser = "sdt@sap.com";
        String userStatus = this.userToDeactivate(userNameToLogin, sdToolUser);
        Assert.assertTrue(userStatus.contains("Successfully Deactivated User(s) :"), userStatus);
        this.log.info((Object)"User is deactivated successfully");
        userStatus = this.activateUserAfterDeactivation(sdToolUser);
        Assert.assertTrue(userStatus.contains("Successfully Activated User(s) :"), userStatus);
        this.log.info((Object)"User is activated Successfully");
    }
    
    @Test(priority = 12, dataProvider = "LogIn Credentials")
    public void deactivateButtonNegativeTestForDeactivatedEMTTCustSupportUser(final String userNameToLogin) {
        final String deactEMTTCustSupport = "dhaval@ceca.es";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactEMTTCustSupport);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 13, dataProvider = "SalesForceAdmin")
    public void deactivateButtonNegativeTestForDeactivatedCustSupportUser(final String userNameToLogin) {
        final String deactCustSupp = "deactivatedCustSupport@microsoft.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactCustSupp);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 14, dataProvider = "LogIn Credentials")
    public void deactivateButtonNegativeTestForDeactivatedSdToolUser(final String userNameToLogin) {
        final String deactSdToolUser = "test122@sap.com";
        final String userStatus = this.userToDeactivate(userNameToLogin, deactSdToolUser);
        Assert.assertTrue(userStatus.contains("One or more users are already deactivated. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already deactivated, functionality is working as expected");
    }
    
    @Test(priority = 15, dataProvider = "LogIn Credentials")
    public void activateButtonFunctionalityNegativeTestForActivatedSalesSupportUser(final String userNameToLogin) {
        final String activatedSalesSupportUser = "harry@sap.com";
        final String userStatus = this.userToActivate(userNameToLogin, activatedSalesSupportUser);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 16, dataProvider = "LogIn Credentials")
    public void activateButtonFunctionalityNegativeTestForActivatedEMTTSalesUser(final String userNameToLogin) {
        final String userStatus = this.userToActivate(userNameToLogin, TestUtil.EMTT_SALES_USER_EMAIL);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 17, dataProvider = "SalesForceAdmin")
    public void activateButtonFunctionalityNegativeTestForActivatedCustomerAdmin(final String userNameToLogin) {
        final String custAdminEmail = "custadminhimani@microsoft.com";
        final String userStatus = this.userToActivate(userNameToLogin, custAdminEmail);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 18, dataProvider = "LogIn Credentials")
    public void activateButtonFunctionalityNegativeTestForActivatedEMTTCustomerAdmin(final String userNameToLogin) {
        final String userStatus = this.userToActivate(userNameToLogin, TestUtil.EMTT_CUST_ADMIN_EMAIL);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 19, dataProvider = "SalesForceAdmin")
    public void activateButtonFunctionalityNegativeTestForActivatedCustomerSupport(final String userNameToLogin) {
        final String activatedCustSupport = "feb@cos.com";
        final String userStatus = this.userToActivate(userNameToLogin, activatedCustSupport);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 20, dataProvider = "LogIn Credentials")
    public void activateButtonFunctionalityNegativeTestForActivatedEMTTCustomerSupport(final String userNameToLogin) {
        final String activatedEMTTCustSupport = "james2@appirio.com";
        final String userStatus = this.userToActivate(userNameToLogin, activatedEMTTCustSupport);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 21, dataProvider = "LogIn Credentials")
    public void activateButtonFunctionalityNegativeTestForActivatedSDToolUser(final String userNameToLogin) {
        final String activatedSdToolUser = "rtr@sap.com";
        final String userStatus = this.userToActivate(userNameToLogin, activatedSdToolUser);
        Assert.assertTrue(userStatus.contains("One or more users are already active. Please deselect those from the list."), userStatus);
        this.log.info((Object)"User was already activated, functionality is working as expected");
    }
    
    @Test(priority = 22, dataProvider = "SalesForceAdmin")
    public void searchButtonFunctionalityForSingleField(final String userNameToLogin) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        final String searchByName = String.valueOf(TestUtil.SEARCH_BY_FIRST_NAME) + ":" + "jack";
        final String searchByLastName = String.valueOf(TestUtil.SEARCH_BY_LAST_NAME) + ":" + "lack";
        final String searchByEmail = String.valueOf(TestUtil.SEARCH_BY_EMAIL) + ":" + "custadminhimani@microsoft.com";
        final String searchByPhoneNo = String.valueOf(TestUtil.SEARCH_BY_PHONE_NO) + ":" + "7654321437";
        final String searchByCompany = String.valueOf(TestUtil.SEARCH_BY_COMPANY_NAME) + ":" + "microsoft";
        final String searchByRole = String.valueOf(TestUtil.SEARCH_BY_ROLE) + ":" + "Salesforce Administrator";
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByName);
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByLastName);
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByEmail);
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByPhoneNo);
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByCompany);
        UserManagementPageTests.driver.navigate().refresh();
        this.userManage.searchRecordsWithSingleIdentifier(searchByRole);
        this.log.info((Object)"Search button functionality is working fine for a single field");
    }
    
    @Test(priority = 23, dataProvider = "SalesForceAdmin")
    public void searchButtonFunctionalityForMultipleFields(final String userNameToLogin) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        final List<String> multIdentifiers = new ArrayList<String>();
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_FIRST_NAME) + ":" + "raj");
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_LAST_NAME) + ":" + "jose");
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_PHONE_NO) + ":" + "4085501228");
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_COMPANY_NAME) + ":" + "tesla");
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_ROLE) + ":" + TestUtil.CUST_ADMIN);
        this.userManage.searchRecordsWithMultipleIdentifiers((List)multIdentifiers);
        this.log.info((Object)"Search button functionality is working fine for multiple fields");
    }
    
    @Test(priority = 24, dataProvider = "SalesForceAdmin")
    public void searchButtonFunctionalityForNoRecords(final String userNameToLogin) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        this.userManage.searchRecordByEmail("johnwick");
        final int noOfRecords = this.userManage.getNoOfTableRecords();
        Assert.assertTrue(noOfRecords == 1, "No of records coming are " + noOfRecords);
        final String msg = this.userManage.getNoRecordMsg();
        Assert.assertTrue(msg.equals("No Records Found."), "Message is coming as = " + msg + "& Expected was = " + "No Records Found.");
        this.log.info((Object)"No Record Found message is coming as expected");
    }
    
    @Test(priority = 25, dataProvider = "LogIn Credentials")
    public void viewDataExtractMsgOptionForEMTTCustAdminAndSupport(final String userNameToLogin) {
        this.login.login(userNameToLogin, TestUtil.STANDARD_PSSWRD);
        this.log.info((Object)("Logged In Successfully with " + userNameToLogin));
        this.homepage.waitForThePageToBeLoaded();
        this.homepage.redirectToUserManagementPage();
        final String emttCustAdminWithDataExtractYes = "EMTTCustAdminForFrenchByEMTTAdmin@mkoiki.com";
        final String emttCustAdminWithDataExtractNo = "EMTTCustAdminBySuperAdminForFrench@mkoiki.com";
        this.userManage.viewDataExtractValue(emttCustAdminWithDataExtractYes, "Yes");
        this.userManage.searchOptionClearBtn.click();
        UserManagementPageTests.wait.until((Function)ExpectedConditions.visibilityOf(this.userManage.searchBtn));
        UserManagementPageTests.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManage.searchBtn));
        this.userManage.viewDataExtractValue(emttCustAdminWithDataExtractNo, "No");
        this.log.info((Object)"Data Extract value for all the EMTT Cust Support are coming as same as their EMTT Customer Admin");
    }
    
    @Test(priority = 26, dataProvider = "UserToModify")
    public void modifyAdminRoles(final String userToModify) {
        final String userStatus = this.userToModify(userToModify);
        Assert.assertEquals(userStatus, "User role modified successfully");
        this.log.info((Object)"Customer admin has been modified into Customer Support");
    }
    
    @Test(priority = 27, dataProvider = "UserToModify")
    public void modifySpprtRoles(final String userToModify) {
        final String userStatus = this.userToModify(userToModify);
        Assert.assertTrue(userStatus.equals("User role modified successfully"));
        this.log.info((Object)"Customer Support has been modified into Customer Admin");
    }
    
    @Test(priority = 28)
    public void modifyCustAdminRoleTestForNoCustSupp() {
        final String custAdminWithNoCustSuppUser = "supportcustomer@bazaz.com";
        final String userStatus = this.userToModify(custAdminWithNoCustSuppUser);
        Assert.assertTrue(userStatus.equals(TestUtil.NO_CUST_SUPP_MSG));
    }
    
    @Test(priority = 29)
    public void modifyEMTTCustAdminRoleTestForNoEMTTCustSupp() {
        final String emttCustAdminWithNoEMTTCustSuppUser = "bhushan.kishanrao.mukhedkar@citi.com";
        final String userStatus = this.userToModify(emttCustAdminWithNoEMTTCustSuppUser);
        Assert.assertTrue(userStatus.equals(TestUtil.NO_EMTT_CUST_SUPP_MSG));
    }
    
    @Test(priority = 30)
    public void modifyRoleNegativeTestForSalesSupportUser() {
        final String userStatus = this.userToModify(TestUtil.SALES_SPRT_USER_EMAIL);
        Assert.assertTrue(userStatus.equals("This user cannot be modified."));
        this.log.info((Object)"We can't modify role for Other Users");
    }
    
    @Test(priority = 31)
    public void modifyRoleNegativeTestEMTTSalesUser() {
        final String userStatus = this.userToModify(TestUtil.EMTT_SALES_USER_EMAIL);
        Assert.assertTrue(userStatus.equals("This user cannot be modified."));
        this.log.info((Object)"We can't modify role for Other Users");
    }
    
    @Test(priority = 32)
    public void modifyRoleNegativeTestForDeactivatedCustAdminUser() {
        final String deactivatedCustAdmin = "test.admin@testcompany.com";
        final String userStatus = this.userToModify(deactivatedCustAdmin);
        Assert.assertTrue(userStatus.equals("This user is deactivated. Please activate it in order to modify it."));
        this.log.info((Object)"Modify role functionality is working as expected for Other Users");
    }
    
    @Test(priority = 33)
    public void modifyRoleNegativeTestForDeactivatedCustSuppUser() {
        final String deactivatedCustSupportUser = "customer1@sap.com";
        final String userStatus = this.userToModify(deactivatedCustSupportUser);
        Assert.assertTrue(userStatus.equals("This user is deactivated. Please activate it in order to modify it."));
        this.log.info((Object)"Modify role functionality is working as expected for Other Users");
    }
    
    @Test(priority = 34)
    public void modifyRoleNegativeTestForDeactivatedEMTTCustAdminUser() {
        final String deactivatedEMTTCustAdmin = "philip.dias@c-tag.com";
        final String userStatus = this.userToModify(deactivatedEMTTCustAdmin);
        Assert.assertTrue(userStatus.equals("This user is deactivated. Please activate it in order to modify it."));
        this.log.info((Object)"Modify role functionality is working as expected for Other Users");
    }
    
    @Test(priority = 35)
    public void modifyRoleNegativeTestForDeactivatedEMTTCustSuppUser() {
        final String deactivatedEMTTCustSupportUser = "nihit@ceca.es";
        final String userStatus = this.userToModify(deactivatedEMTTCustSupportUser);
        Assert.assertTrue(userStatus.equals("This user is deactivated. Please activate it in order to modify it."));
        this.log.info((Object)"Modify role functionality is working as expected for Other Users");
    }
    
    @Test(priority = 36)
    public void modifyRoleNegativeTestForSdToolUser() {
        final String sdToolUser = "Himanipathaksdt@sap.com";
        final String userStatus = this.userToModify(sdToolUser);
        Assert.assertTrue(userStatus.equals("This user cannot be modified."));
        this.log.info((Object)"We can't modify role for Other Users");
    }
}