package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUi;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUiPageObject extends NavigationUi {


    static {
        MY_LIST_LINK = "id:Saved";
    }

    public IOSNavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}


