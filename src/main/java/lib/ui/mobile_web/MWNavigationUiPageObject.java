package lib.ui.mobile_web;

import lib.ui.NavigationUi;
import lib.ui.factories.NavigationUiPageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUiPageObject extends NavigationUi {
    static {
        MY_LIST_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        HOME_BUTTON = "css:a[data-event-name='home']";
    }

    public MWNavigationUiPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}


