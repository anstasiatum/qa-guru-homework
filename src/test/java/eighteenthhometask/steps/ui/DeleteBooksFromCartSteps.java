package eighteenthhometask.steps.ui;

import helpers.annotations.WithLoginUI;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static eighteenthhometask.pages.ProfilePage.bookTable;
import static eighteenthhometask.pages.ProfilePage.deleteASingleBookFromCartButton;
import static eighteenthhometask.pages.ProfilePage.noRowsFound;
import static eighteenthhometask.pages.ProfilePage.profileURL;
import static eighteenthhometask.pages.components.DeletionConfirmationModal.okButton;

public class DeleteBooksFromCartSteps {
    @Step
    @DisplayName("Open profile")
    public void openProfile() {
        open(profileURL);
    }

    @Step
    @DisplayName("UI. Delete a book from the cart")
    @WithLoginUI
    public void deleteBookFromCartViaUI() {
        deleteASingleBookFromCartButton.click();
        okButton.click();
    }

    @Step
    @DisplayName("UI. Check the \"No rows found\" text")
    @WithLoginUI
    public void checkNoRowsFoundTextViaUi() {
        noRowsFound
                .shouldBe(visible)
                .shouldHave(text("No rows found"));

    }

    @Step
    @DisplayName("UI. Check the table does npt have the specified book")
    @WithLoginUI
    public void checkTheBookIsNotInTheTableViaUi(String bookName) {
        bookTable.shouldNotHave(text(bookName));

    }
}
