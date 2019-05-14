package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        rotateScreenPortrait();
        skipWelcomePageForIOSApp();
        openWikiPageForMobileWeb();
    }

//    @Override
//    protected void tearDown() throws Exception {
//        driver.quit();
//        super.tearDown();
//    }

    public void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method: rotateScreenPortrait() did nothing for Mobile Web " + Platform.getInstance().getPlatformVar());
        }
    }

    public void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method: rotateScreenLandscape() did nothing for Mobile Web " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method: openWikiPageForMobileWeb() support only in mobile web testing " + Platform.getInstance().getPlatformVar());
        }
    }

    public void backgroundApp(Duration seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method: backgroundApp() did nothing for Mobile Web " + Platform.getInstance().getPlatformVar());
        }

    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS() && driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }
}
