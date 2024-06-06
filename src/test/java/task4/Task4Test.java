package task4;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class Task4Test extends BaseTests {

    @Test
    @DisplayName("Тестирование сайта интернет-магазина 'Золотое яблоко'")
    public void test(){
        pageManager.getStartPage_task4().verifyHomePageUrl().clickOnCategory();

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

