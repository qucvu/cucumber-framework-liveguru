package stepsDefinition;

import hooks.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.UserAccountPageObject;
import pageObjects.UserLoginPageObject;

import java.util.List;
import java.util.Map;

public class UserLoginSteps {
    TestContext testContext;
    private UserLoginPageObject userLoginPage;
    private UserAccountPageObject accountPage;

    public UserLoginSteps(TestContext testContext) {
        this.testContext = testContext;
        userLoginPage = testContext.getUserLoginPage();
        accountPage = testContext.getUserAccountPage();

    }

    @And("User click to `Create an Account` button")
    public void userClickCreateAnAccountButton() {
        userLoginPage.clickToCreateAnAccountButton();
    }

    @Given("User was on the Login Page")
    public void userWasOnTheLoginPage() {
        testContext.verifyTrue(userLoginPage.isLoginPageTitleDisplayed());
    }

    @And("User enters the login credentials")
    public void userEnterLoginCredentials(DataTable dataTable) {
        String email;
        String password;
        if (dataTable == null) {
            email = (String) testContext.getSharedState().getDataContext("email");
            password = (String) testContext.getSharedState().getDataContext("password");
        } else {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            email = data.get(0).get("email");
            password = data.get(0).get("password");
        }
        userLoginPage.enterToDynamicTextboxById("Email", "email", email);
        userLoginPage.enterToDynamicTextboxById("Password", "pass", password);
    }

    @And("User clicks to 'Login' button")
    public void userClickToLoginButton() {
        userLoginPage.clickToLoginButton();
    }

    @Then("The 'welcome' message should be shown for full name: {string} at Dashboard")
    public void theWelcomeMessageShouldBeShown(String fullName) {
        testContext.verifyEquals(accountPage.getCurrentTabTitle(), "MY DASHBOARD");
        testContext.verifyTrue(accountPage.isWelcomeMessageIsDisplayedAtMyDashboard(fullName));
    }

}
