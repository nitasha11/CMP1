package utilities;


import org.apache.commons.io.FileUtils;
import java.util.Date;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import Base.TestBase;

public class Screenshot extends TestBase
{
    public static DateFormat dateFormat;
    static Logger log;
    
    static {
        Screenshot.dateFormat = new SimpleDateFormat("MMddhhmmss");
        Screenshot.log = Logger.getLogger(Screenshot.class.getName());
    }
    
    public static void screenshot(final String snapshotName) {
        try {
            final TakesScreenshot ts = (TakesScreenshot)Screenshot.driver;
            Thread.sleep(1000L);
            final File source = (File)ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/" + snapshotName + "_" + Screenshot.dateFormat.format(new Date()) + ".png"));
            Screenshot.log.info((Object)("Screenshot \"" + snapshotName + "_" + Screenshot.dateFormat.format(new Date()) + ".png\" is taken"));
        }
        catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
}
