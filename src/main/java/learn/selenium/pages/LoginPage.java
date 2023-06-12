package learn.selenium.pages;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import learn.selenium.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    WebElement email;
    @FindBy(id = "pass")
    WebElement password;

    @FindBy(name = "login")
    WebElement login;

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Login with the email and password...")
    public void login() {
        email.sendKeys("abc@gmail.com");
        password.sendKeys("abcsaknf");
        login.click();
    }

    @Step("Login with the invalid email...")
    public void loginFailed() {
        email.sendKeys("abc@gmail.com");
        password.sendKeys("abcsaknf");
        login.click();
        Assert.isTrue(1==2,"Not equal...");
    }

}
