package learn.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactoryNotInUse {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactoryNotInUse.class);
    private static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();
    private boolean isRemoteDriver = false;

    private RemoteWebDriver createDriverInstance() {
        instantiateDriver();
        return getDriver();
    }

    public synchronized void instantiateDriver() {
        String browser = "Chrome";
        LOGGER.info("Browser used for the automation->" + browser);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (isRemoteDriver) {

        } else {
            switch (browser) {
                case "firefox": {
                    driverThreadLocal.set(new FirefoxDriver());
                }
                case "chrome": {
                    driverThreadLocal.set(new ChromeDriver());
                }
                default:
                    driverThreadLocal.set(new InternetExplorerDriver());
            }
        }
    }

    public RemoteWebDriver setWebDriverManager() {
        String browser = "chrome";
        switch (browser) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                LOGGER.info("Current firefox browser binary path->" + WebDriverManager.firefoxdriver().getDownloadedDriverPath());
                LOGGER.info("Current firefox browser binary version->" + WebDriverManager.firefoxdriver().getDownloadedDriverVersion());
                return createDriverInstance();
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                LOGGER.info("Current chrome browser binary path->" + WebDriverManager.chromedriver().getDownloadedDriverPath());
                LOGGER.info("Current chrome browser binary version->" + WebDriverManager.chromedriver().getDownloadedDriverVersion());
                return createDriverInstance();
            }
            default:
                WebDriverManager.iedriver().setup();
                LOGGER.info("Current ie driver browser binary path->" + WebDriverManager.iedriver().getDownloadedDriverPath());
                LOGGER.info("Current ie driver browser binary version->" + WebDriverManager.iedriver().getDownloadedDriverVersion());
                return createDriverInstance();
        }
    }

    public static synchronized RemoteWebDriver getDriver() {
        LOGGER.info("Thread id = " + Thread.currentThread().getId());
        return driverThreadLocal.get();
    }

    public static synchronized void terminateThreadDriver() {
        LOGGER.info("Thread id = " + Thread.currentThread().getId());
        driverThreadLocal.remove();
    }

    public static synchronized void quitDriver() {
        if (!(driverThreadLocal.get() == null)) {
            driverThreadLocal.get().quit();
        }
    }
}
