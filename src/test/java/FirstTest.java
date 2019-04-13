import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
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

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //HomeWork8
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    public void tearDown() {
        driver.quit();
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

        assertTrue("Search failed. 0 elements",
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

    @Test
    public void testFooterButton() {

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        String text = waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        ).getText();

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + text + "']"),
                "cant find appium"
        );

        swipeUpToFindElement(
                By.id("org.wikipedia:id/page_external_link"),
                "no element", 20
        );


    }

    @Test
    public void saveArticleToMyList() {
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "cant find 'Search Wikipedia' input"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find or click to reading list"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cant click to GOT IT tip overlay"
        );

        String folderName = "Learning programing";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folderName,
                "cant input text in artice folder"
        );

        waitForElementPresentAndClick(
                By.id("android:id/button1"),
                "Cannot press OK button"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find or press close up button"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find or press My listst btn"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']"),
                "cant find folder with article"
        );

        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "cant find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "cant find saved article",
                1
        );
    }

    //HomeWork 5
    @Test
    public void addTwoArticlesInFolderTest() {
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "cant find 'Search Wikipedia' input"
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find or click to reading list"
        );

        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cant click to GOT IT tip overlay"
        );

        String folderName = "Learning programing";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                folderName,
                "cant input text in artice folder"
        );

        waitForElementPresentAndClick(
                By.id("android:id/button1"),
                "Cannot press OK button"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find or press close up button"
        );
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "cant find 'Search Wikipedia' input"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find more options button"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find or click to reading list"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']"),
                "cant find folder"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find or press close up button"
        );

        waitForElementPresentAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find or press My listst btn"
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + folderName + "']"),
                "cant find folder with article"
        );

        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "cant find saved article for delete"
        );

        WebElement title = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "cant find saved article"
        );
        String titleInFolder = title.getText();
        title.click();

        String articleTitle = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cant find article title", 5).getText();

        assertEquals("Titles text not equal", titleInFolder, articleTitle);

    }

    //HomeWork 6
    @Test
    public void findTitleInArticle() {
        waitForElementPresentAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input in title screen"
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresentAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
                        "//*[@text='Object-oriented programming language']"),
                "cant find 'Search Wikipedia' input"
        );
        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cant find article title"
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
        element.clear();
        element.sendKeys(value);
//        driver.hideKeyboard();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage) {
        return waitForElementAndSendKeys(by, value, errorMessage, 5);
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
            String text = element.getText().toLowerCase();
            assertTrue(text.contains(value.toLowerCase()));
        }
    }

    protected void swipeUp(long timeOfSwipe) {

        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);


        action
                .press(PointOption.point(x, startY))
                .waitAction(
                        WaitOptions.waitOptions(Duration.ofNanos(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();

    }

    protected void swipeUpQuick() {
        swipeUp(200);

    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {

        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cant find elemnt after swiping up\n" + errorMessage, 0);
                return;
            }

            swipeUpQuick();
            ++alreadySwiped;
        }

    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        TouchAction action = new TouchAction(driver);
        WebElement element = waitForElementPresent(by, errorMessage);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action
                .press(
                        PointOption.point(rightX, middleY))
                .waitAction(
                        WaitOptions.waitOptions(
                                Duration.ofNanos(150)))
                .moveTo(
                        PointOption.point(leftX, middleY))
                .release()
                .perform();
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(By by, String errorMessage) {
        int amountOfElements = getAmountOfElements(by);

        if (amountOfElements <= 0) {
            throw new AssertionError("Elements dosent present on screen " + errorMessage + " " + by.toString());
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeOfWait) {
        WebElement element = waitForElementPresent(by, errorMessage, timeOfWait);
        return element.getAttribute(attribute);
    }
}
