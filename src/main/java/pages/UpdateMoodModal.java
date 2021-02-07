package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class UpdateMoodModal {
    private final String SEND_MOOD_XPATH = "//*[@class='modal-footer']//*[contains(@class,'ButtonUpdate')]";
    private final String MOOD_SLIDER_XPATH = "//*[@class='modal-body']//*[contains(@class,'ui-slider-handle')]";
    private final String TEXT_AREA_XPATH = "//*[@id='TextBoxUpdateMoodTag']";
    private final String UPDATING_MOOD_DONE_BUTTON = "//*[contains(@class,'btn') and contains(text(),'Done')]";
    private final String ADVANCED_BUTTON_XPATH =
            "//*[@id='Advanced']//*[contains(@class,'btn') and contains(text(),'%s')]";

    public UpdateMoodModal waitForModalOpened() {
        $x(SEND_MOOD_XPATH).shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

    @Step("Click send mood button")
    public UpdateMoodModal clickSendMoodButton() {
        $x(SEND_MOOD_XPATH).click();
        return this;
    }

    /**
     * Sets mood value by {@code rate} int value
     *
     * @param rate mood rate int
     * @return this page
     */
    @Step("Set mood to {rate}")
    public UpdateMoodModal setMoodValue(int rate) {
        $x(MOOD_SLIDER_XPATH).click();
        int defaultMoodValue = 5;
        boolean direction = rate >= defaultMoodValue;
        int difference = Math.abs(defaultMoodValue - rate);
        for (int i = 0; i < difference; i++) {
            if (direction) {
                $x(MOOD_SLIDER_XPATH).sendKeys(Keys.ARROW_UP);
            } else {
                $x(MOOD_SLIDER_XPATH).sendKeys(Keys.ARROW_DOWN);
            }
        }
        return this;
    }

    @Step("Set description")
    public UpdateMoodModal setDescription(String description) {
        $x(TEXT_AREA_XPATH).sendKeys(description);
        return this;
    }

    @Step("Click 'Updating mood done' button")
    public FeedPage clickUpdatingMoodDoneButton() {
        $x(UPDATING_MOOD_DONE_BUTTON).click();
        return new FeedPage().waitForPageOpened().waitForPostSendAndRefresh();
    }

    @Step("Choose date by label '{label}'")
    public UpdateMoodModal setDateByLabel(String label) {
        $x(String.format(ADVANCED_BUTTON_XPATH, label)).click();
        return this;
    }
}
