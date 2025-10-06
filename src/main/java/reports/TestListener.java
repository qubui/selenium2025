package reports;

import com.aventstack.extentreports.*;
import org.testng.*;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // implement other methods if needed
}