package eighteenthhometask.steps.api;

import helpers.annotations.WithLoginAPI;
import io.qameta.allure.Step;

import static eighteenthhometask.specs.DeleteAllBooksSpec.deleteAllBooksRequestSpec;
import static eighteenthhometask.specs.DeleteAllBooksSpec.deleteAllBooksResponseSpec;
import static io.restassured.RestAssured.given;

public class DeleteAllBooksFromCart {

    @Step("API. Delete all books from cart")
    @WithLoginAPI
    public void deleteAllBooksFromCart() {

        given(deleteAllBooksRequestSpec)
                .when()
                .delete()
                .then()
                .spec(deleteAllBooksResponseSpec);
    }
}
