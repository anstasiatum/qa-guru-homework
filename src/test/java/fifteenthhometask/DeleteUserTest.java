package fifteenthhometask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fifteenthhometask.TestData.headerO;
import static fifteenthhometask.TestData.headerS;
import static fifteenthhometask.TestData.requestUserWithoutID;
import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    int userID;

    @Test
    @DisplayName("Successful user deletion by ID")
    void successfulDeletionOfASingleUserTest() {
        userID = 2;
        String request = requestUserWithoutID + userID;

        given()
                .header(headerS, headerO)
                .log().uri()
                .when()
                .delete(request)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
