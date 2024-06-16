package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class XboxPage extends BasePage {
    private static final Logger logger = Logger.getLogger(task3.pages.StartPage.class);

    @FindBy(xpath = "//div[@data-auto-themename='listDetailed']")
    private List<WebElement> productList;

    @FindBy(xpath = "//h1[@data-auto='title']")
    private WebElement title;

    private WebElement wishlistButton;

    @FindBy(xpath = "//div[@data-auto='notification']")
    private WebElement notification;

    @FindBy(xpath = "//div[@data-baobab-name='favorites']")
    private WebElement favoruteButton;

    @Step("Проверяем, что заголовок равен 'x-box игровые приставки'")
    public XboxPage verifyTitle() {
        waitUntilElementToBeVisible(title);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String elementText = title.getText();
        Assert.assertEquals("Заголовок страницы не соответствует ожидаемому", "Игровые приставки Xbox", elementText);
        logger.info("Заголовок страницы верный: " + elementText);
        return pageManager.getxboxPage();
    }

    //Здесь не работала корректно ссылка на первую приставку во владке, поэтому начинаем элемент с первого доступного элемента
    @Step("Вывести в лог первые 5 найденных товаров")
    public XboxPage logProducts() {
        boolean flag = true;
        for (int i =1 ; i < 6 && i < productList.size(); i++) {
            WebElement product = productList.get(i);
            moveToElement(product);

            String title = product.findElement(By.xpath(".//h3")).getText();
            String price = product.findElement(By.xpath(".//span[@data-auto='snippet-price-current']")).getText();
            if(flag == true){
                firstProduct = product;
                firstTitle = title;
                firstPrice = price;
            }
            flag = false;
            logger.info("Название: " + title + ". Цена: " + price);

        }
        String title = firstProduct.findElement(By.xpath(".//h3")).getText();
        String price = firstProduct.findElement(By.xpath(".//span[@data-auto='snippet-price-current']")).getText();
        logger.info(title + price);
        logger.info("Название первого товара: " + firstTitle + ". Цена первого товара: " + firstPrice);
        return this;
    }

    @Step("Нажать на каталог")
    public XboxPage clickOnWishListButton() {
        if (firstProduct == null) {
            Assert.fail("Первый продукт не был сохранен. Пожалуйста, вызовите метод logProducts() перед этим методом.");
        }
        moveToElement(firstProduct);
        wishlistButton = firstProduct.findElement(By.xpath(".//button"));

        String beforeTitle = wishlistButton.getAttribute("title");
        System.out.println(beforeTitle);
        firstProduct.findElement(By.xpath(".//button")).click();
        logger.info("Кнопка 'Добавить в избранное' нажата");

        // Проверка изменения кнопки на "Удалить из избранного"
        String afterTitle = wishlistButton.getAttribute("title");
        Assert.assertEquals("Кнопка 'Добавить в избранное' не изменилась на 'Удалить из избранного'", "Удалить из избранного", afterTitle);
        logger.info("Сообщение '" + beforeTitle + "' поменялось на '" + afterTitle +"'");

        waitUntilElementToBeVisible(notification);
        String notificationText = notification.getText();
        Assert.assertTrue("Всплывающее сообщение не появилось или неверно", notificationText.contains("Товар добавлен в избранное. Нажмите, чтобы перейти к списку."));
        logger.info("Появилось всплывающее сообщение: " + notificationText);

        return this;
    }

    @Step("Нажать на переход во вкладку 'Избранное'")
    public FavoritePage clickOnFavoriteButton(){
        moveToElement(favoruteButton);
        favoruteButton.click();
        return pageManager.getfavoritePage().verifyTitle().verifyProducts();
    }
}
