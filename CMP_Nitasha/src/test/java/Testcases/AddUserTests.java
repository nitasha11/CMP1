package Testcases;


	import java.util.Collections;
	import org.testng.annotations.Test;
	import java.util.function.Function;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.testng.annotations.DataProvider;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import utilities.Screenshot;
	import org.testng.ITestResult;
	import org.testng.annotations.BeforeMethod;
	import utilities.TestUtil;
	import pages.UserManagementPage;
	import pages.HomePage;
	import pages.LoginPage;
	import pages.AddUserPage;
	import org.testng.annotations.BeforeClass;
	import TestData.TestDataFromExcel;
	import org.openqa.selenium.WebElement;
	import java.util.List;
	import java.util.ArrayList;
	import org.apache.log4j.Logger;
	import Base.TestBase;

	public class AddUserTests extends TestBase
	{
	    Logger log;
	    private ArrayList<ArrayList<String>> userData;
	    List<WebElement> emSupportToolOptions;
	    private final String EMTT_ROLE_NOT_MATCHED_TO_PROV_ERROR = "Provisioning request role type not match to EM Ticket Tool role type";
	    private final String EMTT_ROLE_NOT_MATCHED_TO_SD_ERROR = "EM Ticket Tool role type not match to SD troubleshooting role type";
	    private final String SD_ROLE_NOT_MATCHED_TO_PROV_ERROR = "SD troubleshooting role type not match to Prov request role type";
	    private final String SD_ROLE_NOT_MATCHED_TO_EMTT_PROV_ERROR = "SD troubleshooting role type not match to EM Ticket Tool/Prov request role type";
	    private final String SELECT_AN_APP_ERROR = "Please select the application";
	    
	    public AddUserTests() {
	        this.log = Logger.getLogger((Class)AddUserTests.class);
	        this.userData = null;
	        this.emSupportToolOptions = null;
	    }
	    
	    @BeforeClass
	    public void initializeExcelObject() {
	        AddUserTests.testDataObject = new TestDataFromExcel();
	    }
	    
	    @BeforeMethod
	    public void setUp() {
	        initialization();
	        this.addUserPage = new AddUserPage();
	        this.login = new LoginPage();
	        this.homepage = new HomePage();
	        this.userManage = new UserManagementPage();
	        this.login.login(TestUtil.CMP_ADMIN_EMAIL, TestUtil.STANDARD_PSSWRD);
	        this.log.info((Object)"Logged In Successfully");
	        this.homepage.waitForThePageToBeLoaded();
	    }
	    
	    @AfterMethod
	    public void tearDown(final ITestResult result) {
	        if (2 == result.getStatus()) {
	            this.log.info((Object)(String.valueOf(result.getName()) + " test is failed"));
	            Screenshot.screenshot(result.getName());
	        }
	        AddUserTests.driver.close();
	        this.log.info((Object)"Browser Closed");
	    }
	    
	    @DataProvider(name = "UserDetails")
	    public Object[] getDataFromDataprovider() {
	        this.userData = (ArrayList<ArrayList<String>>)AddUserTests.testDataObject.readUserDetailsFromExcel();
	        Assert.assertTrue(this.userData.size() > 1, "Not able to read UserDetails");
	        this.userData.remove(0);
	        return this.userData.toArray(new Object[this.userData.size()]);
	    }
	    
	    @Test(dataProvider = "UserDetails")
	    public void createUsers(final ArrayList<String> userDetails) {
	        this.addUserPage.setCheckCount(1);
	        AddUserTests.testDataObject.setNewUserDetails((ArrayList)userDetails);
	        this.addUserPage.createUser();
	        this.homepage.extendedWait.until((Function)ExpectedConditions.alertIsPresent());
	        this.addUserPage.checkUserCreationStatus();
	        this.log.info((Object)("User with role :" + AddUserTests.testDataObject.getRole() + ".... Email id: " + AddUserTests.testDataObject.getEmail() + " is Created"));
	        this.userManage.deleteUser(AddUserTests.testDataObject.getEmail());
	        this.log.info((Object)("User with emailId: " + AddUserTests.testDataObject.getEmail() + " is deleted"));
	    }
	    
	    @Test(enabled = false)
	    public void allUsersAreAvailableInAddUserPage() {
	        boolean result = false;
	        final ArrayList<String> availableUsers = (ArrayList<String>)this.addUserPage.verifyingAllUsersAvailable();
	        Collections.sort(availableUsers);
	        Collections.sort((List<Comparable>)TestUtil.ALL_USERS_FOR_CMP_ADMIN);
	        result = this.addUserPage.checkValidFields((List)availableUsers, (List)TestUtil.ALL_USERS_FOR_CMP_ADMIN);
	        Assert.assertTrue(result);
	        this.log.info((Object)"All required User roles are present on the page");
	        this.addUserPage.clickOnNextBtn();
	        this.addUserPage.verifyErrorMsgsOfRoles();
	    }
	    
	    @Test(enabled = false)
	    public void errorMsgsInCaseOfInvalidRoleTypesSelection() {
	        final String userType1 = "Customer Administrator,EMTT Sales";
	        final String userType2 = "Sales,EMTT Customer Administrator";
	        final String userType3 = "Customer Administrator,SDTool User";
	        final String userType4 = "EMTT Customer Administrator,SDTool User";
	        final String userType5 = "Sales,EMTT Customer Administrator,SDTool User";
	        String msg = null;
	        this.addUserPage.redirectingToAddUserPage();
	        this.addUserPage.clickOnPreviousBtn();
	        Assert.assertTrue(this.addUserPage.provReqIsPresent(), "We can proceed back from Selecting Application Type in New User Creation Page");
	        this.addUserPage.clickOnNextBtn();
	        msg = this.addUserPage.getAppListError();
	        Assert.assertEquals(msg, "Please select the application");
	        this.addUserPage.selectUserRole(userType1);
	        msg = this.addUserPage.getWrongEMTTUserSelectionErrorMsgs();
	        Assert.assertEquals(msg, "Provisioning request role type not match to EM Ticket Tool role type", "Not getting proper error in case EMTT role is not supported with Provisioning Role");
	        AddUserTests.driver.navigate().refresh();
	        this.addUserPage.selectUserRole(userType2);
	        msg = this.addUserPage.getWrongEMTTUserSelectionErrorMsgs();
	        Assert.assertEquals(msg, "Provisioning request role type not match to EM Ticket Tool role type", "Not getting proper error in case EMTT role is not supported with Provisioning Role");
	        AddUserTests.driver.navigate().refresh();
	        this.addUserPage.selectUserRole(userType3);
	        msg = this.addUserPage.getWrongSDUserSelectionErrorMsgs();
	        Assert.assertEquals(msg, "SD troubleshooting role type not match to Prov request role type", "Not getting proper error in case SD role is not supported with Provisioning Role");
	        AddUserTests.driver.navigate().refresh();
	        this.addUserPage.selectUserRole(userType4);
	        msg = this.addUserPage.getWrongEMTTUserSelectionErrorMsgs();
	        Assert.assertEquals(msg, "EM Ticket Tool role type not match to SD troubleshooting role type", "Not getting proper error in case EMTT role is not supported with SD Role");
	        AddUserTests.driver.navigate().refresh();
	        this.addUserPage.selectUserRole(userType5);
	        msg = this.addUserPage.getWrongSDUserSelectionErrorMsgs();
	        Assert.assertEquals(msg, "SD troubleshooting role type not match to EM Ticket Tool/Prov request role type", "Not getting proper error in case SD role is not supported with EMTT/Provisioning Role");
	        AddUserTests.driver.navigate().refresh();
	    }
	    
	    @DataProvider(name = "UserRoles")
	    public Object[] getRolesFromDataprovider() {
	        final String userType1 = "Sales";
	        final String userType2 = "Customer Administrator";
	        final String userType3 = "EMTT Sales";
	        final String userType4 = "EMTT Customer Administrator";
	        final String userType5 = "SDTool User";
	        final ArrayList<String> testRoles = new ArrayList<String>();
	        testRoles.add(userType1);
	        testRoles.add(userType2);
	        testRoles.add(userType3);
	        testRoles.add(userType4);
	        testRoles.add(userType5);
	        return testRoles.toArray(new Object[testRoles.size()]);
	    }
	    
	    @Test(dataProvider = "UserRoles", enabled = false)
	    public void userDetailErrorMessages(final String userRole) {
	        final String publicEmailId = "abc@gmail.com";
	        this.addUserPage.redirectingToAddUserPage();
	        this.addUserPage.selectUserRole(userRole);
	        this.addUserPage.clickOnPreviousBtn();
	        AddUserTests.wait.until((Function)ExpectedConditions.visibilityOf(this.addUserPage.getProvReqElement()));
	        Assert.assertTrue(this.addUserPage.provReqIsPresent(), "Previous button functionality is not working while filling the details");
	        this.addUserPage.clickOnNextBtn();
	        this.addUserPage.verifyErrorMessagesOfBasicFields();
	        if (userRole.toLowerCase().contains("admin")) {
	            final String msg = this.addUserPage.getPublicEmailIdError(publicEmailId);
	            Assert.assertEquals(msg, TestUtil.PUBLIC_EMAIL_ID_ERROR, "Not getting proper error message for Public Email ID");
	            this.addUserPage.verifyUserDetailsErrorMessagesForAdmin();
	        }
	    }
	}






