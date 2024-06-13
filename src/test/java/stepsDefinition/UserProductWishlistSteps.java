package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.UserWishListPageObject;

public class UserProductWishlistSteps {
    TestContext testContext;
    private UserWishListPageObject userWishListPage;


    public UserProductWishlistSteps(TestContext testContext) {
        this.testContext = testContext;
        userWishListPage = testContext.getUserWishListPage();
    }


    @Then("The 'Add product to wishlist success' message should be displayed for the product: {string}")
    public void theAddProductToWishlistSuccessMessageShouldBeDisplayedForTheProduct(String productShareWishlist) {
        testContext.verifyTrue(userWishListPage.isAddToWishlistSuccessMessageDisplayedByProductName(productShareWishlist));
    }


    @And("User clicks on the `Share Wishlist` button")
    public void userClicksOnTheShareWishlistButton() {
        userWishListPage.clickToShareWishlistbutton();
    }

    @And("User enters the `Wishlist` data field with email: {string} and message: {string}")
    public void userEntersTheWishlistDataFieldWithEmailAndMessage(String emailShareWishlist, String messageShareWishlist) {
        userWishListPage.enterToEmailTextarea(emailShareWishlist);
        userWishListPage.enterToMessageTextarea(messageShareWishlist);
    }

    @Then("The 'Share wishlist' success message should be displayed")
    public void theShareWishlistSuccessMessageShouldBeDisplayed() {
        testContext.verifyTrue(userWishListPage.isShareWishlistSuccessMessageDisplayed());
    }

    @Then("The 'My Wishlist' page should show the product item: {string}")
    public void theMyWishlistPageShouldShowTheProductItem(String productShareWishlist) {
        testContext.verifyTrue(userWishListPage.isProductNameDisplayedAtWishlistTable(productShareWishlist));
        testContext.verifyEquals(userWishListPage.getCurrentWishlistItem(), 1);
    }

    @And("User clicks on the 'Add to Cart' button for the product: {string}")
    public void userClicksOnAddToCartButton(String productName) {
        userWishListPage.clickToAddToCartButtonByProductName(productName);
    }


}
