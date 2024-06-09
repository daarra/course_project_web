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

import java.time.Duration;
import java.util.List;

public class PromotionsPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h1[@class='-F41N']")
    private WebElement pageTitle;

    @Step("Проверить, что заголовок 'Акции' присутствует на странице")
    public PromotionsPage isPageTitleCorrect() {
        Assert.assertTrue("Заголовок с классом '-F41N' и текстом 'Акции' не найден",
                pageTitle.isDisplayed() && pageTitle.getText().trim().equals("Акции"));
        logger.info("Заголовок 'Акции' с классом '-F41N' найден на странице");
        return pageManager.getpromotionsPage();
    }
}