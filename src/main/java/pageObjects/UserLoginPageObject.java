package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    public UserLoginPageObject(WebDriver driver) {
        super(driver);
    }


    @Step("Click to `Create an Account` button")
    public UserRegisterPageObject clickToCreateAnAccountButton() {
        waitForElementClickable(UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return new UserRegisterPageObject(driver);
    }

    @Step("Click to Login button")
    public UserAccountPageObject clickToLoginButton() {
        waitForElementClickable(UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(UserLoginPageUI.LOGIN_BUTTON);
        return new UserAccountPageObject(driver);
    }

    @Step("Get the error message under Page Title")
    public String getErrorMessageUnderPageTitle() {
        waitForElementVisibility(UserLoginPageUI.ERROR_MESSAGE_UNDER_PAGE_TITLE);
        return getElementText(UserLoginPageUI.ERROR_MESSAGE_UNDER_PAGE_TITLE);
    }


    @Step("Verify the Login Page Title `LOGIN OR CREATE AN ACCOUNT` is displayed ")
    public Boolean isLoginPageTitleDisplayed() {
        waitForElementVisibility(UserLoginPageUI.LOGIN_PAGE_TITLE);
        return isElementDisplayed(UserLoginPageUI.LOGIN_PAGE_TITLE);
    }
}
