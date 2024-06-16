//package task3;
//
//import io.qameta.allure.junit4.DisplayName;
//import org.junit.Test;
//import task3.BaseTests;
//
//public class Task3Test extends BaseTests {
//
//    @Test
//    @DisplayName("Тестирование 'Яндекс-маркета'")
//    public void test() {
//        pageManager.getStartPage_task3()
//                .verifyHomePageUrl()
//                .clickOnCatalog()
//                .clickOnMenuItem("Все для гейминга", "Xbox", "Игровые приставки")
//                .logProducts()
//                .clickOnWishListButton()
//                .clickOnFavoriteButton()
//                .removeFromWishListButton()
//                .updatePage();
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
