package tests.before_test;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.Objects;

public class AllureUtilities {
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot() throws IOException {
        return FileUtils.readFileToByteArray(Objects.requireNonNull(Screenshots.takeScreenShotAsFile()));
    }
}
