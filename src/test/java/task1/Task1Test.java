//package task1;
//
//import io.qameta.allure.junit4.DisplayName;
//import org.junit.Test;
//
//public class Task1Test extends BaseTests {
//
//    @Test
//    @DisplayName("Тестирование списка дел \"LambdaTest Sample App\"")
//    public void test(){
//        pageManager.getStartPage_task1().isPageTitleDisplayed()
//                .isRemainingTextDisplayed()
//                .checkTheListItemAndClick("li1")
//                .checkTheListItemAndClick("li2")
//                .checkTheListItemAndClick("li3")
//                .checkTheListItemAndClick("li4")
//                .checkTheListItemAndClick("li5")
//                .addItemToList("Новый элемент списка")
//                .checkTheListItemAndClick("li6");
//
//        try{
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
//
