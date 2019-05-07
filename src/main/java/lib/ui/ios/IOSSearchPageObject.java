package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        //TODO Переписать элемент, на выбор первого
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        CLEAR_SEARCH = "id:clear mini";

        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULTS = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_IMG = "id:org.wikipedia:id/search_empty_image";
        SEARCH_EMPTY_RESULT_TEXT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/../*[@text='{ARTICLE_DESCRIPTION}']/..";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
