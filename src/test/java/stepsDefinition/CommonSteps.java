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
        commonPage.openPageUrl(ConfigLoaderEnvironment.config.getString("app.endUserUrl"));
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


}
