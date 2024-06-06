package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.PropsConst;

import java.util.concurrent.TimeUnit;

public class ChromeDriverManager {

    private WebDriver driver;
    private TestPropManager propManager = TestPropManager.getInstance();

    private static ChromeDriverManager INSTANCE = null;

    private ChromeDriverManager() {
    }

    public static ChromeDriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChromeDriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConst.PATH_CHROME_DRIVER_WINDOWS));
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}