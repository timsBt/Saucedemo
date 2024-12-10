package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static utils.PropertiesUtils.valueProperties;

public class LoginTest extends BaseTest {

    @Test(description = "Тест авторизации. Позитивный кейс")
    public void loginTest() {
        loginPage.login(valueProperties("login"), valueProperties("password"));
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(description = "Тест авторизации с помощью Actions. Позитивный кейс")
    public void loginActionsTest() {
        loginPage.loginActions(valueProperties("login"), valueProperties("password"));
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(description = "Тест авторизации с помощью JavaScriptExecutor. Позитивный кейс")
    public void loginExecutorTest() {
        loginPage.loginExecutor(valueProperties("login"), valueProperties("password"));
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(description = "Тест проверки ошибки с некорректным логином и паролем. Негативный кейс")
    public void loginFailedTest() {
        loginPage.login(valueProperties("loginFailed"), valueProperties("passwordFailed"));
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не появилось");
    }

    @Test(description = "Тест проверки ошибки с пустым логином. Негативный кейс")
    public void loginFailedUsernameTest() {
        loginPage.loginWithoutUsername(valueProperties("password"));
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не появилось");
    }

    @Test(description = "Тест проверки ошибки с пустым паролем. Негативный кейс")
    public void loginFailedPasswordTest() {
        loginPage.loginWithoutPassword(valueProperties("login"));
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не появилось");
    }
}
