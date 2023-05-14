package learn.selenium.base;

import learn.selenium.driver.DriverManagerFactory;
import learn.selenium.utils.ElementAction;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    public RemoteWebDriver driver;
    public ElementAction elementAction;

    public BasePage(RemoteWebDriver driver) {
        if (driver == null) {
            this.driver = DriverManagerFactory.getDriverManager().getDriver();
        } else {
            this.driver = driver;
        }
        elementAction = new ElementAction(this.driver);
        elementAction.launchURL();
    }
}
