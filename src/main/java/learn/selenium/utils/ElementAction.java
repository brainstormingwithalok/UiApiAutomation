package learn.selenium.utils;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElementAction.class);
    public RemoteWebDriver driver;

    public ElementAction(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void launchURL() {
        String url = ConfigReader.INSTANCE.getProperty("url");
        this.driver.get(url);
    }
}
