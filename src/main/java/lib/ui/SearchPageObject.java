package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULTS,
            SEARCH_EMPTY_IMG,
            SEARCH_RESULT_ARTICLE_TITLE,
            SEARCH_EMPTY_RESULT_TEXT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            CLEAR_SEARCH;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String searchResultByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL
                .replace("{TITLE}", title)
                .replace("{ARTICLE_DESCRIPTION}", description);

    }

    /*TEMPLATE METHODS */


    public void initSearchInput() {
        this.waitForElementPresentAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search after clicking search init element");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input", 5);
        if (Platform.getInstance().isAndroid()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.hideKeyboard();
        }

    }

    public void waitForSearchResult(String substring) {
        String searchResult = getResultSearchElement(substring);
        this.waitForElementPresent(searchResult, "Cant find result with substring: " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResult = getResultSearchElement(substring);
        this.waitForElementPresentAndClick(searchResult, "Cant find and click  with substring: " + substring);
    }

    public void waitForCancelButtonAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find X button on screen");
    }

    public void waitForCancelButtonDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "X button still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementPresentAndClick(SEARCH_CANCEL_BUTTON, "Cant click on X button");
    }

    public void checkThatSearchListHaveResults(long timeOutInSecends) {
        this.checkAvailableElements(SEARCH_RESULTS, "0 elements after search", timeOutInSecends);
    }

    public void checkEmptyResultImg() {
        this.waitForElementPresent(SEARCH_EMPTY_IMG, "Cant find zero result img. Maybe you dosent cancel search", 5);
    }

    public void checkThatSearchListHaveTitle(String text) {
        this.checkElementsText(SEARCH_RESULT_ARTICLE_TITLE, text, "Search result dosent have title " + text, 5);
    }

    public int getAmoutOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULTS, "Cant find any search result");
        return getAmountOfElements(SEARCH_RESULTS);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresent(
                searchResultByTitleAndDescription(title, description),
                "Cannot find article by title: " + "'" + title + "'" + " and description: " + "'" + description + "'" + " in search results",
                10
        );

    }

    public void clearSearchFieldMethodForIOS() {
        waitForElementPresentAndClick(CLEAR_SEARCH, "Cannot find clear button");
    }

}
