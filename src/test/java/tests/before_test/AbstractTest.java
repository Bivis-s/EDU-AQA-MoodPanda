package tests.before_test;

import com.codeborne.selenide.Configuration;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotTestListener.class)
public abstract class AbstractTest {

    @BeforeMethod(alwaysRun = true)
    public void setup(ITestContext context) {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
    }
}
