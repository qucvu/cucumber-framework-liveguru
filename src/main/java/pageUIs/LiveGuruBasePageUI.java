package pageUIs;

public class LiveGuruBasePageUI {
    public final static String COMPARISON_WINDOW_TITLE = "Products Comparison List - Magento Commerce";
    public final static String MOBILE_WINDOW_TITLE = "Mobile";
    public final static String DYNAMIC_TEXTBOX_BY_ID = "css=input#%s";
    public final static String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public final static String FOOTER_LINK_BY_TEXT = "xpath=//div[@class='footer']//div[@class='links']//a[text()='%s']";
    public static final String DYNAMIC_LINK_AT_LEFT_SIDEBAR = "xpath=//div[contains(@class, 'sidebar')]//div[@class='block-content']//li//a[text()='%s']";
    public static final String CURRENT_ACTIVE_LINK_AT_LEFT_SIDEBAR = "XPATH=//div[@class='block-content']//li[@class='current']//strong[text()='%s']";

    public final static String DYNAMIC_DEFAULT_DROPDOWN_BY_ID = "css=select#%s";
    public final static String MY_ACCOUNT_LINK_AT_ACCOUNT_HEADER = "css=div#header-account a[title='My Account']";
    public final static String MOBILE_LINK_AT_NAVIGATION_BAR = "css=nav#nav a[href*='mobile.html']";
    public final static String TV_LINK_AT_NAVIGATION_BAR = "css=nav#nav a[href*='tv.html']";

    public final static String CLOSE_WINDOW_BUTTON = "CSS=button[title='Close Window']";
    public final static String MY_WISHLIST_LINK_AT_ACCOUNT_HEADER = "css=div#header-account a[title*='My Wishlist']";
    public final static String INCOMING_MESSAGE_POPUP_ADMIN_PAGE = "css=div#message-popup-window";
    public final static String CLOSE_LINK_INCOMING_MESSAGE_POPUP = "css=a[title=close]";
    public final static String HEADER_LINK_BY_NAME_ADMIN_PAGE = "xpath=//span[text()='%s']/parent::a";
    public final static String SUBMIT_BUTTON = "CSS=button[title=Submit]";
    public final static String SEARCH_BUTTON = "CSS=button[title=Search]";
    public final static String INDEX_COLUMN_BY_COLUMN_NAME = "xpath=//span[@class='sort-title' and text()='%s']/ancestor::th/preceding-sibling::th";
    public final static String TITLE_MESSAGE_ABOVE_HEADER_BY_MESSAGE = "xpath=//div[@id='messages'] //li/span[text()='%s']";
    public final static String LOADING_MASK = "ID=loading-mask";
    public final static String SORT_TITLE_BY_TITLE = "xpath=//span[@class='sort-title' and text()='%s']/parent::a";
    public final static String CHECKBOX_ROW_ITEM = "CSS=input.massaction-checkbox";
    public final static String COLUMN_DATA_BY_INDEX_COLUMN = "XPATH=//table[@class='data']//tbody//td[%s]";
    public final static String VIEW_PER_PAGE_DROPDOWN = "CSS=select[name=limit][onchange*=sales_order]";
    public final static String ACCOUNT_LINK_HEADER = "css=div.account-cart-wrapper>a[data-target-element='#header-account']";
    public static final String LOGIN_LINK_AT_ACCOUNT_HEADER = "css=div#header-account a[title='Log In']";
    public static final String LOGOUT_LINK_AT_ACCOUNT_HEADER = "css=div#header-account a[title='Log Out']";
    public static final String PAGE_TITLE = "CSS=div.page-title>h1";
}
