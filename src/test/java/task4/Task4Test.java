package task4;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class Task4Test extends BaseTests {

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование каталога акций")
    public void test1() {
        pageManager.getStartPage_task4().verifyHomePageUrl().clickOnCategory().verifyPageTitle().logFirstThreeProducts();

        try {
            Thread.sleep(10000);
       } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование промо-акций")
    public void test2() {
        pageManager.getStartPage_task4().verifyHomePageUrl().clickOnPromo().selectCity("Нижний Новгород").goToFirstPromotion().getInfo();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'. Тестирование поиска брендов")
    public void test3() {
        pageManager.getStartPage_task4().verifyHomePageUrl().clickOnButtonLocation().clickOnBrands().enterSearchQuery("Clarins").verifySearchResultsCount().checkNotification();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





