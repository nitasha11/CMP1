package Base;

import org.openqa.selenium.WebDriver;

	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import java.io.IOException;
	import java.io.FileNotFoundException;
	import java.io.InputStream;
	import java.io.FileInputStream;
	import org.apache.log4j.Logger;
	import pages.UserManagementPage;
	import pages.AddUserPage;
	import pages.HomePage;
	import pages.LoginPage;
	import TestData.TestDataFromExcel;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import java.util.Properties;
	import org.openqa.selenium.WebDriver;

	public class TestBase
	{
	    public static WebDriver driver;
	    public static Properties prop;
	    public static WebDriverWait wait;
	    public static Select select;
	    public static TestDataFromExcel testDataObject;
	    public LoginPage login;
	    public HomePage homepage;
	    public AddUserPage addUserPage;
	    public UserManagementPage userManage;
	    static String driverPath;
	    static String configFilePath;
	    static Logger log;
	    
	    static {
	        TestBase.driverPath = String.valueOf(System.getProperty("user.dir")) + "\\drivers\\";
	        TestBase.configFilePath = String.valueOf(System.getProperty("user.dir")) + "/src/main/java" + "/com/cmp/qa/config/config.properties";
	        TestBase.log = Logger.getLogger((Class)TestBase.class);
	    }
	    
	    public TestBase() {
	        try {
	            TestBase.prop = new Properties();
	            final FileInputStream ip = new FileInputStream(TestBase.configFilePath);
	            TestBase.prop.load(ip);
	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e2) {
	            e2.printStackTrace();
	        }
	    }
	    
	    public static void initialization() {
	        final String browserName = TestBase.prop.getProperty("browserName");
	        if (browserName.equalsIgnoreCase("chrome")) {
	            TestBase.log.info((Object)"Initializing chrome driver");
	            System.setProperty("webdriver.chrome.driver", String.valueOf(TestBase.driverPath) + "chromedriver.exe");
	            final ChromeOptions options = new ChromeOptions();
	            options.addArguments(new String[] { "--no-sandbox" });
	            TestBase.driver = (WebDriver)new ChromeDriver(options);
	            TestBase.wait = new WebDriverWait(TestBase.driver, 30L);
	        }
	        if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
	            TestBase.log.info((Object)"Initialising firefox driver");
	            System.setProperty("webdriver.gecko.driver", String.valueOf(TestBase.driverPath) + "geckodriver.exe");
	            System.setProperty("webdriver.firefox.marionette", "true");
	            System.setProperty("webdriver.firefox.logfile", "/dev/null");
	            TestBase.driver = (WebDriver)new FirefoxDriver();
	        }
	        TestBase.driver.manage().window().maximize();
	        TestBase.driver.manage().deleteAllCookies();
	        TestBase.log.info((Object)("Navigating to url -> " + TestBase.prop.getProperty("url")));
	        TestBase.driver.get(TestBase.prop.getProperty("url"));
	    }
	}
