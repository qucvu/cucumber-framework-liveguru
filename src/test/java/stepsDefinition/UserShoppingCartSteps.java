package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.user.UserShoppingCartPageObject;

public class UserShoppingCartSteps {
    TestContext testContext;
    private UserShoppingCartPageObject userShoppingCartPage;


    public UserShoppingCartSteps(TestContext testContext) {
        this.testContext = testContext;
        userShoppingCartPage = testContext.getUserShoppingCartPage();
    }

    @Then("The 'Add to cart success' message should be shown by product {string}")
    public void theAddToCartSuccessMessageShouldBeShown(String productName) {
        testContext.verifyTrue(userShoppingCartPage.isAddToCartSuccessMessageDisplayed(productName));
    }

    @And("User applies discount code: {string}")
    public void userAppliesDiscountCode(String discountCode) {
        userShoppingCartPage.enterToDynamicTextboxById("Coupon code", "coupon_code", discountCode);
        userShoppingCartPage.clickToApplyDiscountButton();
    }

    @Then("The discount price should be displayed with value {float}")
    public void theDiscountPriceShouldBeDisplayedWithValue(float discountPrice) {
        testContext.verifyTrue(userShoppingCartPage.isDiscountPriceDisplayed(discountPrice));
    }

    @Then("The 'Grand Total' price should be displayed with value {float}")
    public void theGrandTotalPriceShouldBeDisplayedWithValue(float grandTotalAfterDiscount) {
        testContext.verifyEquals(userShoppingCartPage.getGrandTotalPrice(), grandTotalAfterDiscount, "The grand total price is not correct after applying coupon code");

    }

    @And("User enters 'Quantity' textbox with value: {string} of product: {string}")
    public void userEntersQuantityTextboxWithValue(String quantityValue, String productName) {
        userShoppingCartPage.enterToQuantityTextboxByProductName(quantityValue, productName);
    }

    @And("User clicks on 'Update' button by Product: {string}")
    public void userClicksOnUpdateButton(String productName) {
        userShoppingCartPage.clickToUpdateButtonByProductName(productName);
    }

    @Then("The error message: {string} should be shown under the Shopping Cart Title")
    public void theErrorMessageShouldBeShownUnderShoppingCartTitle(String errorMessage) {
        testContext.verifyTrue(userShoppingCartPage.isMessageDisplayedUnderShoppingCartTitle(errorMessage));
    }

    @Then("The 'Maximum Allowed Quantity' message should be shown under product {string}")
    public void theMaximumAllowedQuantityMessageShouldBeShownUnderProduct(String productName) {
        testContext.verifyTrue(userShoppingCartPage.isMaximumQuantityErrorMessageDisplayedByProductName(productName));
    }

    @And("User clicks on 'Empty Cart' button")
    public void userClicksOnEmptyCartButton() {
        userShoppingCartPage.clickToEmptyCartButton();
    }

    @Then("The 'Shopping Cart is Empty' message should be shown")
    public void theShoppingCartIsEmptyMessageShouldBeShown() {
        testContext.verifyTrue(userShoppingCartPage.isShoppingCartEmptyDescriptionDisplayed());
    }

    @Then("The 'Cart Empty' description should be shown")
    public void theCartEmptyDescriptionShouldBeShown() {
        testContext.verifyTrue(userShoppingCartPage.isShoppingCartEmptyDescriptionDisplayed());
    }

    @And("User enters valid Estimate Shipping data with Country: {string}, State: {string}, and Zip code: {string}")
    public void userEntersValidEstimateShippingData(String country, String state, String zipCode) {
        userShoppingCartPage.selectToDefaultDropdownById("country", country);
        userShoppingCartPage.selectToDefaultDropdownById("region_id", state);
        userShoppingCartPage.enterToDynamicTextboxById("Zip code", "postcode", zipCode);
    }

    @And("User clicks on the `Estimate` button")
    public void userClicksOnEstimateButton() {
        userShoppingCartPage.clickToEstimateButton();
    }

    @Then("The Shipping Cost should be displayed with value: {string}")
    public void theShippingCostShouldBeDisplayed(String shippingCost) {
        testContext.verifyEquals(userShoppingCartPage.getShippingCostEstimation(), shippingCost);
    }

    @And("User selects the 'Flat Rate' shipping option")
    public void userSelectsFlatRateShippingOption() {
        userShoppingCartPage.checkToFixedShippingCostRadio();

    }

    @And("User clicks on the 'Update Total' button")
    public void userClicksOnUpdateTotalButton() {
        userShoppingCartPage.clickToUpdateTotalButton();
    }

    @Then("The Shipping Cost should be displayed in the cart total as: {string}")
    public void theShippingCostShouldBeDisplayedInCartTotal(String shippingCost) {
        testContext.verifyTrue(userShoppingCartPage.isFixedShippingCostDisplayedAtCartTotal(shippingCost));
    }

    @Then("The Grand Total price should be displayed with value: {string}")
    public void theGrandTotalPriceShouldBeDisplayed(String grandTotal) {
        testContext.verifyEquals(userShoppingCartPage.getGrandTotalCost(), grandTotal);
    }

    @And("User clicks on the 'Proceed to Checkout' button")
    public void userClicksOnProceedToCheckoutButton() {
        userShoppingCartPage.clickToProceedToCheckoutButton();
    }


}
