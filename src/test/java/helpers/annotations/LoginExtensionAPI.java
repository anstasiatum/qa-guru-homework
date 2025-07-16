package helpers.annotations;

import eighteenthhometask.steps.api.LoginSteps;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static eighteenthhometask.tests.DemoQATestData.login;
import static eighteenthhometask.tests.DemoQATestData.password;

public class LoginExtensionAPI implements BeforeEachCallback {
    LoginSteps loginSteps = new LoginSteps();

    public void beforeEach(ExtensionContext context) {
        loginSteps.authorizeViaApi(login, password);
    }
}
