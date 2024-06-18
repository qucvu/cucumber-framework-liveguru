package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import pageObjects.user.UserProductComparisonPageObject;

public class UserProductComparisonSteps {
    private final TestContext testContext;
    UserProductComparisonPageObject userProductComparisonPage;

    public UserProductComparisonSteps(TestContext testContext) {
        this.testContext = testContext;
        userProductComparisonPage = testContext.getUserProductComparisonPage();
    }

    @Then("The title of the window should be 'COMPARE PRODUCTS'")
    public void theTitleOfTheWindowShouldBeCompareProducts() {
        testContext.verifyTrue(userProductComparisonPage.isTitleCompareProductDisplayed());

    }

    @Then("The product information for {string} including {string}, {string}, and {string} should be displayed properly")
    public void theProductInformationIncludingPriceSKUImageShouldBeDisplayedProperly(String productName, String productPrice, String productSKU, String productImage) {
        testContext.verifyTrue(userProductComparisonPage.isProductCompareNameDisplayed(productName));
        testContext.verifyTrue((userProductComparisonPage.isProductComparePriceDisplayed(productName, productPrice)));
        testContext.verifyTrue((userProductComparisonPage.isProductCompareImageDisplayed(productName, productImage)));
        testContext.verifyTrue((userProductComparisonPage.isProductCompareSKUDisplayed(productName, productSKU)));

    }

}
