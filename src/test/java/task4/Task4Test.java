package task4;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class Task4Test extends BaseTests{

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование поиска продуктов")
    public void test1() {
        pageManager.getStartPage_task4().verifyHomePageUrl().goToSearch().enterSearchQuery("Пудра").checkProducts("Пудра").checkProductPrice().checkPriceAndName();

        try {
            Thread.sleep(10000);
       } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование добавления товара в корзину")
    public void test2() {
        pageManager.getStartPage_task4().verifyHomePageUrl().moveToCatalog().findNameCategory("Волосы").sortByAscendingPrice().hoverAndClickFirstProduct().verifyAddToCartNotification();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование поиска брендов")
    public void test3() {
        pageManager.getStartPage_task4().verifyHomePageUrl().clickOnBrands().enterSearchQuery("Clarins").verifySearchResultsCount().checkNotification();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





