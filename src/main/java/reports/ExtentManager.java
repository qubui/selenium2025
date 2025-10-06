package reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            File reportDir = new File("target/extent-report");
            if (!reportDir.exists()) reportDir.mkdirs();

            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report/HTMLReport.html");
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Selenium Demo Framework Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Add environment details
            extent.setSystemInfo("Environment", config.ConfigReader.get("env"));
            extent.setSystemInfo("Browser", config.ConfigReader.get("browser"));
        }
        return extent;
    }
}