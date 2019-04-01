import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTest");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("skipDeviceInitialization", true);
        capabilities.setCapability("skipServerInstallation", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/nik/Work/Java/JavaAppiumTests/builds/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.closeApp();
    }

    //HomeWork 1
    @Test
    public void searchTest() {
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Test",
                "Cannot find search input",
                5
        );
    }

    //HomeWork 2
    @Test
    public void cancelSearchTest() {
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Test",
                "Cannot find search input",
                5
        );

        Assert.assertTrue("Search failed. 0 elements",
                checkAvailableElements(
                        By.id("org.wikipedia:id/page_list_item_container"),
                        "Zero elements after search",
                        10
                ));

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button"
        );


        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Cannot Find empty img"
        );
    }

    //HomeWork 3
    @Test
    public void correctSearchTest() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        String text = waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Test",
                "Cannot find search input",
                5
        ).getText();

        checkElementsText(
                By.id("org.wikipedia:id/page_list_item_title"),
                text,
                "Cannot find title in search. Maybe search results is empty?",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementPresentAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresentAndClick(By by, String errorMessage) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean checkAvailableElements(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
        return elements.size() >= 1;
    }

    private void checkElementsText(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
        for (WebElement element : elements) {
            String text = element.getText();
            Assert.assertTrue(text.toLowerCase().contains(value.toLowerCase()));
        }
    }
}
