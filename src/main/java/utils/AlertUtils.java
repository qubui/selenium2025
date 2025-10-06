package utils;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertUtils {
    private static WebDriver driver = DriverFactory.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(config.ConfigReader.getInt("explicitWait")));

    public static void acceptAlertIfPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // ignore
        }
    }

    public static String getAlertText() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }
}