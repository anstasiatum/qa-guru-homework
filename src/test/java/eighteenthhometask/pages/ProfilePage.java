package eighteenthhometask.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    public static final String profileURL = "/profile";
    public static final SelenideElement deleteASingleBookFromCartButton = $("#delete-record-undefined");
    public static final SelenideElement noRowsFound = $(".rt-noData");
    public static final SelenideElement bookTable = $(".ReactTable");
}