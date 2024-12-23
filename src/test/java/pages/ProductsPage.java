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
    String addToCartPattern = "//div[text() = '%s']//ancestor::div[@class = 'inventory_item']" +
            "//button[text() = 'Add to cart']";
    String removeProductPattern = "//div[text() = '%s']//ancestor::div[@class = 'inventory_item']" +
            "//button[text() = 'Remove']";
    String priceProductPattern = "//div[text() = '%s']//ancestor::div[@class = 'inventory_item']" +
            "//div[@class = 'inventory_item_price']";
    String descriptionProductPattern = "//div[text() = '%s']//ancestor::div[@class = 'inventory_item']" +
            "//div[@class = 'inventory_item_desc']";
    By cartButton = By.cssSelector(".shopping_cart_link");
    By cartBadge = By.cssSelector(".shopping_cart_badge");
    By allAddToCardButton = By.xpath("//button[text() = 'Add to cart']");
    By allProductName = By.cssSelector(".inventory_item_name");
    By checkoutButton = By.xpath("//button[text() = 'Checkout']");

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
    public void addToCart(String product) {
        findBy(By.xpath(String.format(addToCartPattern, product))).click();
    }

    @Step("Метод клика на Remove по названию Продукта")
    public void clickRemoveButton(String product) {
        findBy(By.xpath(String.format(removeProductPattern, product))).click();
    }

    @Step("Метод получения Цены у товара по названию Продукта")
    public String getPriceProduct(String product) {
        return findBy(By.xpath(String.format(priceProductPattern, product))).getText();
    }

    @Step("Метод получения Описания у товара по названию Продукта")
    public String getDescriptionProduct(String product) {
        return findBy(By.xpath(String.format(descriptionProductPattern, product))).getText();
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

    @Step("Метод клика по Checkout")
    public void checkoutClick() {
        findBy(checkoutButton).click();
    }
}
