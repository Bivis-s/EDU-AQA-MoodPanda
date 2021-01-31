package tests;

import org.testng.annotations.DataProvider;

public class MoodPandaValues {
    public static final String EMAIL = "bivis-s-moodpanda@mailinator.com";
    public static final String PASSWORD = "42s53*SAtg34";
    public static final String USERNAME = "Bivis";

    @DataProvider(name = "moodData")
    public Object[][] provideMoodData() {
        return new Object[][]{
                {1},
                {3},
                {5},
                {7},
                {10}
        };
    }

    @DataProvider(name = "descriptionData")
    public Object[][] provideDescriptionData() {
        return new Object[][]{
                {"All good"},
                {"50/50"},
                {"Every day of my life is full of feelings and emotions. I can't say that I'm a sensitive person, but there are a lot of events in my life that impress me somehow. Every day I see people who I love. It'"}
        };
    }

    @DataProvider(name = "dateData")
    public Object[][] provideDateData() {
        return new Object[][]{
                {"3 days ago", "3d ·"},
                {"2 days ago", "2d ·"},
                {"Yesterday", "1d ·"},
        };
    }
}
