package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TMPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }

    public iOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
