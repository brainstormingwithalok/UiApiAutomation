package api.selenium;

import learn.selenium.driver.DriverFactorySingle;
import learn.selenium.pages.LoginPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestWithSingleFactory {
    LoginPage loginPage;
    DriverFactorySingle factorySingle;

    @BeforeClass
    public void setup() {
        factorySingle = new DriverFactorySingle();
        factorySingle.instantiateDriver();
    }

    @BeforeMethod
    public void beforeMethod() {

        RemoteWebDriver driver = factorySingle.getDriver();
        loginPage = new LoginPage(driver);
        PageFactory.initElements(loginPage.driver, loginPage);
    }

    @AfterMethod
    public void afterMethod() {
        factorySingle.quitDriver();
    }

    @Test
    public void loginToFacebook() {
        loginPage.login();
    }
}
