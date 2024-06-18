package pageObjects.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserCheckoutPageUI;

public class UserCheckoutPageObject extends BasePage {
    public UserCheckoutPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickToContinueButtonByContainerId(String containerId) {
        scrollToElement(UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
        waitForElementClickable(UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
        clickToElement(UserCheckoutPageUI.CONTINUE_BUTTON_CONTAINER_ID, containerId);
    }

    public void checkToPaymentMethodRadio(String paymentMethod) {
        waitForElementClickable(UserCheckoutPageUI.PAYMENT_METHOD_RADIO, paymentMethod);
        clickToElement(UserCheckoutPageUI.PAYMENT_METHOD_RADIO, paymentMethod);

    }

    public void clickToPlaceOrderButton() {
        waitForElementClickable(UserCheckoutPageUI.PLACE_ORDER_BUTTON);
        clickToElement(UserCheckoutPageUI.PLACE_ORDER_BUTTON);

    }

    public boolean isOrderSuccessMessageDisplayed() {
        waitForElementVisibility(UserCheckoutPageUI.ORDER_SUCCESS_MESSAGE);
        return isElementDisplayed(UserCheckoutPageUI.ORDER_SUCCESS_MESSAGE);
    }

    public boolean isOrderNumberGenerated() {
        waitForElementVisibility(UserCheckoutPageUI.ORDER_NUMBER_GENERATION);
        return isElementDisplayed(UserCheckoutPageUI.ORDER_NUMBER_GENERATION);
    }


}
