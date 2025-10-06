package utils;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

public class WaitUtils {
    private static WebDriver driver = DriverFactory.getDriver();
    private static int defaultTimeout = config.ConfigReader.getInt("timeout");

    public static void waitForElementClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
            .ignoring(NoSuchElementException.class)
            .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void  waitForElementVisible(WebElement element) {
         new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
            .ignoring(NoSuchElementException.class)
            .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForTextPresent(WebElement element, String text) {
         new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout))
            .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // add other waits as needed
}