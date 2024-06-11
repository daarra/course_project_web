package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewProductsPage extends BasePage {

    private static final Logger logger = Logger.getLogger(task4.pages.StartPage.class);

    @FindBy(xpath = "//span[@class='FdRS+']")
    private WebElement count;

    @FindBy(xpath = "//h1[@class='MaS1o']")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@class='qxoib b0Gsz']")
    private WebElement buttonView;

    @FindBy(css = ".se+LQ.CfHgU.rkJyu")
    private List<WebElement> icons;

    @FindBy(xpath = "//article[@itemtype='https://schema.org/Product']")
    private List<WebElement> products;

    @Step("Проверяем, что открыта главная страница")
    public NewProductsPage verifyPageTitle() {
        String title = pageTitle.getText();
        Assert.assertEquals("Кнопка 'Добавить в избранное' не изменилась на 'Удалить из избранного'", "новинки", title);
        logger.info("Открыли главную страницу сайта");
        return this;
    }

    @Step("Проверяем, сколько продуктов у нас в категории новинок")
    public NewProductsPage getCount() {
        String title = count.getText();
        Assert.assertEquals("6 088 продуктов", title, "Incorrect product count displayed");
        logger.info("На сайте представлено " + title);
        return this;
    }

    @Step("Добавляем в лог первые 3 позиции: название бренда и его стоимость")
    public void logFirstThreeProducts() {
        int count = Math.min(products.size(), 3); // Only get up to 3 products
        for (int i = 0; i < count; i++) {
            WebElement product = products.get(i);
            String productName = product.findElement(By.xpath(".//meta[@itemprop='name']")).getAttribute("content");
            String productPrice = product.findElement(By.xpath(".//meta[@itemprop='price']")).getAttribute("content");
            logger.info("Название бренда: " + productName + ", стоимость: " + productPrice + "₽");
        }
    }

}







