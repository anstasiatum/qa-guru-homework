package eighteenthhometask.specs;

import io.restassured.specification.RequestSpecification;

import static eighteenthhometask.tests.DemoQATestData.authToken;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class AddBookSpec {
    public static RequestSpecification addBookRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .header("Authorization", "Bearer " + authToken)
            .log().all();
}

