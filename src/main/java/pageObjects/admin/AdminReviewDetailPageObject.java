package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminReviewDetailPageUI;

public class AdminReviewDetailPageObject extends BasePage {
    public AdminReviewDetailPageObject(WebDriver driver) {
        super(driver);
    }

    public AdminManageReviewPageObject clickToSaveReviewButton() {
        waitForElementClickable(AdminReviewDetailPageUI.SAVE_REVIEW_BUTTON);
        clickToElement(AdminReviewDetailPageUI.SAVE_REVIEW_BUTTON);
        return new AdminManageReviewPageObject(driver);
    }

}
