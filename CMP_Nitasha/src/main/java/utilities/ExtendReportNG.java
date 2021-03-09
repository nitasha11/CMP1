package utilities;


import java.util.Calendar;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.IResultMap;
import org.testng.ITestContext;
import java.util.Map;
import java.util.Iterator;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ISuiteResult;
import java.io.File;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.IReporter;

public class ExtendReportNG implements IReporter
{
    private ExtentReports extent;
    
    public void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites, final String outputDirectory) {
        this.extent = new ExtentReports("./Extent Report" + File.separator + "Extent.html", Boolean.valueOf(true));
        for (final ISuite suite : suites) {
            final Map<String, ISuiteResult> result = (Map<String, ISuiteResult>)suite.getResults();
            for (final ISuiteResult r : result.values()) {
                final ITestContext context = r.getTestContext();
                this.buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                this.buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                this.buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        this.extent.flush();
        this.extent.close();
    }
    
    private void buildTestNodes(final IResultMap tests, final LogStatus status) {
        if (tests.size() > 0) {
            for (final ITestResult result : tests.getAllResults()) {
                final ExtentTest test = this.extent.startTest(result.getMethod().getMethodName());
                test.setStartedTime(this.getTime(result.getStartMillis()));
                test.setEndedTime(this.getTime(result.getEndMillis()));
                String[] groups;
                for (int length = (groups = result.getMethod().getGroups()).length, i = 0; i < length; ++i) {
                    final String group = groups[i];
                    test.assignCategory(new String[] { group });
                }
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                }
                else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
                this.extent.endTest(test);
            }
        }
    }
    
    private Date getTime(final long millis) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
