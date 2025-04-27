import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ThirdHomeTaskTests {

    @BeforeAll
    static void configuration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void practiceFormFillTest() {
        // Setting up the test data
        String firstName = "John";
        String surname = "Doe";
        String fullName = firstName + " " + surname;
        String email = "johndoe@gmail.com";
        String phoneNumber = "9315334916";
        String currentAddress = "Test Address";

        // Opening the form page
        open("/automation-practice-form");

        //Filling the form
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(surname);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(phoneNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("2014");
        $(".react-datepicker__day--016").click();

        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#currentAddress").setValue(currentAddress);
        $("#uploadPicture").uploadFromClasspath("AvatarExample.jpg");

        $("#state").click();
        $("#react-select-3-input").setValue("Ut").pressEnter();

        $("#city").click();
        $("#react-select-4-input").setValue("Luck").pressEnter();

        $("#submit").click();

        // Checking the entered values in the modal window

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(fullName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("16 May,2014"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("	AvatarExample.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Uttar Pradesh Lucknow"));
    }
}


