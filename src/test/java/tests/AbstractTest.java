package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
public abstract class AbstractTest {

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 15000;
//        Configuration.holdBrowserOpen = true;
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-popup-blocking", "--incognito");
    }
}
