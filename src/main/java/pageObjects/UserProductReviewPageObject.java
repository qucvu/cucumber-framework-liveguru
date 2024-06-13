package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
    public UserProductReviewPageObject(WebDriver driver) {
        super(driver);
    }


    public void enterToThoughtTextareaEmptyValue() {
        waitForElementVisibility(UserProductReviewPageUI.THOUGHT_TEXTAREA);
        clearElement(UserProductReviewPageUI.THOUGHT_TEXTAREA);
    }

    public void clickToSubmitReviewButton() {
        waitForElementClickable(UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(UserProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
    }

    public String getErrorMessageUnderQualityRateRadio() {
        waitForElementVisibility(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_QUALITY_RADIO);
        return getElementText(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_QUALITY_RADIO);
    }

    public String getErrorMessageUnderThoughtTextarea() {
        waitForElementVisibility(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_THOUGHT_TEXTAREA);
        return getElementText(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_THOUGHT_TEXTAREA);
    }

    public String getErrorMessageUnderSummaryTextbox() {
        waitForElementVisibility(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_SUMMARY_TEXTBOX);
        return getElementText(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_SUMMARY_TEXTBOX);
    }

    public String getErrorMessageUnderNicknameTextbox() {
        waitForElementVisibility(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_NICKNAME_TEXTBOX);
        return getElementText(UserProductReviewPageUI.ERROR_MESSAGE_UNDER_NICKNAME_TEXTBOX);
    }

    public void enterToThoughtTextarea(String thoughtReview) {
        waitForElementVisibility(UserProductReviewPageUI.THOUGHT_TEXTAREA);
        sendKeyToElement(UserProductReviewPageUI.THOUGHT_TEXTAREA, thoughtReview);
    }

    public void checkToQualityRadio(String qualityRateReview) {
        waitForElementClickable(UserProductReviewPageUI.QUALITY_RADIO_BY_RATE_NUMBER, qualityRateReview);
        checkToDefaultCheckboxRadio(UserProductReviewPageUI.QUALITY_RADIO_BY_RATE_NUMBER, qualityRateReview);
    }

    public boolean isMessageUnderBreadcrumbDisplayed(String message) {
        waitForElementVisibility(UserProductReviewPageUI.MESSAGE_UNDER_BREADCRUMB, message);
        return isElementDisplayed(UserProductReviewPageUI.MESSAGE_UNDER_BREADCRUMB, message);
    }

}
