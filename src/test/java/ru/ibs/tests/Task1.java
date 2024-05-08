package ru.ibs.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

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


        // Шаг 3. Проверить, что первый элемент списка не зачеркнут.
        WebElement firstListItem = driver.findElement(By.xpath("//li[1]"));
        String classAttributeValue = firstListItem.getAttribute("class");
        Assert.assertFalse("Первый элемент списка зачеркнут", classAttributeValue.contains("done-true"));

        // Шаг 4. Поставить галочку у первого элемента.
        WebElement firstItemCheckbox = driver.findElement(By.xpath("//li[1]//input[@type='checkbox']"));
        firstItemCheckbox.click();

        // Ожидаемый результат: Элемент списка становится зачеркнутым (применяется класс "done-true")
        // и отображаемое число оставшихся элементов уменьшается на 1
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '4 of 5')]"));
        Assert.assertTrue("Текст '4 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed());

        // Проверяем, что элемент списка стал зачеркнутым (применяется класс "done-true")
        firstListItem = driver.findElement(By.xpath("//li[1]"));
        classAttributeValue = firstListItem.getAttribute("class");
        Assert.assertFalse("Первый элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        // Шаг 5: Повторить шаги 3, 4 для остальных элементов
        // Второй элемент
        WebElement secondListItem = driver.findElement(By.xpath("//li[2]"));
        classAttributeValue = secondListItem.getAttribute("class");
        Assert.assertFalse("Второй элемент списка зачеркнут", classAttributeValue.contains("done-true"));
        WebElement secondItemCheckbox = driver.findElement(By.xpath("//li[2]//input[@type='checkbox']"));
        secondItemCheckbox.click();
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '3 of 5')]"));
        Assert.assertTrue("Текст '3 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed());
        secondListItem = driver.findElement(By.xpath("//li[2]"));
        classAttributeValue = secondListItem.getAttribute("class");
        Assert.assertFalse("Второй элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        // Третий элемент
        WebElement thirdListItem = driver.findElement(By.xpath("//li[3]"));
        classAttributeValue = thirdListItem.getAttribute("class");
        Assert.assertFalse("Третий элемент списка зачеркнут", classAttributeValue.contains("done-true"));
        WebElement thirdItemCheckbox = driver.findElement(By.xpath("//li[3]//input[@type='checkbox']"));
        thirdItemCheckbox.click();
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '2 of 5')]"));
        Assert.assertTrue("Текст '2 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed());
        thirdListItem = driver.findElement(By.xpath("//li[3]"));
        classAttributeValue = thirdListItem.getAttribute("class");
        Assert.assertFalse("Третий элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        // Четвертый элемент
        WebElement fourthListItem = driver.findElement(By.xpath("//li[4]"));
        classAttributeValue = fourthListItem.getAttribute("class");
        Assert.assertFalse("Четвертый элемент списка зачеркнут", classAttributeValue.contains("done-true"));
        WebElement fourthItemCheckbox = driver.findElement(By.xpath("//li[4]//input[@type='checkbox']"));
        fourthItemCheckbox.click();
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '1 of 5')]"));
        Assert.assertTrue("Текст '1 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed());
        fourthListItem = driver.findElement(By.xpath("//li[4]"));
        classAttributeValue = fourthListItem.getAttribute("class");
        Assert.assertFalse("Четвертый элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        // Пятый элемент
        WebElement fifthListItem = driver.findElement(By.xpath("//li[5]"));
        classAttributeValue = fifthListItem.getAttribute("class");
        Assert.assertFalse("Пятый элемент списка зачеркнут", classAttributeValue.contains("done-true"));
        WebElement fifthItemCheckbox = driver.findElement(By.xpath("//li[5]//input[@type='checkbox']"));
        fifthItemCheckbox.click();
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '0 of 5')]"));
        Assert.assertTrue("Текст '0 of 5 remaining' не найден на странице", remainingTextElement.isDisplayed());
        fifthListItem = driver.findElement(By.xpath("//li[5]"));
        classAttributeValue = fifthListItem.getAttribute("class");
        Assert.assertFalse("Пятый элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        // Шаг 6: Добавить новый элемент списка
        String newItemText = "Новый элемент списка";
        WebElement inputField = driver.findElement(By.xpath("//input[@placeholder='Want to add more']"));
        inputField.sendKeys(newItemText);
// Нахождение кнопки "Add" по значению атрибута "value" и нажатие на нее
        WebElement addButton = driver.findElement(By.xpath("//input[@value='add']"));
        addButton.click();

        WebElement sixthListItem = driver.findElement(By.xpath("//li[6]"));
        classAttributeValue = sixthListItem.getAttribute("class");
        Assert.assertFalse("Шестой элемент списка зачеркнут", classAttributeValue.contains("done-true"));
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '1 of 6')]"));
        Assert.assertTrue("Текст '1 of 6 remaining' не найден на странице", remainingTextElement.isDisplayed());
        WebElement sixthItemCheckbox = driver.findElement(By.xpath("//li[6]//input[@type='checkbox']"));
        sixthItemCheckbox.click();
        remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '0 of 6')]"));
        Assert.assertTrue("Текст '0 of 6 remaining' не найден на странице", remainingTextElement.isDisplayed());
        sixthListItem = driver.findElement(By.xpath("//li[6]"));
        classAttributeValue = sixthListItem.getAttribute("class");
        Assert.assertFalse("Шестой элемент списка не становится зачеркнутым", classAttributeValue.contains("done-false"));

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Закрываем браузер
        driver.quit();
    }
}
