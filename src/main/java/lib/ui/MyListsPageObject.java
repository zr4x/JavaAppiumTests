package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class MyListsPageObject extends MainPageObject {
    protected static String
            FOLDER_BY_NAME_TMPL,
            ARTICLE_BY_TITLE_TMPL,
            ARTICLE_TITLE,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS */
    private static String getFolderName(String substring) {
        return FOLDER_BY_NAME_TMPL.replace("{FOLDER_NAME}", substring);
    }

    private static String getSavedXpathByTitle(String substring) {
        return ARTICLE_BY_TITLE_TMPL.replace("{TITLE}", substring);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /*TEMPLATE METHODS */

    public void openFolderByName(String folderName) {
        waitForElementPresentAndClick(
                getFolderName(folderName),
                "Cant find folder by name " + folderName
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        waitForElementPresent(getSavedXpathByTitle(articleTitle),
                "Article is doesn't here", 5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        waitForElementNotPresent(getSavedXpathByTitle(articleTitle),
                "Article is still here", 5);
    }

    public void swipeByArticleToDelete(String articleTitle) throws InterruptedException {
        String article = getSavedXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            swipeElementToLeft(
                    article,
                    "cant find saved article"
            );
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementPresentAndClick(
                    removeLocator,
                    "Cannot click button to remove article from saved.",
                    10
            );
        }

        if (Platform.getInstance().isIOS()) {
            clickElementToTheRightUpperCorner(article, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            Thread.sleep(500);
            driver.navigate().refresh();

        }

        waitForArticleToDisappearByTitle(article);
    }


    public String getArticleTitle() {
        return waitForElementAndGetAttribute(ARTICLE_TITLE, "text", "Cant find any article in folder", 5);
    }

    public void clickToArticleInFolder(String articleTitle) {
        String article = getSavedXpathByTitle(articleTitle);
        waitForElementPresentAndClick(article, "Cant Find Article");
    }

}
