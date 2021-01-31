package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class FeedPage extends AbstractPage {
    protected static final String FEED_URL = URL + "Feed/";
    private final String UPDATE_MOOD_BUTTON_XPATH = "//*[@id='LinkUpdate']";
    private final String POST_XPATH = "//*[contains(@class,'name ')]//*[contains(text(),'%s')]//ancestor::*[contains(@class,'rounded-top-corner')]";
    private final String POST_MOOD_VALUE_CONTAINER_XPATH = POST_XPATH + "//*[contains(@class,'pull-right')]";
    private final String POST_DESCRIPTION_CONTAINER_XPATH = POST_XPATH + "//*[contains(@class,'MoodPostItem')]";
    private final String POST_DATE_CONTAINER_XPATH = POST_XPATH + "//*[contains(@class,'text-muteda')]";
    private final String POST_HUG_BUTTON_XPATH = POST_XPATH + "//*[contains(@class,'ButtonHug')]";
    private final String COMMENT_CONTAINER_XPATH = POST_XPATH + "//following::*[contains(@class,'media-comment')]";
    private final String NEW_COMMENT_XPATH =
            COMMENT_CONTAINER_XPATH + "//*[contains(@class,'text-muteda') and contains(text(),'Just Now')]";

    @Step("Open feed page")
    public FeedPage openPage() {
        open(FEED_URL);
        return this.waitForPageOpened();
    }

    /**
     * Uses for wait for MoodPanda server will process sent post
     *
     * @return this page
     */
    public FeedPage waitForPostSendAndRefresh() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refresh();
        return this;
    }

    public FeedPage waitForPageOpened() {
        $x(UPDATE_MOOD_BUTTON_XPATH).shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Click update mood button")
    @Description("Open update mood modal")
    public UpdateMoodModal clickUpdateMoodButton() {
        $x(UPDATE_MOOD_BUTTON_XPATH).click();
        return new UpdateMoodModal().waitForModalOpened();
    }

    @Step("Get user's '{username}' post mood rate")
    public int getMoodRateFromLastUserPost(String username) {
        return Integer.parseInt($x(String.format(POST_MOOD_VALUE_CONTAINER_XPATH, username)).getText());
    }

    @Step("Get user's '{username}' post description")
    public String getDescriptionFromLastUserPost(String username) {
        return $x(String.format(POST_DESCRIPTION_CONTAINER_XPATH, username)).getText();
    }

    @Step("Get user's '{username}' post date time")
    public String getDateFromLastUserPost(String username) {
        return $x(String.format(POST_DATE_CONTAINER_XPATH, username)).getText();
    }

    @Step("Click hug button under user's '{username}' post")
    public FeedPage clickHugButtonUnderUsersPost(String username) {
        $x(String.format(POST_HUG_BUTTON_XPATH, username)).click();
        return this;
    }

    @Step("Is the new comment under user's '{username}' post visible")
    public boolean isNewCommentUnderPostVisible(String username) {
        $x(String.format(NEW_COMMENT_XPATH, username)).shouldBe(Condition.visible);
        return true;
    }
}
