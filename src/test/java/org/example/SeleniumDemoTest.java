package org.example;

import baseTests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static drivers.DriversManager.getDriver;

public class SeleniumDemoTest extends BaseTest {

    @Test
    public void testGoogle(){
        getDriver().get("https://www.google.com/");
        System.out.println(getDriver().getTitle());
    }
    @Test(dataProvider = "loginRestData")
    public void testReadingFromCsvFile(String userName, String password,String col3){
        getDriver().get("https://poe.com/chat/27uhf6sodqf3e4tuszp");
        System.out.println(userName+", "+ password+", "+col3);
    }


}
// class ParallelExecutionTrial extends BaseTest{
//    @Test
//    public void testLoginLogoutFullScenario(){
//        String actualWelcomeMessage = loginPage.enterEmail(email)
//                .enterPassword(password)
//                .clickLoginBtn()
//                .getWelcomeHeaderText();
///*  Verify that the login is successful by checking if the user is redirected to the home page
//    or if a success message is displayed.*/
//        Assert.assertEquals(actualWelcomeMessage,expectedWelcomeMessage,
//                String.format("Expected actual message to be %s, but found %s",
//                        actualWelcomeMessage,expectedWelcomeMessage));
//
////  Print the title of the home page.
//        String titleOfAdminPage = adminPage.getTitleOfAdminPage();
//        System.out.println(titleOfAdminPage);
//
////    Verify that the user is successfully logged out by checking if the login page is displayed again
//        String actualLoginPageTitle = adminPage.clickOnHumanImageOnTheTopRightOfTheScreen()
//                .clickOnLogOutSelector()
//                .getLoginPageTitle();
//        Assert.assertEquals(actualLoginPageTitle,expectedLoginPageTitle,
//                String.format("Expected actual message to be %s, but found %s",
//                        actualLoginPageTitle,expectedLoginPageTitle));
//
//    }
//        }