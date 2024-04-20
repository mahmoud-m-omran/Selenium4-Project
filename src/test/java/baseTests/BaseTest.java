package baseTests;

import enums.Browsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import utils.dataReaders.CsvFileReader;
import utils.dataReaders.UserData;

import java.util.List;

import static drivers.DriversManager.createDriver;
import static drivers.DriversManager.quitDriver;
public class BaseTest {
    protected HomePage homePage;

    protected String email = "store@admin.com";
    protected String password = "P@ssw0rd";
    protected String expectedWelcomeMessage = "Welcome To Dashboard";
    protected String expectedLoginPageTitle = "Admin | Login";
    protected LoginPage loginPage;
    protected AdminPage adminPage;

//    protected WebDriver driver;
//@BeforeTest
//    @Parameters({"browser"})
//    public void setup(@Optional("chrome") String browser) {
//        if ("firefox".equalsIgnoreCase(browser)) {
////            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        } else if ("edge".equalsIgnoreCase(browser)) {
////            WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
//        } else {
////            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        }
//        homePage = new HomePage(driver);
//        loginPage = new LoginPage(driver);
//        adminPage=new AdminPage(driver);
//        driver.manage().window().maximize();
//        driver.get("https://oyn-adminportal-qc-demo.salmonsky-1edff179.westeurope.azurecontainerapps.io/identity/login");
//    }

    @Parameters({"browser"})
    @BeforeClass
    public void setup(final String browser){
        createDriver(Browsers.valueOf(browser.toUpperCase()));
        homePage = new HomePage();
        loginPage = new LoginPage();
        adminPage=new AdminPage();
    }


@AfterTest
    public void tearDown() {
        quitDriver();
    }

    @DataProvider(name = "loginRestData")
    public Object[][] getLoginTestData() {
        List<UserData> usersData = CsvFileReader.getUserData();
        Object[][] testData = new Object[usersData.size()][3];

        for (int i = 0; i < usersData.size(); i++) {
            UserData userData = usersData.get(i);
            testData[i][0] = userData.getUserName();
            testData[i][1] = userData.getPassword();
            testData[i][2] = userData.getCol3();

        }

        return testData;
    }
}
