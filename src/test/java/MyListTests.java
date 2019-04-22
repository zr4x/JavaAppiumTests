import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    @Test
    public void testSaveArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUi navigationUi = new NavigationUi(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();

        String articleTitle = articlePageObject.getArticleTitle();
        String folderName = "Learning programing";

        articlePageObject.addFirstArticleToMyList(folderName);
        articlePageObject.closeArticle();

        navigationUi.clickMyList();

        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(articleTitle);


    }

    //Refactoring HomeWork 5
    @Test
    public void testAddTwoArticlesInFolderTest() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUi navigationUi = new NavigationUi(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);

        String folderName = "Learning programing";

        //Search and add first artcile in myList
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String firstArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addFirstArticleToMyList(folderName);
        articlePageObject.closeArticle();
        //Search and add second article to created list
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        String secondArticleTitle = articlePageObject.getArticleTitle();
        articlePageObject.addSecondArticleToCreatedFolder(folderName);
        articlePageObject.closeArticle();
        //Go to my list
        navigationUi.clickMyList();
        // Open Learning programing folder and delete java article,
        // after compare article title in folder and article title in article page
        myListsPageObject.openFolderByName(folderName);
        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        String articleTitleInFolder = myListsPageObject.getArticleTitle();
        myListsPageObject.clickToArticleInFolder(secondArticleTitle);

        assertEquals(articleTitleInFolder, secondArticleTitle);


    }
}
