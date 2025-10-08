package pages;

import driver.DriverFactory;
import utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for https://the-internet.herokuapp.com/infinite_scroll
 */
public class InfiniteScrollPage {
    private WebDriver driver;

    @FindBy(css = ".jscroll-added p")
    private List<WebElement> paragraphs;

    public InfiniteScrollPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(config.ConfigReader.getBaseUrl() + "/infinite_scroll");
    }

    public int getParagraphCount() {
        return paragraphs.size();
    }

    public void scrollDown(int times) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < times; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            WaitUtils.sleep(2000); // wait for content to load
        }
    }
    
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}