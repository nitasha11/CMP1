package pages;

import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import TestData.TestDataFromExcel;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class LoginPage extends TestBase
{
    @FindBy(xpath = "*//div[@class='header title']")
    WebElement pageHeaderTitle;
    @FindBy(id = "userName")
    WebElement userNameIpt;
    @FindBy(id = "password")
    WebElement passwordIpt;
    @FindBy(id = "loginSubmit")
    WebElement loginBtn;
    @FindBy(id = "loginClear")
    WebElement clearBtn;
    @FindBy(id = "loginErrorLabel")
    WebElement loginErrorLabel;
    @FindBy(xpath = "//div[@class='app_name_login']")
    WebElement sapPortalLabel;
    
    public LoginPage() {
        PageFactory.initElements(LoginPage.driver, (Object)this);
        (LoginPage.testDataObject = new TestDataFromExcel()).setCredentials(1);
    }
    
    public String getTitle() {
        return LoginPage.driver.getTitle();
    }
    
    public void login(final String userName, final String password) {
        this.userNameIpt.clear();
        this.userNameIpt.sendKeys(new CharSequence[] { userName });
        this.passwordIpt.clear();
        this.passwordIpt.sendKeys(new CharSequence[] { password });
        this.loginBtn.click();
    }
    
    public void clearButton(final String userName, final String password) {
        this.userNameIpt.sendKeys(new CharSequence[] { userName });
        this.passwordIpt.sendKeys(new CharSequence[] { password });
        this.clearBtn.click();
    }
    
    public void clickOnClearBUtton() {
        this.clearBtn.click();
    }
    
    public String getLoginError() {
        String errorText = null;
        LoginPage.wait.until((Function)ExpectedConditions.visibilityOf(this.loginErrorLabel));
        if (this.loginErrorLabel.isDisplayed()) {
            errorText = this.loginErrorLabel.getText();
        }
        return errorText;
    }
    
    public String getSapPageLabel() {
        final String label = this.sapPortalLabel.getText();
        return label;
    }
    
    public String getUserNameFieldValue() {
        return this.userNameIpt.getText();
    }
    
    public String getPasswordFieldValue() {
        return this.passwordIpt.getText();
    }
}