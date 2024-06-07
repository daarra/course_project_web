package task3;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task3.BaseTests;

public class Task3Test extends BaseTests {

    @Test
    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
    public void test(){
        pageManager.getStartPage_task3().verifyHomePageUrl().clickOnCatalog().moveToCategory("Все для гейминга").clickOnMenuItem("Игровые приставки");

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
