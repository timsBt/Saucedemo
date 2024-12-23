package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YourCartPage {

    WebDriver driver;

    By title = By.xpath("//span[text() = 'Your Cart']");
    String productNamePattern = "//div[text() = '%s']";
    String productDescriptionPattern = "//div[text() = '%s']/ancestor::div[@class = 'cart_item_label']" +
            "//div[@class = 'inventory_item_desc']";
    String productPricePattern = "//div[text() = '%s']/ancestor::div[@class = 'cart_item_label']" +
            "//div[@class = 'inventory_item_price']";
    By allProductName = By.cssSelector(".inventory_item_name");

    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Метод инициализации элемента")
    public WebElement findBy(By element) {
        return driver.findElement(element);
    }

    @Step("Метод получения текста названия Страницы")
    public String getTitle() {
        return findBy(title).getText();
    }

    @Step("Метод получения Названия товара по названию Продукта")
    public String getNameProduct(String product) {
        return findBy(By.xpath(String.format(productNamePattern, product))).getText();
    }

    @Step("Метод получения Описания у товара по названию Продукта")
    public String getDescriptionProduct(String product) {
        return findBy(By.xpath(String.format(productDescriptionPattern, product))).getText();
    }

    @Step("Метод получения Цены у товара по названию Продукта")
    public String getPriceProduct(String product) {
        return findBy(By.xpath(String.format(productPricePattern, product))).getText();
    }

    @Step("Список всех Названий товаров в корзине")
    public List<WebElement> allProductName() {
        return driver.findElements(allProductName);
    }
}
