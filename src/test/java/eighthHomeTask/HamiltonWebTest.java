package eighthHomeTask;

import eighthHomeTask.data.Language;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class HamiltonWebTest extends TestBase {

    @BeforeAll
    static void setUp() {
        open("/new-york");
        $("[aria-label=\"Close Chat Popup\"]").click();
    }

    @EnumSource(Language.class)
    @DisplayName("Hamilton website should have localization")
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectText(Language language) {
        $(".site-header__container > div:nth-of-type(4)").click();
        $$("#menu-translations-menu").find(text(language.getLanguageName())).click();
        $(".rpmblock--heading__container").shouldHave(text(language.getDescription()));
    }
}
