package pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    public static final String LOGIN_PAGE_URL = "https://moodpanda.com/Login/";
    private final String GOOGLE_PLAY_ICON_CSS = "[alt = 'MoodPanda Android App on Google Play']";
    private final String EMAIL_INPUT_CSS = "#ContentPlaceHolderContent_TextBoxEmail";
    private final String PASSWORD_INPUT_CSS = "#ContentPlaceHolderContent_TextBoxPassword";
    private final String LOGIN_BUTTON_CSS = "#ContentPlaceHolderContent_ButtonLogin";

    public LoginPage openPage() {
        open(LOGIN_PAGE_URL);
        return this.waitForPageOpened();
    }

    public FeedPage login(String email, String password) {
        $(EMAIL_INPUT_CSS).sendKeys(email);
        $(PASSWORD_INPUT_CSS).sendKeys(password);
        $(LOGIN_BUTTON_CSS).click();
        return new FeedPage().waitForPageOpened();
    }

    public LoginPage waitForPageOpened() {
        $(GOOGLE_PLAY_ICON_CSS).shouldBe(Condition.visible, Duration.ofSeconds(10));
        return this;
    }

}