package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChromeTest {
    RemoteWebDriver driver;

    @BeforeClass
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    void setupTest() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        // Exercise
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String title = driver.getTitle();

        // Verify
        Assert.assertTrue(title.contains("Selenium WebDriver"));
    }

    @Test
    public void testImageComparison() throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File("/Users/alokmishra/Documents/learning/code/UiApiAutomation/src/test/resources/Image1.png"));
        BufferedImage actualImage = ImageIO.read(new File("/Users/alokmishra/Documents/learning/code/UiApiAutomation/src/test/resources/Image3.png"));
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        if (diff.hasDiff() == true) {
            System.out.println("Images are same");
        } else {
            System.out.println("Images are different");
        }
    }
}
