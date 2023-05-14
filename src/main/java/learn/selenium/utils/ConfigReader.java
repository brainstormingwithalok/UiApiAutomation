package learn.selenium.utils;

import java.io.InputStream;
import java.util.Properties;

public enum ConfigReader {

    INSTANCE;
    private final Properties properties;

    ConfigReader() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String propertyFile = "config.properties";
        Properties properties = null;
        try {
            InputStream inputStream = classLoader.getResourceAsStream(propertyFile);
            if (inputStream == null) {
                throw new RuntimeException("The File " + propertyFile + " does not exists...");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception eta) {
            eta.printStackTrace();
        }
        this.properties = properties;
    }

    public String getProperty(String key) {
        String prop = this.properties.getProperty(key);
        return prop;
    }
}
