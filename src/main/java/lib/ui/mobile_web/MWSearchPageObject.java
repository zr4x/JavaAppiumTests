package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        //TODO Переписать элемент, на выбор первого
        SEARCH_INPUT = "css:form > input.search";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";

        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULTS = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_TEXT = "css:p.without-results";
        SEARCH_RESULT_ARTICLE_TITLE = "css:ul.page-list>li.page-summary>a>h3";
        //HomeWork 18
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://li[@title='{TITLE}']//div[@class='wikidata-description'][contains(text(),'{ARTICLE_DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

