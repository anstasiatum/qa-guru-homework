package sixteenthhometask;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    final String headerS = "x-api-key";
    final String headerO = "reqres-free-v1";

    @BeforeAll
    static void configuration() {
        RestAssured.baseURI = "https://reqres.in";
    }
}
