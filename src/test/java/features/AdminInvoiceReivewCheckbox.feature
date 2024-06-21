@adminInvoiceReview
Feature: Admin can sort, search with pagination

  Background: User navigate to the Manage Customer Page
    Given User was on the Admin site
    When User enters the valid login credentials on the Admin Login Page
    And User clicks the `Login` button on the Admin Login Page
    And User dismisses the `Incoming Message` popup if it appears
    Then The `Manage Customers` title should be displayed

  @invoicePrinted
  Scenario: Verify The invoice can be printed
    Given User was on the Admin site
    When User navigates to the Admin Order page from Admin Home Page
    And User selects 'Canceled' in the Filter Status dropdown
    And User clicks on the `Search` button Admin Page
    And User selects to the first checkbox Row Order
    And User selects 'Print Invoices' in the Action dropdown
    And User clicks on the Submit button Admin Page
    Then the 'There are no printable documents related to selected orders.' message should be displayed above the Admin Page header
    And User selects 'Complete' in the Filter Status dropdown
    And User clicks on the `Search` button Admin Page
    And User selects to the first checkbox Row Order
    And User selects 'Print Invoices' in the Action dropdown
    And User clicks on the Submit button Admin Page
    Then The Invoice should be downloaded

  @adminReview
  Scenario Outline: Verify the Mechanism Review can work properly on Admin Page
    Given User was on the end user site
    When User clicks on the 'TV' link in the header navigation
    And User opens the Product Details page for the product <productReview>
    And User clicks on the `Add to review` link
    And User fills in the `Review` data fields with Nickname: <nicknameReview>, Thought: <thoughtReview>, Summary: <summaryReview>, Rate Quality: <qualityRateReview>
    And User clicks on the `Submit Review` button
    Then The `<reviewAcceptMessage>` message should be displayed
    When User was on the Admin site
    And User navigates to the Admin Manage Pending Review page from Admin Home Page
    Then the Review data should be displayed properly with Nickname <nicknameReview>, Summary <summaryReview>, and Thought <thoughtReview>
    And User clicks on the Sort 'ID' column with value descending
    And User clicks on Review row with Nick name: <nicknameReview>
    And User saves the Review with 'Approved' status
    Then the 'The review has been saved.' message should be displayed above the Admin Page header
    And User navigates to the End User Page
    And User clicks on the 'TV' link in the header navigation
    And User opens the Product Details page for the product <productReview>
    And User clicks on the Review quantity
    Then The Review data should be displayed at the Box Reviews
      | nicknameReview | summaryReview | thoughtReview                                       | qualityRateReview |
      | 'Geni'         | 'Good'        | 'This product is goood,Can be used for a long time' | '3'               |
    Examples:
      | productReview | thoughtReview                                       | summaryReview | qualityRateReview | nicknameReview | reviewAcceptMessage                             |
      | 'Samsung LCD' | 'This product is goood,Can be used for a long time' | 'Good'        | '3'               | 'Geni'         | 'Your review has been accepted for moderation.' |

  @adminSelectCheckbox
  Scenario: Checkbox functionality can work properly
    Given User was on the Admin site
    When User navigates to the Admin Order page from Admin Home Page
    And User selects the 'Select Visible' link at Admin Page
    Then The current selected item count should be 20 at Admin Page
    And The quantity of selected checkboxes should be 20
    And User selects the 'Unselect Visible' link at Admin Page
    Then The current selected item count should be 0 at Admin Page
    And The quantity of selected checkboxes should be 0




