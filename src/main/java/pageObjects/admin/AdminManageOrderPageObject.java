package pageObjects.admin;

import commons.BasePage;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminManageOrderPageUI;

public class AdminManageOrderPageObject extends BasePage {
    public AdminManageOrderPageObject(WebDriver driver) {
        super(driver);
    }

    public void selectToFirstCheckboxOrderRow() {
        waitForLoadingMaskUnInvisibility();
        waitForElementClickable(AdminManageOrderPageUI.FIRST_CHECKBOX_ORDER_ROW);
        checkToDefaultCheckboxRadio(AdminManageOrderPageUI.FIRST_CHECKBOX_ORDER_ROW);
    }

    public boolean isInvoicePDFDownloaded(String fileName) {
        return isFileDownloaded(GlobalConstants.DOWNLOAD_FILE_FOLDER, fileName);
    }

    public int getCurrentItemSelected() {
        waitForElementVisibility(AdminManageOrderPageUI.CURRENT_ITEM_SELECTED);
        return Integer.parseInt(getElementText(AdminManageOrderPageUI.CURRENT_ITEM_SELECTED));
    }

    public void clickToSelectVisibleLink() {
        waitForElementClickable(AdminManageOrderPageUI.SELECT_VISIBLE_LINK);
        clickToElement(AdminManageOrderPageUI.SELECT_VISIBLE_LINK);
    }

    public void clickToUnSelectVisibleLink() {
        waitForElementClickable(AdminManageOrderPageUI.UNSELECT_VISIBLE_LINK);
        clickToElement(AdminManageOrderPageUI.UNSELECT_VISIBLE_LINK);

    }

}
