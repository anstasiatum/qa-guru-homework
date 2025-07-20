package helpers.annotations;

import eighteenthhometask.steps.api.LoginSteps;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static eighteenthhometask.tests.DemoQATestData.authToken;
import static eighteenthhometask.tests.DemoQATestData.expires;
import static eighteenthhometask.tests.DemoQATestData.login;
import static eighteenthhometask.tests.DemoQATestData.password;
import static eighteenthhometask.tests.DemoQATestData.userID;

public class LoginExtensionUI implements BeforeEachCallback {
    LoginSteps loginSteps = new LoginSteps();

    public void beforeEach(ExtensionContext context) {
        loginSteps.authorizeViaApi(login, password);
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", userID));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", authToken));
    }
}
