package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {
    private final static String
            FOLDER_BY_NAME_TMPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TMPL = "//*[@text='{ARTICLE_TITLE}']",
            ARTICLE_TITLE = "org.wikipedia:id/page_list_item_title";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS */
    private static String getFolderName(String substring) {
        return FOLDER_BY_NAME_TMPL.replace("{FOLDER_NAME}", substring);
    }

    private static String getSavedXpathByTitle(String substring) {
        return ARTICLE_BY_TITLE_TMPL.replace("{ARTICLE_TITLE}", substring);
    }
    /*TEMPLATE METHODS */

    public void openFolderByName(String folderName) {
        waitForElementPresentAndClick(
                By.xpath(getFolderName(folderName)),
                "Cant find folder by name " + folderName
        );


    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        waitForElementPresent(By.xpath(getSavedXpathByTitle(articleTitle)),
                "Article is doesn't here", 5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        waitForElementNotPresent(By.xpath(getSavedXpathByTitle(articleTitle)),
                "Article is still here", 5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        String article = getSavedXpathByTitle(articleTitle);
        swipeElementToLeft(
                By.xpath(article),
                "cant find saved article"
        );
        waitForArticleToDisappearByTitle(article);
    }


    public String getArticleTitle() {
        return waitForElementAndGetAttribute(By.id(ARTICLE_TITLE), "text","Cant find any article in folder",5);
    }

    public void clickToArticleInFolder(String articleTitle) {
        String article = getSavedXpathByTitle(articleTitle);
        waitForElementPresentAndClick(By.xpath(article), "Cant Find Article");
    }

}
