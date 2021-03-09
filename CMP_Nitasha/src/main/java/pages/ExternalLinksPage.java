package pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class ExternalLinksPage extends TestBase
{
    @FindBy(xpath = "//div[@id='externallink']//label[contains(text(),'Community Board')]")
    WebElement communityBoardLabel;
    
    public ExternalLinksPage() {
        PageFactory.initElements(ExternalLinksPage.driver, (Object)this);
    }
    
    public String getCommunityBoardLabel() {
        return this.communityBoardLabel.getText();
    }
}

