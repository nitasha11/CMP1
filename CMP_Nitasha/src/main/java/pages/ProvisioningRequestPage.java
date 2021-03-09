package pages;


import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import Base.TestBase;

public class ProvisioningRequestPage extends TestBase
{
    @FindBy(xpath = "//span[contains(text(),'Search')]")
    WebElement searchBtn;
    @FindBy(xpath = "//span/span[text()='Assign']")
    WebElement assignBtn;
    @FindBy(xpath = "//span[text()='Export']")
    WebElement exportBtn;
    @FindBy(xpath = "//span[text()='Short Code']")
    WebElement shortCodeLabel;
    @FindBy(xpath = "//span[text()='Status']")
    WebElement statusLabel;
    @FindBy(xpath = "//span[text()='Campaign Type']")
    WebElement campaignTypeLabel;
    @FindBy(xpath = "//span[text()='Start Date']")
    WebElement startDateBtn;
    @FindBy(xpath = "//span[text()='End Date']")
    WebElement endDateBtn;
    @FindBy(xpath = "//span[contains(text(),'Actions')]")
    WebElement actionsBtn;
    @FindBy(xpath = "//span[contains(@class,'jtable-page-list')]/span[1]")
    WebElement paginationToFirstPage;
    @FindBy(xpath = "//span[contains(@class,'jtable-page-list')]/span[2]")
    WebElement paginationToOnePageBack;
    @FindBy(xpath = "//span[contains(@class,'jtable-page-list')]/span[8]")
    WebElement paginationToOnePageFrwrd;
    @FindBy(xpath = "//span[contains(@class,'jtable-page-list')]/span[9]")
    WebElement paginationToLastPage;
    
    public ProvisioningRequestPage() {
        PageFactory.initElements(ProvisioningRequestPage.driver, (Object)this);
    }
}