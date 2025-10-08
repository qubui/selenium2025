package pages;

import driver.DriverFactory;
import utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for https://the-internet.herokuapp.com/hovers
 */
public class HoversPage {
    private WebDriver driver;

    @FindBy(css = ".figure")
    private List<WebElement> figures;

    @FindBy(css = ".figcaption h5")
    private List<WebElement> captions;

    public HoversPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(config.ConfigReader.getBaseUrl() + "/hovers");
    }

    public void hoverOverFigure(int index) {
        WaitUtils.waitForElementVisible(figures.get(index));
        Actions actions = new Actions(driver);
        actions.moveToElement(figures.get(index)).perform();
    }

    public String getCaption(int index) {
        WaitUtils.waitForElementVisible(captions.get(index));
        return captions.get(index).getText();
    }

    public int getFigureCount() {
        return figures.size();
    }
}
