package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    private static final String EXPECTED_URL = "https://goldapple.ru/";

    private static final String EXPECTED_URL2 = "https://goldapple.ru/novinki";

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/section[3]/div/div/div[2]/div/section/header/div[1]/div/a/h2")
    private WebElement newCategory;

    @Step("Проверяем, что открыта главная страница по URL")
    public StartPage verifyHomePageUrl() {
        // Wait for the page to load completely
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        // Wait until the URL is as expected
        wait.until(ExpectedConditions.urlToBe(EXPECTED_URL));

        // Get the actual URL of the current page
        String actualUrl = chromeDriverManager.getDriver().getCurrentUrl();

        // Assert that the actual URL matches the expected URL
        Assert.assertEquals("Текущий URL не совпадает с ожидаемым", EXPECTED_URL, actualUrl);

        // Log that the homepage is opened
        logger.info("Открыли главную страницу сайта");

        // Return the StartPage instance
        return pageManager.getStartPage_task4();
    }


    @Step("Нажать на каталог")
    public StartPage clickOnCategory() {
        waitUntilElementToBeClickable(newCategory).click();
        logger.info("Кликнули на каталог Новинок");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getStartPage_task4();
    }

    @Step("Проверяем, что открыта страница с новинками по URL")
    public NewProductsPage verifyNewPageUrl() {
        // Wait for the page to load completely
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        // Wait until the URL is as expected
        wait.until(ExpectedConditions.urlToBe(EXPECTED_URL2));

        // Get the actual URL of the current page
        String actualUrl = chromeDriverManager.getDriver().getCurrentUrl();

        // Assert that the actual URL matches the expected URL
        Assert.assertEquals("Текущий URL не совпадает с ожидаемым", EXPECTED_URL2, actualUrl);

        // Log that the homepage is opened
        logger.info("Открыли страницу с новинками сайта");

        // Return the StartPage instance
        return pageManager.getNewProductsPage();
    }

}