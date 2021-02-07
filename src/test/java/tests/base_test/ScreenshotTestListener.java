package tests.base_test;

import org.openqa.selenium.NoSuchSessionException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ScreenshotTestListener implements ITestListener {
    private byte[] takeScreenshot() {
        try {
            return AllureUtilities.takeScreenshot();
        } catch (NoSuchSessionException | IllegalStateException | IOException ex) {
            return new byte[]{};
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
    }
}
