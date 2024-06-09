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
        chromeDriverManager.getDriver().get("https://market.yandex.ru/catalog--igrovye-pristavki-xbox/41813471/list?hid=91122&glfilter=12782797%3A17888497%2C15163455%2C15163454%2C15163453%2C15163452%2C16092905");
//        chromeDriverManager.getDriver().manage().addCookie(new Cookie("spravka",
//                "dD0xNzE3OTQ2NTU1O2k9MTc4LjcyLjY5Ljc1O0Q9REZGRDVFM0I1QkNCRjA5NjdFREZDN0JBOTFFNDMwMDE0MkE2ODQ3MkMyRDQyQzE4QzVDMTU5REY3NUEzNzcyNjREMTI3NjQzQUM4REFBRTlBRDJBRDk2M0RCRTI4OEFBMjMyRERBMTE4ODk5QjU5OENEREQwMUE0OEZBRjcyNUQwNDE5NDkxODNDNzc5MzE2Q0ExRURBRDJBNTVGMzAxNjU3OTM3NENCODM7dT0xNzE3OTQ2NTU1ODk2MDcxNTQ2O2g9MzI0NTBjNjdhMjNiY2IwNzIzOWJiMDk1YTdiZDUyM2U=",
//                ".yandex.ru", "/", new Date(2024, 7, 9, 15, 22, 35)));
//        try{
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        chromeDriverManager.getDriver().get("https://market.yandex.ru/catalog--igrovye-pristavki-xbox/41813471/list?hid=91122&glfilter=12782797%3A17888497%2C15163455%2C15163454%2C15163453%2C15163452%2C16092905");
    }



    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}