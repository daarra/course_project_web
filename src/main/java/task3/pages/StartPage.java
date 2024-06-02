package task3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import java.util.List;


public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(task3.pages.StartPage.class);

    @FindBy(xpath = "//meta[@property='og:url']")
    private WebElement url;

    @FindBy(xpath = "//div[@data-baobab-name='catalog']/button")
    private WebElement catalogButton;

    @FindBy(xpath = "//li[@data-zone-name='category-link']/a")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//ul[@data-autotest-id]//li//a")
    private List<WebElement> menuItemList;

    private static final String EXPECTED_URL = "https://market.yandex.ru/";

    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actualUrl = url.getAttribute("content");
        Assert.assertTrue("Текущий URL не совпадает с ожидаемым",
                EXPECTED_URL.equals(actualUrl));
        logger.info("Открыли главную страницу сайта");
        return pageManager.getStartPage_task3();
    }

    @Step("Нажать на каталог")
    public StartPage clickOnCatalog() {
        waitUntilElementToBeClickable(catalogButton).click();
        logger.info("Кликнули на каталог");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getStartPage_task3();
    }


    @Step("Навести на категорию '{category}'")
    public StartPage moveToCategory1(String category) {
        boolean categoryFound = false;
        for (WebElement element : categoryList) {
            waitUntilElementToBeVisible(element);
            moveToElement(element);
            try {
                WebElement spanElement = element.findElement(By.xpath("./span"));
                String spanText = spanElement.getText().trim();
                if (spanText.equalsIgnoreCase(category)) {
                    logger.info("Навели на категорию '" + category + "'");
                    categoryFound = true;
                    return pageManager.getStartPage_task3();
                }
            } catch (Exception e) {
                logger.warn("Не удалось найти элемент span внутри категории: " + e.getMessage());
                return pageManager.getStartPage_task3();
            }
        }

        if (!categoryFound) {
            Assert.fail("Нет категории '" + category + "'");
            return pageManager.getStartPage_task3();
        }
        return pageManager.getStartPage_task3();
    }

    @Step("Нажать на пункт меню '{menuItem}'")
    public XboxPage clickOnMenuItem(String menuItem) {
        for (WebElement item: menuItemList) {
            if (waitUntilElementToBeVisible(item).getText().equals(menuItem)){
                moveToElement(item);
                item.click();
                logger.info("Переход на страницу с ноутбуками");
                return pageManager.getxboxPage();
            }
        }
        Assert.fail("Не найден пункт меню " + menuItem);
        return pageManager.getxboxPage();
    }

}
