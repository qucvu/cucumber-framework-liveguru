package stepsDefinition;

import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.user.UserProductDetailPageObject;

import java.util.List;
import java.util.Map;

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


    @And("User clicks on the Review quantity")
    public void userClicksViewQuantity() {
        userProductDetailPage.clickToQuantityReviewsLink();
    }

    @Then("The Review data should be displayed at the Box Reviews")
    public void reviewDataShouldBeDisplayedAtBoxReview(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps();
        Map<String, String> data = dataList.get(0);
        String nicknameReview = data.get("nicknameReview").replace("'", "");
        String thoughtReview = data.get("thoughtReview").replace("'", "");
        String summaryReview = data.get("summaryReview").replace("'", "");
        String qualityRateReview = data.get("qualityRateReview").replace("'", "");
        testContext.verifyTrue(userProductDetailPage.isSummaryReviewDisplayedByNickname(nicknameReview, summaryReview));
        testContext.verifyTrue(userProductDetailPage.isThoughtReviewDisplayedByNickname(nicknameReview, thoughtReview));
        testContext.verifyTrue(userProductDetailPage.isRatingReviewDisplayedByNickname(nicknameReview, qualityRateReview));
    }
}
