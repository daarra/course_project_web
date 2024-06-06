package task2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import task2.pages.BasePage;
import java.util.List;

public class GroupSchedulePage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//div[@class='found-groups row not-print']/div")
    private List<WebElement> listItems;

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/h4")
    private WebElement title;

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/div[3]/input[1]")
    private WebElement input;

    @FindBy(xpath = "//div[contains(@class, 'schedule-day_today')]")
    private WebElement todayElement;

    @FindBy(xpath = "//div[contains(@class, 'schedule-day_today')]/div[contains(@class, 'title')]")
    private WebElement dayToday;

    @Step("Проверка перехода на страницу расписание занятий")
    public GroupSchedulePage checkOpenClassSchedulePage() {
        waitUntilElementToBeVisible(title);
        String actualTitleText = title.getText();
        String expectedTitleText = "Расписание занятий";
        Assert.assertTrue("Заголовок Расписание занятий не найден", title.isDisplayed() &&
                actualTitleText.contains(expectedTitleText));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getStartGroupSchedulePage();
    }

    @Step("Вводим номер группы и проверяем, что в результате поиска только эта группа")
    public GroupSchedulePage inputGroup(String groupNumber) {
        waitUntilElementToBeVisible(input);
        input.clear();
        input.sendKeys(groupNumber);
        logger.info("Ввели номер группы: " + groupNumber);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Динамически находим нужную группу по id
        WebElement foundGroup = findGroupById(groupNumber);

        Assert.assertTrue("В результатах поиска не отображается нужная группа",
                foundGroup.isDisplayed() && foundGroup.getText().equals(groupNumber));
        Assert.assertEquals("В результатах поиска отображается больше одной группы", 1, listItems.size());
        logger.info("В результатах поиска только нужная группа");

        // Нажимаем на найденную группу
        foundGroup.click();
        logger.info("Кликнули на группу: " + groupNumber);

        // Определяем текущий день недели и выводим его
        highlightCurrentDay();

        return pageManager.getStartGroupSchedulePage();
    }

    @Step("Определяем текущий день недели и выделяем его в расписании")
    private void highlightCurrentDay() {
        waitUntilElementToBeVisible(dayToday);
        if (dayToday.isDisplayed()) {
            String currentDay = dayToday.getText();
            logger.info("Текущий день недели: " + currentDay);
        } else {
            logger.info("Сегодня воскресенье. Расписание отсутствует.");
        }
    }

    private WebElement findGroupById(String groupId) {
        for (WebElement element : listItems) {
            if (element.getAttribute("id").equals(groupId)) {
                return element;
            }
        }
        throw new RuntimeException("Группа с id " + groupId + " не найдена");
    }
}