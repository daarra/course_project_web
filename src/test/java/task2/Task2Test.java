//package task2;
//
//import io.qameta.allure.junit4.DisplayName;
//import org.junit.Test;
//
//public class Task2Test extends BaseTests {
//
//    @Test
//    @DisplayName("Тестирование страницы расписания на сайте Мосполитеха")
//    public void test(){
//        pageManager.getStartPage_task2()
//                .checkOpenLink()
//                .switchToSchedulePage()
//                .scheduleLink().checkOpenClassSchedulePage()
//                .inputGroup("221-361");
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
