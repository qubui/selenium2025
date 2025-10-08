package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import utils.WaitUtils;

public class DragAndDropPage {
    private WebDriver driver;

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    @FindBy(xpath = "//div[@id='column-a']/header")
    private WebElement headerA;

    @FindBy(xpath = "//div[@id='column-b']/header")
    private WebElement headerB;

    public DragAndDropPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(config.ConfigReader.getBaseUrl() + "/drag_and_drop");
    }

    public void dragAtoB() {
        WaitUtils.waitForElementVisible(columnA);
        WaitUtils.waitForElementVisible(columnB);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(columnA, columnB).perform();
    }

    public String getHeaderA() {
        WaitUtils.waitForElementVisible(headerA);
        return headerA.getText();
    }

    public String getHeaderB() {
        WaitUtils.waitForElementVisible(headerB);
        return headerB.getText();
    }
}
