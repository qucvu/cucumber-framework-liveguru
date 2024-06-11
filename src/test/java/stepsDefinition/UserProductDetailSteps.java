package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import pageObjects.UserProductDetailPageObject;

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

}
