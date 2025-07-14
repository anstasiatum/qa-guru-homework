package fifteenthhometask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fifteenthhometask.APIKey.headerO;
import static fifteenthhometask.APIKey.headerS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class GetSingleUserTest {
    final String requestWithoutID = "https://reqres.in/api/users/";
    int userID;

    @Test
    @DisplayName("Successful search for a single existent user")
    void successfulSearchForASingleUserTest() {
        userID = 2;
        String request = requestWithoutID + userID;

        given()
                .header(headerS, headerO)
                .log().uri()

                .when()
                .get(request)

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(userID))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"))
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }

    @Test
    @DisplayName("Search for a user with non-existent ID")
    void unsuccessfulSearchForASingleUserTest() {
        userID = 23;
        String request = requestWithoutID + userID;

        given()
                .header(headerS, headerO)
                .log().uri()

                .when()
                .get(request)

                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Missing API key test during search")
    void unsuccessfulSearchForASingleUserMissingAPIKeyTest() {
        userID = 3;
        String request = requestWithoutID + userID;

        given()
                .log().uri()

                .when()
                .get(request)

                .then()
                .log().status()
                .log().body()
                .statusCode(401);
    }
}
