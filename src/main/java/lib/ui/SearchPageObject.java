package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                    "//*[@text='{SUBSTRING}']",
            SEARCH_RESULTS = "org.wikipedia:id/page_list_item_container",
            SEARCH_EMPTY_IMG = "org.wikipedia:id/search_empty_image",
            SEARCH_RESULT_ARTICLE_TITLE = "org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS */


    public void initSearchInput()
    {
        this.waitForElementPresentAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find and click init element", 5);
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Cannot find search after clicking search init element");
    }

    public void typeSearchLine(String searchLine) {

        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type into search input", 5);

    }

    public void waitForSearchResult(String substring) {

        String searchResult = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResult), "Cant find result with substring: " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {

        String searchResult = getResultSearchElement(substring);
        this.waitForElementPresentAndClick(By.xpath(searchResult), "Cant find and click  with substring: " + substring);
    }

    public void waitForCancelButtonAppear() {

        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find X button on screen");
    }

    public void waitForCancelButtonDisappear() {

        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "X button still present",5);
    }

    public void clickCancelSearch() {

        this.waitForElementPresentAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cant click on X button");
    }

    public void checkThatSearchListHaveResults(long timeOutInSecends) {

        this.checkAvailableElements(By.id(SEARCH_RESULTS), "0 elements after search", timeOutInSecends);
    }

    public void checkEmptyResultImg() {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_IMG), "Cant find zero result img. Maybe you dosent cancel search", 5);
    }

    public void checkThatSearhListHaveTitle(String text) {
        this.checkElementsText(By.id(SEARCH_RESULT_ARTICLE_TITLE), text, "Search result dosent have title "+text, 5);
    }

    public int getAmoutOfFoundArticles() {
        this.waitForElementPresent(By.id(SEARCH_RESULTS), "Cant find any search result");
        return getAmountOfElements(By.id(SEARCH_RESULTS));
    }

}
