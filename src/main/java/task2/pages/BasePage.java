package task2.pages;

import managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import managers.PageManager;
import java.util.List;

import java.time.Duration;

public class BasePage {
    protected final ChromeDriverManager chromeDriverManager = ChromeDriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(chromeDriverManager.getDriver(), Duration.ofSeconds(10), Duration.ofMillis(1000));
    protected Actions action = new Actions(chromeDriverManager.getDriver());


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

    protected void moveToElement(WebElement element) {
        scrollToElementJs(element);
    }

    protected WebElement waitUntilElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitUntilElementsToBeVisible(List<WebElement> elements) {
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }


    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    protected boolean isBeforeElementPresent(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) chromeDriverManager.getDriver();
        String script = "var element = arguments[0];" +
                "var style = window.getComputedStyle(element, '::before');" +
                "return style.getPropertyValue('content') !== '';";
        return (Boolean) js.executeScript(script, element);
    }

    protected void moveToNewTab() {
        for (String tab : chromeDriverManager.getDriver().getWindowHandles()) {
            chromeDriverManager.getDriver().switchTo().window(tab);
        }
    }

}