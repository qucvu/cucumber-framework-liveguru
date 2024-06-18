package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminManageReviewPageUI;

public class AdminManageReviewPageObject extends BasePage {
    public AdminManageReviewPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayedByNicknameGridTable(String nickname, String title) {
        waitForElementVisibility(AdminManageReviewPageUI.TITLE_CELL_BY_NICK_NAME, nickname, title);
        return isElementDisplayed(AdminManageReviewPageUI.TITLE_CELL_BY_NICK_NAME, nickname, title);
    }

    public boolean isReviewDisplayedByNicknameGridTable(String nickname, String review) {
        waitForElementVisibility(AdminManageReviewPageUI.REVIEW_DESCRIPTION_CELL_BY_NICK_NAME, nickname, review);
        return isElementDisplayed(AdminManageReviewPageUI.REVIEW_DESCRIPTION_CELL_BY_NICK_NAME, nickname, review);
    }

    public AdminReviewDetailPageObject clickToEditLinkByNickname(String nickname) {
        waitForElementClickable(AdminManageReviewPageUI.EDIT_LINK_BY_NICKNAME, nickname);
        clickToElement(AdminManageReviewPageUI.EDIT_LINK_BY_NICKNAME, nickname);
        return new AdminReviewDetailPageObject(driver);
    }

}
