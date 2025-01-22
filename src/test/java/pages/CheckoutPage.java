package pages;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver driver;

    By titleYourInformation = By.xpath("//span[text() = 'Checkout: Your Information']");
    By titleOverview = By.xpath("//span[text() = 'Checkout: Overview']");
    By titleComplete = By.xpath("//span[text() = 'Checkout: Complete!']");
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By backHomeButton = By.id("back-to-products");
    String productPrice = "//div[text()='%s']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";
    By itemTotalValue = By.cssSelector(".summary_subtotal_label");
    By taxValue = By.cssSelector(".summary_tax_label");
    By totalValue = By.cssSelector(".summary_total_label");
    By completeMessage = By.xpath("//h2[text() = 'Thank you for your order!']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Метод инициализации элемента")
    public WebElement findBy(By element) {
        return driver.findElement(element);
    }

    @Step("Получение текста названия Страницы")
    public String getTitleYourInformation() {
        return findBy(titleYourInformation).getText();
    }

    @Step("Получение текста названия Страницы")
    public String getTitleOverview() {
        return findBy(titleOverview).getText();
    }

    @Step("Получение текста названия Страницы")
    public String getTitleComplete() {
        return findBy(titleComplete).getText();
    }

    @Step("Ввод информации в поля: {firstName}, {lastName}, {postalCode} и Нажатие кнопки continue")
    public void sendYourInformation(String firstName, String lastName, String postalCode) {
        findBy(firstNameField).sendKeys(firstName);
        findBy(lastNameField).sendKeys(lastName);
        findBy(postalCodeField).sendKeys(postalCode);
        findBy(continueButton).click();
    }

    @Step("Получение Цены у товара по названию Продукта: {product}")
    public String getPriceProduct(String product) {
        return findBy(By.xpath(String.format(productPrice, product))).getText();
    }

    @Step("Получение значения в Item Total блока Price Total")
    public String getItemTotalValue() {
        return findBy(itemTotalValue).getText();
    }

    @Step("Получение значения в Tax блока Price Total")
    public String getTaxValue() {
        return findBy(taxValue).getText();
    }

    @Step("Получение значения в Total блока Price Total")
    public String getTotalValue() {
        return findBy(totalValue).getText();
    }

    @Step("Клик по кнопке Finish")
    public void finishClick() {
        findBy(finishButton).click();
    }

    @Step("Клик по кнопке Back Home")
    public void backHomeClick() {
        findBy(backHomeButton).click();
    }

    @Step("Получение сообщения после оформления заказа")
    public String getCompleteMessage() {
        return findBy(completeMessage).getText();
    }
}
