package selenium;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import learn.selenium.driver.DriverManagerFactory;
import learn.selenium.pages.LoginPage;
import learn.selenium.utils.Retry;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Epic("Sanity Test")
@Feature("LoginTest for thread 1")
public class LoginTestThread1 {
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(null);
        PageFactory.initElements(loginPage.driver, loginPage);
    }

    @AfterMethod
    public void afterMethod() {
        DriverManagerFactory.getDriverManager().quitDriver();
    }

    @Test
    public void loginToFacebook() {
        loginPage.login();
    }

    @Test
    public void loginFailed() {
        loginPage.loginFailed();
    }

    /*@Test(retryAnalyzer = Retry.class)
    public void loginFailedWithRetry() {
        loginPage.loginFailed();
    }*/

}
