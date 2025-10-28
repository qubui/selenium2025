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
public class LoginPageQAExcercise {
    private WebDriver driver;

    @FindBy(name = "email")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginBtn;

    @FindBy(xpath = "//form[@action='/login']/p")
    private WebElement erorMessage;

    public LoginPageQAExcercise() {
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

    public String getErrorMessage() {
        WaitUtils.waitForElementVisible(erorMessage);
        return erorMessage.getText();
    }
}