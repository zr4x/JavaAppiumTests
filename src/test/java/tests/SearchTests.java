package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    // Refactoring HomeWork 1
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    //Refactoring Ex3
    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.checkThatSearchListHaveResults(10);

        searchPageObject.waitForCancelButtonAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.checkEmptyResultImg();
    }


    //Refactoring HomeWork 3
    @Test
    public void testCorrectSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        String searchText = "Test";
        //Test Script
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.checkThatSearchListHaveTitle(searchText);

    }
    //refactoring for ios HomeWork 12
    @Test
    public void testWaitForArticlesByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");

        searchPageObject.waitForElementByTitleAndDescription("Python", "isambiguation page providing links to topics that could be referred to by the same search term");
        searchPageObject.waitForElementByTitleAndDescription("Python (programming language)", "eneral-purpose, high-level programming language");
        searchPageObject.waitForElementByTitleAndDescription("Python (missile)", "amily of air-to-air missiles");

    }


}
