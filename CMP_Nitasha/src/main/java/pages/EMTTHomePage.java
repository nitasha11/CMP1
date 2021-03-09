package pages;

import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class EMTTHomePage extends TestBase
{
    @FindBy(xpath = "//a[contains(text(),'Action Board')]")
    WebElement leftPanelActionBoard;
    @FindBy(xpath = "//a[contains(text(),'Open Tickets')]")
    WebElement leftPanelOpenTickets;
    @FindBy(xpath = "//a[contains(@id, 'search')]")
    WebElement leftPanelSearchBtn;
    @FindBy(xpath = "//a[contains(@id, 'new_ticket')]")
    WebElement leftPanelCreateTicket;
    @FindBy(xpath = "//a[contains(@id, 'questionnaire')]")
    WebElement leftPanelFeedbackQues;
    @FindBy(xpath = "//select[contains(@id, 'serviceAccount')]")
    WebElement serviceAccount;
    @FindBy(xpath = "//span[contains(text(),'Search')]")
    WebElement searchBtn;
    @FindBy(id = "a2p_dataRequestExtracts")
    WebElement dataExtractRequest;
    
    public EMTTHomePage() {
        PageFactory.initElements(EMTTHomePage.driver, (Object)this);
    }
    
    public void selectServiceAccount(final String serviceAccountName) {
        final Select serviceAccnt = new Select(this.serviceAccount);
        serviceAccnt.selectByVisibleText(serviceAccountName);
    }
    
    public void clickOnDataExtractRequestLink() {
        this.dataExtractRequest.click();
    }
    
    public void waitForThePageToBeLoaded() {
        EMTTHomePage.wait.until((Function)ExpectedConditions.or(new ExpectedCondition[] { ExpectedConditions.titleIs("Action Board") }));
    }
    
    public String getDataExtractLinkLabel() {
        return this.dataExtractRequest.getText();
    }
}


