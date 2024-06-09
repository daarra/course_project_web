package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FavoritePage extends BasePage{
    private static final Logger logger = Logger.getLogger(task3.pages.StartPage.class);
    @FindBy(xpath = "//span[@class='_1_C0_ _1n-yE _33-CL AODiB']")
    private WebElement title;

    @FindBy(xpath = "//div[@data-zone-name='productSnippet']")
    private List<WebElement> productList;

    private WebElement wishlistButton;

    @FindBy(xpath = "//div[@data-auto='notification']")
    private WebElement notification;

    @FindBy(xpath = "//span[@data-auto='emptyStateHeader']")
    private WebElement message;

    @FindBy(xpath = "//div[@data-baobab-name='favorites']")
    private WebElement favoruteButton;

    private WebElement productFirst;

    @Step("Проверяем, что перешли по нужной ссылке 'Избранное' ")
    public FavoritePage verifyTitle() {
        waitUntilElementToBeVisible(title);
        String elementText = title.getText();
        System.out.println(elementText);
        Assert.assertEquals("Заголовок страницы не соответствует ожидаемому", "Избранное", elementText);
        logger.info("Заголовок страницы верный: " + elementText);
        return pageManager.getfavoritePage();
    }

    @Step("Проверяем, что на странице нужный нам товар ")
    public FavoritePage verifyProducts() {
        logger.info("Проверка");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
       }
        logger.info(productList.size());
        for (int i = 0; i < productList.size(); i++) {
            logger.info("Проверка");
            WebElement product = productList.get(i);
            moveToElement(product);
            logger.info("Проверка");
            String title = product.findElement(By.xpath(".//h3")).getText();
            System.out.println(title);
            logger.info("Проверка");
            String price = product.findElement(By.xpath(".//span[@class='_1ArMm']")).getText();
            System.out.println(price);
            logger.info("Проверка");
            System.out.println(firstTitle);
            System.out.println(firstPrice);
            if(title.equals(firstTitle) && (price.equals(firstPrice))){
                productFirst = product;
                logger.info("Проверка");
                logger.info("В 'Избранном' представлен нужный товар");
                return pageManager.getfavoritePage();
            }
        }
        return pageManager.getfavoritePage();
    }

    @Step("Нажать на сердечко для того, чтобы удалить из Избранного")
    public FavoritePage removeFromWishListButton() {
        logger.info("Кнопка 'Убрать из избранного' нажата");
        moveToElement(productFirst);
        wishlistButton = productFirst.findElement(By.xpath(".//button"));
        wishlistButton.click();
        logger.info("Кнопка 'Убрать из избранного' нажата");

        waitUntilElementToBeVisible(notification);
        String notificationText = notification.getText();
        System.out.println(notificationText);
        Assert.assertTrue("Всплывающее сообщение не появилось или неверно", notificationText.contains("Товар удалён из избранного"));
        logger.info("Появилось всплывающее сообщение: " + notificationText);
        return this;
    }

    public FavoritePage updatePage(){
        moveToElement(favoruteButton);
        favoruteButton.click();
        Assert.assertEquals("Cообщение о входе в аккаунт не появилось", "Войдите в аккаунт", message.getText());
        logger.info("Появилось всплывающее сообщение: " + message.getText());
        return pageManager.getfavoritePage();
    }



}
