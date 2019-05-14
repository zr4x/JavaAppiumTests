package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUiPageObject;
import lib.ui.ios.IOSNavigationUiPageObject;
import lib.ui.mobile_web.MWNavigationUiPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUiPageObjectFactory {

    public static NavigationUi get(RemoteWebDriver driver) throws Exception {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUiPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUiPageObject(driver);
        } else {
            return new MWNavigationUiPageObject(driver);
        }
    }
}
