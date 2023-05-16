package learn.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import learn.selenium.utils.ConfigReader;
import learn.selenium.utils.Environment;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactorySingle {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactorySingle.class);
    RemoteWebDriver driver;

    public void instantiateDriver() {
        String browser = Environment.getBrowser();
        LOGGER.info("Browser used for the automation->" + browser);
        switch (browser) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                LOGGER.info("Current firefox browser binary path->" + WebDriverManager.firefoxdriver().getDownloadedDriverPath());
                LOGGER.info("Current firefox browser binary version->" + WebDriverManager.firefoxdriver().getDownloadedDriverVersion());
                driver = new FirefoxDriver();
                break;
            }
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                LOGGER.info("Current chrome browser binary path->" + WebDriverManager.chromedriver().getDownloadedDriverPath());
                LOGGER.info("Current chrome browser binary version->" + WebDriverManager.chromedriver().getDownloadedDriverVersion());
                driver = new ChromeDriver();
                break;
            }
            default: {
                String url = ConfigReader.INSTANCE.getProperty("gridURL");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                URL gridURL = null;
                try {
                    gridURL = new URL(url);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                capabilities.setBrowserName(Environment.getBrowser());
                capabilities.setCapability("enableVNC", true);
                if (Environment.getBrowser().equalsIgnoreCase("chrome")) {
                    capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOption());
                }
                driver = new RemoteWebDriver(gridURL, capabilities);
                break;
            }
        }
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (!(driver == null)) {
            driver.quit();
        }
    }

    public ChromeOptions getChromeOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

}
