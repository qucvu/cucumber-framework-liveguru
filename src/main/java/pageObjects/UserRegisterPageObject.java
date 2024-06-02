package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    public UserRegisterPageObject(WebDriver driver) {
        super(driver);
    }

    public UserAccountPageObject clickToRegisterButton() {
        waitForElementClickable(UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(UserRegisterPageUI.REGISTER_BUTTON);
        return new UserAccountPageObject(driver);
    }


}
