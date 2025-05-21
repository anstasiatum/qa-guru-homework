package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class InputVerificationTable {
    private static SelenideElement resultTable = $(".table-responsive");

    public InputVerificationTable compareUserData(String key, String value) {
        resultTable.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    public InputVerificationTable isVisible() {
        resultTable.shouldBe(visible);
        return this;
    }

    public InputVerificationTable isAbsent() {
        resultTable.shouldNotBe(visible);
        return this;
    }
}
