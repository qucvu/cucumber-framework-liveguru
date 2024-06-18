package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import pageObjects.user.UserProductDetailPageObject;

public class UserProductDetailSteps {
    TestContext testContext;
    private UserProductDetailPageObject userProductDetailPage;

    public UserProductDetailSteps(TestContext testContext) {
        this.testContext = testContext;
        userProductDetailPage = testContext.getUserProductDetailPage();
    }


    @And("User retrieves the cost of the current product on the Product Details Page")
    public void userRetrieveProductCostBtProductName() {
        float productPriceAtDetailPage = userProductDetailPage.getProductCostByProductName();
        testContext.getSharedState().setDataContext("productPriceAtDetailPage", productPriceAtDetailPage);
    }

    @And("User clicks on the 'Add to Cart' button")
    public void userClickOnAddToCartButton() {
        userProductDetailPage.clickToAddToCartButton();
    }

    @And("User clicks on the `Add to review` link")
    public void userClicksOnTheAddToReviewLink() {
        userProductDetailPage.clickToAddYourReviewLink();
    }


}
