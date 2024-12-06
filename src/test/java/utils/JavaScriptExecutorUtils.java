package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс утилит для работы с JavaScriptExecutor.
 */
public class JavaScriptExecutorUtils {

    private final WebDriver driver;

    public JavaScriptExecutorUtils(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Метод клика на элемент")
    public void executorClickOnElement(final WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Не работает почему в полях username и password на этом сайте, значения вводятся но потом исчезают
    @Step("Метод ввода значения в поле")
    public void executorSendKeys(WebElement element, String stringValue) {
        // ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + stringValue +"')", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + stringValue + "';", element);
    }
}
