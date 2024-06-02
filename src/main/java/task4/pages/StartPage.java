package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    private static final String EXPECTED_URL = "https://goldapple.ru/";

    @Step("Проверяем, что открыта главная страница по URL")
    public StartPage verifyHomePageUrl() {
        wait.until(ExpectedConditions.urlToBe(EXPECTED_URL));
        String actualUrl = driverManager.getDriver().getCurrentUrl();
        Assert.assertEquals("Текущий URL не совпадает с ожидаемым", EXPECTED_URL, actualUrl);
        logger.info("Открыли главную страницу сайта");
        return pageManager.getStartPage_task4();
    }
}
