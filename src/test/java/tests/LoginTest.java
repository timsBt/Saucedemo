package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static utils.PropertiesUtils.valueProperties;

@Epic("UI tests")
public class LoginTest extends BaseTest {

    @Test(testName = "Тест авторизации. Позитивный кейс", description = "Тест авторизации",
            groups = {"smoke", "ui"})
    @Description("Тест авторизации. Позитивный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTest() {
        loginPage.login(login, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(testName = "Тест авторизации с помощью Actions. Позитивный кейс", description = "Тест авторизации",
            groups = {"smoke", "ui"})
    @Description("Тест авторизации с помощью Actions. Позитивный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    public void loginActionsTest() {
        loginPage.loginActions(login, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(testName = "Тест авторизации с помощью JavaScriptExecutor. Позитивный кейс", description = "Тест авторизации",
            groups = {"smoke", "ui"})
    @Description("Тест авторизации с помощью JavaScriptExecutor. Позитивный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    public void loginExecutorTest() {
        loginPage.loginExecutor(login, password);
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройденна");
    }

    @Test(testName = "Тест проверки ошибки с некорректным логином и паролем. Негативный кейс",
            description = "Тест проверки ошибки с некорректным логином и паролем", groups = {"smoke", "ui"})
    @Description("Тест проверки ошибки с некорректным логином и паролем. Негативный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFailedTest() {
        loginPage.login(loginFailed, passwordFailed);
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не появилось");
    }

    @Test(testName = "Тест проверки ошибки с пустым логином. Негативный кейс",
            description = "Тест проверки ошибки с пустым логином", groups = {"smoke", "ui"})
    @Description("Тест проверки ошибки с пустым логином. Негативный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFailedUsernameTest() {
        loginPage.loginWithoutUsername(password);
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не появилось");
    }

    @Test(testName = "Тест проверки ошибки с пустым паролем. Негативный кейс",
            description = "Тест проверки ошибки с пустым паролем", groups = {"smoke", "ui"})
    @Description("Тест проверки ошибки с пустым паролем. Негативный кейс")
    @Feature("Проверка авторизации")
    @Story("Авторизация с некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void loginFailedPasswordTest() {
        loginPage.loginWithoutPassword(login);
        assertEquals(
                loginPage.getErrorText(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не появилось");
    }

    @Test(testName = "Падающий тест для проверки скриншота", description = "Падающий тест для проверки скриншота",
            groups = {"smoke", "ui"}, enabled = false)
    @Description("Падающий тест для проверки скриншота")
    @Feature("Проверка скриншота")
    @Story("Специально падающий тест")
    @Severity(SeverityLevel.TRIVIAL)
    @Flaky
    public void loginFailedScreenShotTest() {
        loginPage.login(login, password);
        assertEquals(
                productsPage.getTitle(),
                "Страница какая то",
                "Этот тест упал специально :)");
    }
}
