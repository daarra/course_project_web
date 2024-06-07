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

import java.time.Duration;
import java.util.List;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    private static final String EXPECTED_URL = "https://goldapple.ru/";

    private static final String EXPECTED_URL2 = "https://goldapple.ru/novinki";

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/section[3]/div/div/div[2]/div/section/header/div[1]/div/a/h2")
    private WebElement newCategory;
    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        WebDriverWait wait = new WebDriverWait(chromeDriverManager.getDriver(), Duration.ofSeconds(30));

        // Ожидание загрузки заголовка страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        // Ожидание загрузки элементов меню (если есть)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".menu")));

        // Ожидание загрузки футера
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("footer")));

        // Дополнительные ожидания для других элементов страницы

        // После того как все элементы загрузились, проверяем URL
        waitUntilUrlToBe(EXPECTED_URL);
        String actualUrl = chromeDriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals("Текущий URL не совпадает с ожидаемым", EXPECTED_URL, actualUrl);
        logger.info("Открыли главную страницу сайта");
        return pageManager.getStartPage_task4();
    }



    @Step("Нажать на каталог")
    public StartPage clickOnCategory() {
        waitUntilElementToBeClickable(newCategory).click();
        logger.info("Кликнули на каталог Новинок");
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getStartPage_task4();
    }


}