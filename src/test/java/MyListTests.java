import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String folderName = "Learning programing";

    @Test
    public void testSaveArticleToMyList() throws Exception {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUi navigationUi = NavigationUiPageObjectFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();

        String articleTitle = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(folderName);
        } else {
            articlePageObject.addArtcileToMySave();
        }

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeSyncArticlesPopup();
        }
        articlePageObject.closeArticle();

        navigationUi.clickMyList();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folderName);
        }

        myListsPageObject.swipeByArticleToDelete(articleTitle);


    }

    //Refactoring HomeWork 5
    @Test
    public void testAddTwoArticlesInFolderTest() throws Exception {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUi navigationUi = NavigationUiPageObjectFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);

        String firstArticleTitle;
        String secondArticleTitle;

        //Search and add first article in myList
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
        } else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }

        if (Platform.getInstance().isAndroid()) {
            firstArticleTitle = articlePageObject.getArticleTitle();
        } else {
            firstArticleTitle = articlePageObject.getArticleTitle("Java (programming language)");
        }

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(folderName);
        } else {
            articlePageObject.addArtcileToMySave();
        }

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeSyncArticlesPopup();
        }
        articlePageObject.closeArticle();
        //Search and add second article to created list
        searchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clearSearchFieldMethodForIOS();
        }
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        if (Platform.getInstance().isAndroid()) {
            secondArticleTitle = articlePageObject.getArticleTitle();
        } else {
            secondArticleTitle = articlePageObject.getArticleTitle("Appium");
        }

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addSecondArticleToCreatedFolder(folderName);
        } else {
            articlePageObject.addArtcileToMySave();
        }

        articlePageObject.closeArticle();
        //Go to my list
        navigationUi.clickMyList();
        // Open Learning programing folder and delete java article,
        // after compare article title in folder and article title in article page

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folderName);
        }

        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        myListsPageObject.clickToArticleInFolder(secondArticleTitle);
        String articleTitleInFolder;
        if (Platform.getInstance().isAndroid()) {
            articleTitleInFolder = articlePageObject.getArticleTitle();
        } else {
            articleTitleInFolder = articlePageObject.getArticleTitle("Java (programming language)");
        }



        assertEquals(articleTitleInFolder, secondArticleTitle);


    }
}
