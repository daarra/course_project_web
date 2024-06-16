package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//div[contains(@class, 'ga-home-base-stories-circles-slider__wrapper')]")
    private WebElement slider;

    @FindBy(xpath = "//button[contains(@class, 'ga-header__tab ga-header__tab_type_search')]")
    private WebElement search;

    @FindBy(xpath = "//input[@enterkeyhint='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//header//nav/ul/li[2]/a")
    private WebElement brands;

    @FindBy(xpath = "//header//nav/ul/li[1]/button")
    private WebElement catalogButton;

    @FindBy(xpath = "//li[@class='_6QieO']")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//aside[@class='ga-header__location-confirm-address']")
    private WebElement location;

    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        moveToElement(slider);
        boolean flag = slider.isDisplayed();
        Assert.assertTrue("Слайдер не представлен на главной странице", flag);
        logger.info("Открыли главную страницу сайта");
        return this;
    }

    @Step("Переходим в поиск")
    public StartPage goToSearch() {
        waitUntilElementToBeVisible(search);
        moveToElement(search);
        search.click();
        Assert.assertTrue("Элемент поиска не был нажат", search.isEnabled());
        logger.info("Открыли поиск");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Вводим поисковый запрос '{query}' в поле поиска")
    public SearchProductsPage enterSearchQuery(String query) {
        waitUntilElementToBeVisible(searchInput).sendKeys(query);
        logger.info("Введен поисковый запрос: " + query);
        searchButton.click();
        logger.info("Переход на страницу с результатами по поисковому запросу:  " + query);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getSearchProductsPage();
    }


    @Step("Переходим в каталог")
    public StartPage moveToCatalog() {
        clickOnButtonLocation();
        scrollToElementJs(catalogButton);
        waitUntilElementToBeClickable(catalogButton).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Кнопка каталога успешно нажата.");
        return this;
    }

    @Step("Нажать на кнопку потверждения локации")
    public StartPage clickOnButtonLocation() {
        WebElement button = location.findElement(By.xpath(".//button[@type='button']"));
        waitUntilElementToBeVisible(button).click();
        logger.info("Кнопка для всплывающего окна с локацией успешно нажата");
        try {
            Thread.sleep(2000);
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
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getBrandsPage();
    }

    @Step("Поиск нужной категории")
    public CategoryPage findNameCategory(String name) {
        System.out.println(categoryList.size());
        for (WebElement element : categoryList) {
            waitUntilElementToBeVisible(element);
            WebElement spanElement = element.findElement(By.tagName("span"));
            scrollToElementJs(spanElement);
            String elementName = spanElement.getText();
            logger.info("Проверяем элемент с текстом: " + elementName);
            if (elementName.toLowerCase().equals(name.toLowerCase())) {
                spanElement.click();
                return pageManager.getCategoryPage();
            }

        }
        Assert.fail("Не удалось найти категорию " + name);
        return pageManager.getCategoryPage();
    }
}