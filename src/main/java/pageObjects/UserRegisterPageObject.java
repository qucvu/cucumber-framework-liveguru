package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    public UserRegisterPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to `Register` button")
    public UserAccountPageObject clickToRegisterButton() {
        waitForElementClickable(UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(UserRegisterPageUI.REGISTER_BUTTON);
        return new UserAccountPageObject(driver);
    }


    @Step("Verify is `Create An Account` Title is displayed ")
    public boolean isCreateAnAccountTitleDisplayed() {
        waitForElementVisibility(UserRegisterPageUI.CREATE_AN_ACCOUNT_PAGE_TITLE);
        return isElementDisplayed(UserRegisterPageUI.CREATE_AN_ACCOUNT_PAGE_TITLE);
    }
}
