package ru.ibs.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1 {

    @Test
    public void test(){
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        // Шаг 1. Переходим по ссылке.
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        WebElement pageTitle = driver.findElement(By.xpath("//h2[contains(text(), 'LambdaTest Sample App')]"));
        Assert.assertTrue("Заголовок 'LambdaTest Sample App' не найден", pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App"));


        // Шаг 2. Проверяем, что присутствует текст: "5 of 5 remaining"
        WebElement remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '5 of 5 remaining')]"));
        Assert.assertTrue("Текст '5 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed() && remainingTextElement.getText().contains("5 of 5 remaining"));

// Получаем количество элементов списка
        List<WebElement> listItems = driver.findElements(By.xpath("//li"));
        int listItemsCount = listItems.size();

        for (int i = 1; i <= listItemsCount; i++) {
            WebElement listItem = driver.findElement(By.xpath("//li[" + i + "]"));
            WebElement spanElement = listItem.findElement(By.tagName("span"));
            String spanClassAttributeValue = spanElement.getAttribute("class");
            Assert.assertTrue("Элемент списка " + i + " зачеркнут", spanClassAttributeValue.contains("done-false"));

            // Ставим галочку у текущего элемента
            WebElement itemCheckbox = driver.findElement(By.xpath("//li[" + i + "]//input[@type='checkbox']"));
            itemCheckbox.click();


            // Проверяем, что текст счетчика изменился
            String expectedRemainingText = (listItemsCount - i) + " of " + listItemsCount + " remaining";
            WebElement remainingTextElement1 = driver.findElement(By.xpath("//span[contains(text(), '" + expectedRemainingText + "')]"));
            Assert.assertTrue("Текст '" + expectedRemainingText + "' не найден на странице", remainingTextElement1.isDisplayed());

            // Проверяем, что элемент списка зачеркнут
            listItem = driver.findElement(By.xpath("//li[" + i + "]"));
            spanElement = listItem.findElement(By.tagName("span"));
            spanClassAttributeValue = spanElement.getAttribute("class");
            Assert.assertTrue("Элемент списка " + i + " не становится зачеркнутым", spanClassAttributeValue.contains("done-true"));
        }



// Шаг 6: Добавить новый элемент списка
        // Ввод текста в форму перед добавлением элемента
        String newItemText = "Новый элемент списка";
        WebElement inputField = driver.findElement(By.xpath("//input[@placeholder='Want to add more']"));
        inputField.sendKeys(newItemText);

        // Нахождение кнопки "Add" по значению атрибута "value" и нажатие на нее
        WebElement addButton = driver.findElement(By.xpath("//input[@value='add']"));
        addButton.click();
        int new_item = 1;
        listItemsCount += 1;

        WebElement sixthListItem = driver.findElement(By.xpath("//li[" + (listItemsCount) + "]"));
        WebElement spanElement = sixthListItem.findElement(By.tagName("span"));
        String spanClassAttributeValue = spanElement.getAttribute("class");
        Assert.assertTrue("Шестой элемент списка зачеркнут", spanClassAttributeValue.contains("done-false"));
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '1 of " + listItemsCount + "')]"));
        Assert.assertTrue("Текст '" + new_item + " of " + listItemsCount + " remaining' не найден на странице", remainingTextElement.isDisplayed());
        WebElement sixthItemCheckbox = driver.findElement(By.xpath("//li[" + listItemsCount + "]//input[@type='checkbox']"));
        sixthItemCheckbox.click();
        new_item--;
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '0 of " + listItemsCount + "')]"));
        Assert.assertTrue("Текст '" + new_item + " of " + listItemsCount + " remaining' не найден на странице", remainingTextElement.isDisplayed());
        sixthListItem = driver.findElement(By.xpath("//li[" + listItemsCount + "]"));
        spanElement = sixthListItem.findElement(By.tagName("span"));
        spanClassAttributeValue = spanElement.getAttribute("class");
        Assert.assertTrue("Шестой элемент списка не становится зачеркнутым", spanClassAttributeValue.contains("done-true"));




        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Закрываем браузер
        driver.quit();
    }
}
