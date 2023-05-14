package learn.selenium.utils;

public class Environment {
    public static String getBrowser() {
        String browser = System.getenv("browser");
        if (browser == null) {
            return ConfigReader.INSTANCE.getProperty("browser");
        } else {
            return browser;
        }

    }
}
