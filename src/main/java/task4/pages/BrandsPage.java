package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import java.util.List;

public class BrandsPage extends BasePage {
    private static final Logger logger = Logger.getLogger(BrandsPage.class);

    @FindBy(xpath = "//input[@placeholder='найти бренды']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@class, 'Glpeq')]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/div[2]/div/div[2]/div/div/div/div/div[55]/div/ul/li/button")
    private WebElement clarins;

    @FindBy(xpath = "//div[@class='KwHbB']")
    private WebElement notification;

    @Step("Вводим поисковый запрос '{query}' в поле поиска")
    public BrandsPage enterSearchQuery(String query) {
        waitUntilElementToBeVisible(searchInput).sendKeys(query);
        logger.info("Введен поисковый запрос: " + query);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Добавляем бренд в избранное")
    public BrandsPage verifySearchResultsCount() {
        System.out.println(clarins.getText());
        clarins.click();

        return this;
    }

    public BrandsPage checkNotification(){
        waitUntilElementToBeVisible(notification);
        String title = notification.findElement(By.xpath(".//h2")).getText();
        String info = notification.findElement(By.xpath(".//p")).getText();
        Assert.assertTrue("Всплывающее сообщение не появилось или неверно", title.contains("войти или зарегистрироваться"));
        Assert.assertTrue("Всплывающее сообщение не появилось или неверно", info.contains("Войдите в личный кабинет, чтобы добавить бренд в избранное. Позвоним или пришлём SMS. Введите последние четыре цифры номера телефона или код из SMS-сообщения."));
        logger.info("Появилось всплывающее сообщение: " + info );
        return this;
    }


}