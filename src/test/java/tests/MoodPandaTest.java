package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FeedPage;
import pages.LoginPage;
import tests.before_test.AbstractTest;
import tests.before_test.RetryAnalyzer;

import static tests.MoodPandaValues.*;

public class MoodPandaTest extends AbstractTest {
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage();
    }

    @Test(description = "Login via valid data test")
    public void loginTest() {
        loginPage
                .openPage()
                .login(EMAIL, PASSWORD);
    }

    @Test(description = "Send post with mood test", dataProvider = "moodData",
            dataProviderClass = MoodPandaValues.class, retryAnalyzer = RetryAnalyzer.class)
    public void sendPostWithMoodTest(int moodValue) {
        FeedPage feedPage = loginPage
                .openPage()
                .login(EMAIL, PASSWORD)
                .clickUpdateMoodButton()
                .setMoodValue(moodValue)
                .clickSendMoodButton()
                .clickUpdatingMoodDoneButton();
        Assert.assertEquals(feedPage.getMoodRateFromLastUserPost(USERNAME), moodValue);
    }

    @Test(description = "Send post with description test", dataProvider = "descriptionData",
            dataProviderClass = MoodPandaValues.class, retryAnalyzer = RetryAnalyzer.class)
    public void sendPostWithDescriptionTest(String description) {
        FeedPage feedPage = loginPage
                .openPage()
                .login(EMAIL, PASSWORD)
                .clickUpdateMoodButton()
                .setDescription(description)
                .clickSendMoodButton()
                .clickUpdatingMoodDoneButton();
        Assert.assertEquals(feedPage.getDescriptionFromLastUserPost(USERNAME), description);
    }

    @Test(description = "Send post with description test", dataProvider = "dateData",
            dataProviderClass = MoodPandaValues.class, retryAnalyzer = RetryAnalyzer.class)
    public void sendPostWithDateTest(String date, String expectedDateTime) {
        FeedPage feedPage = loginPage
                .openPage()
                .login(EMAIL, PASSWORD)
                .clickUpdateMoodButton()
                .setDateByLabel(date)
                .clickSendMoodButton()
                .clickUpdatingMoodDoneButton();
        Assert.assertEquals(feedPage.getDateFromLastUserPost(USERNAME), expectedDateTime);
    }

    @Test(description = "Comment after clicking hug-button test", retryAnalyzer = RetryAnalyzer.class)
    public void commentAfterClickingHugButtonTest() {
        FeedPage feedPage = loginPage
                .openPage()
                .login(EMAIL, PASSWORD)
                .clickUpdateMoodButton()
                .clickSendMoodButton()
                .clickUpdatingMoodDoneButton()
                .clickHugButtonUnderUsersPost(USERNAME);
        Assert.assertTrue(feedPage.isNewCommentUnderPostVisible(USERNAME));
    }
}
