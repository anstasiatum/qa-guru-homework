package sixteenthhometask;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import sixteenthhometask.models.getuserbyid.GetUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static sixteenthhometask.specs.GetUserSpec.searchForASingleUserRequestSpec;
import static sixteenthhometask.specs.GetUserSpec.searchForASingleUserWithMissingHeadersSpec;
import static sixteenthhometask.specs.GetUserSpec.searchForASingleUserWithNonExistentIdResponseSpec;
import static sixteenthhometask.specs.GetUserSpec.successfulSearchForASingleUserResponseSpec;

@Tag("Reqres")
public class GetSingleUserWithModelsTest extends TestBase {
    int userID;

    @Test
    @DisplayName("Successful search for a single existent user")
    void successfulSearchForASingleUserTest() {
        userID = 2;

        GetUserResponseModel searchResult = step("Make request", () ->
                given(searchForASingleUserRequestSpec)
                        .header(headerS, headerO)

                        .when()
                        .get(String.valueOf(userID))

                        .then()
                        .spec(successfulSearchForASingleUserResponseSpec)
                        .extract().as(GetUserResponseModel.class));

        step("Check Response", () -> {
                    assertEquals(userID, searchResult.getData().getId());
                    assertEquals("janet.weaver@reqres.in", searchResult.getData().getEmail());
                    assertEquals("Janet", searchResult.getData().getFirstName());
                    assertEquals("Weaver", searchResult.getData().getLastName());
                    assertEquals("https://reqres.in/img/faces/2-image.jpg", searchResult.getData().getAvatar());
                    assertEquals("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral", searchResult.getSupport().getUrl());
                    assertEquals("Tired of writing endless social media content? Let Content Caddy generate it for you.", searchResult.getSupport().getText())
                    ;
                }
        );
    }

    @Test
    @DisplayName("Unsuccessful search for a user: non-existent ID")
    void unsuccessfulSearchForASingleUserTest() {
        userID = 23;

        step("Make request and check the response code", () ->
                given(searchForASingleUserRequestSpec)
                .header(headerS, headerO)

                .when()
                .get(String.valueOf(userID))

                .then()
                .spec(searchForASingleUserWithNonExistentIdResponseSpec));
    }

    @Test
    @DisplayName("Unsuccessful search for a user: Missing API key test during search")
    void unsuccessfulSearchForASingleUserMissingAPIKeyTest() {
        userID = 3;

        step("Make request and check the response code", () ->
        given(searchForASingleUserRequestSpec)

                .when()
                .get(String.valueOf(userID))

                .then()
                .spec(searchForASingleUserWithMissingHeadersSpec));
    }
}
