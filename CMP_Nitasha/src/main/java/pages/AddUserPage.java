package pages;


import org.openqa.selenium.TimeoutException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import java.util.Collection;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.Select;
import java.util.Iterator;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.TestUtil;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.apache.log4j.Logger;
import Base.TestBase;

public class AddUserPage extends TestBase
{
    Logger log;
    String rowId;
    List<WebElement> custType;
    Actions actions;
    String userRole;
    private int checkCount;
    WebDriverWait waitFor;
    private final String USER_CREATION_MSG = "User added successfully";
    private final String USER_CREATION_NO_HISTORICAL_MSG = "User is added but no historical requests found";
    private final String CUST_ADMIN_DOMAIN = "@bosh.com";
    private final String CUST_ADMIN_ACTIVATED_ERROR_MSG = "Active Administrator for this company exists";
    private final String EMTT_CUST_ADMIN_ACTIVATED_ERROR_MSG = "Active EMTT Administrator for this company exists.";
    private final String ROLE_NOT_SELECTED_ERROR = "Select the role name";
    private final String INVALID_FIRST_NAME_ERROR = "Enter valid First Name.";
    private final String INVALID_LAST_NAME_ERROR = "Enter valid Last Name.";
    private final String INVALID_CONTACT_NO_ERROR = "Enter valid contact number.";
    private final String INVALID_EMAIL_ERROR = "Enter valid Email.";
    private final String INVALID_COMPANY_NAME_ERROR = "Please select Company.";
    private final String INVALID_CRM_ID_ERROR = "CRM Id should be number.";
    private final String INVALID_SALES_ACCOUNT_MANAGER_ERROR = "Enter valid Sales Account Manager.";
    private final String INVALID_SALES_ENGINEER_ERROR = "Enter valid Sales Engineer.";
    @FindBy(id = "chkProvReq")
    WebElement provReqUserBtn;
    @FindBy(id = "chkEMTT")
    WebElement emtSupportUserBtn;
    @FindBy(id = "chkSDT")
    WebElement sdTroubleUserBtn;
    @FindBy(xpath = "//a[contains(text(),'Next')]")
    WebElement nextBtn;
    @FindBy(xpath = "//a[contains(text(),'Previous')]")
    WebElement previousBtn;
    @FindBy(xpath = "//a[contains(text(),'Finish')]")
    WebElement finishBtn;
    @FindBy(xpath = "//input[@value='Sales']")
    WebElement salesUser;
    @FindBy(xpath = "//input[@value='Sales Support']")
    WebElement salesSupportUser;
    @FindBy(xpath = "//input[@value='Service']")
    WebElement serviceUser;
    @FindBy(xpath = "//input[@value='Customer Administrator']")
    WebElement customerAdminUser;
    @FindBy(xpath = "//input[@value='Customer Support']")
    WebElement customerSuppUser;
    @FindBy(xpath = "//input[@value='EMTT Sales']")
    WebElement emttSalesUser;
    @FindBy(xpath = "//input[@value='EMTT Service Desk']")
    WebElement emttServiceDeskUser;
    @FindBy(xpath = "//input[@value='EMTT Customer Administrator']")
    WebElement emtttCustAdminUser;
    @FindBy(xpath = "//input[@value='EMTT Customer Support']")
    WebElement emtttCustSuppUser;
    @FindBy(xpath = "//input[@value='SDTool User']")
    WebElement sdToolUser;
    @FindBy(id = "custFirstName")
    WebElement custFirstName;
    @FindBy(id = "custEmail")
    WebElement custEmail;
    @FindBy(xpath = "//input[@id='custEmail']/following-sibling::span")
    WebElement sapDomain;
    @FindBy(id = "custLastName")
    WebElement custLastName;
    @FindBy(id = "custPhone")
    WebElement custPhone;
    @FindBy(id = "selectCompany")
    WebElement selectCompany;
    @FindBy(id = "selectprimaryContact")
    WebElement selectprimaryContact;
    @FindBy(id = "custaccountOwner")
    WebElement salesAccntManager;
    @FindBy(id = "userCaseOwner")
    WebElement salesEngineer;
    @FindBy(id = "custCrmId")
    WebElement custCrmId;
    @FindBy(id = "CRMContactID")
    WebElement crmContactID;
    @FindBy(id = "custEmailAlias")
    WebElement custEmailAlias;
    @FindBy(id = "crmAcctStatus")
    WebElement crmAcctStatus;
    @FindBy(xpath = "//div[@class='userError']")
    List<WebElement> userDetailsErrors;
    @FindBy(xpath = "//td[text()='Customer Administrator']/..")
    List<WebElement> custAdminsDetails;
    @FindBy(xpath = "//td[text()='EMTT Customer Administrator']/..")
    List<WebElement> emttCustAdminsDetails;
    @FindBy(id = "selectAllUS")
    WebElement selectAllUS;
    @FindBy(id = "selectAllLATAM")
    WebElement selectAllLATAM;
    @FindBy(id = "selectAllUK")
    WebElement selectAllUK;
    @FindBy(xpath = "//div[@id='prefEMTTPan']/div[@class='expand_heading']/span")
    WebElement emSupportToolWindow;
    @FindBy(id = "prefSDToolPan")
    WebElement sdTroubleshootingWindow;
    @FindBy(id = "prefProvReqPan")
    WebElement provReqWindow;
    @FindBy(xpath = "//div[@id='optOutSurveyRow']/label")
    WebElement optOutSurveyOptionsLabel;
    @FindBy(id = "optOutSurvNo")
    WebElement optOutSurveyNo;
    @FindBy(id = "optOutSurvYes")
    WebElement optOutSurveyYes;
    @FindBy(xpath = "//input[@id='optOutSurvYes']/following-sibling::span")
    WebElement optOutSurvYesLabel;
    @FindBy(xpath = "//input[@id='optOutSurvNo']/following-sibling::span")
    WebElement optOutSurvNoLabel;
    @FindBy(xpath = "//div[@id='includeMsgTxtRow']/label")
    WebElement dataExtractMsgOptionsLabel;
    @FindBy(id = "includeMsgTxtNo")
    WebElement dataExtractMsgNo;
    @FindBy(id = "includeMsgTxtYes")
    WebElement dataExtractMsgYes;
    @FindBy(xpath = "//input[@id='includeMsgTxtYes']/following-sibling::span")
    WebElement dataExtractMsgYesLabel;
    @FindBy(xpath = "//input[@id='includeMsgTxtNo']/following-sibling::span")
    WebElement dataExtractMsgNoLabel;
    @FindBy(xpath = "//div[@id='enableMsgTestRow']/label")
    WebElement enableMsgTestOptionsLabel;
    @FindBy(id = "enableMsgTestYes")
    WebElement enableMsgTestYes;
    @FindBy(id = "enableMsgTestNo")
    WebElement enableMsgTestNo;
    @FindBy(xpath = "//input[@id='enableMsgTestYes']/following-sibling::span")
    WebElement enableMsgTestYesLabel;
    @FindBy(xpath = "//input[@id='enableMsgTestNo']/following-sibling::span")
    WebElement enableMsgTestNoLabel;
    @FindBy(xpath = "//ul[contains(@class,'application_block')]/li/ul/li")
    List<WebElement> availableUsers;
    @FindBy(id = "cmpRoleError")
    WebElement cmpRoleError;
    @FindBy(id = "emttRoleError")
    WebElement emttRoleError;
    @FindBy(id = "sdtRoleError")
    WebElement sdtRoleError;
    @FindBy(id = "appListError")
    WebElement appListError;
    @FindBy(id = "custFirstNameError")
    WebElement firstNameError;
    @FindBy(id = "custLastNameError")
    WebElement lastNameError;
    @FindBy(id = "custEmailError")
    WebElement emailError;
    @FindBy(id = "custContactError")
    WebElement contactError;
    @FindBy(id = "cmp_error")
    WebElement companyNameError;
    @FindBy(id = "cCrmIdError")
    WebElement crmIdError;
    @FindBy(id = "crmContactIDError")
    WebElement crmContactIDError;
    @FindBy(id = "accountOwnerError")
    WebElement accountManagerError;
    @FindBy(id = "userCaseOwnerError")
    WebElement salesEngineerError;
    
    public AddUserPage() {
        this.log = Logger.getLogger((Class)AddUserPage.class);
        this.rowId = null;
        this.custType = null;
        this.actions = null;
        this.userRole = null;
        this.checkCount = 0;
        this.waitFor = new WebDriverWait(AddUserPage.driver, 1L);
        PageFactory.initElements(AddUserPage.driver, (Object)this);
        this.homepage = new HomePage();
        this.userManage = new UserManagementPage();
        this.actions = new Actions(AddUserPage.driver);
    }
    
    public void setCheckCount(final int value) {
        this.checkCount = value;
    }
    
    public void userRole(final String role) {
        if (role.equalsIgnoreCase("Sales")) {
            this.salesUser.click();
        }
        else if (role.equalsIgnoreCase("Sales Support")) {
            this.salesSupportUser.click();
        }
        else if (role.equalsIgnoreCase("Service")) {
            this.serviceUser.click();
        }
        else if (TestUtil.CUST_ADMIN_WAYS.contains(role.toLowerCase())) {
            this.customerAdminUser.click();
        }
        else if (role.equalsIgnoreCase("Customer Support")) {
            this.customerSuppUser.click();
        }
        else if (role.equalsIgnoreCase("EMTT Sales")) {
            this.emttSalesUser.click();
        }
        else if (role.equalsIgnoreCase("EMTT Service Desk")) {
            this.emttServiceDeskUser.click();
        }
        else if (TestUtil.EMTT_CUST_ADMIN_WAYS.contains(role.toLowerCase())) {
            this.emtttCustAdminUser.click();
        }
        else if (role.equalsIgnoreCase("EMTT Customer Supp")) {
            this.emtttCustSuppUser.click();
        }
        else if (role.equalsIgnoreCase("SDTool User")) {
            this.sdToolUser.click();
        }
    }
    
    public void unselectRegion() {
        if (this.selectAllUS.isSelected()) {
            this.selectAllUS.click();
        }
        if (this.selectAllLATAM.isSelected()) {
            this.selectAllLATAM.click();
        }
        if (this.selectAllUK.isSelected()) {
            this.selectAllUK.click();
        }
    }
    
    public void userRegion(final String region) {
        if (region.equalsIgnoreCase("US")) {
            this.selectAllUS.click();
        }
        else if (region.contains("LA")) {
            this.selectAllLATAM.click();
        }
        else if (region.equalsIgnoreCase("UK")) {
            this.selectAllUK.click();
        }
    }
    
    public void selectUserType(final String userType) {
        if (userType.contains("EMTT")) {
            if (!this.emtSupportUserBtn.isSelected()) {
                this.emtSupportUserBtn.click();
                AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.emttSalesUser));
            }
        }
        else if (userType.contains("SD")) {
            if (!this.sdTroubleUserBtn.isSelected()) {
                this.sdTroubleUserBtn.click();
                AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.sdToolUser));
            }
        }
        else if (!this.provReqUserBtn.isSelected()) {
            this.provReqUserBtn.click();
            AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.customerAdminUser));
        }
    }
    
    public void selectUserRole(final String roles) {
        if (roles.contains(",")) {
            final String[] multRoles = roles.split(",");
            String[] array;
            for (int length = (array = multRoles).length, j = 0; j < length; ++j) {
                final String i = array[j];
                this.selectUserType(i);
                this.userRole(i.trim());
            }
        }
        else {
            this.selectUserType(roles);
            this.userRole(roles);
        }
        this.clickOnNextBtn();
    }
    
    public void selectRegion(final String region) {
        if (region.contains(",")) {
            final String[] multRegion = region.split(",");
            String[] array;
            for (int length = (array = multRegion).length, j = 0; j < length; ++j) {
                final String i = array[j];
                this.userRegion(i.trim());
            }
        }
        else {
            this.userRegion(region);
        }
    }
    
    public void fillUserDetails() {
        this.custFirstName.clear();
        this.custLastName.clear();
        this.custEmail.clear();
        this.custPhone.clear();
        this.custFirstName.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getFirstName() });
        this.custLastName.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getLastName() });
        this.custEmail.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getEmail() });
        this.custPhone.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getPhoneNo() });
    }
    
    public boolean checkUserDetails() {
        int errorCount = 0;
        for (final WebElement error : this.userDetailsErrors) {
            if (error.isDisplayed()) {
                this.log.info((Object)error.getText());
                ++errorCount;
            }
        }
        return errorCount <= 0;
    }
    
    public void createCustAdminUser() {
        (AddUserPage.select = new Select(this.selectCompany)).selectByValue(AddUserPage.testDataObject.getCompanyName());
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.selectprimaryContact));
        (AddUserPage.select = new Select(this.selectprimaryContact)).selectByVisibleText(AddUserPage.testDataObject.getPrimaryContact());
        AddUserPage.wait.until((Function)ExpectedConditions.attributeToBe(this.custCrmId, "class", "uneditable"));
        AddUserPage.wait.until((Function)ExpectedConditions.attributeToBe(this.crmContactID, "class", "uneditable"));
        this.fillUserDetails();
        this.custEmail.clear();
        this.salesAccntManager.clear();
        this.salesEngineer.clear();
        this.custEmail.sendKeys(new CharSequence[] { String.valueOf(AddUserPage.testDataObject.getEmail()) + "@bosh.com" });
        this.salesAccntManager.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getSalesManager() });
        this.salesEngineer.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getSalesEngineer() });
    }
    
    public void deActCustAdmin(final String admin) {
        String[] multRole = null;
        this.custType = new ArrayList<WebElement>();
        this.homepage.redirectToUserManagementPage();
        AddUserPage.wait.until((Function)ExpectedConditions.visibilityOf(this.userManage.searchBtn));
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManage.searchBtn));
        this.userManage.searchBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.visibilityOf(this.userManage.searchOptionCompanyName));
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManage.searchOptionCompanyName));
        if (AddUserPage.testDataObject.getCompanyName().contains("(")) {
            final int index = AddUserPage.testDataObject.getCompanyName().indexOf("(");
            this.userManage.searchOptionCompanyName.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getCompanyName().substring(0, index) });
        }
        else {
            this.userManage.searchOptionCompanyName.sendKeys(new CharSequence[] { AddUserPage.testDataObject.getCompanyName() });
        }
        final Select role = new Select(this.userManage.searchOptionRoleName);
        role.selectByVisibleText(admin);
        this.userManage.searchOptionGoBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManage.searchBtn));
        if (this.userRole.toLowerCase().contains(",")) {
            multRole = this.userRole.toLowerCase().split(",");
            String[] array;
            for (int length = (array = multRole).length, i = 0; i < length; ++i) {
                final String roleType = array[i];
                if (TestUtil.CUST_ADMIN_WAYS.contains(roleType.trim())) {
                    this.custType.addAll(this.custAdminsDetails);
                }
                if (TestUtil.EMTT_CUST_ADMIN_WAYS.contains(roleType.trim())) {
                    this.custType.addAll(this.emttCustAdminsDetails);
                }
            }
        }
        else if (TestUtil.CUST_ADMIN_WAYS.contains(this.userRole.toLowerCase())) {
            this.custType.addAll(this.custAdminsDetails);
        }
        else if (TestUtil.EMTT_CUST_ADMIN_WAYS.contains(this.userRole.toLowerCase())) {
            this.custType.addAll(this.emttCustAdminsDetails);
        }
        this.userManage.deActivateMultUser((List)this.custType);
        this.createUser();
    }
    
    public void checkUserCreationStatus() {
        final Alert alert = AddUserPage.driver.switchTo().alert();
        final String userStatus = alert.getText();
        if (userStatus.contains("User added successfully") || userStatus.contains("User is added but no historical requests found")) {
            Assert.assertTrue(true, userStatus);
            alert.accept();
        }
        else if (userStatus.contains("Active Administrator for this company exists")) {
            alert.accept();
            this.log.info((Object)"Going to deactivate active customer adminstrator");
            this.deActCustAdmin(TestUtil.CUST_ADMIN);
            this.homepage.extendedWait.until((Function)ExpectedConditions.alertIsPresent());
            ++this.checkCount;
            if (this.checkCount < 4) {
                this.checkUserCreationStatus();
            }
            else {
                Assert.assertTrue(false, "Tried twice, not able to deactivate the Admin");
            }
        }
        else if (userStatus.contains("Active EMTT Administrator for this company exists.")) {
            alert.accept();
            this.log.info((Object)"Going to deactivate active EMTT adminstrator");
            this.deActCustAdmin(TestUtil.EMTT_CUST_ADMIN);
            this.homepage.extendedWait.until((Function)ExpectedConditions.alertIsPresent());
            ++this.checkCount;
            if (this.checkCount < 4) {
                this.checkUserCreationStatus();
            }
            else {
                Assert.assertTrue(false, "Tried twice, not able to deactivate the Admin");
            }
        }
        else {
            Assert.assertTrue(false, userStatus);
            alert.accept();
        }
    }
    
    public void clickOnAllApplicationType() {
        this.redirectingToAddUserPage();
        this.emtSupportUserBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.emttSalesUser));
        this.sdTroubleUserBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.sdToolUser));
        this.provReqUserBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.salesUser));
    }
    
    public ArrayList<String> verifyingAllUsersAvailable() {
        final ArrayList<String> allUsers = new ArrayList<String>();
        this.clickOnAllApplicationType();
        for (final WebElement i : this.availableUsers) {
            allUsers.add(i.getText());
        }
        return allUsers;
    }
    
    public void verifyErrorMsgsOfRoles() {
        Assert.assertNotNull((Object)this.cmpRoleError.isDisplayed());
        Assert.assertTrue(this.cmpRoleError.getText().equals("Select the role name"), "Not getting proper error for CMP Role");
        this.log.info((Object)"Proper error message is coming for Provision Request application, if we won't select an user role and try to proceed");
        Assert.assertNotNull((Object)this.emttRoleError.isDisplayed());
        Assert.assertTrue(this.emttRoleError.getText().equals("Select the role name"), "Not getting proper error for EMTT Role");
        this.log.info((Object)"Proper error messages is coming for EM Support Tool application, if we won't select an user role and try to proceed");
        Assert.assertNotNull((Object)this.sdtRoleError.isDisplayed());
        Assert.assertTrue(this.sdtRoleError.getText().equals("Select the role name"), "Not getting proper error for SD User Role");
        this.log.info((Object)"Proper error messages is coming for SD Troubleshooting application, if we won't select an user role and try to proceed");
    }
    
    public String getPublicEmailIdError(final String emailId) {
        this.custEmail.sendKeys(new CharSequence[] { emailId });
        this.clickOnNextBtn();
        return this.emailError.getText();
    }
    
    public void verifyErrorMessagesOfBasicFields() {
        this.clickOnNextBtn();
        Assert.assertNotNull((Object)this.firstNameError.isDisplayed());
        Assert.assertTrue(this.firstNameError.getText().equals("Enter valid First Name."), "Not getting proper error for First Name field");
        Assert.assertNotNull((Object)this.lastNameError.isDisplayed());
        Assert.assertTrue(this.lastNameError.getText().equals("Enter valid Last Name."), "Not getting proper error for Last Name field");
        Assert.assertNotNull((Object)this.emailError.isDisplayed());
        Assert.assertTrue(this.emailError.getText().equals("Enter valid Email."), "Not getting proper error for Email Id field");
        Assert.assertNotNull((Object)this.contactError.isDisplayed());
        Assert.assertTrue(this.contactError.getText().equals("Enter valid contact number."), "Not getting proper error for Contact Number field");
    }
    
    public void verifyUserDetailsErrorMessagesForAdmin() {
        Assert.assertNotNull((Object)this.companyNameError.isDisplayed());
        Assert.assertTrue(this.companyNameError.getText().equals("Please select Company."), "Not getting proper error for Company Name field");
        Assert.assertNotNull((Object)this.crmIdError.isDisplayed());
        Assert.assertTrue(this.crmIdError.getText().equals("CRM Id should be number."), "Not getting proper error for CMR ID field");
        Assert.assertNotNull((Object)this.salesEngineerError.isDisplayed());
        Assert.assertTrue(this.salesEngineerError.getText().equals("Enter valid Sales Engineer."), "Not getting proper error for Sales Engineer field");
        Assert.assertNotNull((Object)this.accountManagerError.isDisplayed());
        Assert.assertTrue(this.accountManagerError.getText().equals("Enter valid Sales Account Manager."), "Not getting proper error for Sales Account Manager field");
        Assert.assertNotNull((Object)this.crmContactIDError.isDisplayed());
        Assert.assertTrue(this.crmContactIDError.getText().equals("CRM Id should be number."), "Not getting proper error for CRM Contact Id field");
    }
    
    public String getWrongEMTTUserSelectionErrorMsgs() {
        Assert.assertNotNull((Object)this.emttRoleError.isDisplayed());
        return this.emttRoleError.getText();
    }
    
    public String getWrongSDUserSelectionErrorMsgs() {
        Assert.assertNotNull((Object)this.sdtRoleError.isDisplayed());
        return this.sdtRoleError.getText();
    }
    
    public void redirectingToAddUserPage() {
        this.homepage.redirectToUserManagementPage();
        this.log.info((Object)"Clicked on User Management Button");
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManage.addUserBtn));
        this.userManage.addUserBtn.click();
        AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.provReqUserBtn));
    }
    
    public boolean checkValidFields(final List<String> listToCheck, final List<String> fromList) {
        boolean result = true;
        final List<String> extraMachingField = listToCheck.stream().filter(field -> !fromList.contains(field)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
        final List<String> expectedFields = fromList.stream().filter(field -> !listToCheck.contains(field)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
        if (extraMachingField.size() > 0) {
            result = false;
            this.log.info((Object)("These are the extra fields present on the page, which shouldn't be there" + extraMachingField));
        }
        if (expectedFields.size() > 0) {
            result = false;
            this.log.info((Object)("These fields should be present on the page" + expectedFields));
        }
        return result;
    }
    
    public String getAppListError() {
        return this.appListError.getText();
    }
    
    public void clickOnNextBtn() {
        this.nextBtn.click();
    }
    
    public void clickOnPreviousBtn() {
        this.previousBtn.click();
    }
    
    public boolean provReqIsPresent() {
        boolean flag = false;
        if (this.provReqUserBtn.isDisplayed()) {
            flag = true;
        }
        return flag;
    }
    
    public WebElement getProvReqElement() {
        return this.provReqUserBtn;
    }
    
    public boolean verifyElementNotPresent(final WebElement element) {
        try {
            this.waitFor.until((Function)ExpectedConditions.visibilityOf(element));
            this.log.info((Object)(element + " should not be present on the page"));
            return false;
        }
        catch (TimeoutException ex) {
            return true;
        }
    }
    
    public boolean isAttributePresent(final WebElement element, final String attribute) {
        boolean result = false;
        try {
            final String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        }
        catch (Exception ex) {}
        return result;
    }
    
    public void createNormalUser() {
        this.fillUserDetails();
        Assert.assertTrue(this.sapDomain.isDisplayed(), "@sap.com is not coming for User =" + AddUserPage.testDataObject.getEmail() + " having role =" + AddUserPage.testDataObject.getRole());
        Assert.assertTrue(this.verifyElementNotPresent(this.selectCompany));
        Assert.assertTrue(this.verifyElementNotPresent(this.selectprimaryContact));
        Assert.assertTrue(this.verifyElementNotPresent(this.crmContactID));
        Assert.assertTrue(this.verifyElementNotPresent(this.custCrmId));
        Assert.assertTrue(this.verifyElementNotPresent(this.salesAccntManager));
        Assert.assertTrue(this.verifyElementNotPresent(this.salesEngineer));
        Assert.assertTrue(this.verifyElementNotPresent(this.custEmailAlias));
        Assert.assertTrue(this.verifyElementNotPresent(this.crmAcctStatus));
        this.log.info((Object)("Only FirstName, LastName, Email and Phone fields are coming for User =" + AddUserPage.testDataObject.getEmail() + "and with role = " + AddUserPage.testDataObject.getRole()));
    }
    
    public void createUser() {
        String[] multRole = null;
        final String yes = "Yes";
        final String no = "No";
        this.userRole = AddUserPage.testDataObject.getRole();
        this.redirectingToAddUserPage();
        this.selectUserRole(this.userRole);
        if (TestUtil.EMTT_CUST_ADMIN_WAYS.contains(this.userRole.toLowerCase()) || TestUtil.CUST_ADMIN_WAYS.contains(this.userRole.toLowerCase())) {
            this.createCustAdminUser();
        }
        else if (this.userRole.toLowerCase().contains(",")) {
            multRole = this.userRole.toLowerCase().split(",");
            final String[] array;
            if ((array = multRole).length != 0) {
                final String roleType = array[0];
                if (TestUtil.EMTT_CUST_ADMIN_WAYS.contains(roleType.trim()) || TestUtil.CUST_ADMIN_WAYS.contains(roleType.trim())) {
                    this.createCustAdminUser();
                }
                else {
                    this.createNormalUser();
                }
            }
        }
        else {
            this.createNormalUser();
        }
        this.clickOnNextBtn();
        Assert.assertTrue(this.checkUserDetails());
        if (!this.userRole.toLowerCase().startsWith("emtt") && !this.userRole.toLowerCase().startsWith("sdtool")) {
            AddUserPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.selectAllUK));
            this.selectRegion(AddUserPage.testDataObject.getRegion());
            this.log.info((Object)"Selected the region");
        }
        else {
            Assert.assertTrue(this.verifyElementNotPresent(this.provReqWindow), "Provisioning Request window should not be coming for User = " + AddUserPage.testDataObject.getEmail() + " having role = " + AddUserPage.testDataObject.getRole());
        }
        if (this.userRole.toLowerCase().contains("sdtool")) {
            Assert.assertNotNull((Object)this.sdTroubleshootingWindow, "SD TroubleShooting window is not coming for user = " + AddUserPage.testDataObject.getEmail() + "and user role = " + AddUserPage.testDataObject.getRole());
        }
        else {
            Assert.assertTrue(this.verifyElementNotPresent(this.sdTroubleshootingWindow), "SD TroubleShooting window should not come for user = " + AddUserPage.testDataObject.getEmail() + " having role = " + AddUserPage.testDataObject.getRole());
        }
        if (this.userRole.toLowerCase().contains("emtt")) {
            this.emSupportToolWindow.click();
            final String surveyOption = AddUserPage.testDataObject.getSurveyOption();
            if (this.userRole.toLowerCase().contains(TestUtil.EMTT_SALES_USER)) {
                Assert.assertTrue(this.optOutSurveyOptionsLabel.getText().equals(TestUtil.EMTT_OPT_SURVEY_OPTION), "Opt out of Survey option is not coming for EMTT sales user = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvYesLabel.getText().equals(yes), "Yes Btn is not coming for Opt out of Survey option for EMTT sales user =  " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvNoLabel.getText().equals(no), "No Btn is not coming for Opt out of Survey option for EMTT sales user =  " + AddUserPage.testDataObject.getEmail());
                if (surveyOption.toLowerCase().contains("y")) {
                    this.optOutSurveyYes.click();
                }
                else if (surveyOption.toLowerCase().contains("n")) {
                    this.optOutSurveyNo.click();
                }
                Assert.assertTrue(this.verifyElementNotPresent(this.dataExtractMsgOptionsLabel), "Include Msg text option should not come for User = " + AddUserPage.testDataObject.getEmail() + " having role =" + AddUserPage.testDataObject.getRole());
                Assert.assertTrue(this.verifyElementNotPresent(this.enableMsgTestOptionsLabel), "Enable Msg Test option should not come for User = " + AddUserPage.testDataObject.getEmail() + " having role =" + AddUserPage.testDataObject.getRole());
            }
            else if (this.userRole.toLowerCase().contains(TestUtil.EMTT_SERVICE_DESK_USER)) {
                Assert.assertTrue(this.optOutSurveyOptionsLabel.getText().equals(TestUtil.EMTT_OPT_SURVEY_OPTION), "Opt out of Survey option is not coming for EMTT Service Desk User = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvYesLabel.getText().equals(yes), "Yes Btn is not coming for Opt out of Survey option for EMTT Service Desk User = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvNoLabel.getText().equals(no), "No Btn is not coming for Opt out of Survey option for EMTT Service Desk User = " + AddUserPage.testDataObject.getEmail());
                if (surveyOption.toLowerCase().contains("y")) {
                    this.optOutSurveyYes.click();
                }
                else if (surveyOption.toLowerCase().contains("n")) {
                    this.optOutSurveyNo.click();
                }
                Assert.assertTrue(this.verifyElementNotPresent(this.dataExtractMsgOptionsLabel), "Include Msg text option should not come for User = " + AddUserPage.testDataObject.getEmail() + " having role =" + AddUserPage.testDataObject.getRole());
                Assert.assertTrue(this.verifyElementNotPresent(this.enableMsgTestOptionsLabel), " Enable Msg Test option should not come for User = " + AddUserPage.testDataObject.getEmail() + " having role =" + AddUserPage.testDataObject.getRole());
            }
            else if (this.userRole.toLowerCase().contains(TestUtil.EMTT_CUST_ADMIN_WAYS.get(0)) || this.userRole.toLowerCase().contains(TestUtil.EMTT_CUST_ADMIN_WAYS.get(1)) || this.userRole.toLowerCase().contains(TestUtil.EMTT_CUST_ADMIN_WAYS.get(2))) {
                final String enableTestMsg = AddUserPage.testDataObject.getEnableMessageTestOption();
                final String dataExtractMsg = AddUserPage.testDataObject.getDataExtractOption();
                Assert.assertTrue(this.optOutSurveyOptionsLabel.getText().equals(TestUtil.EMTT_OPT_SURVEY_OPTION), "Opt out of Survey option is not coming for EMTT Customer Admin  = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvYesLabel.getText().equals(yes), "Yes Btn is not coming for Opt out of Survey option for EMTT Customer Admin = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.optOutSurvNoLabel.getText().equals(no), "No Btn is not coming for Opt out of Survey option for EMTT Customer Admin = " + AddUserPage.testDataObject.getEmail());
                if (surveyOption.toLowerCase().contains("y")) {
                    this.optOutSurveyYes.click();
                }
                else if (surveyOption.toLowerCase().contains("n")) {
                    this.optOutSurveyNo.click();
                }
                Assert.assertTrue(this.dataExtractMsgOptionsLabel.getText().equals(TestUtil.EMTT_DATA_EXTRACTION_OPTION), "Data extract message option is not coming for EMTT Customer Admin = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.dataExtractMsgYesLabel.getText().equals(yes), "Yes Btn is not coming for Data extract message option for EMTT Customer Admin = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.dataExtractMsgNoLabel.getText().equals(no), "No Btn is not coming for Data extract message option for EMTT Customer Admin = " + AddUserPage.testDataObject.getEmail());
                Assert.assertFalse(this.isAttributePresent(this.dataExtractMsgYes, "checked"), "Yes shouldn't be selected for 'Data Extract Option' by default for User = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.isAttributePresent(this.dataExtractMsgNo, "checked"), "No should be selected for 'Data Extract Option' by default for User = " + AddUserPage.testDataObject.getEmail());
                this.log.info((Object)("No is selected by default as Data Extract Message for User = " + AddUserPage.testDataObject.getEmail()));
                if (dataExtractMsg.toLowerCase().contains("y")) {
                    this.dataExtractMsgYes.click();
                }
                else if (dataExtractMsg.toLowerCase().contains("n")) {
                    this.dataExtractMsgNo.click();
                }
                Assert.assertTrue(this.enableMsgTestOptionsLabel.getText().equals(TestUtil.EMTT_ENABLE_TEST_OPTION), "Enable Message Test option is not coming for EMTT Customer Admin");
                Assert.assertTrue(this.enableMsgTestYesLabel.getText().equals(yes), "Yes Btn is not coming for Enable Message Test option for EMTT Customer Admin");
                Assert.assertTrue(this.enableMsgTestNoLabel.getText().equals(no), "No Btn is not coming for Enable Message Test option for EMTT Customer Admin");
                Assert.assertFalse(this.isAttributePresent(this.enableMsgTestYes, "checked"), "Yes shouldn't selected for 'Enable Test Option' by default for User = " + AddUserPage.testDataObject.getEmail());
                Assert.assertTrue(this.isAttributePresent(this.enableMsgTestNo, "checked"), "No should be selected for 'Enable Test Option' by default for User = " + AddUserPage.testDataObject.getEmail());
                this.log.info((Object)("No is selected by default as 'Enable Test Option' for User = " + AddUserPage.testDataObject.getEmail()));
                if (enableTestMsg.toLowerCase().contains("y")) {
                    this.enableMsgTestYes.click();
                }
                else if (enableTestMsg.toLowerCase().contains("n")) {
                    this.enableMsgTestNo.click();
                }
            }
        }
        else {
            Assert.assertTrue(this.verifyElementNotPresent(this.emSupportToolWindow), "EMTT Support Window should not be coming for User = " + AddUserPage.testDataObject.getEmail() + " having role = " + AddUserPage.testDataObject.getRole());
        }
        this.finishBtn.click();
        this.log.info((Object)"Clicked on finish button, waiting for the alert to come");
    }
}