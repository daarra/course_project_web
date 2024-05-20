package task3;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.DriverManager;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;
import org.openqa.selenium.Cookie;
import utils.PropsConst;

import java.util.Date;

public class BaseTests {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }

    @Before
    public void before(){
        driverManager.getDriver().get("https://market.yandex.ru/");
        driverManager.getDriver().manage().addCookie(new Cookie("spravka",
                "dD0xNzE2MjA4MDE4O2k9NjUuMjEuNTQuMTczO0Q9NjM1ODc2QzJEQzlDQTExNkM4NDc2RjIxRUU2MUFBOEQ5MzExNTU2QjRFMzhFNjU4NzkyMTFGRUU2ODZFRkVGQTk4M0Q5NjE0MzBGQzU2OUVGNUU3NkQ0QzQ4QThDNjMwRDkxMDVGRkMzRDg2MjdEMUIxQThDMjQ1MURFMkZBMjM4RjRBRDBGMUUxRDYxRkU3QTExN0UxN0QyN0FBM0E0NTIwNTM4RDk1NzMyNURFMDYwNDt1PTE3MTYyMDgwMTgzNTk3ODU2MTU7aD1kM2Y2YTY4NWFmZjExOTU4NGUzOTFjZmFlMzUxNWUzNg==",
                ".yandex.ru", "/", new Date(2024, 6, 19, 12, 26, 58)));
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverManager.getDriver().get("https://market.yandex.ru/");
    }



    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
