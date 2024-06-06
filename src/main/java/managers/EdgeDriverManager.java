package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.PropsConst;

import java.util.concurrent.TimeUnit;

public class EdgeDriverManager {

    private static EdgeDriverManager INSTANCE = null;
    private WebDriver driver;
    private final TestPropManager propManager = TestPropManager.getInstance();
    private EdgeDriverManager(){

    }

    public static EdgeDriverManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new EdgeDriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver(){
        if (driver == null){
            initDriver();
        }
        return driver;
    }


    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        System.setProperty("webdriver.сhrome.driver", propManager.getProperty(PropsConst.PATH_EGD_DRIVER_WINDOWS));
        driver = new EdgeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
