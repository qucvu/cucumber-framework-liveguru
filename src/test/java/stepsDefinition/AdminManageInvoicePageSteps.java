package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.Then;
import pageObjects.admin.AdminManageInvoicePageObject;

public class AdminManageInvoicePageSteps {
    TestContext testContext;
    private AdminManageInvoicePageObject adminManageInvoicePage;


    public AdminManageInvoicePageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageInvoicePage = testContext.getAdminManageInvoicePage();
    }

    @Then("The column 'Amount' amount should be sorted {string}")
    public void columnAmountShouldBeSorted(String option) {
        testContext.verifyTrue(adminManageInvoicePage.isDataAmountColumnSortByOption(option));
    }


}
