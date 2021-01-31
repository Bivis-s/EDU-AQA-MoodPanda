package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.before_test.AbstractTest;

import static tests.MoodPandaValues.EMAIL;
import static tests.MoodPandaValues.PASSWORD;

public class MoodPandaTest extends AbstractTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage();
    }

    @Test(description = "Login via valid data test")
    public void loginTest() {
        loginPage
                .openPage()
                .login(EMAIL, PASSWORD);
    }
}
