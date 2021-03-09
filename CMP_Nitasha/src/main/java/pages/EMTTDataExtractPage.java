package pages;

import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class EMTTDataExtractPage extends TestBase
{
    @FindBy(id = "dataReq_messageType")
    WebElement messageType;
    @FindBy(id = "serviceNumberLabel")
    WebElement serviceNumberLabel;
    @FindBy(xpath = "//div/*[contains(text(),'Data Extract Request')]")
    WebElement dataExtractrequestHeading;
    
    public EMTTDataExtractPage() {
        PageFactory.initElements(EMTTDataExtractPage.driver, (Object)this);
    }
    
    public void selectMessageType(final String value) {
        final Select select = new Select(this.messageType);
        select.selectByValue(value);
    }
    
    public String getServiceNumberLabelValue() {
        return this.serviceNumberLabel.getText();
    }
    
    public void verifyPageIsLoaded() {
        EMTTDataExtractPage.wait.until((Function)ExpectedConditions.visibilityOf(this.dataExtractrequestHeading));
    }
}
