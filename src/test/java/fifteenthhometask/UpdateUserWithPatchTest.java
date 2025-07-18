package fifteenthhometask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fifteenthhometask.TestData.headerO;
import static fifteenthhometask.TestData.headerS;
import static fifteenthhometask.TestData.requestUserWithoutID;
import static fifteenthhometask.TestData.updateUserRequestBody;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

public class UpdateUserWithPatchTest {
    int userID;

    @Test
    @DisplayName("Successful registration of a user via PATCH request")
    void successfulUserRegistrationViaPatch() {
        userID = 2;
        String request = requestUserWithoutID + userID;

        given()
                .header(headerS, headerO)
                .body(updateUserRequestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .patch(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", containsString("T"));
    }
}

