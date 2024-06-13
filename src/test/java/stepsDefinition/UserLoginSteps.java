package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.UserAccountPageObject;
import pageObjects.UserLoginPageObject;

public class UserLoginSteps {
    TestContext testContext;
    private final UserLoginPageObject userLoginPage;
    private final UserAccountPageObject accountPage;
    private final SharedState sharedState;

    public UserLoginSteps(TestContext testContext) {
        this.testContext = testContext;
        userLoginPage = testContext.getUserLoginPage();
        accountPage = testContext.getUserAccountPage();
        sharedState = testContext.getSharedState();

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
    public void userEnterLoginCredentials() {
        System.out.println("Share state" + sharedState);
        String email = (String) sharedState.getDataContext("email");
        String password = (String) sharedState.getDataContext("password");
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
