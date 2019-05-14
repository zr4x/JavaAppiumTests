package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUiPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String folderName = "Learning programing";
    private static final String login = "Testqatest";
    private static final String password = "12345678Q";

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
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.enterLogInData(login, password);
            authorizationPageObject.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals(
                    "We back not to the same page after login.",
                    articleTitle,
                    articlePageObject.getArticleTitle()
            );

            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeSyncArticlesPopup();
        }

        articlePageObject.closeArticle();
        navigationUi.openNavigation();
        navigationUi.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folderName);
        }

        myListsPageObject.swipeByArticleToDelete(articleTitle);


    }

    //Refactoring HomeWork 5 +
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
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement();
        } else {
            articlePageObject.waitForTitleElement("Java (programming language)");
        }

        if (Platform.getInstance().isAndroid()) {
            firstArticleTitle = articlePageObject.getArticleTitle();
        } else if (Platform.getInstance().isIOS()) {
            firstArticleTitle = articlePageObject.getArticleTitle("Java (programming language)");
        } else {
            firstArticleTitle = articlePageObject.getArticleTitle();
        }


        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addFirstArticleToMyList(folderName);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject authorizationPageObject = new AuthorizationPageObject(driver);
            authorizationPageObject.clickAuthButton();
            authorizationPageObject.enterLogInData(login, password);
            authorizationPageObject.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals(
                    "We back not to the same page after login.",
                    firstArticleTitle,
                    articlePageObject.getArticleTitle()
            );

            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isIOS()) {
            articlePageObject.closeSyncArticlesPopup();
        }
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            articlePageObject.closeArticle();
        }else {
            navigationUi.openNavigation();
            navigationUi.goToHome();
        }

        //Search and add second article to created list
        searchPageObject.initSearchInput();

        if (Platform.getInstance().isIOS()) {
            searchPageObject.clearSearchFieldMethodForIOS();
        }
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Roman politician");

        if (Platform.getInstance().isAndroid()) {
            secondArticleTitle = articlePageObject.getArticleTitle();
        } else if(Platform.getInstance().isIOS()) {
            secondArticleTitle = articlePageObject.getArticleTitle("Appium");
        } else {
            secondArticleTitle = articlePageObject.getArticleTitle();
        }

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addSecondArticleToCreatedFolder(folderName);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();
        navigationUi.openNavigation();
        navigationUi.clickMyLists();

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(folderName);
        }

        myListsPageObject.swipeByArticleToDelete(firstArticleTitle);
        myListsPageObject.clickToArticleInFolder(secondArticleTitle);

        String articleTitleInFolder;
        if (Platform.getInstance().isAndroid()) {
            articleTitleInFolder = articlePageObject.getArticleTitle();
        } else if (Platform.getInstance().isIOS()) {
            articleTitleInFolder = articlePageObject.getArticleTitle("Java (programming language)");
        } else {
            articleTitleInFolder = articlePageObject.getArticleTitle();
        }

        System.out.println(articleTitleInFolder);
        System.out.println(secondArticleTitle);
        assertEquals(articleTitleInFolder, secondArticleTitle);


    }
}
