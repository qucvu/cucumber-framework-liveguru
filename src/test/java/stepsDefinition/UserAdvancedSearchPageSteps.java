package stepsDefinition;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.UserAdvancedSearchPageObject;

public class UserAdvancedSearchPageSteps {
    TestContext testContext;
    private UserAdvancedSearchPageObject userAdvancedSearchPage;


    public UserAdvancedSearchPageSteps(TestContext testContext) {
        this.testContext = testContext;
        userAdvancedSearchPage = testContext.getUserAdvancedSearchPage();
    }


    @Given("User was on the User Search Page")
    public void userWasOnUserSearchPage() {
        testContext.verifyEquals(userAdvancedSearchPage.getPageTitle(), "CATALOG ADVANCED SEARCH");

    }

    @When("User enters the Price Range from {int} USD to {int} USD")
    public void userEntersPriceRange(int minPriceRange, int maxPriceRange) {
        userAdvancedSearchPage.enterToDynamicTextboxById("Min Price", "price", String.valueOf(minPriceRange));
        userAdvancedSearchPage.enterToDynamicTextboxById("Max Price", "price_to", String.valueOf(maxPriceRange));
    }

    @When("User clicks on the `Search` button")
    public void userClicksSearchButton() {
        userAdvancedSearchPage.clickToSearchButton();
    }


}
