package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import pageObjects.UserProductCategoryPageObject;

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


}
