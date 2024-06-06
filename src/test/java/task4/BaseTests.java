package task4;

import managers.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTests {
    private final ChromeDriverManager edgeDriverManager = ChromeDriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }


    @Before
    public void before(){
        edgeDriverManager.getDriver().get("https://goldapple.ru/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }


}
