package stepsDefinition;

import commons.BasePage;
import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.admin.AdminManageCustomerPageObject;

import java.util.List;
import java.util.Map;

public class AdminManageCustomerPageSteps {
    TestContext testContext;
    private AdminManageCustomerPageObject adminManageCustomerPage;
    private BasePage commonPage;


    public AdminManageCustomerPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageCustomerPage = testContext.getAdminManageCustomerPage();
        commonPage = testContext.getBasePage();
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

    @And("User selects the Customer checkbox by email {string}")
    public void userSelectsCustomerCheckboxByEmail(String email) {
        email = email.equals("unique_email") ? (String) testContext.getSharedState().getDataContext("email") : email;
        adminManageCustomerPage.checkToCheckboxAtCustomerRowByEmail(email);
    }


    @Then("The selected current Customer count in the table should be {int}")
    public void theSelectedCurrentCustomerCountInTableShouldBeOne(int quantityItem) {
        testContext.verifyEquals(adminManageCustomerPage.getCurrentItemSelected(), quantityItem);
    }

    @And("User selects 'Delete' in the Action dropdown")
    public void userSelectsDeleteInActionDropdown() {
        adminManageCustomerPage.selectToDefaultDropdownById("customerGrid_massaction-select", "Delete");
    }


    @And("User accepts the Delete Alert")
    public void userAcceptsTheDeleteAlert() {
        adminManageCustomerPage.acceptTheDeleteAlert();
    }

    @Then("The account is undisplayed At the Customer Table")
    public void accountShouldNotBeDisplayedInCustomerTable(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps();
        Map<String, String> data = dataList.get(0);
        String email = null, fullName;
        email = data.get("email").equals("unique_email") ? (String) testContext.getSharedState().getDataContext("email") : data.get("email");
        fullName = String.format("%s %s", data.get("firstName"), data.get("lastName"));
        testContext.verifyTrue(adminManageCustomerPage.isFullNameUndisplayedAtNameColumnCustomerTableByEmail(email, fullName));
    }

    @Then("User enters the 'Filter' Customer text box at Admin Page with email: {string} and full name: {string}")
    public void userEnterTheFilterCustomerNameAndEmail(String email, String fullName) {
        email = email.equals("unique_email") ? (String) testContext.getSharedState().getDataContext("email") : email;
        commonPage.enterToDynamicTextboxById("Filter Email", "customerGrid_filter_email", email);
        commonPage.enterToDynamicTextboxById("Filter Name", "customerGrid_filter_name", fullName);
    }

}
