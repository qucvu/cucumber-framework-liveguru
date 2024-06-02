package hooks;

import commons.BasePage;
import commons.VerificationFailures;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.Assert;
import org.testng.Reporter;
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

    public boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);

        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }


    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
