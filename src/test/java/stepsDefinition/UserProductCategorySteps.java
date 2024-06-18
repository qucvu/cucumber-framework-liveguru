package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.user.UserProductCategoryPageObject;

public class UserProductCategorySteps {
    TestContext testContext;
    private UserProductCategoryPageObject userProductCategoryPage;

    public UserProductCategorySteps(TestContext testContext) {
        this.testContext = testContext;
        userProductCategoryPage = testContext.getUserProductCategoryPage();
    }

    @And("User retrieves the cost of the product {string} on the Product Category Page")
    public void userRetrieveCostByProductName(String productName) {
        float productPriceAtCategoryPage = userProductCategoryPage.getProductCostByProductName(productName);
        testContext.getSharedState().setDataContext("productPriceAtCategory", productPriceAtCategoryPage);
    }

    @And("User opens the Product Details page for the product {string}")
    public void userOpenProductDetailPageByProductName(String productName) {
        userProductCategoryPage.openProductDetailPageByProductName(productName);
    }

    @And("User clicks to 'Add to Compare' link for the product: {string}")
    public void userClicksToAddToCompareLinkForTheProduct(String productName) {
        userProductCategoryPage.clickToAddToCompareLinkByProductName(productName);
    }

    @Then("The `Add to comparison list success` should be displayed for the product: {string}")
    public void theAddToComparisonListSuccessMessageShouldBeDisplayedForTheProduct(String productName) {
        testContext.verifyTrue(userProductCategoryPage.isAddProductToComparisonMessageDisplayed(productName));
    }

    @And("User clicks to the `Compare` button")
    public void userClicksTheCompareButton() {
        userProductCategoryPage.clickToCompareButton();
        userProductCategoryPage.switchToProductComparisonPage();
    }

    @And("User clicks on the `Add to Wishlist` button for the product: {string}")
    public void userClicksOnTheAddToWishlistButtonForTheProduct(String productShareWishlist) {
        userProductCategoryPage.clickToAddToWishlistLinkByProductName(productShareWishlist);
    }

    @Then("The all product should be displayed with the price from {int} USD to {int} USD")
    public void verifyProductPriceRange(int minPrice, int maxPrice) {
        testContext.verifyTrue(userProductCategoryPage.areAllProductDisplayedWithinPriceRange(minPrice, maxPrice));
    }

    @Then("Crawl data from Product Search from {int} USD to {int} USD into the 'dataRecord' Folder")
    public void crawlDataIntoDataRecordFolder(int minPrice, int maxPrice) {
        userProductCategoryPage.crawlProductDataByPriceRange(minPrice, maxPrice);

    }


}
