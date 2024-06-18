package pageObjects.user;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.UserProductCategoryPageUI;
import utilities.ClassHelper;

import java.util.ArrayList;
import java.util.List;

public class UserProductCategoryPageObject extends BasePage {
    public UserProductCategoryPageObject(WebDriver driver) {
        super(driver);
    }

    public float getProductCostByProductName(String productName) {
        waitForElementVisibility(UserProductCategoryPageUI.PRICE_BY_PRODUCT_NAME, productName);
        String productPriceText = getElementText(UserProductCategoryPageUI.PRICE_BY_PRODUCT_NAME, productName);
        return Float.parseFloat(productPriceText.substring(1));
    }

    public UserProductDetailPageObject openProductDetailPageByProductName(String productName) {
        waitForElementClickable(UserProductCategoryPageUI.PRODUCT_NAME_LINK_IN_PRODUCT_INFO, productName);
        clickToElement(UserProductCategoryPageUI.PRODUCT_NAME_LINK_IN_PRODUCT_INFO, productName);
        return new UserProductDetailPageObject(driver);
    }

    public void clickToAddToCompareLinkByProductName(String productName) {
        waitForElementClickable(UserProductCategoryPageUI.ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
        clickToElement(UserProductCategoryPageUI.ADD_TO_COMPARE_LINK_BY_PRODUCT_NAME, productName);
    }

    public boolean isAddProductToComparisonMessageDisplayed(String productName) {
        waitForElementVisibility(UserProductCategoryPageUI.ADD_PRODUCT_TO_COMPARISON_SUCCESS_MESSAGE, productName);
        return isElementDisplayed(UserProductCategoryPageUI.ADD_PRODUCT_TO_COMPARISON_SUCCESS_MESSAGE, productName);
    }

    public UserComparisonPageObject clickToCompareButton() {
        waitForElementClickable(UserProductCategoryPageUI.COMPARE_BUTTON);
        clickToElement(UserProductCategoryPageUI.COMPARE_BUTTON);
        return new UserComparisonPageObject(driver);

    }

    public UserWishListPageObject clickToAddToWishlistLinkByProductName(String productName) {
        waitForElementClickable(UserProductCategoryPageUI.ADD_TO_WISHLIST_LINK_BY_PRODUCT_NAME, productName);
        clickToElement(UserProductCategoryPageUI.ADD_TO_WISHLIST_LINK_BY_PRODUCT_NAME, productName);
        return new UserWishListPageObject(driver);
    }

    public boolean areAllProductDisplayedWithinPriceRange(float minPrice, float maxPrice) {
        waitForAllElementVisibility(UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        List<WebElement> priceList = getListElements(UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        for (WebElement priceElement : priceList) {
            float price = Float.parseFloat(priceElement.getText().substring(1));
            if (price > maxPrice && price < minPrice) {
                return false;
            }
        }
        return true;
    }

    public void crawlProductDataByPriceRange(float minPrice, float maxPrice) {
        List<WebElement> productNameList = getListElements(UserProductCategoryPageUI.PRODUCT_NAME_AT_PRODUCT_LIST);
        List<WebElement> priceList = getListElements(UserProductCategoryPageUI.PRODUCT_PRICE_AT_PRODUCT_LIST);
        List<ClassHelper.Product> productList = new ArrayList<ClassHelper.Product>();
        String productName;
        String price;
        for (int i = 0; i < priceList.size(); i++) {
            productName = productNameList.get(i).getText();
            price = priceList.get(i).getText();
            productList.add(new ClassHelper.Product(productName, price));
        }
        String fileName = "Product_List_From_" + String.format("%.0f", minPrice) + "_To_" + String.format("%.0f", maxPrice);
        deleteFileByFilePath(GlobalConstants.DATA_RECORD + fileName + ".txt");
        for (ClassHelper.Product product : productList) {
            String data = String.format(product.getProductName() + ";" + product.getPrice());
            writeDataIntoDataRecordByFileName(data, fileName);
        }

    }


}
