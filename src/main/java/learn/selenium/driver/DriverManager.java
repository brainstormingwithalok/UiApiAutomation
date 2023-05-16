package learn.selenium.driver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
//    protected RemoteWebDriver driver;

    protected abstract void setupDriver();

    protected abstract RemoteWebDriver createDriver();

    public RemoteWebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            setupDriver();
            driverThreadLocal.set(createDriver());
        }
        System.out.println("Thread id=" + Thread.currentThread().getId());
        return driverThreadLocal.get();
    }

    public void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        driverThreadLocal.remove();
    }

}
