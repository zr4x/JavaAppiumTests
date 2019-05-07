package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class Platform {
    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";
    private final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";


    private static Platform instance;

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);

        if (isAndroid()) {
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if (isIOS()) {
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of driver "+ getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
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

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("app", "/Users/nik/Work/Java/JavaAppiumTests/builds/Wikipedia.app");

        return capabilities;
    }

    private boolean isPlatform(String myPlatform) {
        String platform = getPlatformVar();
        return myPlatform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
