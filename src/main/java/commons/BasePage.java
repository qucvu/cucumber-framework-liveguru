package commons;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.UserAccountPageObject;
import pageObjects.UserLoginPageObject;
import pageObjects.UserProductCategoryPageObject;
import pageObjects.UserWishListPageObject;
import pageUIs.LiveGuruBasePageUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private final int longTimeout = GlobalConstants.LONG_TIMEOUT;
    private final int shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void overrideImplicitTimeout(int shortTimeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(shortTimeout));
    }

    public void openPageUrl(String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void refreshCurrentPage() {
        driver.navigate().refresh();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Alert waitForAlertPresence() {
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    protected boolean isAlertPresent() {
        boolean presentFlag = false;
        try {
            driver.switchTo().alert();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
        }

        return presentFlag;
    }

    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    public void setCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(1);
    }

    public void acceptAlert() {
        waitForAlertPresence().accept();
    }

    protected void cancelAlert() {
        waitForAlertPresence().dismiss();
    }

    protected String getAlertText() {
        return waitForAlertPresence().getText();
    }

    protected void sendKeyToAlert(String textValue) {
        waitForAlertPresence().sendKeys(textValue);
    }

    protected String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    protected void switchToWindowByParentId(String windowId) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
            }
        }
    }

    protected void switchToWindowByTitle(String expectedTitlePage) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitlePage)) {
                break;
            }
        }
    }

    protected void closeOtherTabsWithoutParent(WebDriver driver, String parentId) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id).close();
            }
        }
        driver.switchTo().window(parentId);
    }

    public By getByLocator(String locatorType) {
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            return By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            return By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
            return By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            return By.cssSelector(locatorType.substring(5));
        } else {
            throw new RuntimeException("The locator is not supported");
        }
    }

    public static String getDynamicLocator(String locator, String... dynamicValues) {
        return String.format(locator, (Object[]) dynamicValues);
    }

    private WebElement getWebElement(String locatorType) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
        }
        return driver.findElement(getByLocator(locatorType));
    }

    private WebElement getWebElement(String locatorType, String... dynamicValues) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
        }
        return driver.findElement(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
    }

    protected List<WebElement> getListElements(String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected List<WebElement> getListElements(String locatorType, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
    }

    protected void clickToElementByJS(String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locatorType));
    }

    protected void clickToElementByJS(String locatorType, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locatorType, dynamicValues));
    }


    protected void clickToElement(String locatorType) {
        highlightElement(locatorType);
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
            clickToElementByJS(locatorType);
        } else {
            getWebElement(locatorType).click();
        }
    }

    protected void clickToElement(WebElement element) {
        element.click();

    }


    protected void clickToElement(String locatorType, String... dynamicValues) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
            clickToElementByJS(locatorType, dynamicValues);
        } else {
            getWebElement(locatorType, dynamicValues).click();
        }
    }

    protected void clearElement(String locatorType) {
        WebElement element = getWebElement(locatorType);
        element.clear();
    }

    protected void clearElement(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        element.clear();
    }


    protected void sendKeyToElement(String locatorType, String textValue) {
        clearElement(locatorType);
        getWebElement(locatorType).sendKeys(textValue);
    }

    protected void sendKeyToElement(String locatorType, String textValue, String... dynamicValues) {
        clearElement(locatorType, dynamicValues);
        getWebElement(locatorType, dynamicValues).sendKeys(textValue);
    }

    protected void sendKeyToElementByJS(String locatorType, String textValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", getWebElement(locatorType));
        js.executeScript("arguments[0].value = arguments[1];", getWebElement(locatorType), textValue);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", getWebElement(locatorType));

    }

    protected void sendKeyToElementByJS(String locatorType, String textValue, String... dynamicValues) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = getWebElement(getDynamicLocator(locatorType, dynamicValues));
        js.executeScript("arguments[0].value = '';", element);
        js.executeScript("arguments[0].value = arguments[1];", element, textValue);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", element);
    }


    protected String getElementText(String locatorType) {
        return getWebElement(locatorType).getText();
    }

    protected String getElementText(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).getText();
    }

    protected void selectItemInDefaultDropdown(String locatorType, String textItem) {
        new Select(getWebElement(locatorType)).selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropdown(String locatorType, String textItem, String... dynamicValues) {
        new Select(getWebElement(locatorType, dynamicValues)).selectByVisibleText(textItem);
    }

    protected String getSelectedItemDefaultDropdown(String locatorType) {
        return new Select(getWebElement(locatorType)).getFirstSelectedOption().getText();
    }

    protected String getSelectedItemDefaultDropdown(String locatorType, String... dynamicValues) {
        return new Select(getWebElement(locatorType, dynamicValues)).getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(String locatorType) {
        return new Select(getWebElement(locatorType)).isMultiple();
    }

    protected void selectItemDropdown(String parentXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        getWebElement(parentXpath).click();
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                webElement.click();
                break;
            }
        }
    }

    protected void enterAndSelectItemDropdown(String textBoxXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(textBoxXpath);
        element.clear();
        element.sendKeys(expectedTextItem);
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
                webElement.click();
                break;
            }
        }
    }

    protected String getElementAttribute(String attributeName, String locatorType) {
        return getWebElement(locatorType).getAttribute(attributeName);
    }

    protected String getElementAttribute(String attributeName, String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).getAttribute(attributeName);
    }

    protected String getElementCssValue(String locatorType, String propertyName) {
        return getWebElement(locatorType).getCssValue(propertyName);
    }

    protected String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementsSize(String locatorType) {
        return getListElements(locatorType).size();
    }

    protected int getElementsSize(String locatorType, String... dynamicValues) {
        return getListElements(locatorType, dynamicValues).size();
    }

    protected void checkToDefaultCheckboxRadio(String locatorType) {
        WebElement element = getWebElement(locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToDefaultCheckboxRadio(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(String locatorType) {
        WebElement element = getWebElement(locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(String locatorType) {
        try {
            return getWebElement(locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locatorType) {
        overrideImplicitTimeout(shortTimeout);
        List<WebElement> elements = getListElements(locatorType);
        overrideImplicitTimeout(longTimeout);
        return elements.size() == 0 || !elements.get(0).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
        overrideImplicitTimeout(shortTimeout);
        List<WebElement> elements = getListElements(locatorType, dynamicValues);
        overrideImplicitTimeout(longTimeout);
        return elements.size() == 0 || !elements.get(0).isDisplayed();
    }

    protected boolean isElementEnabled(String locatorType) {
        return getWebElement(locatorType).isEnabled();
    }

    protected boolean isElementSelected(String locatorType) {
        return getWebElement(locatorType).isSelected();
    }

    protected boolean isElementSelected(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).isSelected();
    }

    protected void switchToIframe(String locatorType) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locatorType)));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(String locatorType) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locatorType)).build().perform();
    }

    protected void hoverMouseToElement(String locatorType, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locatorType, dynamicValues)).build().perform();
    }

    protected void rightClickToElement(String locatorType) {
        new Actions(driver).contextClick().perform();
    }

    /**
     * only work for HTML4
     *
     * @param locatorType   Locator of first point
     * @param targetLocator Locator of target point
     */
    protected void dragAndDropElement(String locatorType, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(locatorType), getWebElement(targetLocator));
    }


    protected void pressKeyToElement(String locatorType, Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(locatorType), key).build().perform();
    }

    protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(locatorType, dynamicValues), key).build().perform();
    }

    protected void scrollToBottomPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void scrollToElement(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
    }

    protected void scrollToElement(String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType, dynamicValues));
    }

    protected void highlightElement(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void highlightElement(String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(locatorType, dynamicValues);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected String getElementValueByJS(String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.substring(6);
        return (String) jsExecutor.executeScript("$(document.evaluate(" + xpathLocator + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;).val");
    }

    protected void removeAttributeInDOM(String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locatorType));
    }

    protected boolean areJQueryAndJSLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected WebElement getShadowDom(String locatorType) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", getWebElement(locatorType));
    }

    protected String getElementValidationMessage(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locatorType));
    }

    protected boolean isImageLoaded(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(locator));
    }

    protected boolean isImageLoaded(String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(locator, dynamicValues));
    }

    protected void waitForElementVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForElementVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementInvisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));

    }

    protected void waitForElementInvisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementUnDisplayed(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(longTimeout);

    }

    protected void waitForElementUnDisplayed(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
        overrideImplicitTimeout(longTimeout);
    }

    protected void waitForAllElementVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));

    }

    protected void waitForAllElementVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));

    }

    protected void waitForAllElementInVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(locatorType)));
    }

    protected void waitForAllElementInVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(locatorType, dynamicValues)));
    }

    protected void waitForElementClickable(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    protected void waitForElementClickable(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementStaleness(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.stalenessOf(getWebElement(locatorType)));
    }

    protected void uploadFilesByFileName(String locatorType, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
        StringBuilder fullName = new StringBuilder();
        for (String fileName : fileNames) {
            fullName.append(filePath).append(fileName).append("\n");
        }
        fullName = new StringBuilder(fullName.toString().trim());
        getWebElement(locatorType).sendKeys(fullName.toString());
    }

    public boolean isDataStringSortAscLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortAscLamDa(String locatorType, String... dynamicValues) {
        List<WebElement> elementList = getListElements(locatorType, dynamicValues);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }


    public boolean isDataStringSortDescLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortDescLamDa(String locatorType, String... dynamicValues) {
        List<WebElement> elementList = getListElements(locatorType, dynamicValues);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }


    public boolean isDataFloatSortAscLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataFloatSortDescLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<Float> dataList = elementList.stream().map(webElement -> Float.parseFloat(webElement.getText())).toList();
        List<Float> sortList = new ArrayList<Float
                >(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataNumberSortAsc(String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(locator, dynamicValues);
        ArrayList<Long> dataList = new ArrayList<Long>();
        for (WebElement element : elementList) {
            dataList.add(Long.parseLong(element.getText().trim()));
        }
        List<Long> sortList = new ArrayList<Long>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }


    public boolean isDataNumberSortDesc(String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(locator, dynamicValues);
        ArrayList<Long> dataList = new ArrayList<Long>();
        for (WebElement element : elementList) {
            dataList.add(Long.parseLong(element.getText().trim()));
        }
        List<Long> sortList = new ArrayList<Long>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataDateSortByOption(String option, String locator, String... dynamicValues) {
        List<WebElement> elementList = getListElements(locator, dynamicValues);
        ArrayList<Date> dataList = new ArrayList<Date>();
        for (WebElement element : elementList) {
            dataList.add(convertStringToDate(element.getText().trim(), "MMM dd, yyyy h:mm:ss a"));
        }
        List<Date> sortList = new ArrayList<Date>(dataList);
        Collections.sort(sortList);
        if (option.equals("asc")) {
            Collections.reverse(sortList);
        }
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }


    /**
     * This method input the specific value to the textbox by id
     *
     * @param textID id of input locator
     * @param value  value send to textbox
     */
    public void enterToDynamicTextboxById(String nameTextbox, String textID, String value) {
        waitForElementVisibility(LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textID);
        sendKeyToElement(LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textID);
    }

    /**
     * This method click to the button by text
     *
     * @param textValue String text
     */
    public void clickToButtonByText(String textValue) {
        waitForElementClickable(LiveGuruBasePageUI.DYNAMIC_BUTTON_BY_TEXT, textValue);
        clickToElement(LiveGuruBasePageUI.DYNAMIC_BUTTON_BY_TEXT, textValue);
    }

    public void clickToDynamicFooterLinkByText(String textLink) {
        waitForElementClickable(LiveGuruBasePageUI.FOOTER_LINK_BY_TEXT, textLink);
        clickToElement(LiveGuruBasePageUI.FOOTER_LINK_BY_TEXT, textLink);
    }

    public String getValueTextboxById(String id) {
        waitForElementVisibility(LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        return getElementAttribute("value", LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
    }

    public void clickToAccountLinkHeader() {
        waitForElementClickable(LiveGuruBasePageUI.ACCOUNT_LINK_HEADER);
        clickToElement(LiveGuruBasePageUI.ACCOUNT_LINK_HEADER);

    }

    public UserLoginPageObject clickToLoginLinkAtAccountLinkHeader() {
        waitForElementClickable(LiveGuruBasePageUI.LOGIN_LINK_AT_ACCOUNT_HEADER);
        clickToElement(LiveGuruBasePageUI.LOGIN_LINK_AT_ACCOUNT_HEADER);
        return new UserLoginPageObject(driver);
    }

    public UserLoginPageObject clickToLogoutLinkAtAccountLinkHeader() {
        waitForElementClickable(LiveGuruBasePageUI.LOGOUT_LINK_AT_ACCOUNT_HEADER);
        clickToElement(LiveGuruBasePageUI.LOGOUT_LINK_AT_ACCOUNT_HEADER);
        return new UserLoginPageObject(driver);
    }


    public UserWishListPageObject clickToMyWishlistLinkAtAccountLinkHeader() {
        waitForElementClickable(LiveGuruBasePageUI.MY_WISHLIST_LINK_AT_ACCOUNT_HEADER);
        clickToElement(LiveGuruBasePageUI.MY_WISHLIST_LINK_AT_ACCOUNT_HEADER);
        return new UserWishListPageObject(driver);
    }


    public UserProductCategoryPageObject clickToMobileLinkAtHeaderNav() {
        waitForElementClickable(LiveGuruBasePageUI.MOBILE_LINK_AT_NAVIGATION_BAR);
        clickToElement(LiveGuruBasePageUI.MOBILE_LINK_AT_NAVIGATION_BAR);
        return new UserProductCategoryPageObject(driver);

    }


    public void clickToDynamicLinkOnTheLeftSidebar(String linkText) {
        waitForElementClickable(LiveGuruBasePageUI.DYNAMIC_LINK_AT_LEFT_SIDEBAR, linkText);
        clickToElement(LiveGuruBasePageUI.DYNAMIC_LINK_AT_LEFT_SIDEBAR, linkText);
    }

    public boolean isCurrentActiveLinkAtAccountPageByText(String linkText) {
        waitForElementVisibility(LiveGuruBasePageUI.CURRENT_ACTIVE_LINK_AT_LEFT_SIDEBAR, linkText);
        return isElementDisplayed(LiveGuruBasePageUI.CURRENT_ACTIVE_LINK_AT_LEFT_SIDEBAR, linkText);
    }

    public UserAccountPageObject clickToMyAccountLinkAtAccountLinkHeader() {
        waitForElementClickable(LiveGuruBasePageUI.MY_ACCOUNT_LINK_AT_ACCOUNT_HEADER);
        clickToElement(LiveGuruBasePageUI.MY_ACCOUNT_LINK_AT_ACCOUNT_HEADER);
        return new UserAccountPageObject(driver);
    }

//    public UserProductCategoryPO clickToTVLinkAtHeaderNav() {
//        waitForElementClickable(LiveGuruBasePageUI.TV_LINK_AT_NAVIGATION_BAR);
//        clickToElement(LiveGuruBasePageUI.TV_LINK_AT_NAVIGATION_BAR);
//        return PageGeneratorManager.getUserProductCategoryPage(driver);
//
//    }

    public void enterToTextboxEmptyValueById(String id) {
        waitForAllElementVisibility(LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        clearElement(LiveGuruBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
    }

    public void selectToDefaultDropdownById(String Id, String textOption) {
        waitForElementClickable(LiveGuruBasePageUI.DYNAMIC_DEFAULT_DROPDOWN_BY_ID, Id);
        selectItemInDefaultDropdown(LiveGuruBasePageUI.DYNAMIC_DEFAULT_DROPDOWN_BY_ID, textOption, Id);
    }

    public void closeTheIncomingMessagePopupAdminPage() {
        if (!isElementUndisplayed(LiveGuruBasePageUI.INCOMING_MESSAGE_POPUP_ADMIN_PAGE)) {
            waitForElementClickable(LiveGuruBasePageUI.INCOMING_MESSAGE_POPUP_ADMIN_PAGE);
            clickToElement(LiveGuruBasePageUI.CLOSE_LINK_INCOMING_MESSAGE_POPUP);
        }
    }

    public void hoverToDynamicHeaderLinkByNameAdminPage(String name) {
        waitForElementVisibility(LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
        hoverMouseToElement(LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
    }

    public void clickToDynamicHeaderLinkByNameAdminPage(String name) {
        waitForElementClickable(LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);
        clickToElement(LiveGuruBasePageUI.HEADER_LINK_BY_NAME_ADMIN_PAGE, name);

    }

    public boolean isMessageDisplayedAboveHeaderAdminPage(String message) {
        waitForElementVisibility(LiveGuruBasePageUI.TITLE_MESSAGE_ABOVE_HEADER_BY_MESSAGE, message);
        return isElementDisplayed(LiveGuruBasePageUI.TITLE_MESSAGE_ABOVE_HEADER_BY_MESSAGE, message);
    }


    public void clickToSubmitButtonAdminPage() {
        waitForElementClickable(LiveGuruBasePageUI.SUBMIT_BUTTON);
        clickToElement(LiveGuruBasePageUI.SUBMIT_BUTTON);
    }


    public void clickToSearchButtonAdminPage() {
        waitForLoadingMaskUnInvisibility();
        checkToDefaultCheckboxRadio(LiveGuruBasePageUI.SEARCH_BUTTON);

    }

    public void waitForLoadingMaskUnInvisibility() {
        waitForElementInvisibility(LiveGuruBasePageUI.LOADING_MASK);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean isDownloaded = false;
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(dirContents).length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                waitForFileToDownload(String.valueOf(dirContents[i]), GlobalConstants.LONG_TIMEOUT);
                dirContents[i].delete();
                isDownloaded = true;
            }
        }
        return isDownloaded;
    }

    protected void deleteFileByFilePath(String filePath) {
        try {
            Files.deleteIfExists(
                    Paths.get(filePath));
        } catch (NoSuchFileException e) {
            System.out.println(
                    "No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

    }

    protected void writeDataIntoDataRecordByFileName(String data, String fileName) {
        File file = new File(GlobalConstants.DATA_RECORD + fileName + ".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(data);
            fr.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean waitForFileToDownload(String expectedFullPathName, int maxWaitSeconds) {
        File downloadedFile = new File(expectedFullPathName);
        System.out.println("Download file: " + downloadedFile);
        long startTime = System.currentTimeMillis();

        while (!downloadedFile.exists()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);
            if (elapsedTime > maxWaitSeconds) {
                return false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private static Date convertStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clickToSortASCByTitle(String title) {
        waitForElementVisibility(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        String titleAttribute = getElementAttribute("title", LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        if (titleAttribute.equals("asc")) {
            waitForElementClickable(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
            clickToElement(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        }
    }

    public void clickToSortDESCByTitle(String title) {
        waitForElementVisibility(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        String titleAttribute = getElementAttribute("title", LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        if (titleAttribute.equals("desc")) {
            waitForElementClickable(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
            clickToElement(LiveGuruBasePageUI.SORT_TITLE_BY_TITLE, title);
        }

    }

    public int getQuantityItemSelectedCheckboxAdminPage() {
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        List<WebElement> checkboxList = getListElements(LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        System.out.println("Size:" + checkboxList.size());
        int countCheckboxSelected = 0;
        for (WebElement checkbox : checkboxList) {
            if (checkbox.isSelected()) {
                countCheckboxSelected++;
            }
        }
        return countCheckboxSelected;

    }

    public boolean isDataNumberSortASCByTitleColumn(String columnName) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataNumberSortAsc(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataNumberSortDESCByTitleColumn(String columnName) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataNumberSortDesc(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataDateSortByTitleColumnAndOption(String columnName, String option) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        if (option.equals("asc")) {
            return isDataDateSortByOption("asc", LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        }
        return isDataDateSortByOption("desc", LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }


    public boolean isDataStringSortASCByTitleColumn(String columnName) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataStringSortAscLamDa(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
    }

    public boolean isDataStringSortDESCByTitleColumn(String columnName) {
        int columnIndex = getElementsSize(LiveGuruBasePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));
        return isDataStringSortDescLamDa(LiveGuruBasePageUI.COLUMN_DATA_BY_INDEX_COLUMN, String.valueOf(columnIndex));

    }

    public int getQuantityItemRowDisplayedAdminPage(WebDriver driver) {
        waitForLoadingMaskUnInvisibility();
        waitForAllElementVisibility(LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
        return getElementsSize(LiveGuruBasePageUI.CHECKBOX_ROW_ITEM);
    }

    public void selectToViewPerPageDropdownAdminPage(String textValue) {
        waitForElementClickable(LiveGuruBasePageUI.VIEW_PER_PAGE_DROPDOWN);
        selectItemInDefaultDropdown(LiveGuruBasePageUI.VIEW_PER_PAGE_DROPDOWN, textValue);
    }


}
