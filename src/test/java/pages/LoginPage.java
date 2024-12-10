package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.JavaScriptExecutorUtils;

@Getter
public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class = 'error-message-container error']")
    private WebElement errorText;

    @Step("Метод авторизации")
    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();
    }

    @Step("Метод авторизации с помощью Actions")
    public void loginActions(String username, String password) {
        new Actions(driver)
                .sendKeys(this.username, username)
                .sendKeys(this.password, password)
                .click(loginButton)
                .perform();
    }

    @Step("Метод авторизации с кликом по кнопке Login с помощью JavaScriptExecutor")
    public void loginExecutor(String username, String password) {
        JavaScriptExecutorUtils js = new JavaScriptExecutorUtils(driver);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        js.executorClickOnElement(loginButton);
    }

    @Step("Метод авторизации без Username")
    public void loginWithoutUsername(String password) {
        this.password.sendKeys(password);
        loginButton.click();
    }

    @Step("Метод авторизации без Password")
    public void loginWithoutPassword(String username) {
        this.username.sendKeys(username);
        loginButton.click();
    }
}
