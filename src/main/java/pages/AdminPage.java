package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static drivers.DriversManager.getDriver;
import java.time.Duration;

public class AdminPage {
//    Variables
    private WebDriver driver;
    private WebDriverWait wait;

    public AdminPage(){
        this.driver=getDriver();
        wait= new WebDriverWait(driver, Duration.ofSeconds(5));
    }
// Locators
private By headerWelcomeMessageSelector= By.cssSelector(".content p");
    private By imageHumanIconSelector= By.cssSelector(".username .user");
    private By logoutSelector= By.xpath("//li[text()='Logout']");

    //Methods
    public String getWelcomeHeaderText(){
        WebElement element;
        int i=0;
        do {
             wait.until(ExpectedConditions.presenceOfElementLocated(headerWelcomeMessageSelector));
            element= driver.findElement(headerWelcomeMessageSelector);
            i++;
        }while(element==null&&i<=10);
        if (element == null){
            System.out.println("There is a problem with login due to long loading after clicking on the login button and before redirection to the admin page");
        }
        return element.getText();
    }
    public String getTitleOfAdminPage(){
        return driver.getTitle();
    }
    public AdminPage clickOnHumanImageOnTheTopRightOfTheScreen(){
        var element= driver.findElement(imageHumanIconSelector);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        return new AdminPage();
    }
    public LoginPage clickOnLogOutSelector(){
        var element= driver.findElement(logoutSelector);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        return new LoginPage();
    }

}
