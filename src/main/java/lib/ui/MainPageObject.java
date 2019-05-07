package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementPresentAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementPresentAndClick(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        element.click();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage) {
        return waitForElementAndSendKeys(locator, value, errorMessage, 5);
    }

    public void checkAvailableElements(String locator, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
        System.out.println(elements.size());
        if (elements.size() < 1) {
            throw new AssertionError("Zero serch result on screen");
        }
    }

    public void checkElementsText(String locator, String value, String errorMessage, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
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

    public void swipeUp(long timeOfSwipe) {

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

    public void swipeUpQuick() {
        swipeUp(200);

    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = getLocatorByString(locator);

        int alreadySwiped = 0;

        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cant find elemnt after swiping up\n" + errorMessage, 0);
                return;
            }

            swipeUpQuick();
            ++alreadySwiped;
        }

    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;

        while (!isElementLocatiedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(errorMessage, isElementLocatiedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public boolean isElementLocatiedOnTheScreen(String locator) {
        int elementLocationByY = waitForElementPresent(locator, "Cannot find element by locator").getLocation().getY();
        int screenSizeByY = driver.manage().window().getSize().getHeight();

        return elementLocationByY < screenSizeByY;
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        TouchAction action = new TouchAction(driver);
        WebElement element = waitForElementPresent(locator, errorMessage);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        action.press(PointOption.point(rightX, middleY));
        action.waitAction(WaitOptions.waitOptions(Duration.ofNanos(600)));
        if (Platform.getInstance().isAndroid()) {
            action.moveTo(PointOption.point(leftX, middleY));
        } else {
            int offsetX = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offsetX, 0));
            System.out.println("I'M DONE " + element.getSize() + " " + locator);
        }
        action.release();
        action.perform();
    }

    public void clickElementToTheRightUpperCorner(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator + "/..", errorMessage);
        int rightX = element.getLocation().getX();
        int upperY = element.getLocation().getY();
        int lowerY = upperY * element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        int width = element.getSize().getWidth();

        int pointToClickX = (rightX + width) - 3;
        int pointToClickY = middleY;

        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
    }

    public int getAmountOfElements(String locator) {
        By by = getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementPresent(String locator, String errorMessage) {
        By by = getLocatorByString(locator);
        int amountOfElements = getAmountOfElements(locator);

        if (amountOfElements <= 0) {
            throw new AssertionError("Elements dosent present on screen " + errorMessage + " " + by.toString());
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeOfWait) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOfWait);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);

        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator: " + locatorWithType);
        }


    }
}
