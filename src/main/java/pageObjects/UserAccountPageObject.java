package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserAccountPageUI;

public class UserAccountPageObject extends BasePage {
    public UserAccountPageObject(WebDriver driver) {
        super(driver);
    }

    public String getCurrentMessageUnderPageTitle() {
        waitForElementVisibility(UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
        return getElementText(UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
    }


    public boolean isWelcomeMessageIsDisplayedAtMyDashboard(String fullName) {
        waitForElementVisibility(UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
        return isElementDisplayed(UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
    }

    public String getCurrentTabTitle() {
        waitForElementVisibility(UserAccountPageUI.CURRENT_TAB_TITLE);
        return getElementText(UserAccountPageUI.CURRENT_TAB_TITLE);
    }

    public void clickToSaveButton() {
        waitForElementClickable(UserAccountPageUI.SAVE_BUTTON);
        clickToElement(UserAccountPageUI.SAVE_BUTTON);
    }


}
