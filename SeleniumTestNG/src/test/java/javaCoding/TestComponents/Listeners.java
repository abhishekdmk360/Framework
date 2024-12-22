package javaCoding.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import javaCoding.resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporter.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "TEST PASSED");
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	public void onTestSkipped(ITestResult result) {
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
	    extent.flush();
	}

}
