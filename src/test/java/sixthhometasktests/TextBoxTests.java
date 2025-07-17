package sixthhometasktests;

import org.junit.jupiter.api.Test;
import pages.textBoxPage.TextBoxPage;

public class TextBoxTests extends TestBase {

TextBoxPage textBoxPage = new TextBoxPage();

    // Setting up the test data
    final String fullName = "John Doe";
    final String email = "johndoe@gmail.com";
    final String currentAddress = "99-040 KAUHALE ST AIEA, HI 96701";
    final String permanentAddress = "12 1ST ST NW HAMPTON IA 50441-1902";

    @Test
    void textBoxFillAllFieldsTest() {
        // Opening the form page
        textBoxPage.openPage();

        // Filling the form
        textBoxPage.setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit();

        // Verifying the entered values in the modal window
        textBoxPage.verifyUserName(fullName)
                .verifyEmail(email)
                .verifyCurrentAddress(currentAddress)
                .verifyPermanentAddress(permanentAddress);
    }
}
