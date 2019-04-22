import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
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





}
