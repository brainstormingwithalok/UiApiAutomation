package learn.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import learn.selenium.utils.ConfigReader;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FirefoxDriverManager extends DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirefoxDriverManager.class);

    @Override
    protected void setupDriver() {
        WebDriverManager.firefoxdriver().setup();
        LOGGER.info("Current firefox browser binary path->" + WebDriverManager.firefoxdriver().getDownloadedDriverPath());
        LOGGER.info("Current firefox browser binary version->" + WebDriverManager.firefoxdriver().getDownloadedDriverVersion());
    }

    @Override
    protected RemoteWebDriver createDriver() {
        return new FirefoxDriver();
    }
}
