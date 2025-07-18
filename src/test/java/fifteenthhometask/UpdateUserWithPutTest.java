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

public class UpdateUserWithPutTest {
    int userID;

    @Test
    @DisplayName("Successful updating of a user via PUT request")
    void successfulUserUpdatingViaPut() {
        userID = 2;
        String request = requestUserWithoutID + userID;

        given()
                .header(headerS, headerO)
                .body(updateUserRequestBody)
                .contentType(JSON)
                .log().uri()
                .when()
                .put(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", containsString("T"));
    }
}
