package task2;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.DriverManager;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;
import utils.PropsConst;

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
        driverManager.getDriver().get("https://mospolytech.ru/ ");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
