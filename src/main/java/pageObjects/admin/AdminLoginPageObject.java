package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    public AdminLoginPageObject(WebDriver driver) {
        super(driver);
    }

    public AdminManageCustomerPageObject clickToLoginButton() {
        waitForElementClickable(AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(AdminLoginPageUI.LOGIN_BUTTON);
        return new AdminManageCustomerPageObject(driver);
    }

}
