package pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class FeedPage {
    private final String UPDATE_MOOD_BUTTON_CSS = "#LinkUpdate";

    public FeedPage waitForPageOpened() {
        $(UPDATE_MOOD_BUTTON_CSS).shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

}
