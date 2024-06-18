package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.UserAccountPageUI;

public class UserAccountPageObject extends BasePage {
    public UserAccountPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Get the current message under Page Title")
    public String getCurrentMessageUnderPageTitle() {
        waitForElementVisibility(UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
        return getElementText(UserAccountPageUI.CURRENT_MESSAGE_UNDER_PAGE_TITLE);
    }

    @Step("Verify the `Welcome` message is displayed at the Dashboard")
    public boolean isWelcomeMessageIsDisplayedAtMyDashboard(String fullName) {
        waitForElementVisibility(UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
        return isElementDisplayed(UserAccountPageUI.WELCOME_HEADER_MESSAGE_BY_FULL_NAME, fullName);
    }

    @Step("Get the current Tab Title")
    public String getCurrentTabTitle() {
        waitForElementVisibility(UserAccountPageUI.CURRENT_TAB_TITLE);
        return getElementText(UserAccountPageUI.CURRENT_TAB_TITLE);
    }

    @Step("Click to `Save` button")
    public void clickToSaveButton() {
        waitForElementClickable(UserAccountPageUI.SAVE_BUTTON);
        clickToElement(UserAccountPageUI.SAVE_BUTTON);
    }


}
