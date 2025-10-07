package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destDir = "target/extent-report/screenshots/";
            Files.createDirectories(Paths.get(destDir));
            String path = destDir + testName + ".png";
            Files.copy(src.toPath(), Paths.get(path));
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}