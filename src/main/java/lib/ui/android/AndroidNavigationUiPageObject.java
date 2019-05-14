package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUiPageObject extends NavigationUi {

    static {
        MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public AndroidNavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
