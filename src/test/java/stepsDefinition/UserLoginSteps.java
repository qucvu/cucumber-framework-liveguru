package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.And;
import pageObjects.UserLoginPageObject;

public class UserLoginSteps {
    TestContext testContext;
    private UserLoginPageObject userLoginPage;

    public UserLoginSteps(TestContext testContext) {
        this.testContext = testContext;
        userLoginPage = testContext.getUserLoginPage();

    }

    @And("User click to `Create an Account` button")
    public void userClickCreateAnAccountButton() {
        userLoginPage.clickToCreateAnAccountButton();
    }

}
