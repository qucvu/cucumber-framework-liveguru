package stepsDefinition;

import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.user.UserAccountPageObject;

import java.util.List;
import java.util.Map;

public class UserAccountSteps {
    TestContext testContext;
    private UserAccountPageObject userAccountPage;

    public UserAccountSteps(TestContext testContext) {
        this.testContext = testContext;
        userAccountPage = testContext.getUserAccountPage();
    }

    @And("User click to {string} link at the left sidebar")
    public void userClickToDynamicLinkOnTheLeftSidebar(String linkText) {
        userAccountPage.clickToDynamicLinkOnTheLeftSidebar(linkText);
    }

    @Then("User should navigate to the {string} link")
    public void userShouldNavigateToTheDynamicLink(String linkText) {
        testContext.verifyTrue(userAccountPage.isCurrentActiveLinkAtAccountPageByText(linkText));
    }

    @Then("User info is displayed properly on the My Account page")
    public void userInfoIsDisplayedProperly(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = dataList.get(0);
        testContext.verifyEquals(userAccountPage.getValueTextboxById("email"), testContext.getSharedState().getDataContext("email"));
        testContext.verifyEquals(userAccountPage.getValueTextboxById("firstname"), data.get("firstName"));
        testContext.verifyEquals(userAccountPage.getValueTextboxById("lastname"), data.get("lastName"));
    }

    @And("User enters the Account Information with Email {string}, First Name {string}, Last Name {string}")
    public void userEntersAccountInformation(String email, String firstName, String lastName) {
        userAccountPage.enterToDynamicTextboxById("First Name", "firstname", firstName);
        userAccountPage.enterToDynamicTextboxById("Last Name", "lastname", lastName);
        userAccountPage.enterToDynamicTextboxById("Email", "email", email);
        userAccountPage.enterToDynamicTextboxById("Current Password", "current_password", (String) testContext.getSharedState().getDataContext("password"));
    }

    @And("User clicks on the `Save` button")
    public void userClicksOnTheSaveButton() {
        userAccountPage.clickToSaveButton();
    }

    @Then("The {string} message should be displayed under the Page Title")
    public void messageShouldBeDisplayedUnderPageTitle(String expectedMessage) {
        testContext.verifyEquals(userAccountPage.getCurrentMessageUnderPageTitle(), expectedMessage);
    }


}
