package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserProductComparisonPageUI;

public class UserProductComparisonPageObject extends BasePage {
    public UserProductComparisonPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleCompareProductDisplayed() {
        waitForElementVisibility(UserProductComparisonPageUI.COMPARE_PRODUCT_PAGE_TITLE);
        return isElementDisplayed(UserProductComparisonPageUI.COMPARE_PRODUCT_PAGE_TITLE);
    }

    public boolean isProductCompareNameDisplayed(String productName) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_NAME_COMPARISON, productName);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_NAME_COMPARISON, productName);
    }

    public boolean isProductComparePriceDisplayed(String productName, String productPrice) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_PRICE_COMPARISON_BY_PRODUCT_NAME, productName, productPrice);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_PRICE_COMPARISON_BY_PRODUCT_NAME, productName, productPrice);

    }

    public boolean isProductCompareImageDisplayed(String productName, String productImageLink) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_IMAGE_COMPARISON_BY_PRODUCT_NAME, productName, productImageLink);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_IMAGE_COMPARISON_BY_PRODUCT_NAME, productName, productImageLink);
    }

    public boolean isProductCompareSKUDisplayed(String productName, String productSKU) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_SKU_COMPARISON_BY_PRODUCT_NAME, productName, productSKU);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_SKU_COMPARISON_BY_PRODUCT_NAME, productName, productSKU);
    }

}
