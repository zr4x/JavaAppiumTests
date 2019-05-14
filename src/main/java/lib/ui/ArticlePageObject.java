package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_NAME_TMPL,
            SYNC_ARTICLES_POPUP_CLOSE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }


    /*TEMPLATE METHODS */
    private static String getFolderNameByTmpl(String folderName) {
        return FOLDER_NAME_TMPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getTitleByTmpl(String titleName) {
        return TITLE.replace("{TITLE}", titleName);
    }
    /*TEMPLATE METHODS */

    public WebElement waitForTitleElement() {
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementPresent(TITLE, "Cannot find title article on screen " + TITLE, 15);
        } else {
            return this.waitForElementPresent(TITLE, "Cannot find title article on screen", 15);
        }
    }

    public WebElement waitForTitleElement(String titleName) {
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementPresent(TITLE, "Cannot find title article on screen", 15);
        } else {
            return this.waitForElementPresent(getTitleByTmpl(titleName), "Cannot find title article on screen", 15);
        }
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    public String getArticleTitle(String title) {
        WebElement titleElement = waitForTitleElement(title);
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public void swipeToFooter() {

        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cant find footer element",
                    40);

        } else if (Platform.getInstance().isIOS()) {
            swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cant find footer element",
                    40);
        } else {
            scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "Cant find footer element",
                    40);
        }
    }

    public void addFirstArticleToMyList(String nameOfFolder) {

        waitForElementPresentAndClick(
                OPTIONS_BUTTON,
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find or click to reading list"
        );

        waitForElementPresentAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cant click to GOT IT tip overlay"
        );


        waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                nameOfFolder,
                "cant input text in artice folder"
        );

        waitForElementPresentAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button"
        );
    }


    public void addSecondArticleToCreatedFolder(String nameOfFolder) {
        waitForElementPresentAndClick(
                OPTIONS_BUTTON,
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find or click to reading list"
        );

        choseFolderToSaveArticleToMyList(nameOfFolder);

    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementPresentAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTION_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementPresentAndClick(
                    OPTION_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before."
            );
        }
    }


    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        waitForElementPresentAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cant find my article sva button");
    }

    public void choseFolderToSaveArticleToMyList(String folderName) {

        waitForElementPresentAndClick(
                getFolderNameByTmpl(folderName),
                "cant find folder"
        );
    }

    public void getTitleWithoutWaiting() {
        assertElementPresent(TITLE, "Cant find article TITLE");
    }

    public String getTitleText() {
        return waitForElementPresent(
                getTitleByTmpl(TITLE),
                "Cant find article title " + TITLE)
                .getAttribute("name");

    }

    public void closeSyncArticlesPopup() {
        waitForElementPresentAndClick(SYNC_ARTICLES_POPUP_CLOSE_BUTTON,
                "Cant find articles close button with locator: " + SYNC_ARTICLES_POPUP_CLOSE_BUTTON);
    }
}
