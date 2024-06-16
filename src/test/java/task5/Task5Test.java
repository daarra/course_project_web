//package task5;
//
//import io.qameta.allure.junit4.DisplayName;
//import org.junit.Test;
//
//public class Task5Test extends BaseTests {
//
//    @Test
//    @DisplayName("Проверяем WEB на главной странице, проверяем, что при нажатии на кнопку отправки образца запроса результат такой же, как и через API.")
//    public void test(){
//        pageManager.getStartPage_task5()
//                .checkOpenPage()
//                .clickOnButtonAndCheckAPI("List users", "get")
//                .clickOnButtonAndCheckAPI("Single user", "get")
//                .clickOnButtonAndCheckAPI("Single user not found", "get")
//                .clickOnButtonAndCheckAPI("List <resource>", "get")
//                .clickOnButtonAndCheckAPI("Single <resource>", "get")
//                .clickOnButtonAndCheckAPI("Single <resource> not found", "get")
//                .clickOnButtonAndCheckAPI("Create", "post")
//                .clickOnButtonAndCheckAPI("Update", "patch")
//                .clickOnButtonAndCheckAPI("Delete", "delete")
//                .clickOnButtonAndCheckAPI("Register - successful", "post")
//                .clickOnButtonAndCheckAPI("Register - unsuccessful", "post")
//                .clickOnButtonAndCheckAPI("Login - successful", "post")
//                .clickOnButtonAndCheckAPI("Login - unsuccessful", "post")
//                .clickOnButtonAndCheckAPI("Delayed response", "get");
//        try{
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
