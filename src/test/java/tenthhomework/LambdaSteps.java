package tenthhomework;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import tenthhomework.annotationsteps.Steps;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.openqa.selenium.By.linkText;

@Tag("Jenkins")
@Tag("Annotations")
public class LambdaSteps {
    public static final String REPOSITORY = System.getProperty("repository", "anstasiatum/qa-guru-homework");

    @BeforeAll
    static void browserConfiguration() {
        String selenoidHostName = System.getProperty("selenoidHostName", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoidLogin", "user1");
        String selenoidPassword = System.getProperty("selenoidPassword", "1234");
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "138.0");
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

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверяем написание названия вкладки Issues", () -> {
            $("[data-content=Issues]").shouldHave(text("Issues"));
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.shouldBENamedIssue();

    }
}
