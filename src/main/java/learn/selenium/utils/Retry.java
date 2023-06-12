package learn.selenium.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetry = 3;

    @Override
    public boolean retry(ITestResult result) {
        while (retryCount < maxRetry) {
            retryCount++;
            return true;
        }
        return false;
    }
}
