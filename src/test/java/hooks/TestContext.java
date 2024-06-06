package hooks;

import commons.BasePage;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;
import pageObjects.UserAccountPageObject;
import pageObjects.UserHomePageObject;
import pageObjects.UserLoginPageObject;
import pageObjects.UserRegisterPageObject;

public class TestContext {
    private BasePage commonPage;
    private UserRegisterPageObject userRegisterPage;
    private UserAccountPageObject userAccountPage;
    private UserHomePageObject userHomePage;
    private UserLoginPageObject userLoginPage;

    public TestContext() {
        ThreadGuard.protect(new DriverFactory().createDriver());
    }

    public BasePage getBasePage() {
        if (commonPage == null) {
            commonPage = new BasePage(DriverManager.getDriver());
        }
        return commonPage;
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

    public boolean verifyTrue(Boolean condition) {
        if (condition) {
//            LogUtils.info("Verify TRUE: " + condition);
//            if (ExtentTestManager.getExtentTest() != null) {
//                ExtentReportManager.pass("Verify TRUE: " + condition);
//            }
//            AllureManager.saveTextLog("Verify TRUE: " + condition);
        } else {
            Assert.assertTrue(false, "The condition is FALSE.");
        }
        return condition;
    }

    public boolean verifyTrue(Boolean condition, String message) {
        if (!condition) {
            Assert.fail(message);
        }
        return true;
    }

    public boolean verifyEquals(Object value1, Object value2) {
        boolean result = value1.equals(value2);
        if (!result) {
            Assert.assertEquals(value1, value2, value1 + " != " + value2);
        }
        return result;
    }

    public boolean verifyEquals(Object value1, Object value2, String message) {
        boolean result = value1.equals(value2);
        if (!result) {
            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }


    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
