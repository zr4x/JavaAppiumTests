package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private final static String
            LEARN_MORE_BUTTON = "id:Learn more about Wikipedia",
            NEXT_BUTTON = "id:Next",
            NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            PREFER_LANG_BUTTON = "id:Add or edit preferred languages",
            LEARN_MORE_ABOUT_DATA_COLLECTED_BTN = "id:Learn more about data collected",
            GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        waitForElementPresent(LEARN_MORE_BUTTON, "cant find Learn More link on screen");
    }

    public void waitForPreferLangText() {
        waitForElementPresent(PREFER_LANG_BUTTON, "cant find 'Add or edit preferred languages' on screen");
    }

    public void waitForNewWaysToExploreText() {
        waitForElementPresent(NEW_WAYS_TO_EXPLORE_TEXT, "cant find 'New More ways to explore text' on screen");
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        waitForElementPresent(LEARN_MORE_ABOUT_DATA_COLLECTED_BTN, "cant find 'Learn more about data collected' on screen");
    }

    public void clickNextButton() {
        waitForElementPresentAndClick(NEXT_BUTTON, "cant find Next link on screen");
    }
    public void clickGetStartedButton() {
        waitForElementPresentAndClick(GET_STARTED_BUTTON, "cant find 'Get started' button");
    }


}
