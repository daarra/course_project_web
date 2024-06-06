package task1;

import managers.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;

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
        chromeDriverManager.getDriver().get("https://lambdatest.github.io/sample-todo-app/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
