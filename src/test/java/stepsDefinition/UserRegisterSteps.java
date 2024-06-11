package stepsDefinition;

import commons.BasePage;
import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.UserAccountPageObject;
import pageObjects.UserRegisterPageObject;
import utilities.DataHelper;

import java.util.Map;

public class UserRegisterSteps {
    TestContext testContext;
    private final UserRegisterPageObject userRegisterPage;
    private final UserAccountPageObject userAccountPage;
    private final BasePage basePage;

    public UserRegisterSteps(TestContext testContext) {
        this.testContext = testContext;
        userRegisterPage = testContext.getUserRegisterPage();
        userAccountPage = testContext.getUserAccountPage();
        basePage = testContext.getBasePage();
    }

    @Then("User should navigate to the Register Page")
    public void userNavigateToTheRegisterPage() {
        testContext.verifyTrue(userRegisterPage.isCreateAnAccountTitleDisplayed());
    }

    @Given("User enters the valid inputs")
    public void userEnterValidCredentials(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String email = data.get("Email").equals("unique_email") ? dataHelper.getUserEmail() : data.get("Email");
        testContext.getSharedState().setDataContext("email", email);
        testContext.getSharedState().setDataContext("password", data.get("Password"));
        basePage.enterToDynamicTextboxById("First name", "firstname", data.get("First Name"));
        basePage.enterToDynamicTextboxById("Last name", "lastname", data.get("Last Name"));
        basePage.enterToDynamicTextboxById("Email", "email_address", email);
        basePage.enterToDynamicTextboxById("Password", "password", data.get("Password"));
        basePage.enterToDynamicTextboxById("Confirmation Password", "confirmation", data.get("Confirm Password"));
    }

    @And("User click to the `Register` button")
    public void userClickToRegisterButton() {
        userRegisterPage.clickToRegisterButton();
    }

    @Then("The `register successfully` message should be shown")
    public void registerSuccessMessageShouldBeShown() {
        testContext.verifyEquals(userAccountPage.getCurrentMessageUnderPageTitle(), "Thank you for registering with Main Website Store.");
    }

    public final DataHelper dataHelper = DataHelper.getDataHelper();


}
