package eighteenthhometask.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class DemoQaTestBase {
    static final String baseURL = "https://demoqa.com";

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = baseURL;
        RestAssured.baseURI = baseURL;

        String selenoidHostName = System.getProperty("selenoidHostName", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoidLogin", "user1");
        String selenoidPassword = System.getProperty("selenoidPassword", "1234");
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "128.0");
        String screenResolution = System.getProperty("screenResolution", "1920x1080");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserSize = screenResolution;
        Configuration.remote = format("https://%s:%s@%s/wd/hub", selenoidLogin, selenoidPassword, selenoidHostName);
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserCapabilities = capabilities;
        Configuration.pageLoadStrategy = "eager";

    }

    @BeforeEach
    void listenerConfiguration() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
        closeWebDriver();
    }
}
