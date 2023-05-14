package learn.selenium.driver;

import learn.selenium.utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class OptionManager {
    String downloadDir = System.getProperty("user.dir") + File.separator + "downloads";

    public ChromeOptions getChromeOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    public FirefoxOptions getFirefoxOptions() {
        String headless = ConfigReader.INSTANCE.getProperty("headless");
//        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        if (headless.equalsIgnoreCase("yes")) {
//            firefoxBinary.addCommandLineOptions("--headless");
//        }
//        firefoxOptions.setBinary(firefoxBinary);
        //Accept the untrusted certificate
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
        // Instructing firefox to use custom download location
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.dir", downloadDir);
        //Skipping save as dialog box
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/x-pdf");
        firefoxOptions.setProfile(firefoxProfile);
        return firefoxOptions;
    }
}
