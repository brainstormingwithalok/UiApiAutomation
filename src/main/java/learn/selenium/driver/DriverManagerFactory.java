package learn.selenium.driver;

import learn.selenium.utils.ConfigReader;
import learn.selenium.utils.Environment;

public class DriverManagerFactory {
    private OptionManager optionManager = new OptionManager();
    static DriverManager driverManager;

    public static DriverManager getDriverManager() {
        boolean isRemote = Boolean.parseBoolean(ConfigReader.INSTANCE.getProperty("remoteDriver"));
        if (driverManager != null) {
            return driverManager;
        } else {
            switch (Environment.getBrowser()) {
                case "chrome":
                    if (isRemote) {
                        driverManager = new RemoteDriverManager();
                    } else {
                        driverManager = new ChromeDriverManager();
                    }
                    break;
                case "firefox":
                    if (isRemote) {
                        driverManager = new RemoteDriverManager();
                    } else {
                        driverManager = new FirefoxDriverManager();
                    }
                    break;
                default:
                    if (isRemote) {
                        driverManager = new RemoteDriverManager();
                    } else {
                        driverManager = new SafariDriverManager();
                    }
                    break;

            }
        }
        return driverManager;
    }
}
