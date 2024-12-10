package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.JavaScriptExecutorUtils;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorTextMessage = By.xpath("//div[@class = 'error-message-container error']");

    @Description("Метод инициализации элемента")
    public WebElement findBy(By element) {
        return driver.findElement(element);
    }

    @Step("Метод авторизации")
    public void login(String username, String password) {
        findBy(usernameField).sendKeys(username);
        findBy(passwordField).sendKeys(password);
        findBy(loginButton).click();
    }

    @Step("Метод авторизации с помощью Actions")
    public void loginActions(String username, String password) {
        new Actions(driver)
                .sendKeys(findBy(usernameField), username)
                .sendKeys(findBy(passwordField), password)
                .click(findBy(loginButton))
                .perform();
    }

    @Step("Метод авторизации с кликом по кнопке Login с помощью JavaScriptExecutor")
    public void loginExecutor(String username, String password) {
        JavaScriptExecutorUtils js = new JavaScriptExecutorUtils(driver);
        findBy(usernameField).sendKeys(username);
        findBy(passwordField).sendKeys(password);
        js.executorClickOnElement(findBy(loginButton));
    }

    @Step("Метод авторизации без Username")
    public void loginWithoutUsername(String password) {
        findBy(passwordField).sendKeys(password);
        findBy(loginButton).click();
    }

    @Step("Метод авторизации без Password")
    public void loginWithoutPassword(String username) {
        findBy(usernameField).sendKeys(username);
        findBy(loginButton).click();
    }

    @Step("Метод получения текста ошибки")
    public String getErrorText() {
        return findBy(errorTextMessage).getText();
    }
}
