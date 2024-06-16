package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchProductsPage extends BasePage {

    private static final Logger logger = Logger.getLogger(task4.pages.StartPage.class);

    @FindBy(xpath = "//article[@itemtype='https://schema.org/Product']")
    private List<WebElement> products;

    @FindBy(xpath = "//button[contains(@class, 'ga-header__tab ga-header__tab_type_search')]")
    private WebElement search;

    @FindBy(xpath = "//input[@enterkeyhint='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @Step("Проверяем содержание поискового слова в товарах. Запоминаем первый товар по запросу.")
    public  SearchProductsPage checkProducts(String word) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean flag = true;
        int count = Math.min(products.size(), 2);
        for (int i = 0; i < count; i++) {
            WebElement product = products.get(i);
            scrollToElementJs(product);
            String productName = product.findElement(By.xpath(".//a/div[2]/div[2]")).getText();
            System.out.println(productName);
            Assert.assertTrue("Продукт номер " + (i+1) + " не содержит поисковое слово в кратком описании", productName.toLowerCase().contains(word.toLowerCase()));

            if(flag == true){
                String brandName = product.findElement(By.xpath(".//meta[@itemprop='name']")).getAttribute("content");
                String price = product.findElement(By.xpath(".//meta[@itemprop='price']")).getAttribute("content");


                WebElement brandElement = product.findElement(By.xpath(".//span[@itemprop='brand']/meta[@itemprop='name']"));
                WebElement parentDiv = brandElement.findElement(By.xpath("./../.."));
                String fullText = parentDiv.getText().trim();


                String productDescription = fullText.replace(brandName, "").trim();


                firstProduct = product;
                firstTitle = brandName;
                firstPrice = price;
                firstDescription = productDescription;
                logger.info("Название первого бренда: " + brandName + ", цена: " + price + ", описание: " + productDescription);
                flag = false;
            }
        }
        logger.info("Проверили содержание поискового слова в товарах");
        return this;
    }

    @Step("Открываем поиск для проверки цены товара")
    public DescriptionSearchPage checkProductPrice(){
        waitUntilElementToBeVisible(search);
        moveToElement(search);
        search.click();
        logger.info("Открыли поиск");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitUntilElementToBeVisible(searchInput).sendKeys(firstDescription);
        logger.info("Введен поисковый запрос: " + firstDescription);
        Assert.assertTrue("Поиск не открылся или не удалось выполнить поиск по описанию: " + firstDescription,
                waitUntilElementToBeVisible(searchInput).getAttribute("value").contains(firstDescription));

        searchButton.click();
        logger.info("Введен поисковый запрос: " + firstDescription);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getDescriptionSearchPage();
    }

    @Step("Вводим поисковый запрос '{query}' в поле поиска")
    public SearchProductsPage enterSearchQuery(String query) {
        waitUntilElementToBeVisible(searchInput).sendKeys(query);
        logger.info("Введен поисковый запрос: " + query);
        searchButton.click();
        logger.info("Введен поисковый запрос: " + query);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pageManager.getSearchProductsPage();
    }

}







