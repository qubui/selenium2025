package pages;

import driver.DriverFactory;
import utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object for https://the-internet.herokuapp.com/dropdown
 */
public class DropdownPage {
    private WebDriver driver;

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public DropdownPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(config.ConfigReader.getBaseUrl() + "/dropdown");
    }

    public void selectByValue(String value) {
        WaitUtils.waitForElementVisible(dropdown);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void selectByText(String text) {
        WaitUtils.waitForElementVisible(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public String getSelectedOption() {
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
}