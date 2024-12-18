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
    By nameProduct;
    By descriptionProduct;
    By priceProduct;
    By allProductName = By.xpath("//div[@class = 'inventory_item_name']");

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
    public String getNameProduct(String productName) {
        return findBy(nameProduct = By.xpath("//div[text() = '" + productName + "']")).getText();
    }

    @Step("Метод получения Описания у товара по названию Продукта")
    public String getDescriptionProduct(String productName) {
        return findBy(descriptionProduct = By.xpath("//div[text() = '" + productName + "']/ancestor::" +
                "div[@class = 'cart_item_label']//div[@class = 'inventory_item_desc']")).getText();
    }

    @Step("Метод получения Цены у товара по названию Продукта")
    public String getPriceProduct(String productName) {
        return findBy(priceProduct = By.xpath("//div[text() = '" + productName + "']/ancestor::" +
                "div[@class = 'cart_item_label']//div[@class = 'inventory_item_price']")).getText();
    }

    @Step("Список всех Названий товаров в корзине")
    public List<WebElement> allProductName() {
        return driver.findElements(allProductName);
    }
}
