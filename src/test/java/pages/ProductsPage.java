package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    WebDriver driver;

    By title = By.xpath("//span[text() = 'Products']");
    By addToCardProductButton;
    By removeProductButton;
    By priceProduct;
    By descriptionProduct;
    By cartButton = By.cssSelector(".shopping_cart_link");
    By cartBadge = By.xpath("//span[@class = 'shopping_cart_badge']");
    By allAddToCardButton = By.xpath("//button[text() = 'Add to cart']");
    By allProductName = By.xpath("//div[@class = 'inventory_item_name ']");

    public ProductsPage(WebDriver driver) {
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

    @Step("Метод клика на Add to cart по названию Продукта")
    public void clickAddToCardProductButton(String productName) {
        findBy(addToCardProductButton = By.xpath("//div[text() = '" + productName + "']/ancestor::" +
                "div[@class = 'inventory_item_description']//button[text() = 'Add to cart']")).click();
    }

    @Step("Метод клика на Remove по названию Продукта")
    public void clickRemoveButton(String productName) {
        findBy(removeProductButton = By.xpath("//div[text() = '" + productName + "']/ancestor::" +
                "div[@class = 'inventory_item_description']//button[text() = 'Remove']")).click();
    }

    @Step("Метод получения Цены у товара по названию Продукта")
    public String getPriceProduct(String productName) {
        return findBy(priceProduct = By.xpath("//div[text() = '" + productName + "']/ancestor::div[@class =" +
                " 'inventory_item_description']//div[@class = 'inventory_item_price']")).getText();
    }

    @Step("Метод получения Описания у товара по названию Продукта")
    public String getDescriptionProduct(String productName) {
        return findBy(descriptionProduct = By.xpath("//div[text() = '" + productName + "']/ancestor::" +
                "div[@class = 'inventory_item_description']//div[@class = 'inventory_item_desc']")).getText();
    }

    @Step("Метод клика по корзине")
    public void clickCartButton() {
        findBy(cartButton).click();
    }

    @Step("Метод получения значения в иконке корзины")
    public String getCartBadge() {
        return findBy(cartBadge).getText();
    }

    @Step("Метод клика по корзине - все кнопки addToCardButton на странице")
    public List<WebElement> clickAddToCardButton() {
        return driver.findElements(allAddToCardButton);
    }

    @Step("Список всех Названий товаров на странице")
    public List<WebElement> allProductName() {
        return driver.findElements(allProductName);
    }
}
