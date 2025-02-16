package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static utils.PropertiesUtils.valueProperties;

@Epic("UI tests")
public class ProductsTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    String productName = "Sauce Labs Fleece Jacket";
    String productName2 = "Sauce Labs Backpack";

    @Test(testName = "Тест добавления товара в корзину. Проверка наименования, описания и цены товара в корзине",
            description = "Тест добавления товара в корзину", groups = {"smoke", "ui"})
    @Description("Тест добавления товара в корзину. Проверка наименования, описания и цены товара в корзине")
    @Feature("Проверка товара в корзине")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductTest() {
        loginPage.login(login, password);
        softAssert.assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройдена");
        productsPage.addToCart(productName);
        String descriptionProductName = productsPage.getDescriptionProduct(productName);
        String priceProductName = productsPage.getPriceProduct(productName);
        productsPage.clickCartButton();
        softAssert.assertEquals(
                yourCartPage.getTitle(),
                "Your Cart",
                "Страница Your Cart не открылась");
        softAssert.assertEquals(
                yourCartPage.getNameProduct(productName),
                productName,
                "Товар в корзине не соответствует названию товара на странице Products");
        softAssert.assertEquals(
                yourCartPage.getDescriptionProduct(productName),
                descriptionProductName,
                "Товар в корзине не соответствует описанию товара на странице Products");
        softAssert.assertEquals(
                yourCartPage.getPriceProduct(productName),
                priceProductName,
                "Товар в корзине не соответствует цене товара на странице Products");
        softAssert.assertAll();
    }

    @Test(testName = "Тест проверки значения иконки корзины после добавления и удаления товара",
            description = "Тест проверки значения иконки корзины", groups = {"smoke", "ui"})
    @Description("Тест проверки значения иконки корзины после добавления и удаления товара")
    @Feature("Проверка товара в корзине")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.MINOR)
    public void checkCartBadgeTest() {
        loginPage.login(login, password);
        softAssert.assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройдена");
        productsPage.clickAddToCardButton().get(0).click();
        softAssert.assertEquals(
                productsPage.getCartBadge(),
                "1",
                "Количество товара в иконке не соответствует количеству добавленных товаров");
        productsPage.clickAddToCardButton().forEach(WebElement::click);
        softAssert.assertEquals(
                productsPage.getCartBadge(),
                "6",
                "Количество товара в иконке не соответствует количеству добавленных товаров");
        productsPage.clickRemoveButton(productName);
        softAssert.assertEquals(
                productsPage.getCartBadge(),
                "5",
                "Количество товара в иконке не соответствует количеству добавленных товаров");
        softAssert.assertAll();
    }

    @Test(testName = "Тест проверки попадания всех товаров на странице в корзину",
            description = "Тест проверки попадания всех товаров на странице в корзину", groups = {"smoke", "ui"})
    @Description("Тест проверки попадания всех товаров на странице в корзину")
    @Feature("Проверка товара в корзине")
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    public void checkAllProductsNameOnCartTest() {
        loginPage.login(login, password);
        softAssert.assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройдена");
        List<String> productsNameOnProductsList = new ArrayList<>();
        for (WebElement webElement : productsPage.allProductName()) {
            productsNameOnProductsList.add(webElement.getText());
        }
        productsPage.clickAddToCardButton().forEach(WebElement::click);
        productsPage.clickCartButton();
        softAssert.assertEquals(
                yourCartPage.getTitle(),
                "Your Cart",
                "Страница Your Cart не открылась");
        List<String> productsNameOnCartList = new ArrayList<>();
        for (WebElement webElement : yourCartPage.allProductName()) {
            productsNameOnCartList.add(webElement.getText());
        }
        softAssert.assertEquals(
                productsNameOnCartList,
                productsNameOnProductsList,
                "В корзине не совпадают названия товаров со страницы Products");
        softAssert.assertAll();
    }

    @Test(testName = "Тест проверки расчета сумм в блоке Price Total",
            description = "Тест проверки расчета сумм в блоке Price Total", groups = {"smoke", "ui"})
    @Description("Тест проверки расчета сумм в блоке Price Total")
    @Feature("Проверка товара в корзине")
    @Story("Проверка расчета сумм")
    @Severity(SeverityLevel.CRITICAL)
    public void checkValuePriceTotalTest() {
        loginPage.login(login, password);
        productsPage.addToCart(productName);
        productsPage.addToCart(productName2);
        String priceProductName = productsPage.getPriceProduct(productName);
        String priceProductName2 = productsPage.getPriceProduct(productName2);
        String productsSum = String.valueOf(parseDouble(priceProductName.replace("$", "")) +
                parseDouble(priceProductName2.replace("$", "")));
        productsPage.clickCartButton();
        productsPage.checkoutClick();
        softAssert.assertEquals(
                checkoutPage.getTitleYourInformation(),
                "Checkout: Your Information",
                "Страница Checkout: Your Information не открылась");
        checkoutPage.sendYourInformation("Droid", "R2-D2", "999");
        softAssert.assertEquals(
                checkoutPage.getTitleOverview(),
                "Checkout: Overview",
                "Страница Checkout: Overview не открылась");
        softAssert.assertEquals(
                checkoutPage.getPriceProduct(productName),
                priceProductName,
                "Товар в Checkout: Overview не соответствует цене товара №1 на странице Products");
        softAssert.assertEquals(
                checkoutPage.getPriceProduct(productName2),
                priceProductName2,
                "Товар в Checkout: Overview не соответствует цене товара №2 на странице Products");
        softAssert.assertEquals(
                checkoutPage.getItemTotalValue().replace("Item total: $", ""),
                productsSum,
                "В поле Item total Сумма значений двух товаров не верное");
        String tax = checkoutPage.getTaxValue().replace("Tax: $", "");
        String total = String.format("%.2f", (parseDouble(productsSum) + parseDouble(tax))).replace(",", ".");
        softAssert.assertEquals(
                checkoutPage.getTotalValue().replace("Total: $", ""),
                total,
                "Сумма Total не соответствует сумме всех товаров и Tax");
        softAssert.assertAll();
    }

    @Test(testName = "Тест проверки оформления заказа",
            description = "Тест проверки оформления заказа", groups = {"smoke", "ui"})
    @Description("Тест проверки оформления заказа")
    @Feature("Проверка товара в корзине")
    @Story("Оформление заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCompleteOrderTest() {
        loginPage.login(login, password);
        productsPage.addToCart(productName);
        productsPage.clickCartButton();
        productsPage.checkoutClick();
        checkoutPage.sendYourInformation("Droid", "R2-D2", "999");
        checkoutPage.finishClick();
        softAssert.assertEquals(
                checkoutPage.getTitleComplete(),
                "Checkout: Complete!",
                "Страница Checkout: Complete! не открылась");
        softAssert.assertEquals(
                checkoutPage.getCompleteMessage(),
                "Thank you for your order!",
                "Сообщение не отобразилось");
        checkoutPage.backHomeClick();
        softAssert.assertEquals(
                productsPage.getTitle(),
                "Products",
                "Страница Products не открылась");
        softAssert.assertAll();
    }
}
