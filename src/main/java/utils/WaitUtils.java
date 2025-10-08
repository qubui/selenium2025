package utils;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import org.openqa.selenium.By;
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
    
    /**
     * Wait until the element is not visible (disappears)
     */
    public static boolean waitForElementNotVisible(By locator) {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep was interrupted", e);
        }
    }
    
    /**
     * Check if element is displayed using WebElement
     */
    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | NullPointerException e) {
            return false;
        }
    }
    

    // add other waits as needed
}