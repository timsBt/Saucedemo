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
}
