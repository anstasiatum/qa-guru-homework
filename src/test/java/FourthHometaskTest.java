import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FourthHometaskTest {

    @BeforeAll
    static void configuration() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void shouldHaveJUnitInSoftAssertionsTest() {
        // Opening the Selenide page in Github
        open("/selenide/selenide");
        // Finding its Wiki
        $("#wiki-tab").click();
        // Asserting there is a Soft assertions page
        $("#wiki-body").shouldHave(text("Soft assertions"));
        // Opening the Soft assertions page
        $("#wiki-body").$(byText("Soft assertions")).click();
        // Making sure there are two JUnit5 code snippets
        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));

        $(".markdown-body").shouldHave(text("""
                class Tests {
                  @RegisterExtension
                  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();
                
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }
}
