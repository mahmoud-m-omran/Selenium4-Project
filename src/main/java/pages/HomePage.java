package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static drivers.DriversManager.getDriver;
import java.time.Duration;

public class HomePage {
    //Elements
    private By litePackageText = By.id("name-lite");
    private By litePackagePriceText = By.cssSelector("#currency-lite b");
    private By litePackageCurrencyText = By.cssSelector("#currency-lite i");
    private By classicPackageText = By.id("name-classic");
    private By classicPackagePriceText = By.cssSelector("#currency-classic b");
    private By classicPackageCurrencyText = By.cssSelector("#currency-classic i");
    private By premiumPackageText = By.id("name-premium");
    private By premiumPackagePriceText = By.cssSelector("#currency-premium b");
    private By premiumPackageCurrencyText = By.cssSelector("#currency-premium i");
    private By countryBtn = By.cssSelector(".head-links .country-current");
    private By kuwaitCountryFlag = By.id("kw-contry-flag");
    private By bahrainCountryFlag = By.id("bh-contry-flag");
    long sec = 20;
    private WebDriver driver;
    public WebDriverWait wait;

    public HomePage() {
        this.driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    public void clickOnCountryBtn() {
        clickBtn(countryBtn);
    }

    public void selectKuwaitFlag() {
        clickBtn(kuwaitCountryFlag);
    }

    public void selectBahrainFlag() {
        clickBtn(bahrainCountryFlag);
    }


    public String getLitePackageText() {
        return getText(litePackageText);
    }


    public String getLitePackagePrice() {
        String currencyFullText = getText(litePackagePriceText);
        String[] currency = currencyFullText.split("/");
        return currency[0];
    }

    public String getCurrency() {
        String currencyFullText = getText(litePackageCurrencyText);
        String[] currency = currencyFullText.split("/");
        return currency[0];
    }

    public String getClassicPackageText() {
        return getText(classicPackageText);
    }

    public String getClassicPackagePrice() {
        return getText(classicPackagePriceText);
    }


    public String getPremiumPackageText() {
        return getText(premiumPackageText);
    }

    public String getPremiumPackagePrice() {
        return getText(premiumPackagePriceText);
    }
    private void clickBtn(By element){
        int i = 0;
        do {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                driver.findElement(element).click();
                return;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            i++;
        } while (i < 3);
    }

    private String getText(By element) {
        int i = 0;
        do {
            try {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
                return driver.findElement(element).getText();
            } catch (NullPointerException e) {
            }
            i++;
            return driver.findElement(element).getText();
        } while (i < 3);

    }
}
