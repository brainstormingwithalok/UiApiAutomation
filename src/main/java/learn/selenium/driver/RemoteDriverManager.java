package learn.selenium.driver;

import learn.selenium.utils.ConfigReader;
import learn.selenium.utils.Environment;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManager extends DriverManager {
    OptionManager optionManager = new OptionManager();

    @Override
    protected void setupDriver() {
    //Not needed for the selenoid
    }
    @Override
    protected void createDriver() {
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
            capabilities.setCapability(ChromeOptions.CAPABILITY, optionManager.getChromeOption());
        }
        driver = new RemoteWebDriver(gridURL, capabilities);
    }
}
