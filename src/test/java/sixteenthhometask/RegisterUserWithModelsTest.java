package sixteenthhometask;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sixteenthhometask.models.registeruser.SuccessfulUserRegistrationResponseModel;
import sixteenthhometask.models.registeruser.UnsuccessfulUserRegistrationResponseModel;
import sixteenthhometask.models.registeruser.UserRegistrationRequestModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sixteenthhometask.specs.GetResponse.getResponse;
import static sixteenthhometask.specs.RegisterUserSpec.userRegistrationRequestSpec;

@Tag("Reqres")
public class RegisterUserWithModelsTest extends TestBase {
    Faker faker = new Faker();
    UserRegistrationRequestModel requestBody = new UserRegistrationRequestModel();

    @Test
    @DisplayName("Successful registration of a user")
    void successfulUserRegistration() {
        requestBody.setEmail("eve.holt@reqres.in");
        requestBody.setPassword("pistol");

        SuccessfulUserRegistrationResponseModel responseBody = step("Make request", () ->
                given(userRegistrationRequestSpec)
                        .header(headerS, headerO)
                        .body(requestBody)
                        .when()
                        .post()
                        .then()
                        .spec(getResponse(200))
                        .extract().as(SuccessfulUserRegistrationResponseModel.class));

        step("Check response", () -> {
                    assertEquals(4, responseBody.getId());
                    assertEquals("QpwL5tke4Pnpja7X4", responseBody.getToken());
                }
        );
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing API key")
    void unsuccessfulUserRegistrationMissingAPIKey() {
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPassword(faker.internet().password());

        step("Make request and check status code", () ->
                given(userRegistrationRequestSpec)
                        .body(requestBody)
                        .when()
                        .post()
                        .then()
                        .spec(getResponse(401)));

    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing email")
    void unsuccessfulUserRegistrationMissingEmail() {
        requestBody.setPassword(faker.internet().password());

        UnsuccessfulUserRegistrationResponseModel responseBody = step("Make request", () ->
                given(userRegistrationRequestSpec)
                        .header(headerS, headerO)
                        .body(requestBody)
                        .when()
                        .post()
                        .then()
                        .spec(getResponse(400))
                        .extract().as(UnsuccessfulUserRegistrationResponseModel.class));

        step("Check response", () -> assertEquals("Missing email or username", responseBody.getError()));
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing password")
    void unsuccessfulUserRegistrationMissingPassword() {
        requestBody.setEmail(faker.internet().emailAddress());

        UnsuccessfulUserRegistrationResponseModel responseBody = step("Make request", () ->
                given(userRegistrationRequestSpec)
                        .header(headerS, headerO)
                        .body(requestBody)
                        .when()
                        .post()
                        .then()
                        .spec(getResponse(400))
                        .extract().as(UnsuccessfulUserRegistrationResponseModel.class));

        step("Check response", () -> assertEquals("Missing password", responseBody.getError()));
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: empty body")
    void unsuccessfulUserRegistrationEmptyBody() {

        UnsuccessfulUserRegistrationResponseModel responseBody = step("Make request", () ->
                given(userRegistrationRequestSpec)
                        .header(headerS, headerO)
                        .body(requestBody)
                        .when()
                        .post()
                        .then()
                        .spec(getResponse(400))
                        .extract().as(UnsuccessfulUserRegistrationResponseModel.class));

        step("Check response", () -> assertEquals("Missing email or username", responseBody.getError()));
    }
}
