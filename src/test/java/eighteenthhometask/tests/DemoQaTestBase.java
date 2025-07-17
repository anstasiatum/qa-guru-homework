package eighteenthhometask.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import eighteenthhometask.config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class DemoQaTestBase {
    static final String baseURL = "https://demoqa.com";
    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = baseURL;
        RestAssured.baseURI = baseURL;

        Configuration.browserSize = config.getScreenResolution();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = "eager";

        if (Objects.equals(config.getEnvironment(), "QA_GURU")) {
            String selenoidHostName = config.getSelenoidHostName();
            String selenoidLogin = config.getSelenoidLogin();
            String selenoidPassword = config.getSelenoidPassword();
            Configuration.remote = format("https://%s:%s@%s/wd/hub", selenoidLogin, selenoidPassword, selenoidHostName);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

    }

    @BeforeEach
    void listenerConfiguration() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.addVideo();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
        closeWebDriver();
    }
}
