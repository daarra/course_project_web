package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoryPage extends BasePage{
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//div[@id='plp-sorting']")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//ul[@class='CUrx5']/li[3]//span[text()='по возрастанию цены']")
    private WebElement sortAscendingPriceOption;

    @FindBy(xpath = "//span[@class='ga-select__box-content-value']")
    private WebElement selectedSortOption;

    @FindBy(xpath = "//article[@itemtype='https://schema.org/Product']")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='ga-notifications-swiper ga-notifications__notification-swiper']")
    private WebElement notificationTitle;

    String nameProduct;

    @Step("Выбираем сортировку по возрастанию цены")
    public CategoryPage sortByAscendingPrice() {
        waitUntilElementToBeClickable(sortingDropdown).click();
        logger.info("Открыли выпадающий список сортировки");

        try {
            Thread.sleep(1000);  // Пауза для стабильности
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitUntilElementToBeVisible(sortAscendingPriceOption).click();
        logger.info("Выбрали сортировку по возрастанию цены");

        try {
            Thread.sleep(1000);  // Пауза для стабильности
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String selectedOptionText = waitUntilElementToBeVisible(selectedSortOption).getText();
        System.out.println(selectedOptionText);
        logger.info("Текущая выбранная сортировка: " + selectedOptionText);

        // Добавляем проверку
        Assert.assertEquals("Выбранная сортировка не соответствует ожидаемой.", "по возрастанию цены", selectedOptionText);
        return this;
    }

    @Step("Навести на элемент изображения и нажать на кнопку для первого продукта")
    public CategoryPage hoverAndClickFirstProduct() {
        if (products.isEmpty()) {
            logger.warn("No products found on the page.");
            return this;
        }

        WebElement firstProduct = products.get(0);
        scrollToElementJs(firstProduct );
        // Находим элемент изображения и кнопку внутри первого продукта
        WebElement imageElement = firstProduct.findElement(By.xpath(".//picture[@itemprop='image']"));

        // Находим span с itemprop='brand' и затем соседний текстовый узел
        WebElement brandElement = firstProduct.findElement(By.xpath(".//span[@itemprop='brand']/meta[@itemprop='name']"));
        WebElement parentDiv = brandElement.findElement(By.xpath("./../.."));
        String fullText = parentDiv.getText().trim();
        nameProduct = fullText;
        WebElement secondButtonElement = firstProduct.findElement(By.xpath("//button[contains(@class, 'U89+H _5WWcc')]"));

        // Наводим курсор на элемент изображения
        moveToElement(imageElement);
        logger.info("Навели курсор на элемент изображения");

        // Ожидаем появления кнопки после наведения
        waitUntilElementToBeClickable(secondButtonElement).click();
        logger.info("Нажали на кнопку");
        return this;
    }


    @Step("Проверяем уведомление о добавлении товара в корзину")
    public void verifyAddToCartNotification() {
        // Ожидаем появление уведомления
        waitUntilElementToBeVisible(notificationTitle);

        // Проверяем текст уведомления
        String notificationText = notificationTitle.getText().trim();
        System.out.println(notificationText);
        Assert.assertTrue("Уведомление не содержит текст 'добавлен в корзину'", notificationText.contains("добавлен в корзину"));
        Assert.assertTrue("Верный товар добавлен в корзину'", notificationText.contains(nameProduct));

//        // Проверяем описание товара
//        String actualProductDescription = productDescription.getText().trim();
//        Assert.assertEquals("Описание товара в уведомлении не совпадает с ожидаемым", expectedProductName, actualProductDescription);
//
//        // Проверяем количество товара
//        String actualProductQuantity = productQuantity.getText().trim();
//        Assert.assertEquals("Количество товара в уведомлении не совпадает с ожидаемым", expectedQuantity, actualProductQuantity);

        logger.info("Уведомление о добавлении товара в корзину успешно проверено.");
    }

}
