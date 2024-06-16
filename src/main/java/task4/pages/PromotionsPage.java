package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PromotionsPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h1[@class='s2dsx'")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[@id='promo-city-select']//div[contains(@class, 'ga-select__box')]")
    private WebElement cityDropdown;

    @FindBy(xpath = "//article[@class='sZV0h F6eAO o3uSq']")
    private List<WebElement> promoArticles;

    @FindBy(xpath = "//*[@id=\"__layout\"]/div/main/div[2]/div/div[1]/div/div/div/article[1]/a")
    private WebElement firstPromo;

    @Step("Проверить, что заголовок 'Акции' присутствует на странице")
    public PromotionsPage isPageTitleCorrect() {
        Assert.assertTrue("Заголовок с классом '-F41N' и текстом 'Акции' не найден",
                pageTitle.isDisplayed() && pageTitle.getText().trim().equals("акции"));
        logger.info("Заголовок 'Акции' с классом '-F41N' найден на странице");
        return pageManager.getpromotionsPage();
    }

    @Step("Выбрать город {cityName}")
    public PromotionsPage selectCity(String cityName) {
        try {
            // Открытие выпадающего списка
            wait.until(ExpectedConditions.elementToBeClickable(cityDropdown)).click();

            // Ожидание появления опций и выбор нужного города
            WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ga-select-list__option']/span[text()='" + cityName + "']")));
            cityOption.click();

            logger.info("Успешно выбрали город: " + cityName);
        } catch (Exception e) {
            logger.error("Возникла ошибка при выборе города: " + cityName, e);
        }
        return this;
    }

    @Step("Перейти по ссылке на первую акцию")
    public PromotionsPage goToFirstPromotion() {
        waitUntilElementToBeClickable(firstPromo).click();
        logger.info("Кликнули на первую акцию");
        return this;
    }

}