package sixteenthhometask.specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class GetUserSpec {
    public static RequestSpecification searchForASingleUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .basePath("/users/");
}


