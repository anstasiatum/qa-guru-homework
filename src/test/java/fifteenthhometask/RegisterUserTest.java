package fifteenthhometask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fifteenthhometask.TestData.headerO;
import static fifteenthhometask.TestData.headerS;
import static fifteenthhometask.TestData.registerUserRequestBody;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegisterUserTest {
    final String request = "https://reqres.in/api/register";

    @Test
    @DisplayName("Successful registration of a user")
    void successfulUserRegistration() {

        given()
                .header(headerS, headerO)
                .body(registerUserRequestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing API key")
    void unsuccessfulUserRegistrationMissingAPIKey() {

        given()
                .body(registerUserRequestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(401);
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing email")
    void unsuccessfulUserRegistrationMissingEmail() {

        final String requestBody = """
                {
                    "password": "pistol"
                }
                """;

        given()
                .header(headerS, headerO)
                .body(requestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: missing password")
    void unsuccessfulUserRegistrationMissingPassword() {

        final String requestBody = """
                {
                    "email": "eve.holt@reqres.in"
                }
                """;

        given()
                .header(headerS, headerO)
                .body(requestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Unsuccessful registration of a user: empty body")
    void unsuccessfulUserRegistrationEmptyBody() {

        final String requestBody = "{}";

        given()
                .header(headerS, headerO)
                .body(requestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .post(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}
