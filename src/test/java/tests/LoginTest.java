package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesUtils.valueProperties;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void pageInit() {
        loginPage = new LoginPage(getDriver());
        productsPage = new ProductsPage(getDriver());
    }

    @Test(description = "Тест авторизации. Позитивный кейс")
    public void loginTest() {
        loginPage.login(valueProperties("login"), valueProperties("password"));
        assertTrue(productsPage.getProductsPageText().isDisplayed());
    }

    @Test(description = "Тест авторизации с помощью Actions. Позитивный кейс")
    public void loginActionsTest() {
        loginPage.loginActions(valueProperties("login"), valueProperties("password"));
        assertTrue(productsPage.getProductsPageText().isDisplayed());
    }

    @Test(description = "Тест авторизации с помощью JavaScriptExecutor. Позитивный кейс")
    public void loginExecutorTest() {
        loginPage.loginExecutor(valueProperties("login"), valueProperties("password"));
        assertTrue(productsPage.getProductsPageText().isDisplayed());
    }

    @Test(description = "Тест проверки ошибки с некорректным логином и паролем. Негативный кейс")
    public void loginFailedTest() {
        loginPage.login(valueProperties("loginFailed"), valueProperties("passwordFailed"));
        assertEquals(loginPage.getErrorText().getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(description = "Тест проверки ошибки с пустым логином. Негативный кейс")
    public void loginFailedUsernameTest() {
        loginPage.loginWithoutUsername(valueProperties("password"));
        assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Username is required");
    }

    @Test(description = "Тест проверки ошибки с пустым паролем. Негативный кейс")
    public void loginFailedPasswordTest() {
        loginPage.loginWithoutPassword(valueProperties("login"));
        assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Password is required");
    }
}
