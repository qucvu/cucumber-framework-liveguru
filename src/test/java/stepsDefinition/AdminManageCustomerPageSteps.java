package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.admin.AdminManageCustomerPageObject;

public class AdminManageCustomerPageSteps {
    TestContext testContext;
    private AdminManageCustomerPageObject adminManageCustomerPage;


    public AdminManageCustomerPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageCustomerPage = testContext.getAdminManageCustomerPage();
    }

    @When("User dismisses the `Incoming Message` popup if it appears")
    public void userDismissesPopup() {
        adminManageCustomerPage.closeTheIncomingMessagePopupAdminPage();
    }

    @Then("The `Manage Customers` title should be displayed")
    public void verifyManageCustomersTitleDisplayed() {
        testContext.verifyTrue(adminManageCustomerPage.isManageCustomersTitleDisplayed());
    }

    @Then("The account data include Email: {string}, First Name: {string}, Last Name: {string}")
    public void verifyAccountData(String email, String firstName, String lastName) {
        if (email.equals("unique_email")) email = (String) testContext.getSharedState().getDataContext("email");
        testContext.verifyTrue(adminManageCustomerPage.isEmailDisplayedAtEmailColumnCustomerTable(email));
        testContext.verifyTrue(adminManageCustomerPage.isFullNameDisplayedAtNameColumnCustomerTableByEmail(email, String.format("%s %s", firstName, lastName)));
    }


}
