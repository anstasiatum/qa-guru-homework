package eighteenthhometask.tests;

import eighteenthhometask.steps.api.AddBooksToCartSteps;
import eighteenthhometask.steps.api.DeleteAllBooksFromCart;
import eighteenthhometask.steps.ui.DeleteBooksFromCartSteps;
import helpers.annotations.WithLoginUI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static eighteenthhometask.tests.DemoQATestData.bookName;
import static eighteenthhometask.tests.DemoQATestData.isbnValue;

@Tag("book_store_app")
public class BookDeletionTest extends DemoQaTestBase {
    DeleteAllBooksFromCart deleteAllBooks = new DeleteAllBooksFromCart();
    AddBooksToCartSteps addBook = new AddBooksToCartSteps();
    DeleteBooksFromCartSteps deleteBook = new DeleteBooksFromCartSteps();

    @AfterEach
    @DisplayName("Deleting all books form the cart via API")
    void deleteAllBooksViaAPI() {
        deleteAllBooks.deleteAllBooksFromCart();
    }

    @Test
    @DisplayName("Delete a single book from the cart")
    @WithLoginUI
    void deleteASingleBookTest() {
        addBook.addBookToCartViaApi(isbnValue);
        deleteBook.openProfile();
        deleteBook.deleteBookFromCartViaUI();
        deleteBook.checkNoRowsFoundTextViaUi();
        deleteBook.checkTheBookIsNotInTheTableViaUi(bookName);
    }
}
