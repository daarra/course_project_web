package task1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import io.qameta.allure.Step;

import java.util.List;
import org.apache.log4j.Logger;
import managers.PageManager;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);
    private PageManager pageManager = PageManager.getInstance();

    @FindBy(xpath = "//h2[contains(text(), 'LambdaTest Sample App')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[contains(text(), '5 of 5 remaining')]")
    private WebElement remainingText;

    @FindBy(xpath = "//li[@class='ng-scope']")
    private List<WebElement> listItems;

    @FindBy(xpath = "//input[@placeholder='Want to add more']")
    private WebElement inputField;

    @FindBy(xpath = "//input[@value='add']")
    private WebElement addButton;

    @FindBy(xpath = "//span[@class='ng-binding']")
    private WebElement countLi;

    @Step("Проверить, что присутствует заголовок")
    public StartPage isPageTitleDisplayed() {
        Assert.assertTrue("Заголовок 'LambdaTest Sample App' не найден",
                pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App"));
        logger.info("Нужный заголовок присутствует на странице сайта");
        return pageManager.getStartPage_task1();
    }

    @Step("Проверить, что присутствует текст '5 of 5 remaining'")
    public StartPage isRemainingTextDisplayed() {
        Assert.assertTrue("Текст '5 of 5 remaining' не найден на странице",
                remainingText.isDisplayed() && remainingText.getText().contains("5 of 5 remaining"));
        logger.info("Нужный текст присутствует на странице сайта");
        return pageManager.getStartPage_task1();
    }

    @Step("Добавить элемент в список")
    public StartPage addItemToList(String text) {
        inputField.sendKeys(text);
        addButton.click();  // Добавлено нажатие кнопки добавления
        logger.info("Элемент добавлен в список");
        return pageManager.getStartPage_task1();
    }

    @Step("Проверить, что элемент списка зачеркнут")
    public StartPage checkTheListItemAndClick(String nameInput) {
        int initialCount = Integer.parseInt(countLi.getText().split("\\s+")[0]);

        for (WebElement menuItem : listItems) {
            String inputName = menuItem.findElement(By.tagName("input")).getAttribute("name");
            if (inputName.equalsIgnoreCase(nameInput)) {
                String spanClass = menuItem.findElement(By.tagName("span")).getAttribute("class");
                Assert.assertTrue("Элемент списка " + nameInput + " зачеркнут", spanClass.contains("done-false"));
                menuItem.findElement(By.xpath(".//input[@type='checkbox']")).click();
                break;
            }
        }

        int newCount = Integer.parseInt(countLi.getText().split("\\s+")[0]);

        for (WebElement menuItem : listItems) {
            String inputName = menuItem.findElement(By.tagName("input")).getAttribute("name");
            if (inputName.equalsIgnoreCase(nameInput)) {
                String spanClass = menuItem.findElement(By.tagName("span")).getAttribute("class");
                Assert.assertEquals("Количество оставшихся элементов не уменьшилось на один", initialCount - 1, newCount);
                Assert.assertTrue("Элемент списка " + nameInput + " не становится зачеркнутым", spanClass.contains("true"));
                break;
            }
        }
        logger.info("Элемент зачеркнут");
        return pageManager.getStartPage_task1();
    }
}
