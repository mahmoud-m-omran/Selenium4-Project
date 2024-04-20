package drivers;

import enums.Browsers;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AdminPage;
import pages.LoginPage;

public class DriversManager {

    private static final ThreadLocal<WebDriver> WEBDRIVERTHREADLOCAL = new ThreadLocal<>();
    protected LoginPage loginPage;
    protected AdminPage adminPage;

    public static void setDriver(WebDriver webDriverThreadLocal){
        DriversManager.WEBDRIVERTHREADLOCAL.set(webDriverThreadLocal);
    }

    public static WebDriver getDriver(){
        return DriversManager.WEBDRIVERTHREADLOCAL.get();
    }
    public static void createDriver(final @NotNull Browsers browser){
        switch (browser){
            case CHROME -> DriversManager.setDriver(new ChromeDriver());
            case FIREFOX -> DriversManager.setDriver(new FirefoxDriver());
        }

    }
    public static void quitDriver(){
    WebDriver driver = DriversManager.getDriver();
    if(driver != null){
        driver.quit();
        WEBDRIVERTHREADLOCAL.remove();
    }
    }


}
