package stepsDefinition;

import commons.ConfigLoaderEnvironment;
import hooks.TestContext;
import io.cucumber.java.en.When;
import pageObjects.admin.AdminLoginPageObject;

public class AdminLoginPageSteps {
    TestContext testContext;
    private AdminLoginPageObject adminLoginPage;


    public AdminLoginPageSteps(TestContext testContext) {
        this.testContext = testContext;
        adminLoginPage = testContext.getAdminLoginPage();
    }

    @When("User enters the valid login credentials on the Admin Login Page")
    public void userEntersValidLoginCredentials() {
        String userName = ConfigLoaderEnvironment.config.getString("app.usernameAdmin");
        String password = ConfigLoaderEnvironment.config.getString("app.passwordAdmin");
        adminLoginPage.enterToDynamicTextboxById("Username", "username", userName);
        adminLoginPage.enterToDynamicTextboxById("Password", "login", password);
    }


    @When("User clicks the `Login` button on the Admin Login Page")
    public void userClicksLoginButton() {
        adminLoginPage.clickToLoginButton();
    }


}
