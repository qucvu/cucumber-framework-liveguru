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

}
