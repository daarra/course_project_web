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

    @FindBy(xpath = "//li[@data-zone-name='category-link']")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//ul[@data-autotest-id]//li//a")
    private List<WebElement> menuItemList;

    @FindBy(xpath = "//div[@data-baobab-name='linkSnippet']")
    private List<WebElement> xboxItemList;

    private static final String EXPECTED_URL = "https://market.yandex.ru/";

    @Step("Проверяем, что открыта главная страница")
    public StartPage verifyHomePageUrl() {
        try{
            Thread.sleep(10000);
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
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getStartPage_task3();
    }


    @Step("Навести на категорию '{category}'")
    public StartPage moveToCategory(String category) {
        try {
            Thread.sleep(1000); // Ждем перед наведением, если это необходимо
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement element : categoryList) {
            waitUntilElementToBeVisible(element);
            WebElement linkElement = element.findElement(By.xpath("./a"));
            WebElement spanElement = linkElement.findElement(By.xpath("./span"));
            String elementText = spanElement.getText();
            logger.info("Проверяем элемент с текстом: " + elementText);

            if (elementText.equals(category)) {
                // Логирование перед наведением
                String ariaSelectedValueBefore = element.getAttribute("aria-selected");
                logger.info("До наведения, aria-selected='" + ariaSelectedValueBefore + "' для категории '" + category + "'");

                // Наведение на ссылку <a> внутри <li>
                moveToElement(linkElement);

                // Явное ожидание после наведения
                waitUntilElementToBeVisible(spanElement);

                // Дополнительное ожидание для проверки
                try {
                    Thread.sleep(2000); // Дополнительное ожидание
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Логирование после наведения
                String ariaSelectedValueAfter = element.getAttribute("aria-selected");
                logger.info("После наведения, aria-selected='" + ariaSelectedValueAfter + "' для категории '" + category + "'");

                // Проверяем, что aria-selected="true"
                logger.info("Навели на категорию '" + category + "', проверяем aria-selected...");
                try {
                    waitUntilAttributeToBe(element, "aria-selected", "true");
                } catch (Exception e) {
                    logger.error("Не удалось дождаться изменения aria-selected на 'true' для категории '" + category + "'.");
                    Assert.fail("Не удалось дождаться изменения aria-selected на 'true' для категории '" + category + "'.");
                }

                ariaSelectedValueAfter = element.getAttribute("aria-selected");
                if ("true".equals(ariaSelectedValueAfter)) {
                    logger.info("Элемент с категорией '" + category + "' успешно выбран (aria-selected=true)");
                    return this;
                } else {
                    Assert.fail("Элемент найден, но aria-selected не установлен в 'true' для категории '" + category + "'. Текущее значение: " + ariaSelectedValueAfter);
                }
            }
        }
        Assert.fail("Нет категории '" + category + "'");
        return this;
    }



    @Step("Нажать на пункт меню '{menuItem}'")
    public XboxPage clickOnMenuItem(String category, String section, String menuItem) {
        moveToCategory(category);
        boolean sectionFound = false;

        for (WebElement item : xboxItemList) {
            if (sectionFound) {
                // После нахождения нужного секции, ищем menuItem
                if (waitUntilElementToBeVisible(item).getText().equals(menuItem)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    moveToElement(item);
//                    item.click();

                    logger.info("Переход на страницу с меню: " + menuItem);
                    return pageManager.getxboxPage().verifyTitle();
                }
            } else if (waitUntilElementToBeVisible(item).getText().equals(section)) {
                sectionFound = true;
            }
        }

        Assert.fail("Не найден пункт меню " + menuItem);
        return pageManager.getxboxPage();
    }

}