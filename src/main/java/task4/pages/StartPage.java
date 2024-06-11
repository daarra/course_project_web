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

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/section[3]/div/div/div[2]/div/section/header/div[1]/div/a/h2")
    private WebElement newCategory;

    @FindBy(xpath = "//div[@class='ga-home-base-stories-circles-slider__wrapper v9Ktf QGzi0']")
    private WebElement slider;

    @FindBy(xpath = "//*[@id='__layout']/div/header/div[3]/div/nav/ul/li[4]/a")
    private WebElement promo;

    @FindBy(xpath = "//*[@id='__layout']/div/header/div[3]/div/nav/ul/li[2]/a")
    private WebElement brands;

    @FindBy(xpath = "//button[@class='vJN8q nDH3D _2f6zk MB703 z8puL ic2Pk']")
    private WebElement button;


    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        moveToElement(slider);
        boolean flag = slider.isDisplayed();
        Assert.assertTrue("Slider element is not present on the page", flag);
        logger.info("Открыли главную страницу сайта");
        return this;
    }

    @Step("Нажать на каталог новинок")
    public NewProductsPage clickOnCategory() {
        waitUntilElementToBeClickable(newCategory).click();
        logger.info("Кликнули на каталог Новинок");
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getNewProductsPage();
    }

    @Step("Нажать на каталог акций")
    public PromotionsPage clickOnPromo() {
        scrollToElementJs(promo);
        waitUntilElementToBeClickable(promo).click();
        logger.info("Кликнули на каталог Акций");
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getpromotionsPage();
    }

    @Step("Нажать на кнопку потверждения локации")
    public StartPage clickOnButtonLocation() {
        scrollToElementJs(button);
        waitUntilElementToBeClickable(button).click();
        logger.info("Кнопка для всплывающего окна с локацией успешно нажата");
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getStartPage_task4();
    }

    @Step("Нажать на каталог брендов")
    public BrandsPage clickOnBrands() {
        scrollToElementJs(brands);
        waitUntilElementToBeClickable(brands).click();
        logger.info("Кликнули на каталог Брендов");
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getBrandsPage();
    }

}