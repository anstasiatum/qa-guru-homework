package eighteenthhometask.steps.api;

import io.qameta.allure.Step;

import static eighteenthhometask.specs.DeleteAllBooksSpec.deleteAllBooksRequestSpec;
import static eighteenthhometask.specs.ResponseSpec.getResponseSpec;
import static io.restassured.RestAssured.given;

public class DeleteAllBooksFromCart {

    @Step("API. Delete all books from cart")
    public void deleteAllBooksFromCart() {
        given(deleteAllBooksRequestSpec)
                .when()
                .delete()
                .then()
                .spec(getResponseSpec(204));
    }
}
