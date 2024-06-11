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

import java.util.List;

public class BrandsPage extends BasePage {
    private static final Logger logger = Logger.getLogger(BrandsPage.class);

    @FindBy(xpath = "//input[@placeholder='найти бренды']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@class, 'Glpeq')]")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@class='_0SdbU']")
    private List<WebElement> searchResults;

    @Step("Вводим поисковый запрос '{query}' в поле поиска")
    public BrandsPage enterSearchQuery(String query) {
        waitUntilElementToBeVisible(searchInput).sendKeys(query);
        logger.info("Введен поисковый запрос: " + query);
        return this;
    }

    @Step("Проверяем отображение результатов поиска")
    public BrandsPage verifySearchResultsCount() {
        waitForSearchResultsToLoad();
        waitUntilElementToBeVisible(searchResults.get(0)); // Дождаться отображения хотя бы одного результата
        int resultCount = searchResults.size();
        logger.info("Количество найденных результатов: " + resultCount);
        System.out.println("Количество найденных результатов: " + resultCount);
        return this;
    }

    @Step("Проверяем отображение результата поиска для: {expectedText}")
    public BrandsPage verifySearchResult(String expectedText) {
        waitForSearchResultsToLoad();
        String xpath = String.format("//a[contains(@href, '/brands/%s') and .//mark[text()='%s']]", expectedText.toLowerCase(), expectedText);
        WebElement resultElement = waitUntilElementToBeVisible(chromeDriverManager.getDriver().findElement(By.xpath(xpath)));
        Assert.assertTrue("Результаты поиска не отображаются", resultElement.isDisplayed());
        logger.info("Результат поиска отображается корректно для: " + expectedText);
        return this;
    }

    private void waitForSearchResultsToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriverManager.getDriver();

        // Scroll to the bottom of the page to ensure lazy-loaded elements are visible
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        try {
            // Wait for search results to be visible
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='_0SdbU']")));
        } catch (Exception e) {
            logger.error("Ошибка при ожидании результатов поиска", e);
        }
    }
}