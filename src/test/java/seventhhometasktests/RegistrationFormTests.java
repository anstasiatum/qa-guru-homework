package seventhhometasktests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.registrationPage.RegistrationPage;
import sixthhometasktests.TestBase;

import java.util.Map;

import static seventhhometasktests.TestData.currentAddress;
import static seventhhometasktests.TestData.email;
import static seventhhometasktests.TestData.firstName;
import static seventhhometasktests.TestData.fullName;
import static seventhhometasktests.TestData.getRandomAvatar;
import static seventhhometasktests.TestData.getRandomBirthday;
import static seventhhometasktests.TestData.getRandomCity;
import static seventhhometasktests.TestData.getRandomGender;
import static seventhhometasktests.TestData.getRandomHobby;
import static seventhhometasktests.TestData.getRandomMonth;
import static seventhhometasktests.TestData.getRandomState;
import static seventhhometasktests.TestData.getRandomSubject;
import static seventhhometasktests.TestData.getRandomYear;
import static seventhhometasktests.TestData.lastName;
import static seventhhometasktests.TestData.phoneNumber;

@Tag("Jenkins")
public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    //Generating test data
    private final String gender = getRandomGender();
    private final String subject = getRandomSubject();
    private final String hobby = getRandomHobby();
    private final String birthMonth = getRandomMonth();
    private final String birthYear = getRandomYear();
    private final String birthDay = getRandomBirthday(birthMonth, birthYear);
    private final String dateOfBirth = birthDay + " " + birthMonth + "," + birthYear;
    private final String state = getRandomState();
    private final String city = getRandomCity(state);
    private final String stateAndCity = state + " " + city;
    private final String avatarPath = getRandomAvatar();

    @BeforeAll
    static void browserConfiguration() {
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void practiceFormFillAllFieldsTest() {

        // Opening the form page
        registrationPage.openPage();

        // Filling the form
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setSubject(subject)
                .setHobby(hobby)
                .setCurrentAddress(currentAddress)
                .setAvatar(avatarPath)
                .setDateOfBirth(birthDay, birthMonth, birthYear)
                .setState(state)
                .setCity(city)
                .submit();

        // Verifying the entered values in the modal window
        registrationPage.checkResult("Student Name", fullName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dateOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", avatarPath)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", stateAndCity);
    }

    @Test
    void practiceFormFillOnlyMandatoryFieldsTest() {
        // Opening the form page
        registrationPage.openPage();

        // Filling the form
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .submit();

        // Verifying the entered values in the modal window
        registrationPage.checkResult("Student Name", fullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);
    }

    @Test
    void practiceFormEmptyFormSubmissionTest() {
        // Opening the form page
        registrationPage.openPage();

        // Submitting an empty form
        registrationPage.submit();

        // Verifying the table is not opened
        registrationPage.checkTableIsAbsent();

        // Verifying the required fields are highlighted in red
        registrationPage.firstNameFieldHasError()
                .lastNameFieldHasError()
                .userNumberFieldHasError();
    }
}

