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

    @FindBy(xpath = "//h1[@class='gEsmK']")
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
        System.out.println(title);
        Assert.assertEquals("Кнопка 'Добавить в избранное' не изменилась на 'Удалить из избранного'", "новинки", title);

        // Ожидание полной загрузки страницы (проверка всех ресурсов)

        logger.info("Открыли главную страницу сайта");
        return this;
    }

    @Step("Проверяем, что открыта главная страница")
    public NewProductsPage getCount() {
        String title = count.getText();
        System.out.println(title);
        Assert.assertEquals("6 088 продуктов", title, "Incorrect product count displayed");

        // Ожидание полной загрузки страницы (проверка всех ресурсов)

        logger.info("Открыли главную страницу сайта");
        return this;
    }


    @Step("Проверяем, что открыта главная страница и меняем вид каталога")
    public NewProductsPage changeView() {
        logger.info("Иконки поменяли свои классы местами");
        // Нажимаем на кнопку для изменения вида каталога
        buttonView.click();
        logger.info("Иконки поменяли свои классы местами");
        // Ожидание, чтобы изменения вступили в силу и элементы обновились
        waitUntilClassContains(icons.get(0), "A24uK");
        logger.info("Иконки поменяли свои классы местами");
        // Проверка, что иконки обновились
        if (icons.size() < 2) {
            Assert.fail("Количество иконок меньше двух после изменения вида");
        }
        logger.info("Иконки поменяли свои классы местами");
        // Проверяем, что классы поменялись местами
        Assert.assertEquals("A24uK", icons.get(0).getAttribute("class").split(" ")[3], "First icon class name is not correct");
        Assert.assertEquals("_3tEki", icons.get(1).getAttribute("class").split(" ")[3], "Second icon class name is not correct");

        // Логируем успешную проверку
        logger.info("Иконки поменяли свои классы местами");
        return this;
    }

    public void logFirstThreeProducts() {
        int count = Math.min(products.size(), 3); // Only get up to 3 products
        for (int i = 0; i < count; i++) {
            WebElement product = products.get(i);
            String productName = product.findElement(By.xpath(".//meta[@itemprop='name']")).getAttribute("content");
            String productPrice = product.findElement(By.xpath(".//meta[@itemprop='price']")).getAttribute("content");
            logger.info("Product Name: " + productName + ", Price: " + productPrice + "₽");
        }
    }

}







