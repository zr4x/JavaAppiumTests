package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject {

    private final static String
            MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        waitForElementPresentAndClick(
                MY_LIST_LINK,
                "Cannot find or press My list btn"
        );
    }
}
