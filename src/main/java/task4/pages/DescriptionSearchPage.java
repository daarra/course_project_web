package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DescriptionSearchPage extends BasePage{
    private static final Logger logger = Logger.getLogger(task4.pages.StartPage.class);

    @FindBy(xpath = "//article[@itemtype='https://schema.org/Product']")
    private List<WebElement> products;

    @Step("Проверяем товары, которые вышли по запросу. Ищем соответствие с первым товаров")
    public DescriptionSearchPage checkPriceAndName() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        System.out.println(products.size());
        for (WebElement element : products) {
            String brandName = element.findElement(By.xpath(".//meta[@itemprop='name']")).getAttribute("content");
            String price = element.findElement(By.xpath(".//meta[@itemprop='price']")).getAttribute("content");

            WebElement brandElement = element.findElement(By.xpath(".//span[@itemprop='brand']/meta[@itemprop='name']"));
            WebElement parentDiv = brandElement.findElement(By.xpath("./../.."));
            String fullText = parentDiv.getText().trim();

            String productDescription = fullText.replace(brandName, "").trim();
            assertEquals("Описание продуктов не соответствует первому из каталога поиска", firstDescription, productDescription);
            assertEquals("Описание продуктов не соответствует первому из каталога поиска", firstPrice, price);
        }
        logger.info("Проверили содержание поискового слова в товарах");
        return this;
    }

}
