package pages;


import org.openqa.selenium.support.ui.ExpectedCondition;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import utilities.TestUtil;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import Base.TestBase;

public class HomePage extends TestBase
{
    public WebDriverWait extendedWait;
    private String userMngmntPageLink;
    @FindBy(xpath = "//a[contains(text(),'Log off ')]")
    WebElement logOffBtn;
    @FindBy(id = "dashboardLnk")
    WebElement leftPanelDashboardBtn;
    @FindBy(id = "requestInbox")
    WebElement leftPanelInboxBtn;
    @FindBy(id = "requestManagementSearch")
    WebElement leftPanelSearchBtn;
    @FindBy(id = "onlineForm")
    WebElement leftPanelNewCampaignReqBtn;
    @FindBy(id = "savedCampaign")
    WebElement leftPanelSavedCampaignBtn;
    @FindBy(id = "profilelink")
    WebElement leftPanelProfileBtn;
    @FindBy(id = "changePasswordLink")
    WebElement leftPanelChangePassword;
    @FindBy(id = "settings")
    WebElement leftPanelSettingsBtn;
    @FindBy(id = "settingsTitle")
    WebElement settingsPageTitle;
    @FindBy(id = "survey_settings")
    WebElement leftPanelSurveySettingsBtn;
    @FindBy(id = "survey_reports")
    WebElement leftPanelSurveyReportsBtn;
    @FindBy(id = "externalNav")
    WebElement leftPanelExternalLinksBtn;
    @FindBy(id = "exPageTitle")
    WebElement externalLinkPageTitle;
    @FindBy(id = "systemTemplateLnk")
    WebElement leftPanelSystemReportsLink;
    @FindBy(id = "customTemplateLnk")
    WebElement leftPanelCustomReportsLink;
    @FindBy(id = "scheduleLnk")
    WebElement leftPanelScheduleReportsLink;
    @FindBy(id = "loadingMessage")
    WebElement loadingMessage;
    @FindBy(id = "userManagementTopLink")
    WebElement userManagementBtn;
    @FindBy(id = "changePasswordLink")
    WebElement changePasswordButton;
    @FindBy(xpath = "//span[contains(text(),'Change Password')]")
    WebElement changePasswordTab;
    @FindBy(id = "oldpassword")
    WebElement oldPasswordTextBox;
    @FindBy(id = "newpassword")
    WebElement newPasswordTextBox;
    @FindBy(id = "cnfmpassword")
    WebElement confirmPasswordTextBox;
    @FindBy(xpath = "//div[@id='chgPwdDailog']/following-sibling::div//span[contains(text(),'Change')]")
    WebElement changePswrdChangeButton;
    @FindBy(xpath = "//div[@id='chgPwdDailog']/following-sibling::div//span[contains(text(),'Cancel')]")
    WebElement changePswrdCancelButton;
    @FindBy(id = "changePasswordError")
    WebElement changePasswordError;
    @FindBy(id = "loginMessageLabel")
    WebElement passwordChangedLabelBox;
    @FindBy(id = "loginMessageLabel2")
    WebElement logInWithNewCredLabelBox;
    @FindBy(xpath = "//a[contains(text(),'Log off')]")
    WebElement logOutButton;
    
    public HomePage() {
        this.userMngmntPageLink = null;
        PageFactory.initElements(HomePage.driver, (Object)this);
        this.extendedWait = new WebDriverWait(HomePage.driver, (long)TestUtil.LONG_WAIT);
        this.userMngmntPageLink = HomePage.prop.getProperty("userManagementLink");
    }
    
    public boolean checkTabs(final WebElement ele) {
        try {
            return ele.isDisplayed();
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }
    
    public void waitForUserManagementBtnToBeClickable() {
        HomePage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.userManagementBtn));
    }
    
    public void waitForThePageToBeLoaded() {
        HomePage.wait.until((Function)ExpectedConditions.or(new ExpectedCondition[] { ExpectedConditions.titleIs("Request Inbox"), ExpectedConditions.titleIs("Request Search") }));
        if (this.loadingMessage.isDisplayed()) {
            this.extendedWait.until((Function)ExpectedConditions.invisibilityOf(this.loadingMessage));
        }
        else {
            HomePage.wait.until((Function)ExpectedConditions.elementToBeClickable(this.leftPanelDashboardBtn));
        }
    }
    
    public void changePassword(final String oldPswrd, final String newPassword, final String confirmPassword) {
        this.changePasswordButton.click();
        HomePage.wait.until((Function)ExpectedConditions.visibilityOf(this.changePasswordTab));
        this.oldPasswordTextBox.sendKeys(new CharSequence[] { oldPswrd });
        this.newPasswordTextBox.sendKeys(new CharSequence[] { newPassword });
        this.confirmPasswordTextBox.sendKeys(new CharSequence[] { confirmPassword });
    }
    
    public String getPasswordChangedText() {
        return this.passwordChangedLabelBox.getText().trim();
    }
    
    public String getLoginWithNewCredText() {
        return this.logInWithNewCredLabelBox.getText().trim();
    }
    
    public void clickOnChangePasswordCancelButton() {
        this.changePswrdCancelButton.click();
    }
    
    public void clickOnLogoutButton() {
        this.logOutButton.click();
        HomePage.wait.until((Function)ExpectedConditions.titleIs(TestUtil.LOGIN_PAGE_TITLE));
    }
    
    public void clickOnChangePasswordChangeButton() {
        this.changePswrdChangeButton.click();
    }
    
    public String getChangePasswordErrorMsg() {
        HomePage.wait.until((Function)ExpectedConditions.visibilityOf(this.changePasswordError));
        return this.changePasswordError.getText();
    }
    
    public void clickOnExternalTabLink() {
        this.leftPanelExternalLinksBtn.click();
        HomePage.wait.until((Function)ExpectedConditions.visibilityOf(this.externalLinkPageTitle));
    }
    
    public void clickOnSettingsTab() {
        this.leftPanelSettingsBtn.click();
        HomePage.wait.until((Function)ExpectedConditions.visibilityOf(this.settingsPageTitle));
    }
    
    public boolean leftPanelNewCampaignReqBtnPresent() {
        return this.checkTabs(this.leftPanelNewCampaignReqBtn);
    }
    
    public boolean leftPanelSavedCampaignTabPresent() {
        return this.checkTabs(this.leftPanelSavedCampaignBtn);
    }
    
    public boolean userMngmntTabPresent() {
        return this.checkTabs(this.userManagementBtn);
    }
    
    public boolean leftPanelDashBoardBtnPresent() {
        return this.checkTabs(this.leftPanelDashboardBtn);
    }
    
    public boolean leftPanelInboxBtnPresent() {
        return this.checkTabs(this.leftPanelInboxBtn);
    }
    
    public boolean leftPanelSearchBtnPresent() {
        return this.checkTabs(this.leftPanelSearchBtn);
    }
    
    public boolean leftPanelProfileBtnPresent() {
        return this.checkTabs(this.leftPanelProfileBtn);
    }
    
    public boolean leftPanelChangePsswrdBtnPresent() {
        return this.checkTabs(this.leftPanelChangePassword);
    }
    
    public boolean leftPanelSettingsBtnPresent() {
        return this.checkTabs(this.leftPanelSettingsBtn);
    }
    
    public boolean leftPanelSurveySettingsBtnPresent() {
        return this.checkTabs(this.leftPanelSurveySettingsBtn);
    }
    
    public boolean leftPanelSurveyReportsBtnPresent() {
        return this.checkTabs(this.leftPanelSurveyReportsBtn);
    }
    
    public boolean leftPanelExternalLinksBtnPresent() {
        return this.checkTabs(this.leftPanelExternalLinksBtn);
    }
    
    public boolean leftPanelSystemReportsBtnPresent() {
        return this.checkTabs(this.leftPanelSystemReportsLink);
    }
    
    public boolean leftPanelCustomReportsBtnPresent() {
        return this.checkTabs(this.leftPanelCustomReportsLink);
    }
    
    public boolean leftPanelScheduleReportsBtnPresent() {
        return this.checkTabs(this.leftPanelScheduleReportsLink);
    }
    
    public void redirectToUserManagementPage() {
        HomePage.driver.get(this.userMngmntPageLink);
    }
}