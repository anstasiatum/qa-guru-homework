import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FifthHometaskDragTest {
    @BeforeAll
    static void configuration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void rectanglesShouldSwitchPlacesTest() {
        open("/drag_and_drop");

        // Checking the initial state
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        // Swapping the objects
        $("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));

        // Asserting the new object positions
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
