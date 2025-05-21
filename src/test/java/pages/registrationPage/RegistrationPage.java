package pages.registrationPage;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.InputVerificationTable;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderInput = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement calendarInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesInput = $("#hobbiesWrapper");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement avatarInput = $("#uploadPicture");
    private final SelenideElement stateMenuOpener = $("#state");
    private final SelenideElement stateInput = $("#react-select-3-input");
    private final SelenideElement cityMenuOpener = $("#city");
    private final SelenideElement cityInput = $("#react-select-4-input");
    private final String ERROR_BORDER_COLOUR = "rgb(220, 53, 69)";
    private final String SUCCESS_BORDER_COLOUR = "rgb(40, 167, 69)";

    CalendarComponent calendarComponent = new CalendarComponent();
    InputVerificationTable inputVerificationTable = new InputVerificationTable();

    private final SelenideElement submitButton = $("#submit");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage firstNameFieldHasError() {
        firstNameInput.shouldHave(cssValue("border-color", ERROR_BORDER_COLOUR));
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage lastNameFieldHasError() {
        lastNameInput.shouldHave(cssValue("border-color", ERROR_BORDER_COLOUR));
        return this;
    }

    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String phoneNumber) {
        userNumberInput.setValue(phoneNumber);
        return this;
    }

    public RegistrationPage userNumberFieldHasError() {
        userNumberInput.shouldHave(cssValue("border-color", ERROR_BORDER_COLOUR));
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress).pressEnter();
        return this;
    }

    public RegistrationPage setAvatar(String fileName) {
        avatarInput.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateMenuOpener.click();
        stateInput.setValue(state).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityMenuOpener.click();
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        inputVerificationTable.compareUserData(key, value);
        return this;
    }

    public RegistrationPage checkTableIsAbsent() {
        inputVerificationTable.isAbsent();
        return this;
    }

    public RegistrationPage checkTableIsVisible() {
        inputVerificationTable.isVisible();
        return this;
    }
}
