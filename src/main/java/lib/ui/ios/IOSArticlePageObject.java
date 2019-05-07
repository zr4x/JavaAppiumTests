package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
//        TITLE = "id:Java (programming language)";
        TITLE = "id:{TITLE}";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSER_ARTICLE_BUTTON = "id:Back";
        SYNC_ARTICLES_POPUP_CLOSE_BUTTON = "id:places auth close";
    }


    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
