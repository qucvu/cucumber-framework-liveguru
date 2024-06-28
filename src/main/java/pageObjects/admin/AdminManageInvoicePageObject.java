package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.LiveGuruBasePageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminManageInvoicePageObject extends BasePage {
    public AdminManageInvoicePageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isDataAmountColumnSortByOption(String option) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, "Amount") + 1;
        waitForLoadingMaskUnInvisibility();
        List<WebElement> elementList = getListElements(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        ArrayList<Float> dataList = new ArrayList<>();
        for (WebElement element : elementList) {
            dataList.add(Float.parseFloat(element.getText().trim().substring(1).replace(",", "")));
        }
        List<Float> sortList = new ArrayList<Float>(dataList);
        Collections.sort(sortList);
        if (!option.equals("ascending")) {
            Collections.reverse(sortList);
        }

        return sortList.equals(dataList);
    }

}
