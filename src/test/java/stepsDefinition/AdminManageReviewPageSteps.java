package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import pageObjects.admin.AdminManageReviewPageObject;

public class AdminManageReviewPageSteps {
    TestContext testContext;
    private AdminManageReviewPageObject adminManageReviewPage;


    public AdminManageReviewPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageReviewPage = testContext.getAdminManageReviewPage();
    }

    @Then("the Review data should be displayed properly with Nickname {string}, Summary {string}, and Thought {string}")
    public void reviewDataShouldBeDisplayedProperly(String nicknameReview, String summaryReview, String thoughtReview) {
        testContext.verifyTrue(adminManageReviewPage.isTitleDisplayedByNicknameGridTable(nicknameReview, summaryReview));
        testContext.verifyTrue(adminManageReviewPage.isReviewDisplayedByNicknameGridTable(nicknameReview, thoughtReview));
    }


}
