package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.YourCartPage;
import utils.AllureUtils;

import java.time.Duration;


public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    YourCartPage yourCartPage;
    CheckoutPage checkoutPage;

    public String mainPageUrl = System.getProperty("mainPageUrl");
    public String login = System.getProperty("login");
    public String password = System.getProperty("password");
    public String loginFailed = System.getProperty("loginFailed");
    public String passwordFailed = System.getProperty("passwordFailed");

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("Chrome") String browser) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(mainPageUrl);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        yourCartPage = new YourCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        driver.quit();
    }
}
