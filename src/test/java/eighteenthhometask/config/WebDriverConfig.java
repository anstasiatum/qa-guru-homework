package eighteenthhometask.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browser.name")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browser.version")
    @DefaultValue("138.0")
    String getBrowserVersion();

    @Key("browser.screenResolution")
    @DefaultValue("1920x1080")
    String getScreenResolution();

    @Key("environment")
    @DefaultValue("QA_GURU")
    String getEnvironment();

    @Key("selenoid.hostName")
    @DefaultValue("selenoid.autotests.cloud")
    String getSelenoidHostName();

    @Key("selenoid.login")
    @DefaultValue("user1")
    String getSelenoidLogin();

    @Key("selenoid.password")
    @DefaultValue("1234")
    String getSelenoidPassword();
}
