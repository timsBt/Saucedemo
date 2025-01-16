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
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorTextMessage = By.xpath("//div[@class = 'error-message-container error']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Метод инициализации элемента")
    public WebElement findBy(By element) {
        return driver.findElement(element);
    }

    @Step("Авторизация с логином: {username} и паролем: {password}")
    public void login(String username, String password) {
        findBy(usernameField).sendKeys(username);
        findBy(passwordField).sendKeys(password);
        findBy(loginButton).click();
    }

    @Step("Авторизация с логином: {username} и паролем: {password}")
    public void loginActions(String username, String password) {
        new Actions(driver)
                .sendKeys(findBy(usernameField), username)
                .sendKeys(findBy(passwordField), password)
                .click(findBy(loginButton))
                .perform();
    }

    @Step("Авторизация с логином: {username} и паролем: {password}")
    public void loginExecutor(String username, String password) {
        JavaScriptExecutorUtils js = new JavaScriptExecutorUtils(driver);
        findBy(usernameField).sendKeys(username);
        findBy(passwordField).sendKeys(password);
        js.executorClickOnElement(findBy(loginButton));
    }

    @Step("Авторизация без Username и с паролем: {password}")
    public void loginWithoutUsername(String password) {
        findBy(passwordField).sendKeys(password);
        findBy(loginButton).click();
    }

    @Step("Авторизация без Password и с логином: {username}")
    public void loginWithoutPassword(String username) {
        findBy(usernameField).sendKeys(username);
        findBy(loginButton).click();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return findBy(errorTextMessage).getText();
    }
}
