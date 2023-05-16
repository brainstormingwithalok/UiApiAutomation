package learn.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverManager extends DriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChromeDriverManager.class);
    OptionManager optionManager = new OptionManager();

    @Override
    protected void setupDriver() {
        WebDriverManager.chromedriver().setup();
        LOGGER.info("Current chrome browser binary path->" + WebDriverManager.chromedriver().getDownloadedDriverPath());
        LOGGER.info("Current chrome browser binary version->" + WebDriverManager.chromedriver().getDownloadedDriverVersion());
    }

    @Override
    protected RemoteWebDriver createDriver() {
        return new ChromeDriver(optionManager.getChromeOption());
    }
}
