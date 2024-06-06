package stepsDefinition;

import commons.BasePage;
import commons.ConfigLoaderEnvironment;
import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.UserHomePageObject;

public class CommonSteps {
    TestContext testContext;
    UserHomePageObject userHomePage;
    BasePage basePage;

    public CommonSteps(TestContext context) {
        this.testContext = context;
        userHomePage = testContext.getUserHomePage();
        basePage = testContext.getBasePage();
    }

    @Given("User was on the end user site")
    public void userWasOnTheEndUserSite() {
        basePage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.EndUserUrl"));
    }

    @When("User click to {string} link at the footer")
    public void userClickToDynamicFooterLink(String linkText) {
        basePage.clickToDynamicFooterLinkByText(linkText);
    }
}
