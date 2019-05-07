package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;

public class IOSNavigationUiPageObject extends NavigationUi {


    static {
        MY_LIST_LINK = "id:Saved";
    }

    public IOSNavigationUiPageObject(AppiumDriver driver) {
        super(driver);
    }
}


