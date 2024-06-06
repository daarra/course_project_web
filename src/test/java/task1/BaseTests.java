package task1;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.EdgeDriverManager;
import managers.InitManager;
import managers.PageManager;
import managers.TestPropManager;

public class BaseTests {
    private final EdgeDriverManager edgeDriverManager = EdgeDriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }


    @Before
    public void before(){
        edgeDriverManager.getDriver().get("https://lambdatest.github.io/sample-todo-app/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
