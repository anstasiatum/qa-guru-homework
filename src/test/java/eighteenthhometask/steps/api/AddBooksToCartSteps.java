package eighteenthhometask.steps.api;

import eighteenthhometask.models.AddBookRequestModel;
import eighteenthhometask.models.IsbnModel;
import io.qameta.allure.Step;

import java.util.List;

import static eighteenthhometask.specs.AddBookSpec.addBookRequestSpec;
import static eighteenthhometask.specs.ResponseSpec.getResponseSpec;
import static eighteenthhometask.tests.DemoQATestData.userID;
import static io.restassured.RestAssured.given;

public class AddBooksToCartSteps {
    AddBookRequestModel requestBody = new AddBookRequestModel();
    IsbnModel isbn = new IsbnModel();

    @Step("API. Add a book to cart")
    public void addBookToCartViaApi(String isbnValue) {
        isbn.setIsbn(isbnValue);
        requestBody.setUserId(userID);
        requestBody.setCollectionOfIsbns(List.of(isbn));

        given(addBookRequestSpec)
                .body(requestBody)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(getResponseSpec(201));
    }
}
