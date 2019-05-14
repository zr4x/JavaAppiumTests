package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_container";
        SEARCH_EMPTY_IMG = "id:org.wikipedia:id/search_empty_image";
        SEARCH_RESULT_ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://*[@text='{TITLE}']/../*[@text='{ARTICLE_DESCRIPTION}']/..";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }


}
