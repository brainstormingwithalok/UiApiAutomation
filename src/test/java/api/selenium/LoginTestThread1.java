package api.selenium;

import learn.selenium.driver.DriverManagerFactory;
import learn.selenium.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestThread1 {
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(null);
        PageFactory.initElements(loginPage.driver,loginPage);
    }

    @AfterMethod
    public void afterMethod() {
        DriverManagerFactory.getDriverManager().quitDriver();
    }

    @Test
    public void loginToFacebook() {
        loginPage.login();
    }

}
