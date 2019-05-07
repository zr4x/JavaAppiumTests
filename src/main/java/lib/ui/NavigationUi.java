package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUi extends MainPageObject {

    protected static String
            MY_LIST_LINK;

    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        waitForElementPresentAndClick(
                MY_LIST_LINK,
                "Cannot find or press My list btn"+ MY_LIST_LINK
        );
    }
}
