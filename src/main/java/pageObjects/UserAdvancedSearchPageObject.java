package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserAdvancedSearchPageUI;

public class UserAdvancedSearchPageObject extends BasePage {
    public UserAdvancedSearchPageObject(WebDriver driver) {
        super(driver);
    }

    public UserProductCategoryPageObject clickToSearchButton() {
        waitForElementClickable(UserAdvancedSearchPageUI.SEARCH_BUTTON);
        clickToElement(UserAdvancedSearchPageUI.SEARCH_BUTTON);
        return new UserProductCategoryPageObject(driver);
    }

}
