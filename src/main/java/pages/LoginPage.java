package pages;

import driver.DriverFactory;
import utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for https://the-internet.herokuapp.com/login
 */
public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "button.radius")
    private WebElement loginBtn;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(config.ConfigReader.getBaseUrl()+"/login");
    }

    public void setUsername(String user) {
    	WaitUtils.waitForElementVisible(username);
        username.clear();
        username.sendKeys(user);
    }

    public void setPassword(String pass) {
    	WaitUtils.waitForElementVisible(password);
        password.clear();
        password.sendKeys(pass);
    }

    public void clickLogin() {
    	WaitUtils.waitForElementClickable(loginBtn);
        loginBtn.click();
    }

    public void login(String user, String pass) {
        setUsername(user);
        setPassword(pass);
        clickLogin();
    }

    public String getFlashMessage() {
        WaitUtils.waitForElementVisible(flashMessage);
        return flashMessage.getText();
    }
}