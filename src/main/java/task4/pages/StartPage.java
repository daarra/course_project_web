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

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/h1")
    private WebElement pageTitle;

    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        // Ожидание загрузки заголовка страницы
        waitUntilElementToBeVisible(pageTitle);

        // Проверка заголовка страницы
        Assert.assertEquals("Заголовок страницы не соответствует ожидаемому", "Золотое яблоко", pageTitle.getText());

        // Ожидание полной загрузки страницы (проверка всех ресурсов)
        waitUntilPageLoadComplete();

        logger.info("Открыли главную страницу сайта");
        return this;
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