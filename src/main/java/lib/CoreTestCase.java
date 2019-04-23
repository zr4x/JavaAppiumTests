package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static URL AppiumURL;

    static {
        try {
            AppiumURL = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        DesiredCapabilities capabilities = getDesiredCapabilitesByPlatformEnv();
        driver = startDriverByEnv(AppiumURL, capabilities);
        rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();

        super.tearDown();
    }

    public void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void backgroundApp(Duration seconds) {
        driver.runAppInBackground(seconds);

    }

    private DesiredCapabilities getDesiredCapabilitesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTest");
            capabilities.setCapability("platformVersion", "8.1");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("skipDeviceInitialization", true);
            capabilities.setCapability("skipServerInstallation", true);
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/nik/Work/Java/JavaAppiumTests/builds/org.wikipedia.apk");
            capabilities.setCapability("avd", "Nexus5x");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "12.2");
            capabilities.setCapability("app", "/Users/nik/Work/Java/JavaAppiumTests/builds/Wikipedia.app");
        } else {
            throw new Exception("Cannot find platfrom in env varible. Platform value: " + platform);
        }
        return capabilities;
    }

    private AppiumDriver startDriverByEnv(URL url, DesiredCapabilities capabilities) throws Exception {
        String driver = System.getenv("DRIVER");

        if (driver.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(url, capabilities);
        } else if (driver.equals(PLATFORM_IOS)) {
            return new IOSDriver(url, capabilities);
        } else {
            throw new Exception("Please check correct driver name. You're driver name: "+ driver);
        }
    }
}
