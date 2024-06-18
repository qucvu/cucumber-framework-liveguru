package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminManageCustomerPageUI;
import pageUIs.LiveGuruBasePageUI;

public class AdminManageCustomerPageObject extends BasePage {
    public AdminManageCustomerPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isManageCustomersTitleDisplayed() {
        waitForElementClickable(AdminManageCustomerPageUI.MANAGE_CUSTOMER_TITLE);
        return isElementDisplayed(AdminManageCustomerPageUI.MANAGE_CUSTOMER_TITLE);
    }

    public boolean isEmailDisplayedAtEmailColumnCustomerTable(String email) {
        int emailIndexColumn = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, "Email") + 1;
        waitForElementVisibility(AdminManageCustomerPageUI.CELL_DATA_BY_COLUMN_INDEX_AND_TEXT, String.valueOf(emailIndexColumn), email);
        return isElementDisplayed(AdminManageCustomerPageUI.CELL_DATA_BY_COLUMN_INDEX_AND_TEXT, String.valueOf(emailIndexColumn), email);
    }

    public boolean isFullNameDisplayedAtNameColumnCustomerTableByEmail(String email, String fullName) {
        waitForElementVisibility(AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
        return isElementDisplayed(AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
    }

    public void checkToCheckboxAtCustomerRowByEmail(String email) {
        waitForElementClickable(AdminManageCustomerPageUI.CHECKBOX_CUSTOMER_ROW_BY_EMAIL, email);
        checkToDefaultCheckboxRadio(AdminManageCustomerPageUI.CHECKBOX_CUSTOMER_ROW_BY_EMAIL, email);
    }

    public int getCurrentItemSelected() {
        waitForElementVisibility(AdminManageCustomerPageUI.CURRENT_ITEM_SELECTED);
        return Integer.parseInt(getElementText(AdminManageCustomerPageUI.CURRENT_ITEM_SELECTED));
    }


    public void acceptTheDeleteAlert() {
        acceptAlert();
    }


    public boolean isFullNameUndisplayedAtNameColumnCustomerTableByEmail(String email, String fullName) {
        return isElementUndisplayed(AdminManageCustomerPageUI.FULL_NAME_CELL_BY_EMAIL, email, fullName);
    }


}
