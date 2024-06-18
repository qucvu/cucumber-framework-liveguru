package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserMyWishlistPageUI;

public class UserWishListPageObject extends BasePage {
    public UserWishListPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isAddToWishlistSuccessMessageDisplayedByProductName(String productName) {
        waitForElementVisibility(UserMyWishlistPageUI.ADD_TO_WISHLIST_SUCCESS_MESSAGE, productName);
        return isElementDisplayed(UserMyWishlistPageUI.ADD_TO_WISHLIST_SUCCESS_MESSAGE, productName);
    }

    public void clickToShareWishlistbutton() {
        waitForElementClickable(UserMyWishlistPageUI.SHARE_WISHLIST_BUTTON);
        clickToElement(UserMyWishlistPageUI.SHARE_WISHLIST_BUTTON);

    }

    public void enterToEmailTextarea(String email) {
        waitForElementVisibility(UserMyWishlistPageUI.EMAIL_SHARE_WISHLIST_TEXTAREA);
        sendKeyToElement(UserMyWishlistPageUI.EMAIL_SHARE_WISHLIST_TEXTAREA, email);
    }

    public void enterToMessageTextarea(String message) {
        waitForElementVisibility(UserMyWishlistPageUI.MESSAGE_SHARE_WISHLIST_TEXTAREA);
        sendKeyToElement(UserMyWishlistPageUI.MESSAGE_SHARE_WISHLIST_TEXTAREA, message);

    }

    public boolean isProductNameDisplayedAtWishlistTable(String productName) {
        waitForElementVisibility(UserMyWishlistPageUI.PRODUCT_NAME_AT_WISHLIST_INFO, productName);
        return isElementDisplayed(UserMyWishlistPageUI.PRODUCT_NAME_AT_WISHLIST_INFO, productName);
    }

    public int getCurrentWishlistItem() {
        waitForElementVisibility(UserMyWishlistPageUI.WISHLIST_ITEM_QUANTITY);
        return getElementsSize(UserMyWishlistPageUI.WISHLIST_ITEM_QUANTITY);
    }

    public boolean isShareWishlistSuccessMessageDisplayed() {
        waitForElementVisibility(UserMyWishlistPageUI.SHARE_WISHLIST_SUCCESS_MESSAGE);
        return isElementDisplayed(UserMyWishlistPageUI.SHARE_WISHLIST_SUCCESS_MESSAGE);
    }

    public UserShoppingCartPageObject clickToAddToCartButtonByProductName(String productName) {
        waitForElementClickable(UserMyWishlistPageUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(UserMyWishlistPageUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        return new UserShoppingCartPageObject(driver);
    }

}
