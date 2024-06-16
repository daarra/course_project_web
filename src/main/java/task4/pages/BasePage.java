package task4.pages;

import managers.ChromeDriverManager;
import managers.PageManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final ChromeDriverManager chromeDriverManager = ChromeDriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(chromeDriverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(500));
    protected Actions action = new Actions(chromeDriverManager.getDriver());

    protected static WebElement firstProduct;
    protected static String firstTitle;
    protected static String firstPrice;
    protected static String firstDescription;

    public BasePage() {
        PageFactory.initElements(chromeDriverManager.getDriver(), this);
    }


    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) chromeDriverManager.getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    void hoverOverElement(WebElement element) {
        String mouseOverScript = "var event = new MouseEvent('mouseover', {view: window, bubbles: true, cancelable: true}); arguments[0].dispatchEvent(event);";
        ((JavascriptExecutor) chromeDriverManager).executeScript(mouseOverScript, element);
        waitUntilElementToBeVisible(element); // Убедимся, что элемент видим после наведения
    }


    protected void waitUntilPageLoadComplete() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    protected void waitUntilClassContains(WebElement element, String className) {
        wait.until(ExpectedConditions.attributeContains(element, "class", className));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilUrlToBe(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(chromeDriverManager.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    private void waitUntilUrlIsExpected(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    protected void moveToElement(WebElement element) {
        Actions actions = new Actions(chromeDriverManager.getDriver());
        actions.moveToElement(element).perform();

    }

    private void assertUrlIsExpected(String expectedUrl) {
        String actualUrl = chromeDriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals("Текущий URL не совпадает с ожидаемым", expectedUrl, actualUrl);
    }

}
