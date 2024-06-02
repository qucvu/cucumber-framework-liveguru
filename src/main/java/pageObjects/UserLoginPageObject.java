package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    public UserLoginPageObject(WebDriver driver) {
        super(driver);
    }


    public UserRegisterPageObject clickToCreateAnAccountButton() {
        waitForElementClickable(UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return new UserRegisterPageObject(driver);
    }

}
