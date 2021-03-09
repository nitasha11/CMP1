package pages;


import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class SettingsPage extends TestBase
{
    @FindBy(id = "others")
    WebElement othersTab;
    @FindBy(id = "loadBalTxt")
    WebElement loadBalTxt;
    @FindBy(id = "external")
    WebElement externalTab;
    @FindBy(xpath = "//div[@id='externallink']//label[contains(text(),'Community Board')]")
    WebElement communityBoardLabel;
    @FindBy(id = "changePasswordAdmin")
    WebElement settingsPageChangePasswordButton;
    
    public SettingsPage() {
        PageFactory.initElements(SettingsPage.driver, (Object)this);
    }
    
    public void clickOnOthersTab() {
        this.othersTab.click();
        SettingsPage.wait.until((Function)ExpectedConditions.visibilityOf(this.loadBalTxt));
    }
    
    public void clickOnExternalLinksTab() {
        this.externalTab.click();
        SettingsPage.wait.until((Function)ExpectedConditions.visibilityOf(this.communityBoardLabel));
    }
    
    public String getLoadBalancerUrl() {
        this.clickOnOthersTab();
        return this.loadBalTxt.getAttribute("value");
    }
    
    public String getCommunityBoardLabel() {
        return this.communityBoardLabel.getText();
    }
}
