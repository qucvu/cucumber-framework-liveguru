package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.user.UserCheckoutPageObject;

public class UserCheckoutPageSteps {
    private TestContext testContext;
    private UserCheckoutPageObject userCheckoutPage;

    public UserCheckoutPageSteps(TestContext testContext) {
        this.testContext = testContext;
        userCheckoutPage = testContext.getUserCheckoutPage();
    }


    @And("User enters new valid `Billing Address` data with First Name: {string}, Last Name: {string}, Address: {string}, City: {string}, State: {string}, Zip code: {string} and Telephone: {string}")
    public void userEntersNewValidBillingAddress(String firstName, String lastName, String address, String city, String state, String zipCode, String telephone) {
//        userCheckoutPage.selectToDefaultDropdownById("billing-address-select", "New Address");
        userCheckoutPage.enterToDynamicTextboxById("First Name", "billing\\:firstname", firstName);
        userCheckoutPage.enterToDynamicTextboxById("Last Name", "billing\\:lastname", lastName);
        userCheckoutPage.enterToDynamicTextboxById("Address", "billing\\:street1", address);
        userCheckoutPage.enterToDynamicTextboxById("Address", "billing\\:street1", address);
        userCheckoutPage.enterToDynamicTextboxById("City", "billing\\:city", city);
        userCheckoutPage.selectToDefaultDropdownById("billing\\:region_id", state);
        userCheckoutPage.enterToDynamicTextboxById("Zip code", "billing\\:postcode", zipCode);
        userCheckoutPage.enterToDynamicTextboxById("Telephone", "billing\\:telephone", telephone);
    }

    @And("User clicks on the `Continue` button with Container Id: {string}")
    public void userClicksOnContinueButtonBilling(String containerId) {
        userCheckoutPage.clickToContinueButtonByContainerId(containerId);
    }


    @And("User selects the 'Payment Information' radio button with value: {string}")
    public void userSelectsPaymentInformation(String paymentMethod) {
        userCheckoutPage.checkToPaymentMethodRadio(paymentMethod);
    }


    @And("User clicks on the `Place Order` button")
    public void userClicksOnPlaceOrderButton() {
        userCheckoutPage.clickToPlaceOrderButton();
    }

    @Then("The 'order success' message should be displayed and the order number should be generated")
    public void theOrderSuccessMessageShouldBeDisplayedAndOrderNumberGenerated() {
        testContext.verifyTrue(userCheckoutPage.isOrderSuccessMessageDisplayed());
        testContext.verifyTrue(userCheckoutPage.isOrderNumberGenerated());

    }


}
