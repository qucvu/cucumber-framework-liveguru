package stepsDefinition;

import commons.BasePage;
import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.admin.AdminManageReviewPageObject;

public class AdminManageReviewPageSteps {
    TestContext testContext;
    private AdminManageReviewPageObject adminManageReviewPage;
    private BasePage commonPage;


    public AdminManageReviewPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageReviewPage = testContext.getAdminManageReviewPage();
        commonPage = testContext.getBasePage();
    }

    @Then("the Review data should be displayed properly with Nickname {string}, Summary {string}, and Thought {string}")
    public void reviewDataShouldBeDisplayedProperly(String nicknameReview, String summaryReview, String thoughtReview) {
        testContext.verifyTrue(adminManageReviewPage.isTitleDisplayedByNicknameGridTable(nicknameReview, summaryReview));
        testContext.verifyTrue(adminManageReviewPage.isReviewDisplayedByNicknameGridTable(nicknameReview, thoughtReview));
    }

    @And("User clicks on Review row with Nick name: {string}")
    public void userClicksReviewRowByNickName(String nickName) {
        adminManageReviewPage.clickToEditLinkByNickname(nickName);
    }

    @And("User saves the Review with {string} status")
    public void userSavesReviewWithStatus(String status) {
        adminManageReviewPage.selectToDefaultDropdownById("status_id", status);
        adminManageReviewPage.clickToSaveReviewButton();
    }


}
