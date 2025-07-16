package eighteenthhometask.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static eighteenthhometask.tests.DemoQATestData.authToken;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class AddBookSpec {
    public static RequestSpecification addBookRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .header("Authorization", "Bearer " + authToken)
            .log().all()
            .basePath("/BookStore/v1/Books");

    public static ResponseSpecification addBookResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();
}

