package hooks;

import commons.BasePage;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminManageCustomerPageObject;
import pageObjects.admin.AdminManageReviewPageObject;
import pageObjects.user.*;
import reportConfigs.AllureManager;
import stepsDefinition.SharedState;

public class TestContext {
    private BasePage commonPage;
    private SharedState sharedState;
    private UserRegisterPageObject userRegisterPage;
    private UserAccountPageObject userAccountPage;
    private UserHomePageObject userHomePage;
    private UserLoginPageObject userLoginPage;
    private UserProductCategoryPageObject userProductCategory;
    private UserProductDetailPageObject userProductDetailPage;
    private UserShoppingCartPageObject userShoppingCartPage;
    private UserProductComparisonPageObject userProductComparisonPage;
    private UserWishListPageObject userWishListPage;
    private UserProductReviewPageObject userProductReviewPage;
    private UserCheckoutPageObject userCheckoutPage;
    private UserAdvancedSearchPageObject userAdvancedSearchPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminManageCustomerPageObject adminManageCustomerPage;
    private AdminManageReviewPageObject adminManageReviewPage;

    public TestContext() {
        ThreadGuard.protect(new DriverFactory().createDriver());
    }

    public BasePage getBasePage() {
        if (commonPage == null) {
            commonPage = new BasePage(DriverManager.getDriver());
        }
        return commonPage;
    }

    public SharedState getSharedState() {
        if (sharedState == null) {
            sharedState = new SharedState();
        }
        return sharedState;
    }

    public UserRegisterPageObject getUserRegisterPage() {
        if (userRegisterPage == null) {
            userRegisterPage = new UserRegisterPageObject(getDriver());
        }
        return userRegisterPage;
    }

    public UserAccountPageObject getUserAccountPage() {
        if (userAccountPage == null) {
            userAccountPage = new UserAccountPageObject(getDriver());
        }
        return userAccountPage;
    }

    public UserHomePageObject getUserHomePage() {
        if (userHomePage == null) {
            userHomePage = new UserHomePageObject(DriverManager.getDriver());
        }
        return userHomePage;
    }

    public UserLoginPageObject getUserLoginPage() {
        if (userLoginPage == null) {
            userLoginPage = new UserLoginPageObject(DriverManager.getDriver());
        }
        return userLoginPage;
    }

    public UserProductCategoryPageObject getUserProductCategoryPage() {
        if (userProductCategory == null) {
            userProductCategory = new UserProductCategoryPageObject(DriverManager.getDriver());
        }
        return userProductCategory;
    }

    public UserProductDetailPageObject getUserProductDetailPage() {
        if (userProductDetailPage == null) {
            userProductDetailPage = new UserProductDetailPageObject(DriverManager.getDriver());
        }
        return userProductDetailPage;
    }

    public UserShoppingCartPageObject getUserShoppingCartPage() {
        if (userShoppingCartPage == null) {
            userShoppingCartPage = new UserShoppingCartPageObject(DriverManager.getDriver());
        }
        return userShoppingCartPage;
    }

    public UserProductComparisonPageObject getUserProductComparisonPage() {
        if (userProductComparisonPage == null) {
            userProductComparisonPage = new UserProductComparisonPageObject(DriverManager.getDriver());
        }
        return userProductComparisonPage;
    }

    public UserWishListPageObject getUserWishListPage() {
        if (userWishListPage == null) {
            userWishListPage = new UserWishListPageObject(DriverManager.getDriver());
        }
        return userWishListPage;
    }

    public UserProductReviewPageObject getUserProductReviewPage() {
        if (userProductReviewPage == null) {
            userProductReviewPage = new UserProductReviewPageObject(DriverManager.getDriver());
        }
        return userProductReviewPage;
    }


    public UserCheckoutPageObject getUserCheckoutPage() {
        if (userCheckoutPage == null) {
            userCheckoutPage = new UserCheckoutPageObject(DriverManager.getDriver());
        }
        return userCheckoutPage;
    }

    public UserAdvancedSearchPageObject getUserAdvancedSearchPage() {
        if (userAdvancedSearchPage == null) {
            userAdvancedSearchPage = new UserAdvancedSearchPageObject(DriverManager.getDriver());
        }
        return userAdvancedSearchPage;
    }

    public AdminLoginPageObject getAdminLoginPage() {
        if (adminLoginPage == null) {
            adminLoginPage = new AdminLoginPageObject(DriverManager.getDriver());
        }
        return adminLoginPage;
    }


    public AdminManageCustomerPageObject getAdminManageCustomerPage() {
        if (adminManageCustomerPage == null) {
            adminManageCustomerPage = new AdminManageCustomerPageObject(DriverManager.getDriver());
        }
        return adminManageCustomerPage;
    }


    public AdminManageReviewPageObject getAdminManageReviewPage() {
        if (adminManageReviewPage == null) {
            adminManageReviewPage = new AdminManageReviewPageObject(DriverManager.getDriver());
        }
        return adminManageReviewPage;
    }

    public void verifyTrue(boolean condition) {
        Assert.assertTrue(condition);
        AllureManager.saveTextLog("Verify TRUE: " + true);
    }

    public void verifyTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
        AllureManager.saveTextLog("Verify TRUE: " + true);
    }

    public void verifyEquals(Object value1, Object value2) {
        Assert.assertEquals(value1, value2);
        boolean result = value1.equals(value2);
        if (!result) {
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
        }
        AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
    }

    public void verifyEquals(Object value1, Object value2, String message) {
        Assert.assertEquals(value1, value2, message);
        boolean result = value1.equals(value2);
        if (!result) {
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
        }
        AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
    }


    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
