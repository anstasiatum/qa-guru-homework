package eighthhometask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DuckDuckGoWebTest {
    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com/");
    }

    @ValueSource(strings = {
            "Hamilton the Musical", "Гамильтон Мюзикл"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен быть непустой список карточек")
    @Tag("Smoke")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }


    @CsvSource(value = {
            "Hamilton the Musical, https://en.wikipedia.org",
            "Гамильтон Мюзикл, https://ru.wikipedia.org"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть википедия на нужном языке {1}")
    @Tag("Regression")
    void searchResultsShouldContainExpectedWikipediaPage(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }

    @CsvFileSource(resources = "/TestDataForEighthHomeTask.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должно быть имя {1}")
    @Tag("Regression")
    void searchResultsShouldContainMirandasName(String searchQuery, String expectedName) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedName));
    }
}
