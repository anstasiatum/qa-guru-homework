package eighteenthhometask.steps.api;

import eighteenthhometask.models.LoginRequestModel;
import eighteenthhometask.models.LoginResponseModel;
import io.qameta.allure.Step;

import static eighteenthhometask.specs.LoginSpec.loginRequestSpec;
import static eighteenthhometask.specs.ResponseSpec.getResponseSpec;
import static eighteenthhometask.tests.DemoQATestData.authToken;
import static eighteenthhometask.tests.DemoQATestData.expires;
import static eighteenthhometask.tests.DemoQATestData.userID;
import static io.restassured.RestAssured.given;

public class LoginSteps {
    LoginRequestModel requestBody = new LoginRequestModel();

    @Step("API. Authorization")
    public void authorizeViaApi(String login, String password) {
        requestBody.setUserName(login);
        requestBody.setPassword(password);

        LoginResponseModel response = given(loginRequestSpec)
                .body(requestBody)
                .when()
                .post()
                .then()
                .spec(getResponseSpec(200))
                .extract().as(LoginResponseModel.class);

        authToken = response.getToken();
        userID = response.getUserId();
        expires = response.getExpires();
    }
}
