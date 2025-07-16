package eighteenthhometask.steps.api;

import eighteenthhometask.models.AddBookRequestModel;
import eighteenthhometask.models.ISBNModel;
import helpers.annotations.WithLoginAPI;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static eighteenthhometask.specs.AddBookSpec.addBookRequestSpec;
import static eighteenthhometask.specs.AddBookSpec.addBookResponseSpec;
import static eighteenthhometask.tests.DemoQATestData.userID;
import static io.restassured.RestAssured.given;

public class AddBooksToCartSteps {
    AddBookRequestModel requestBody = new AddBookRequestModel();
    ISBNModel isbn = new ISBNModel();

    @Step("API. Add a book to cart")
    @WithLoginAPI
    public void addBookToCartViaApi(String isbnValue) {
        isbn.setIsbn(isbnValue);
        requestBody.setUserId(userID);
        requestBody.setCollectionOfIsbns(List.of(isbn));

        given(addBookRequestSpec)
                .body(requestBody)
                .when()
                .post()
                .then()
                .spec(addBookResponseSpec);
    }
}
