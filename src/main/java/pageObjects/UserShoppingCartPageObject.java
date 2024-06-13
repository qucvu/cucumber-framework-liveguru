package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
    public UserShoppingCartPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isAddToCartSuccessMessageDisplayed(String productName) {
        waitForElementVisibility(UserShoppingCartPageUI.ADD_TO_CART_SUCCESS_MESSAGE_BY_PRODUCT_NAME, productName);
        return isElementDisplayed(UserShoppingCartPageUI.ADD_TO_CART_SUCCESS_MESSAGE_BY_PRODUCT_NAME, productName);
    }

    public void clickToApplyDiscountButton() {
        waitForElementClickable(UserShoppingCartPageUI.APPLY_DISCOUNT_BUTTON);
        clickToElement(UserShoppingCartPageUI.APPLY_DISCOUNT_BUTTON);
    }

    public void clickToCancelDiscountButton() {
        waitForElementClickable(UserShoppingCartPageUI.CANCEL_DISCOUNT_BUTTON);
        clickToElement(UserShoppingCartPageUI.CANCEL_DISCOUNT_BUTTON);

    }


    public boolean isDiscountPriceDisplayed(float discountPrice) {
        String discountPriceText = String.format("%.02f", discountPrice);
        waitForElementVisibility(UserShoppingCartPageUI.DISCOUNT_PRICE_AT_TOTAL_TABLE, discountPriceText);
        return isElementDisplayed(UserShoppingCartPageUI.DISCOUNT_PRICE_AT_TOTAL_TABLE, discountPriceText);
    }


    public float getGrandTotalPrice() {
        waitForElementVisibility(UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        String totalPriceText = getElementText(UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        return Float.parseFloat(totalPriceText.substring(1));
    }

    public void enterToQuantityTextboxByProductName(String quanityValue, String productName) {
        waitForElementVisibility(UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_NAME, productName);
        sendKeyToElement(UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_NAME, quanityValue, productName);
    }

    public void clickToUpdateButtonByProductName(String productName) {
        waitForElementClickable(UserShoppingCartPageUI.UPDATE_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(UserShoppingCartPageUI.UPDATE_BUTTON_BY_PRODUCT_NAME, productName);
    }

    public boolean isMessageDisplayedUnderShoppingCartTitle(String message) {
        waitForElementVisibility(UserShoppingCartPageUI.MESSAGE_UNDER_PAGE_TITLE, message);
        return isElementDisplayed(UserShoppingCartPageUI.MESSAGE_UNDER_PAGE_TITLE, message);
    }

    public boolean isMaximumQuantityErrorMessageDisplayedByProductName(String productName) {
        waitForElementVisibility(UserShoppingCartPageUI.MAXIMUM_QUANTITY_MESSAGE_BY_PRODUCT_NAME, productName);
        return isElementDisplayed(UserShoppingCartPageUI.MAXIMUM_QUANTITY_MESSAGE_BY_PRODUCT_NAME, productName);
    }

    public void clickToEmptyCartButton() {
        waitForElementClickable(UserShoppingCartPageUI.EMPTY_CART_BUTTON);
        clickToElement(UserShoppingCartPageUI.EMPTY_CART_BUTTON);

    }

    public boolean isShoppingCartEmptyTitleDisplayed() {
        waitForElementVisibility(UserShoppingCartPageUI.SHOPPING_CART_EMPTY_TITLE);
        return isElementDisplayed(UserShoppingCartPageUI.SHOPPING_CART_EMPTY_TITLE);
    }

    public boolean isShoppingCartEmptyDescriptionDisplayed() {
        waitForElementVisibility(UserShoppingCartPageUI.SHOPPING_CART_EMPTY_DESCRIPTION);
        return isElementDisplayed(UserShoppingCartPageUI.SHOPPING_CART_EMPTY_DESCRIPTION);

    }

    public void clickToEstimateButton() {
        waitForElementClickable(UserShoppingCartPageUI.ESTIMATE_BUTTON);
        clickToElement(UserShoppingCartPageUI.ESTIMATE_BUTTON);
    }

    public String getShippingCostEstimation() {
        waitForElementVisibility(UserShoppingCartPageUI.SHIPPING_COST_ESTIMATION);
        return getElementText(UserShoppingCartPageUI.SHIPPING_COST_ESTIMATION);
    }

    public void checkToFixedShippingCostRadio() {
        waitForElementClickable(UserShoppingCartPageUI.FIXED_SHIPPING_COST_RADIO);
        checkToDefaultCheckboxRadio(UserShoppingCartPageUI.FIXED_SHIPPING_COST_RADIO);
    }

    public void clickToUpdateTotalButton() {
        waitForElementClickable(UserShoppingCartPageUI.UPDATE_TOTAL_BUTTON);
        clickToElement(UserShoppingCartPageUI.UPDATE_TOTAL_BUTTON);

    }

    public boolean isFixedShippingCostDisplayedAtCartTotal(String shippingCost) {
        waitForElementVisibility(UserShoppingCartPageUI.SHIPPING_COST_AT_CART_TOTAL_DISPLAY, shippingCost);
        return isElementDisplayed(UserShoppingCartPageUI.SHIPPING_COST_AT_CART_TOTAL_DISPLAY, shippingCost);
    }

    public String getGrandTotalCost() {
        waitForElementVisibility(UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
        return getElementText(UserShoppingCartPageUI.GRAND_TOTAL_PRICE);
    }

    public UserCheckoutPageObject clickToProceedToCheckoutButton() {
        waitForElementClickable(UserShoppingCartPageUI.PROCEED_TO_CHECKOUT_BUTTON);
        clickToElement(UserShoppingCartPageUI.PROCEED_TO_CHECKOUT_BUTTON);

        return new UserCheckoutPageObject(driver);
    }


}
