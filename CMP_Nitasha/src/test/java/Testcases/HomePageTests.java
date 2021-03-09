package Testcases;

	
	import java.util.function.Function;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.testng.annotations.DataProvider;
	import java.util.List;
	import java.util.ArrayList;
	import org.testng.annotations.Test;
	import org.testng.Assert;
	import utilities.TestUtil;
	import org.testng.annotations.AfterMethod;
	import utilities.Screenshot;
	import org.testng.ITestResult;
	import org.testng.annotations.BeforeMethod;
	import pages.HomePage;
	import pages.LoginPage;
	import org.apache.log4j.Logger;
	import Base.TestBase;

	public class HomePageTests extends TestBase
	{
	    Logger log;
	    private final String PASSWORD_HAS_CHANGED_MSG = "Your password has been changed,";
	    private final String LOGIN_WITH_NEW_CRED_MSG = "Please re-login with new credentials.";
	    private final String NEW_PASSWORD_LENGTH_ERROR = "Password should be minimum 8 characters.";
	    private final String INVALID_PASSWORD_ERROR = "Incorrect password, please enter a valid password";
	    private final String NEW_AND_CONFIRM_PASSWORD_MISMATCH_ERROR = "New Password and Re-Type Password must be same.";
	    private final String SAME_OLD_AND_NEW_PASSWORD_ERROR = "New Password should be different from Old Password.";
	    private final String NEW_PASSWORD_CRITERIA_NOT_MATCH_ERROR = "New Password should contain one number and one special character.";
	    
	    public HomePageTests() {
	        this.log = Logger.getLogger((Class)HomePageTests.class);
	    }
	    
	    @BeforeMethod
	    public void setUp() {
	        initialization();
	        this.login = new LoginPage();
	        this.homepage = new HomePage();
	    }
	    
	    @AfterMethod
	    public void tearDown(final ITestResult result) {
	        if (2 == result.getStatus()) {
	            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
	            Screenshot.screenshot(result.getName());
	        }
	        HomePageTests.driver.close();
	        this.log.info((Object)"Browser Closed");
	    }
	    
	    @Test
	    public void checkCMPAdminUserAuthorities() {
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(this.homepage.userMngmntTabPresent(), "User Management tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSettingsBtnPresent(), "Setting button should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for CMP Admin User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for CMP Admin User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.CMP_ADMIN_EMAIL));
	    }
	    
	    @Test
	    public void checkSalesUserAuthorities() {
	        this.login.login(TestUtil.SALES_USER_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.SALES_USER_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(!this.homepage.userMngmntTabPresent(), "User Management tab should not come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should not come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should not come for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should not come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelSettingsBtnPresent(), "Setting button should not come for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should not come for Sales User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should come not for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for Sales User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for Sales User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.SALES_USER_EMAIL));
	    }
	    
	    @Test
	    public void checkServiceUserAuthorities() {
	        this.login.login(TestUtil.SERVICE_USER_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.SERVICE_USER_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(!this.homepage.userMngmntTabPresent(), "User Management tab should not come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should not come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should not come for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should not come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelSettingsBtnPresent(), "Setting button should not come for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should not come for Service User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should come not for Service User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for Service User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for Service User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.SERVICE_USER_EMAIL));
	    }
	    
	    @Test
	    public void checkSalesSupportUserAuthorities() {
	        this.login.login(TestUtil.SALES_SPRT_USER_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.SALES_SPRT_USER_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(this.homepage.userMngmntTabPresent(), "User Management tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for Sales Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSettingsBtnPresent(), "Setting button should not come for Sales Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should not come for Sales Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should not come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for Sales Support User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for Sales Support User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.SALES_SPRT_USER_EMAIL));
	    }
	    
	    @Test
	    public void checkCustomerAdminUserAuthorities() {
	        this.login.login(TestUtil.CUST_ADMIN_USER_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CUST_ADMIN_USER_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(this.homepage.userMngmntTabPresent(), "User Management tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for Customer Admin User");
	        Assert.assertTrue(!this.homepage.leftPanelSettingsBtnPresent(), "Setting button should not come for Customer Admin User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should not come for Customer Admin User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should not come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for Customer Admin User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for Customer Admin User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.CUST_ADMIN_USER_EMAIL));
	    }
	    
	    @Test
	    public void checkCustomerSupportUserAuthorities() {
	        this.login.login(TestUtil.CUST_SUPPORT_USER_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CUST_SUPPORT_USER_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        Assert.assertTrue(!this.homepage.userMngmntTabPresent(), "User Management tab should not come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelDashBoardBtnPresent(), "Dashboard tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelInboxBtnPresent(), "Inbox tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelSearchBtnPresent(), "Search button should come in the left panel for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelNewCampaignReqBtnPresent(), "New Campaign request button should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelSavedCampaignTabPresent(), "Saved Campaign tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelProfileBtnPresent(), "Profile tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelChangePsswrdBtnPresent(), "Change Password tab should come for Customer Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSettingsBtnPresent(), "Setting button should not come for Customer Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveySettingsBtnPresent(), "Survey Settings tab should not come for Customer Support User");
	        Assert.assertTrue(!this.homepage.leftPanelSurveyReportsBtnPresent(), "Survey Reports tab should not come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelExternalLinksBtnPresent(), "External Links tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelSystemReportsBtnPresent(), "System Reports tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelCustomReportsBtnPresent(), "Custom Reports tab should come for Customer Support User");
	        Assert.assertTrue(this.homepage.leftPanelScheduleReportsBtnPresent(), "Schedule Reports tab should come for Customer Support User");
	        this.log.info((Object)("All the proper tabs are visible to " + TestUtil.CUST_SUPPORT_USER_EMAIL));
	    }
	    
	    @DataProvider(name = "UsersToLogin")
	    public Object[] getUserNameToLogin() {
	        final List<String> usersTologIn = new ArrayList<String>();
	        final String salesUser = "fff@sap.com";
	        final String salesSupportUser = "sdemprov@sap.com";
	        final String serviceUser = "exp@sap.com";
	        final String customerAdminUser = "calatam@sap.com";
	        final String customerSupportUser = "aaa1111@sap.com";
	        usersTologIn.add(salesUser);
	        usersTologIn.add(salesSupportUser);
	        usersTologIn.add(serviceUser);
	        usersTologIn.add(customerAdminUser);
	        usersTologIn.add(customerSupportUser);
	        return usersTologIn.toArray(new Object[usersTologIn.size()]);
	    }
	    
	    @Test(dataProvider = "UsersToLogin")
	    public void changePasswordFunctionalityTest(final String userToLogin) {
	        final String newPassword = "welcome@11";
	        this.login.login(userToLogin, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + userToLogin));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(TestUtil.STANDARD_PSSWRD, newPassword, newPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        HomePageTests.wait.until((Function)ExpectedConditions.titleIs(TestUtil.LOGIN_PAGE_TITLE));
	        Assert.assertEquals(this.homepage.getPasswordChangedText(), "Your password has been changed,");
	        this.login.login(userToLogin, newPassword);
	        this.log.info((Object)("Logged In Successfully with " + userToLogin));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(newPassword, TestUtil.STANDARD_PSSWRD, TestUtil.STANDARD_PSSWRD);
	        this.homepage.clickOnChangePasswordChangeButton();
	        HomePageTests.wait.until((Function)ExpectedConditions.titleIs(TestUtil.LOGIN_PAGE_TITLE));
	        Assert.assertEquals(this.homepage.getPasswordChangedText(), "Your password has been changed,");
	        Assert.assertEquals(this.homepage.getLoginWithNewCredText(), "Please re-login with new credentials.");
	        this.log.info((Object)("Change Password functionality is working fine for " + userToLogin));
	    }
	    
	    @Test
	    public void changePswrdFunctionalityNegativeTestForPasswordLength() {
	        final String newPassword = "abc";
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(TestUtil.STANDARD_PSSWRD, newPassword, newPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        final String msg = this.homepage.getChangePasswordErrorMsg();
	        Assert.assertEquals(msg, "Password should be minimum 8 characters.");
	        this.log.info((Object)"Proper error message is coming if the password length is not appropriate");
	    }
	    
	    @Test
	    public void changePswrdFunctionalityNegativeTestForPasswordCriteriaNotMatch() {
	        final String newPassword = "abcdefgh";
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(TestUtil.STANDARD_PSSWRD, newPassword, newPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        final String msg = this.homepage.getChangePasswordErrorMsg();
	        Assert.assertEquals(msg, "New Password should contain one number and one special character.");
	        this.log.info((Object)"Proper error message is coming if the password criteria doesn't match");
	    }
	    
	    @Test
	    public void changePswrdFunctionalityNegativeTestForPasswordOldPassword() {
	        final String oldPassword = "welcome@12";
	        final String newPassword = "abcdefgh@1";
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(oldPassword, newPassword, newPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        final String msg = this.homepage.getChangePasswordErrorMsg();
	        Assert.assertEquals(msg, "Incorrect password, please enter a valid password");
	        this.log.info((Object)"Proper error message is coming in case the old password is not right");
	    }
	    
	    @Test
	    public void changePswrdFunctionalityNegativeTestForNewAndConfirmPasswordMismatch() {
	        final String newPassword = "abcdefgh@1";
	        final String confirmPassword = "bacdefgh@1";
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(TestUtil.STANDARD_PSSWRD, newPassword, confirmPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        final String msg = this.homepage.getChangePasswordErrorMsg();
	        Assert.assertEquals(msg, "New Password and Re-Type Password must be same.");
	        this.log.info((Object)"Proper error message is coming if the new password and confirm password doesn't match");
	    }
	    
	    @Test
	    public void changePswrdFunctionalityNegativeTestForSameOldAndNewPassword() {
	        final String newPassword = TestUtil.STANDARD_PSSWRD;
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)("Logged In Successfully with " + TestUtil.CMP_ADMIN_EMAIL));
	        this.homepage.waitForThePageToBeLoaded();
	        this.homepage.changePassword(TestUtil.STANDARD_PSSWRD, newPassword, newPassword);
	        this.homepage.clickOnChangePasswordChangeButton();
	        final String msg = this.homepage.getChangePasswordErrorMsg();
	        Assert.assertEquals(msg, "New Password should be different from Old Password.");
	        this.log.info((Object)"Proper error message is coming if the old password and the new password are same");
	    }
	}


