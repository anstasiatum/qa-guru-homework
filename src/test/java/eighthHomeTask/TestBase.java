package eighthHomeTask;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://hamiltonmusical.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }
}