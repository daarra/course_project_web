package task2.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import task2.pages.BasePage;
import java.util.List;

public class SchedulePage extends BasePage {

    private static final Logger logger = Logger.getLogger(task1.pages.StartPage.class);

    @FindBy(xpath = "//*[@id=\"bx_2015423015_2810\"]/div[2]/div/h1")
    private WebElement title;

    @FindBy(xpath = "//*[@id=\"bx_3777608605_2811\"]/div[3]/div/div[1]/a")
    private WebElement h1;

    @Step("Проверяем, что переходим на нужную нам страницу")
    public SchedulePage pagecheckOpenInsurancePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Расписания", title.getText());
        logger.info("Нужный заголовок присутствует на странице расписания");
        return pageManager.getStartSchedulePage();
    }

    @Step("Кликаем на ссылку 'Смотреть на сайте'")
    public GroupSchedulePage scheduleLink() {
        scrollToElementJs(h1);
        waitUntilElementToBeVisible(h1).click();
        moveToNewTab();
        logger.info("Кликнули на ссылку для просмотра расписания на сайте");
        return pageManager.getStartGroupSchedulePage().checkOpenClassSchedulePage();
    }

}
