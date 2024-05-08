package ru.ibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Page {
    private WebDriver driver;

    @FindBy(xpath = "//h2[contains(text(), 'LambdaTest Sample App')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[contains(text(), 'remaining')]")
    private WebElement remainingTextElement;

    @FindBy(xpath = "//li")
    private List<WebElement> listItems;

    @FindBy(xpath = "//input[@placeholder='Want to add more']")
    private WebElement inputField;

    @FindBy(xpath = "//input[@value='add']")
    private WebElement addButton;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App");
    }

    public boolean isRemainingTextDisplayed(String expectedText) {
        return remainingTextElement.isDisplayed() && remainingTextElement.getText().contains(expectedText);
    }

    public int getListItemsCount() {
        return listItems.size();
    }

    public void toggleListItem(int index) {
        WebElement checkbox = listItems.get(index - 1).findElement(By.xpath(".//input[@type='checkbox']"));
        checkbox.click();
    }

    public String getListItemClass(int index) {
        WebElement spanElement = listItems.get(index - 1).findElement(By.tagName("span"));
        return spanElement.getAttribute("class");
    }

    public void addNewItem(String itemText) {
        inputField.sendKeys(itemText);
        addButton.click();
    }
}
