package sixteenthhometask.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class GetUserSpec {
    public static RequestSpecification searchForASingleUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .basePath("/api/users/");

    public static ResponseSpecification successfulSearchForASingleUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification searchForASingleUserWithNonExistentIdResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .build();

    public static ResponseSpecification searchForASingleUserWithMissingHeadersSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(401)
            .build();
}


