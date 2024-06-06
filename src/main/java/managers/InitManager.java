package managers;

import java.util.concurrent.TimeUnit;

import static utils.PropsConst.*;

public class InitManager {
    private static final TestPropManager props = TestPropManager.getInstance();
    private static final ChromeDriverManager CHROME_DRIVER_MANAGER = ChromeDriverManager.getInstance();

    public static void initFramework() {
        CHROME_DRIVER_MANAGER.getDriver().manage().window().maximize();
        CHROME_DRIVER_MANAGER.getDriver().manage().timeouts()
                .pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        CHROME_DRIVER_MANAGER.quitDriver();
    }
}
