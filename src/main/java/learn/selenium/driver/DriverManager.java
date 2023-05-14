package learn.selenium.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    //private static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
    protected RemoteWebDriver driver;

    protected abstract void setupDriver();

    protected abstract void createDriver();

    public RemoteWebDriver getDriver() {
        if (driver == null) {
            setupDriver();
            createDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
