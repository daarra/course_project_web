package task4.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstPromoPage extends BasePage{
    private static final Logger logger = Logger.getLogger(task4.pages.StartPage.class);
    @FindBy(xpath = "//div[@class='R8dmm Ga1bx +M1JB']")
    private WebElement info;

    public FirstPromoPage getInfo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String title = info.findElement(By.xpath(".//h1")).getText();
            String price = info.findElement(By.xpath(".//div[@class='AQ2m4']")).getText();
            logger.info("Название '" + title + "' описание '" + price+ "'.");
        } catch (NoSuchElementException e) {
            logger.error("Элемент с классом 'R8dmm Ga1bx +M1JB' не найден на странице.");
        }
        return this;
    }

}
