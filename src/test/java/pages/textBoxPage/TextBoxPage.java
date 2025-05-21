package pages.textBoxPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");

    private final SelenideElement submitButton = $("#submit");

    private final SelenideElement nameResult = $("#output #name");
    private final SelenideElement emailResult = $("#output #email");
    private final SelenideElement currentAddressResult = $("#output #currentAddress");
    private final SelenideElement permanentAddressResult = $("#output #permanentAddress");

    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        fullNameInput.setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String userEmail) {
        emailInput.setValue(userEmail);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.click();
        return this;
    }

    public TextBoxPage verifyUserName(String userName) {
        nameResult.shouldHave(text(userName));
        return this;
    }

    public TextBoxPage verifyEmail(String email) {
        emailResult.shouldHave(text(email));
        return this;
    }

    public TextBoxPage verifyCurrentAddress(String currentAddress) {
        currentAddressResult.shouldHave(text(currentAddress));
        return this;
    }

    public TextBoxPage verifyPermanentAddress(String permanentAddress) {
        permanentAddressResult.shouldHave(text(permanentAddress));
        return this;
    }
}
