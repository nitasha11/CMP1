package pages;


import java.util.ArrayList;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.Iterator;
import utilities.TestUtil;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import Base.TestBase;

public class UserManagementPage extends Base.TestBase
{
    JavascriptExecutor jse;
    Logger log;
    private final String USER_IS_DELETED_MSG = "User deleted successfully";
    private final String EXISTING_CUST_SUPP_USER_WILL_BE_MODIFIED_MSG = "The existing Customer Administrator for this company will be converted to Customer Support User. Please Confirm";
    private final String EXISTING_EMTT_CUST_SUPP_USER_WILL_BE_MODIFIED_MSG = "The existing EMTT Customer Administrator for this company will be converted to EMTT Customer Support User. Please Confirm";
    @FindBy(id = "userManagementTopLink")
    public WebElement userManagementBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Live Users')]")
    WebElement liveUsers;
    @FindBy(xpath = "//div/span[contains(text(),'Live Users')]")
    WebElement liveUsersText;
    @FindBy(xpath = "//span/span[contains(text(),'Search')]")
    public WebElement searchBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Add')]")
    WebElement addUserBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Activate')]")
    WebElement activateUserBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Deactivate')]")
    WebElement deactivateUserBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Modify Role')]")
    WebElement modifyRoleBtn;
    @FindBy(xpath = "//span/span[contains(text(),'Delete')]")
    WebElement deleteBtn;
    @FindBy(xpath = "//button/span[contains(text(),'Ok')]")
    WebElement liveUserOkBtn;
    @FindBy(id = "firstname")
    WebElement searchOptionFirstName;
    @FindBy(id = "phoneno")
    WebElement searchOptionPhoneNo;
    @FindBy(id = "lastname")
    WebElement searchOptionLastName;
    @FindBy(id = "companyname")
    WebElement searchOptionCompanyName;
    @FindBy(id = "emailid")
    public WebElement searchOptionEmailId;
    @FindBy(id = "rolename")
    WebElement searchOptionRoleName;
    @FindBy(xpath = "//select[@id='rolename']/option")
    List<WebElement> searchOptionRoleNameList;
    @FindBy(xpath = "//a[contains(text(),'Go')]")
    public WebElement searchOptionGoBtn;
    @FindBy(xpath = "//a[contains(text(),'Clear')]")
    public WebElement searchOptionClearBtn;
    @FindBy(xpath = "//span[contains(text(),'Yes')]")
    WebElement yesBtn;
    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    WebElement okBtn;
    @FindBy(xpath = "//span[contains(text(),'Cancel')]")
    WebElement cancelBtn;
    @FindBy(id = "showMessageDialog")
    WebElement userDeletionStatus;
    @FindBy(id = "modify_rolename")
    WebElement modifyRole;
    @FindBy(xpath = "//span[contains(text(),'Submit')]")
    WebElement modifyRoleSubmitBtn;
    @FindBy(xpath = "//span[contains(text(),'Select a Customer Support')]")
    WebElement modifyRoleSelectCustSpprtMsg;
    @FindBy(xpath = "//div[contains(@id,'select-customer-support')]//input")
    WebElement modifyRoleNewCustAdmin;
    @FindBy(xpath = "//div[@aria-describedby='select-customer-support-dialog-form']//span[contains(text(),'Submit')]")
    WebElement modifyRoleNewCustAdminSubmitBtn;
    @FindBy(id = "showMessageDialog")
    WebElement actionMsg;
    @FindBy(id = "selectionOfCustomerSupport")
    WebElement selectionOfCustomerSupport;
    @FindBy(id = "selectionOfEmttCustomerSupport")
    WebElement selectionOfEMTTCustomerSupport;
    @FindBy(xpath = "//table[contains(@class,'jtable')]//th")
    WebElement recordsTable;
    @FindBy(id = "view_for_user")
    WebElement viewUserDetailsBox;
    @FindBy(id = "outOutImage")
    WebElement viewUserEMSupportTool;
    @FindBy(id = "confirmMessageDialog")
    WebElement confirmMessageDialog;
    @FindBy(xpath = "//div[@id='IncludeMessageTextSection']/label[contains(@for,'includeMessageText')]")
    WebElement viewUserDataExtractValue;
    @FindBy(xpath = "//div[@id='dialogBox_view']/following-sibling::div//button/span[contains(text(),'Close')]")
    WebElement viewUserCloseButton;
    @FindBy(xpath = "//div[@id='searchBox']/following-sibling::table/tbody/tr")
    List<WebElement> tableRecords;
    
    public UserManagementPage() {
        this.jse = null;
        this.log = Logger.getLogger((Class)UserManagementPage.class);
        PageFactory.initElements(UserManagementPage.driver, (Object)this);
        this.jse = (JavascriptExecutor)UserManagementPage.driver;
        this.homepage = new HomePage();
    }
    
    public void recordsCheckboxClick(final String recordIdentifier) {
        final WebElement userToPerformAction = UserManagementPage.driver.findElement(By.xpath("//td[contains(text(),'" + recordIdentifier + "')]/../td[1]"));
        userToPerformAction.click();
    }
    
    public void waitForViewUserBoxToCome() {
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.viewUserDetailsBox));
    }
    
    public void selectIdentifier(final String identifier, String value) {
        if (identifier.equals(TestUtil.SEARCH_BY_FIRST_NAME)) {
            this.searchOptionFirstName.sendKeys(new CharSequence[] { value });
        }
        else if (identifier.equalsIgnoreCase(TestUtil.SEARCH_BY_LAST_NAME)) {
            this.searchOptionLastName.sendKeys(new CharSequence[] { value });
        }
        else if (identifier.equalsIgnoreCase(TestUtil.SEARCH_BY_EMAIL)) {
            this.searchOptionEmailId.sendKeys(new CharSequence[] { value });
        }
        else if (identifier.equalsIgnoreCase(TestUtil.SEARCH_BY_PHONE_NO)) {
            value = String.valueOf(value.substring(0, 3)) + "-" + value.substring(3, 6) + "-" + value.substring(6, 9) + value.charAt(9);
            this.searchOptionPhoneNo.sendKeys(new CharSequence[] { value });
        }
        else if (identifier.equalsIgnoreCase(TestUtil.SEARCH_BY_COMPANY_NAME)) {
            this.searchOptionCompanyName.sendKeys(new CharSequence[] { value });
        }
        else if (identifier.equalsIgnoreCase(TestUtil.SEARCH_BY_ROLE)) {
            this.searchOptionRoleName.click();
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (final WebElement role : this.searchOptionRoleNameList) {
                if (role.getText().equalsIgnoreCase(value)) {
                    role.click();
                }
            }
        }
    }
    
    public void splitIdentifierAndSendValue(final String identAndColumnSeperatedvalue) {
        String[] ident = null;
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionGoBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
        ident = identAndColumnSeperatedvalue.split(":");
        final String identifier = ident[0];
        final String value = ident[1];
        this.selectIdentifier(identifier, value);
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
    }
    
    public void verifySearchedRecords(final String identifier, final String value) {
        final String recordFirstNameXpath = "//td[2]";
        final String recordLastNameXpath = "//td[3]";
        final String recordEmailXpath = "//td[4]";
        final String recordPhoneNoXpath = "//td[5]";
        final String recordCompanyNameXpath = "//td[6]";
        final String recordRoleXpath = "//td[7]";
        String searchedValue = null;
        for (final WebElement record : this.tableRecords) {
            if (identifier.equals(TestUtil.SEARCH_BY_FIRST_NAME)) {
                searchedValue = record.findElement(By.xpath(recordFirstNameXpath)).getText();
            }
            else if (identifier.equals(TestUtil.SEARCH_BY_LAST_NAME)) {
                searchedValue = record.findElement(By.xpath(recordLastNameXpath)).getText();
            }
            else if (identifier.equals(TestUtil.SEARCH_BY_EMAIL)) {
                searchedValue = record.findElement(By.xpath(recordEmailXpath)).getText();
            }
            else if (identifier.equals(TestUtil.SEARCH_BY_PHONE_NO)) {
                searchedValue = record.findElement(By.xpath(recordPhoneNoXpath)).getText();
                searchedValue = String.valueOf(searchedValue.substring(0, 3)) + searchedValue.substring(4, 7) + searchedValue.substring(8, 11) + searchedValue.charAt(11);
            }
            else if (identifier.equals(TestUtil.SEARCH_BY_COMPANY_NAME)) {
                searchedValue = record.findElement(By.xpath(recordCompanyNameXpath)).getText();
            }
            else if (identifier.equals(TestUtil.SEARCH_BY_ROLE)) {
                searchedValue = record.findElement(By.xpath(recordRoleXpath)).getText();
            }
            Assert.assertTrue(searchedValue.equals(value), "Searched value is not coming as expected, value coming is = " + searchedValue);
        }
    }
    
    public void searchRecordsWithMultipleIdentifiers(final List<String> identifiersWithColumnSeperatedvalue) {
        UserManagementPage.driver.navigate().refresh();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchBtn));
        this.searchBtn.click();
        for (final String i : identifiersWithColumnSeperatedvalue) {
            this.splitIdentifierAndSendValue(i);
        }
        this.searchOptionGoBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionGoBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
        for (final String i : identifiersWithColumnSeperatedvalue) {
            final String[] ident = i.split(":");
            final String identifier = ident[0];
            final String value = ident[1];
            this.verifySearchedRecords(identifier, value);
        }
    }
    
    public void searchRecordsWithSingleIdentifier(final String identAndColumnSeperatedvalue) {
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchBtn));
        this.searchBtn.click();
        this.splitIdentifierAndSendValue(identAndColumnSeperatedvalue);
        this.searchOptionGoBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionGoBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
        final String[] ident = identAndColumnSeperatedvalue.split(":");
        final String identifier = ident[0];
        final String value = ident[1];
        this.verifySearchedRecords(identifier, value);
    }
    
    public void searchRecordByEmail(final String emailId) {
        this.searchBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionGoBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
        this.searchOptionEmailId.sendKeys(new CharSequence[] { emailId });
        this.searchOptionGoBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.recordsTable));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.recordsTable));
    }
    
    public String activateUser(final String recordIdentifier) {
        String status = null;
        final String userRow = "//td[contains(text(),'" + recordIdentifier + "')]/..";
        this.recordsCheckboxClick(recordIdentifier);
        final String rowStatus = UserManagementPage.driver.findElement(By.xpath(userRow)).getAttribute("class");
        if (rowStatus.contains("Deactivated")) {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.activateUserBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.yesBtn));
            this.jse.executeScript("arguments[0].click();", new Object[] { this.yesBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        else {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.activateUserBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        this.jse.executeScript("arguments[0].click();", new Object[] { this.okBtn });
        return status;
    }
    
    public String deActivateUser(final String recordIdentifier) {
        String status = null;
        final WebElement recordToDisable = UserManagementPage.driver.findElement(By.xpath("//td[contains(text(),'" + recordIdentifier + "')]/.."));
        final String recordStatus = recordToDisable.getAttribute("class");
        this.recordsCheckboxClick(recordIdentifier);
        if (!recordStatus.contains("Deactivated")) {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.deactivateUserBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.yesBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.yesBtn));
            this.jse.executeScript("arguments[0].click();", new Object[] { this.yesBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        else {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.deactivateUserBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        this.jse.executeScript("arguments[0].click();", new Object[] { this.okBtn });
        return status;
    }
    
    public String deActivateMultUser(final List<WebElement> multRecords) {
        String status = null;
        for (final WebElement record : multRecords) {
            final String recordStatus = record.getAttribute("class");
            if (!recordStatus.contains("Deactivated")) {
                final String rowId = record.getAttribute("id");
                final WebElement custAdminCheckBox = UserManagementPage.driver.findElement(By.xpath("//tr[@id='" + rowId + "']/td[1]"));
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(custAdminCheckBox));
                UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(custAdminCheckBox));
                this.jse.executeScript("arguments[0].click();", new Object[] { custAdminCheckBox });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.deactivateUserBtn));
                UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.deactivateUserBtn));
                this.jse.executeScript("arguments[0].click();", new Object[] { this.deactivateUserBtn });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.yesBtn));
                UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.yesBtn));
                this.jse.executeScript("arguments[0].click();", new Object[] { this.yesBtn });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
                UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
                status = this.actionMsg.getText();
                this.jse.executeScript("arguments[0].click();", new Object[] { this.okBtn });
            }
        }
        return status;
    }
    
    public String modifyRole(final String recordIdentifier) {
        String status = null;
        String text = null;
        final String userRow = "//td[contains(text(),'" + recordIdentifier + "')]/..";
        final String role = UserManagementPage.driver.findElement(By.xpath(String.valueOf(userRow) + "/td[7]")).getText();
        this.recordsCheckboxClick(recordIdentifier);
        final String rowStatus = UserManagementPage.driver.findElement(By.xpath(userRow)).getAttribute("class");
        if (rowStatus.contains("Deactivated")) {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        else if (role.equalsIgnoreCase(TestUtil.CUST_SUPP) || role.equalsIgnoreCase(TestUtil.EMTT_CUST_SUPP)) {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.modifyRole));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.modifyRole));
            final Select selectRole = new Select(this.modifyRole);
            if (role.equalsIgnoreCase(TestUtil.CUST_SUPP)) {
                selectRole.selectByVisibleText(TestUtil.CUST_ADMIN);
            }
            else if (role.equalsIgnoreCase(TestUtil.EMTT_CUST_SUPP)) {
                selectRole.selectByVisibleText(TestUtil.EMTT_CUST_ADMIN);
            }
            this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleSubmitBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.yesBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.yesBtn));
            final String confirmationMsg = this.confirmMessageDialog.getText();
            if (role.equalsIgnoreCase(TestUtil.CUST_SUPP)) {
                Assert.assertTrue(confirmationMsg.equals("The existing Customer Administrator for this company will be converted to Customer Support User. Please Confirm"));
            }
            else if (role.equalsIgnoreCase(TestUtil.EMTT_CUST_SUPP)) {
                Assert.assertTrue(confirmationMsg.equals("The existing EMTT Customer Administrator for this company will be converted to EMTT Customer Support User. Please Confirm"));
            }
            this.jse.executeScript("arguments[0].click();", new Object[] { this.yesBtn });
            this.homepage.extendedWait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            this.homepage.extendedWait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        else if (role.equalsIgnoreCase(TestUtil.CUST_ADMIN) || role.equalsIgnoreCase(TestUtil.EMTT_CUST_ADMIN)) {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.modifyRole));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.modifyRole));
            final Select selectRole = new Select(this.modifyRole);
            if (role.equalsIgnoreCase(TestUtil.CUST_ADMIN)) {
                selectRole.selectByVisibleText(TestUtil.CUST_SUPP);
                this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleSubmitBtn });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.selectionOfCustomerSupport));
                text = this.selectionOfCustomerSupport.getText();
            }
            else if (role.equalsIgnoreCase(TestUtil.EMTT_CUST_ADMIN)) {
                selectRole.selectByVisibleText(TestUtil.EMTT_CUST_SUPP);
                this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleSubmitBtn });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.selectionOfEMTTCustomerSupport));
                text = this.selectionOfEMTTCustomerSupport.getText();
            }
            if (text.equals(TestUtil.NO_CUST_SUPP_MSG)) {
                status = text;
                this.jse.executeScript("arguments[0].click();", new Object[] { this.cancelBtn });
            }
            else if (text.equals(TestUtil.NO_EMTT_CUST_SUPP_MSG)) {
                status = text;
                this.jse.executeScript("arguments[0].click();", new Object[] { this.cancelBtn });
            }
            else {
                this.modifyRoleNewCustAdmin.click();
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.modifyRoleNewCustAdminSubmitBtn));
                UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.modifyRoleNewCustAdminSubmitBtn));
                this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleNewCustAdminSubmitBtn });
                UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.actionMsg));
                status = this.actionMsg.getText();
            }
        }
        else {
            this.jse.executeScript("arguments[0].click();", new Object[] { this.modifyRoleBtn });
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.okBtn));
            UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
            status = this.actionMsg.getText();
        }
        return status;
    }
    
    public void deleteUser(final String emailId) {
        this.homepage.redirectToUserManagementPage();
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.deleteBtn));
        this.searchBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionEmailId));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionEmailId));
        this.searchOptionEmailId.clear();
        this.searchOptionEmailId.sendKeys(new CharSequence[] { UserManagementPage.testDataObject.getEmail() });
        this.searchOptionGoBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.searchOptionGoBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchOptionGoBtn));
        this.recordsCheckboxClick(emailId);
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.deleteBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.deleteBtn));
        this.jse.executeScript("arguments[0].click();", new Object[] { this.deleteBtn });
        UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.yesBtn));
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.yesBtn));
        this.jse.executeScript("arguments[0].click();", new Object[] { this.yesBtn });
        this.homepage.extendedWait.until((Function)ExpectedConditions.elementToBeClickable(this.okBtn));
        Assert.assertTrue("User deleted successfully".equals(this.userDeletionStatus.getText()));
        this.jse.executeScript("arguments[0].click();", new Object[] { this.okBtn });
    }
    
    public List<WebElement> getTableRecords() {
        return this.tableRecords;
    }
    
    public int getNoOfTableRecords() {
        return this.tableRecords.size();
    }
    
    public String getNoRecordMsg() {
        final String msg = this.tableRecords.get(0).findElement(By.xpath("//td")).getText();
        return msg;
    }
    
    public void viewDataExtractValue(final String userEmail, final String expectedValue) {
        UserManagementPage.driver.navigate().refresh();
        this.searchRecordByEmail(userEmail);
        final String companyName = UserManagementPage.driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'" + userEmail + "')]/following-sibling::td[2]")).getText();
        this.searchOptionClearBtn.click();
        UserManagementPage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.searchBtn));
        final List<String> multIdentifiers = new ArrayList<String>();
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_COMPANY_NAME) + ":" + companyName);
        multIdentifiers.add(String.valueOf(TestUtil.SEARCH_BY_ROLE) + ":" + TestUtil.EMTT_CUST_SUPP);
        this.searchRecordsWithMultipleIdentifiers(multIdentifiers);
        final List<WebElement> viewButonForAllCustSpprtRecords = (List<WebElement>)UserManagementPage.driver.findElements(By.xpath("//tbody/tr/td[contains(text(),'" + companyName + "')]/following-sibling::td[3]/input[@title = 'View Record'] "));
        for (final WebElement viewButtonForRecord : viewButonForAllCustSpprtRecords) {
            this.jse.executeScript("arguments[0].click();", new Object[] { viewButtonForRecord });
            this.waitForViewUserBoxToCome();
            this.viewUserEMSupportTool.click();
            UserManagementPage.wait.until((Function)ExpectedConditions.visibilityOf(this.viewUserDataExtractValue));
            final String value = this.viewUserDataExtractValue.getText();
            Assert.assertTrue(value.equals(expectedValue), "Data Extract value is not coming as it's EMTT Cust Admin");
            this.viewUserCloseButton.click();
        }
    }
}