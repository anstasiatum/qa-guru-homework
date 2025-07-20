package eighteenthhometask.specs;

import io.restassured.specification.RequestSpecification;

import static eighteenthhometask.tests.DemoQATestData.authToken;
import static eighteenthhometask.tests.DemoQATestData.userID;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DeleteAllBooksSpec {
    public static RequestSpecification deleteAllBooksRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .header("Authorization", "Bearer " + authToken)
            .queryParam("UserId", userID)
            .log().all()
            .basePath("/BookStore/v1/Books?UserId=" + userID);

}
