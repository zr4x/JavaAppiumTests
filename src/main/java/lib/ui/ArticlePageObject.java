package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "org.wikipedia:id/page_external_link",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "android:id/button1",
            ARTICLE_CLOSE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            FOLDER_NAME_TMPL = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    /*TEMPLATE METHODS */
    private static String getFolderNameByTmpl(String folderName) {
        return FOLDER_NAME_TMPL.replace("{FOLDER_NAME}", folderName);
    }
    /*TEMPLATE METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(ARTICLE_TITLE), "Cannot find title article on screen", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.id(FOOTER_ELEMENT),
                "Cant find footer element",
                20);
    }

    public void addFirstArticleToMyList(String nameOfFolder) {
            waitForElementPresentAndClick(
                    By.xpath(OPTIONS_BUTTON),
                    "Cannot find more options button"
            );

            waitForElementPresentAndClick(
                    By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                    "Cannot find or click to reading list"
            );

            waitForElementPresentAndClick(
                    By.id(ADD_TO_MY_LIST_OVERLAY),
                    "Cant click to GOT IT tip overlay"
            );


            waitForElementAndSendKeys(
                    By.id(MY_LIST_NAME_INPUT),
                    nameOfFolder,
                    "cant input text in artice folder"
            );

            waitForElementPresentAndClick(
                    By.id(MY_LIST_OK_BUTTON),
                    "Cannot press OK button"
            );
    }


    public void addSecondArticleToCreatedFolder(String nameOfFolder) {
        waitForElementPresentAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find or click to reading list"
        );

        choseFolderToSaveArticleToMyList(nameOfFolder);

    }

    public void closeArticle () {
        waitForElementPresentAndClick(
                By.xpath(ARTICLE_CLOSE_BUTTON),
                "Cannot find or press close up button"
        );
    }

    public void choseFolderToSaveArticleToMyList(String folderName) {

        waitForElementPresentAndClick(
                By.xpath(getFolderNameByTmpl(folderName)),
                "cant find folder"
        );
    }

    public void getTitleWithoutWaiting() {
        assertElementPresent(By.id(ARTICLE_TITLE), "Cant find article TITLE");
    }
}
