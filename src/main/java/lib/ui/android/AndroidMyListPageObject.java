package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListsPageObject {

    static {
            FOLDER_BY_NAME_TMPL = "xpath://*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TMPL = "xpath://*[@text='{TITLE}']";
            ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
