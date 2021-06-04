import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTestReport {
	
	static ExtentTest logger;
	static ExtentReports report;
	
	@BeforeTest
	public void startTest() {
		report = new ExtentReports(System.getProperty("user.dir")
				+"/test-output/ExtentReportsResult.html", true);
	}
	
	@Test
	public void verifyPageTitle() {
		logger = report.startTest("Verify Page Title TC");
		String exp = "Google";
		String act = "Google";
		Assert.assertEquals(act, exp);
		logger.log(LogStatus.PASS, "Test case passed");
	}
	
	@Test
	public void verifyPageUrl() {
		logger = report.startTest("Verify Page Url TC");
		String exp = "Google.com";
		String act = "Yahoo.com";
		Assert.assertEquals(act, exp);
		logger.log(LogStatus.PASS, "Test case passed");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test case failed " +result.getName());
			logger.log(LogStatus.FAIL, "Test case failed " +result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test case failed " +result.getName());
			logger.log(LogStatus.SKIP, "Test case failed " +result.getThrowable());
		}
	}
	
	@AfterClass
	public void endTest() {
		report.endTest(logger);
		report.flush();
		report.close();
	}
}
