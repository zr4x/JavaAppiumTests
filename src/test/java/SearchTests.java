import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;


public class SearchTests extends CoreTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    // Refactoring HomeWork 1
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    //Refactoring Ex3
    @Test
    public void testCancelSearch() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

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
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        String searchText = "Test";
        //Test Script
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.checkThatSearhListHaveTitle(searchText);

    }
    //HomeWork 9
    @Test
    public void testWaitForArticlesByTitleAndDescription() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");

        searchPageObject.waitForElementByTitleAndDescription("Python", "Wikimedia disambiguation page");
        searchPageObject.waitForElementByTitleAndDescription("Python (programming language)", "General-purpose, high-level programming language");
        searchPageObject.waitForElementByTitleAndDescription("Python (missile)", "Family of air-to-air missiles");

    }


}
