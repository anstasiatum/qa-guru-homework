package sixthhometasktests;

import org.junit.jupiter.api.Test;
import pages.registrationPage.RegistrationPage;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    // Setting up the test data
    final String firstName = "John";
    final String lastName = "Doe";
    final String fullName = firstName + " " + lastName;
    final String email = "johndoe@gmail.com";
    final String gender = "Other";
    final String phoneNumber = "9315334916";
    final String subject = "Maths";
    final String hobby = "Music";
    final String currentAddress = "Test Address";
    final String avatarPath = "AvatarExample1.jpg";
    final String birthDay = "16";
    final String birthMonth = "May";
    final String birthYear = "2014";
    final String dateOfBirth = birthDay + " " + birthMonth + "," + birthYear;
    final String state = "Uttar Pradesh";
    final String city = "Lucknow";
    final String stateAndCity = state + " " + city;

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
