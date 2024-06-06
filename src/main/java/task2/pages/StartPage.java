package task2.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import task2.pages.BasePage;
import java.util.List;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//button[@class='hamburger']")
    private WebElement menuButtom;

    @FindBy(xpath = "//div[@class='main-nav']")
    private WebElement menu;

    @FindBy(xpath = "/html/body/header/nav/div[2]/div/ul[1]/li[2]/a")
    private WebElement obuchauschimsyaLink;

    @FindBy(xpath = "//a[@href='/obuchauschimsya/raspisaniya/' and @class='main-nav__item-link' and @title='Расписания']")
    private WebElement scheduleLink;

    @FindBy(xpath = "/html/body/main/h1")
    private WebElement title;

    @Step("Проверяем, что мы перешли по нужной ссылке")
    public StartPage checkOpenLink(){
        waitUntilElementToBeVisible(title);
        String actualTitleText = title.getText();
        String expectedTitleText = "Московский Политех";
        Assert.assertTrue("Заголовок 'Московский Политех' не найден", title.isDisplayed() &&
                actualTitleText.contains(expectedTitleText));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getStartPage_task2();
    };

    @Step("Переходим на страницу 'Расписание'")
    public SchedulePage switchToSchedulePage() {
        logger.info("Делаем переход на страницу с Расписанием");
        pageManager.getStartPage_task2().menuHamburgerClick();
        pageManager.getStartPage_task2().hoverOnObuchauschimsyaLink();
        pageManager.getStartPage_task2().clickOnScheduleLink();
        return pageManager.getStartSchedulePage().pagecheckOpenInsurancePage();
    }

    @Step("Нажимаем на гамбургер (меню)")
    public StartPage menuHamburgerClick() {
        menuButtom.click();
        Assert.assertTrue("Меню не открылось", menu.isDisplayed());
        logger.info("Открыли меню");
        return pageManager.getStartPage_task2();
    }

    @Step("Наводим курсор на текст 'Обучающимся'")
    public StartPage hoverOnObuchauschimsyaLink() {
        waitUntilElementToBeVisible(obuchauschimsyaLink);
        action.moveToElement(obuchauschimsyaLink).perform();
        boolean beforeElementPresent = isBeforeElementPresent(scheduleLink);
        Assert.assertTrue("Псевдоэлемент ::before не применен к 'Расписание'", beforeElementPresent);
        logger.info("Навели курсор на текст 'Обучающимся'");
        return pageManager.getStartPage_task2();
    }


    @Step("Нажимаем на кнопку 'Расписание'")
    public SchedulePage clickOnScheduleLink() {
        waitUntilElementToBeClickable(scheduleLink).click();
        logger.info("Нажали на ссылку 'Расписание'");
        return pageManager.getStartSchedulePage();
    }


}
