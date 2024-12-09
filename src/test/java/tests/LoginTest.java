package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @Test(description = "Тест 1 - Авторизация с корректным логином и паролем. Позитивный кейс" +
            "Тест 2 - Авторизация с корректным логином и паролем c помощью Action. Позитивный кейс" +
            "Тест 3 - Авторизация с корректным логином и паролем c помощью JavaScriptExecutor. Позитивный кейс" +
            "Тест 4 - Проверка ошибки с некорректным логином и паролем. Негативный кейс" +
            "Тест 5 - Проверка ошибки с пустым логином. Негативный кейс" +
            "Тест 6 - Проверка ошибки с пустым паролем. Негативный кейс",
            dataProvider = "LoginDataProvider", dataProviderClass = LoginTest.class)
    public void loginTest(String login, String password, int testNumber) {
        switch (testNumber) {
            case 1:
                loginPage.login(login, password);
                assertTrue(productsPage.getProductsPageText().isDisplayed());
                break;
            case 2:
                loginPage.loginActions(login, password);
                assertTrue(productsPage.getProductsPageText().isDisplayed());
                break;
            case 3:
                loginPage.loginExecutor(login, password);
                assertTrue(productsPage.getProductsPageText().isDisplayed());
                break;
            case 4:
                loginPage.login(login, password);
                assertEquals(loginPage.getErrorText().getText(),
                        "Epic sadface: Username and password do not match any user in this service");
                break;
            case 5:
                loginPage.loginWithoutUsername(password);
                assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Username is required");
                break;
            case 6:
                loginPage.loginWithoutPassword(login);
                assertEquals(loginPage.getErrorText().getText(), "Epic sadface: Password is required");
                break;
        }
    }

    @DataProvider(name = "LoginDataProvider")
    private Object[][] getData() {
        return new Object[][]{{valueProperties("login"), valueProperties("password"), 1},
                {valueProperties("login"), valueProperties("password"), 2},
                {valueProperties("login"), valueProperties("password"), 3},
                {valueProperties("loginFailed"), valueProperties("passwordFailed"), 4},
                {"", valueProperties("password"), 5},
                {valueProperties("login"), "", 6}};
    }
}
