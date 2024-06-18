package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserProductDetailPageUI;

public class UserProductDetailPageObject extends BasePage {
    public UserProductDetailPageObject(WebDriver driver) {
        super(driver);
    }


    public float getProductCostByProductName() {
        waitForElementVisibility(UserProductDetailPageUI.CURRENT_REGULAR_PRODUCT_PRICE);
        String productPriceText = getElementText(UserProductDetailPageUI.CURRENT_REGULAR_PRODUCT_PRICE);
        return Float.parseFloat(productPriceText.substring(1));
    }


    public UserShoppingCartPageObject clickToAddToCartButton() {
        waitForElementClickable(UserProductDetailPageUI.ADD_TO_CART_BUTTON);
        clickToElement(UserProductDetailPageUI.ADD_TO_CART_BUTTON);
        return new UserShoppingCartPageObject(driver);
    }

    public UserProductReviewPageObject clickToAddYourReviewLink() {
        waitForElementClickable(UserProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
        clickToElement(UserProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
        return new UserProductReviewPageObject(driver);
    }

    public void clickToQuantityReviewsLink() {
        waitForElementClickable(UserProductDetailPageUI.QUANTITY_REVIEWS_LINK);
        clickToElement(UserProductDetailPageUI.QUANTITY_REVIEWS_LINK);
    }

    public boolean isSummaryReviewDisplayedByNickname(String nickname, String summaryReview) {
        waitForElementVisibility(UserProductDetailPageUI.SUMMARY_REVIEW_BY_NICKNAME, nickname, summaryReview);
        return isElementDisplayed(UserProductDetailPageUI.SUMMARY_REVIEW_BY_NICKNAME, nickname, summaryReview);
    }

    public boolean isThoughtReviewDisplayedByNickname(String nickname, String thoughtReview) {
        waitForElementVisibility(UserProductDetailPageUI.THOUGHT_REVIEW_BY_NICKNAME, nickname, thoughtReview);
        return isElementDisplayed(UserProductDetailPageUI.THOUGHT_REVIEW_BY_NICKNAME, nickname, thoughtReview);
    }

    public boolean isRatingReviewDisplayedByNickname(String nickname, String qualityRateReview) {
        waitForElementVisibility(UserProductDetailPageUI.QUANTITY_RATE_VIEW_BY_NICKNAME, nickname);
        String styleAttribute = getElementAttribute("style", UserProductDetailPageUI.QUANTITY_RATE_VIEW_BY_NICKNAME, nickname);
        int quantityRate = Integer.parseInt(styleAttribute.substring(7, styleAttribute.indexOf(";") - 1)) / 20;
        return String.valueOf(quantityRate).equals(qualityRateReview);
    }


}
