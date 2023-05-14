package learn.selenium.pages;

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

    public void login() {
        email.sendKeys("abc@gmail.com");
        password.sendKeys("abcsaknf");
        login.click();
    }

}
