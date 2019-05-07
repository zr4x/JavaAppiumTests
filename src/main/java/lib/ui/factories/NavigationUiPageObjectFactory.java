package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUi;
import lib.ui.android.AndroidNavigationUiPageObject;
import lib.ui.ios.IOSNavigationUiPageObject;

public class NavigationUiPageObjectFactory {

    public static NavigationUi get(AppiumDriver driver) throws Exception {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUiPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUiPageObject(driver);
        } else {
            throw new IllegalArgumentException("Illegal");
        }
    }
}
