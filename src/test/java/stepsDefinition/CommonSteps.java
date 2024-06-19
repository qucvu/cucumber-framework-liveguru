package stepsDefinition;

import commons.BasePage;
import commons.ConfigLoaderEnvironment;
import hooks.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.user.UserHomePageObject;

public class CommonSteps {
    TestContext testContext;
    UserHomePageObject userHomePage;
    BasePage commonPage;

    public CommonSteps(TestContext context) {
        this.testContext = context;
        userHomePage = testContext.getUserHomePage();
        commonPage = testContext.getBasePage();
    }

    @Given("User was on the end user site")
    public void userWasOnTheEndUserSite() {
        commonPage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.endUserUrl").split(";")[0]);
    }

    @Given("User navigates to the End User Page")
    public void userNavigatesToEndUserPage() {
        commonPage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.endUserUrl").split(";")[0]);
    }

    @Given("User was on the Admin site")
    public void userWasOnAdminSite() {
        commonPage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.adminUrl"));
    }

    @Given("User navigates to the Admin Page")
    public void userNavigatesToAdminPage() {
        commonPage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.adminUrl"));
    }

    @When("User clicks to {string} link at the footer")
    public void userClickToDynamicFooterLink(String linkText) {
        commonPage.clickToDynamicFooterLinkByText(linkText);
    }

    @When("User clicks to 'Account' link at the header")
    public void userClickToAccountLinkHeader() {
        commonPage.clickToAccountLinkHeader();
    }

    @And("User clicks to 'Log In' link at the 'Account' header")
    public void userClickToLoginLinkAtAccountLinkHeader() {
        commonPage.clickToLoginLinkAtAccountLinkHeader();
    }

    @And("User clicks to 'Log Out' link at the 'Account' header")
    public void userClickToLogoutLinkAtAccountLinkHeader() {
        commonPage.clickToLogoutLinkAtAccountLinkHeader();
    }


    @When("User clicks on the 'Mobile' link in the header navigation")
    public void userClickOnTheMobileLinkInHeader() {
        commonPage.clickToMobileLinkAtHeaderNav();
    }

    @When("User clicks on the 'TV' link in the header navigation")
    public void userClicksOnTheTVLinkInTheHeaderNavigation() {
        commonPage.clickToTVLinkAtHeaderNav();
    }


    @Then("The cost of the product should be the same on both pages")
    public void theCostShouBeSameOnBothPages() {
        float productPriceAtCategoryPage = (float) testContext.getSharedState().getDataContext("productPriceAtCategory");
        float productPriceAtDetailPage = (float) testContext.getSharedState().getDataContext("productPriceAtDetailPage");
        testContext.verifyEquals(productPriceAtCategoryPage, productPriceAtDetailPage);
    }

    @And("User closes the `Comparison Product` window")
    public void userClosesTheComparisonProductWindow() {
        commonPage.closeTheComparisonProductWindow();
    }

    @Then("The {string} tab should be active")
    public void theMyWishlistTabShouldBeActive(String link) {
        testContext.verifyTrue(commonPage.isCurrentActiveLinkAtAccountPageByText(link));
    }

    @And("User clicks to the `My Account'  link at the header")
    public void userClicksMyAccountLinkHeader() {
        commonPage.clickToMyAccountLinkAtAccountLinkHeader();
    }

    @And("User navigates to the Admin Manage Review page from Admin Home Page")
    public void userNavigatesToAdminManageReviewPage() {
        commonPage.hoverToDynamicHeaderLinkByNameAdminPage("Catalog");
        commonPage.hoverToDynamicHeaderLinkByNameAdminPage("Reviews and Ratings");
        commonPage.hoverToDynamicHeaderLinkByNameAdminPage("Customer Reviews");
        commonPage.clickToDynamicHeaderLinkByNameAdminPage("All Reviews");
    }

    @And("User navigates to the Admin Manage Customer page from the Admin Home Page")
    public void userNavigatesToAdminManageCustomerPage() {
        commonPage.hoverToDynamicHeaderLinkByNameAdminPage("Customers");
        commonPage.clickToDynamicHeaderLinkByNameAdminPage("Manage Customers");
    }

    @And("User clicks on the Submit button")
    public void userClicksOnTheButtonAdminPage() {
        commonPage.clickToSubmitButtonAdminPage();
    }

    @Then("the {string} message should be displayed above the Admin Page header")
    public void messageShouldBeDisplayedAboveAdminPageHeader(String expectedMessage) {
        testContext.verifyTrue(commonPage.isMessageDisplayedAboveHeaderAdminPage(expectedMessage));
    }


}
