package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private final static String
            LEARN_MORE_BUTTON = "Learn more about Wikipedia",
            NEXT_BUTTON = "Next",
            NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
            PREFER_LANG_BUTTON = "Add or edit preferred languages",
            LEARN_MORE_ABOUT_DATA_COLLECTED_BTN = "Learn more about data collected",
            GET_STARTED_BUTTON = "Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        waitForElementPresent(By.id(LEARN_MORE_BUTTON), "cant find Learn More link on screen");
    }

    public void waitForPreferLangText() {
        waitForElementPresent(By.id(PREFER_LANG_BUTTON), "cant find 'Add or edit preferred languages' on screen");
    }

    public void waitForNewWaysToExploreText() {
        waitForElementPresent(By.id(NEW_WAYS_TO_EXPLORE_TEXT), "cant find 'New More ways to explore text' on screen");
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        waitForElementPresent(By.id(LEARN_MORE_ABOUT_DATA_COLLECTED_BTN), "cant find 'Learn more about data collected' on screen");
    }

    public void clickNextButton() {
        waitForElementPresentAndClick(By.id(NEXT_BUTTON), "cant find Next link on screen");
    }
    public void clickGetStartedButton() {
        waitForElementPresentAndClick(By.id(GET_STARTED_BUTTON), "cant find 'Get started' button");
    }


}
