package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.admin.AdminManageOrderPageObject;
import utilities.DataHelper;

public class AdminManageOrderPageSteps {
    TestContext testContext;
    private AdminManageOrderPageObject adminManageOrderPage;
    private DataHelper dataHelper = DataHelper.getDataHelper();


    public AdminManageOrderPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminManageOrderPage = testContext.getAdminManageOrderPage();
    }

    @And("User selects to the first checkbox Row Order")
    public void userSelectFirstRowCheckboxOrder() {
        adminManageOrderPage.selectToFirstCheckboxOrderRow();

    }

    @Then("The Invoice should be downloaded")
    public void theInvoiceShouldBeDownloaded() {
        String fileNameInvoice = String.format("invoice%s-%s-%s", dataHelper.getCurrentYear(), dataHelper.getCurrentMonth(), dataHelper.getCurrentDate());
        System.out.println("fileNameInvoice: " + fileNameInvoice);
        testContext.verifyTrue(adminManageOrderPage.isInvoicePDFDownloaded(fileNameInvoice));

    }

    @And("User selects the 'Select Visible' link at Admin Page")
    public void userSelectsTheLinkAtAdminPage() {
        adminManageOrderPage.clickToSelectVisibleLink();
    }

    @And("User selects the 'Unselect Visible' link at Admin Page")
    public void userUnSelectsTheLinkAtAdminPage() {
        adminManageOrderPage.clickToUnSelectVisibleLink();
    }


    @Then("The current selected item count should be {int} at Admin Page")
    public void theCurrentSelectedItemCountShouldBeAtAdminPage(int expectedCount) {
        testContext.verifyEquals(adminManageOrderPage.getCurrentItemSelected(), expectedCount);
    }


}
