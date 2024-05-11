package drivers;

import enums.Browsers;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AdminPage;
import pages.LoginPage;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class DriversManager {

    private static final ThreadLocal<WebDriver> WEBDRIVERTHREADLOCAL = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(DriversManager.class);
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    private static final String CUSTOM_WINDOW_SIZE = "--window-size=1050,600";
    private static final String HEADLESS = "--headless";


    public static void setDriver(WebDriver webDriverThreadLocal){
        DriversManager.WEBDRIVERTHREADLOCAL.set(webDriverThreadLocal);
    }

    public static WebDriver getDriver(){
        return DriversManager.WEBDRIVERTHREADLOCAL.get();
    }
    public static void createDriver(final @NotNull Browsers browser){
        FirefoxOptions options = new FirefoxOptions();

        switch (browser){
            case CHROME -> setupChromeDriver();
            case FIREFOX -> setupFirefoxDriver();
        }

    }
    public static void quitDriver(){
    WebDriver driver = DriversManager.getDriver();
    if(driver != null){
        LOGGER.info("Closing the driver ...");
        driver.quit();
        WEBDRIVERTHREADLOCAL.remove();
    }
    }

    private static void setupChromeDriver () {
        LOGGER.info ("Setting up Chrome Driver....");

        final var isHeadless = Boolean.parseBoolean(Objects.requireNonNullElse(System.getProperty("headless"),"true"));

        final var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put ("safebrowsing.enabled", "true");
        chromePrefs.put ("download.prompt_for_download", "false");
        chromePrefs.put ("download.default_directory",
                String.valueOf (Paths.get (System.getProperty ("user.home"), "Downloads")));

        final var options = new ChromeOptions();
        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (CUSTOM_WINDOW_SIZE);
        if (isHeadless) {
            options.addArguments (HEADLESS);
        }
        options.addArguments ("--safebrowsing-disable-download-protection");
        options.setExperimentalOption ("prefs", chromePrefs);

        setDriver (WebDriverManager.chromedriver ()
                .capabilities (options)
                .create ());
        LOGGER.info ("Chrome Driver created successfully!");
    }
    private static void setupFirefoxDriver () {
        LOGGER.info ("Setting up Firefox Driver....");
        final var options = new FirefoxOptions ();
        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (CUSTOM_WINDOW_SIZE);
        options.addArguments (HEADLESS);
        setDriver (WebDriverManager.firefoxdriver ()
                .capabilities (options)
                .create ());
        LOGGER.info ("Firefox Driver created successfully!");
    }


}
