package task3;

import managers.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;
import org.openqa.selenium.Cookie;

import java.util.Date;

public class BaseTests {
    private final ChromeDriverManager chromeDriverManager = ChromeDriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @Before
    public void before(){
        chromeDriverManager.getDriver().get("https://market.yandex.ru/");
        chromeDriverManager.getDriver().manage().addCookie(new Cookie("spravka",
                "dD0xNzE3NjcxNzkwO2k9NjUuMjEuNTQuMTczO0Q9OUYyRjJEMzMxRjJFNzdCNkIxRjRCMTE3NzRFMkVFQkY1OUVGMjdENTIyRjIyRjgyNzlBQzJDRkFCMjIzOEMyMTc1MTE2RjJCMTVBMEE2M0Q2NTYzNjUyNDg5N0YzMzJFOTg0MDcyOTFDQ0I1NTlFMDI2Mjk1QzYyOTZFQzNFMkUwOUQ0ODlCNDAyODRDREMwMkM2QjIyMkM5ODQ4NTczNjQ2MjA1N0MyNkIyMTY3RjZGQ0M0MDZERjt1PTE3MTc2NzE3OTA4NzM4MjM3MzI7aD1jZTkyNzA5NDBlZmFhNGVkYjI2NDQ4ZmI5MDM1NDY0OQ==",
                ".yandex.ru", "/", new Date(2024, 7, 6, 11, 03, 10)));
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chromeDriverManager.getDriver().get("https://market.yandex.ru/");
    }



    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}