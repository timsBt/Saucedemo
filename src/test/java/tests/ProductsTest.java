package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static utils.PropertiesUtils.valueProperties;

public class ProductsTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    String productName = "Sauce Labs Fleece Jacket";

    @Test(description = "Тест добавления товара в корзину. Проверка наименования, описания и цены товара в корзине")
    public void addProductTest() {
        loginPage.login(valueProperties("login"), valueProperties("password"));
        softAssert.assertEquals(
                productsPage.getTitle(),
                "Products",
                "Авторизация не пройдена");
        productsPage.clickAddToCardProductButton(productName);
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

    @Test(description = "Тест проверки значения иконки корзины после добавления и удаления товара")
    public void checkCartBadgeTest() {
        loginPage.login(valueProperties("login"), valueProperties("password"));
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

    @Test(description = "Тест проверки попадания всех товаров на странице в корзину")
    public void checkAllProductsNameOnCartTest() {
        loginPage.login(valueProperties("login"), valueProperties("password"));
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
}
