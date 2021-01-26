package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static tests.MoodPandaValues.EMAIL;
import static tests.MoodPandaValues.PASSWORD;

public class MoodPandaTest extends AbstractTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage();
    }

    @Test
    public void loginTest() {
        loginPage
                .openPage()
                .login(EMAIL, PASSWORD);
    }
}
