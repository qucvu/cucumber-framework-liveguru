package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.UserProductReviewPageObject;

public class UserProductReviewSteps {
    TestContext testContext;
    private UserProductReviewPageObject userProductReviewPage;


    public UserProductReviewSteps(TestContext testContext) {
        this.testContext = testContext;
        userProductReviewPage = testContext.getUserProductReviewPage();

    }


    @And("User leaves the `Review` data fields empty")
    public void userLeavesTheReviewDataFieldsEmpty() {
        userProductReviewPage.enterToThoughtTextareaEmptyValue();
        userProductReviewPage.enterToTextboxEmptyValueById("summary_field");
        userProductReviewPage.enterToTextboxEmptyValueById("nickname_field");
    }

    @And("User clicks on the `Submit Review` button")
    public void userClicksOnTheSubmitReviewButton() {
        userProductReviewPage.clickToSubmitReviewButton();
    }

    @Then("The `Required field` message should be displayed under each data field: Rate, Thought, Summary, Nickname")
    public void theRequiredFieldMessageShouldBeDisplayedUnderEachDataField() {
        testContext.verifyEquals(userProductReviewPage.getErrorMessageUnderQualityRateRadio(), "Please select one of each of the ratings above");
        testContext.verifyEquals(userProductReviewPage.getErrorMessageUnderThoughtTextarea(), "THIS IS A REQUIRED FIELD.");
        testContext.verifyEquals(userProductReviewPage.getErrorMessageUnderSummaryTextbox(), "THIS IS A REQUIRED FIELD.");
        testContext.verifyEquals(userProductReviewPage.getErrorMessageUnderNicknameTextbox(), "THIS IS A REQUIRED FIELD.");
    }

    @And("User fills in the `Review` data fields with Nickname: {string}, Thought: {string}, Summary: {string}, Rate Quality: {string}")
    public void userFillsInTheReviewDataFields(String nicknameReview, String thoughtReview, String summaryReview, String qualityRateReview) {
        userProductReviewPage.enterToThoughtTextarea(thoughtReview);
        userProductReviewPage.enterToDynamicTextboxById("Summary Review", "summary_field", summaryReview);
        userProductReviewPage.enterToDynamicTextboxById("Nickname Review", "nickname_field", nicknameReview);
        userProductReviewPage.checkToQualityRadio(qualityRateReview);
    }

    @Then("The `{string}` message should be displayed")
    public void theReviewAcceptedMessageShouldBeDisplayed(String message) {
        testContext.verifyTrue(userProductReviewPage.isMessageUnderBreadcrumbDisplayed(message));
    }


}
