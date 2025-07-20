package fifteenthhometask;

public class TestData {
    // Headers
    final static String headerS = "x-api-key";
    final static String headerO = "reqres-free-v1";

    // User request URL without ID
    final static String requestUserWithoutID = "https://reqres.in/api/users/";

    // Request Bodies
    final static String updateUserRequestBody = """
            {
                "name": "morpheus",
                "job": "zion resident"
            }
            """;

    final static String registerUserRequestBody = """
            {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
            }
            """;
}
