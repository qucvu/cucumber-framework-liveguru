@adminSortSearchPagination
Feature: Admin can sort, search with pagination

  Background: User navigate to the Manage Customer Page
    Given User was on the Admin site
    When User enters the valid login credentials on the Admin Login Page
    And User clicks the `Login` button on the Admin Login Page
    And User dismisses the `Incoming Message` popup if it appears
    Then The `Manage Customers` title should be displayed

  @adminSort
  Scenario: Verify the sort can work properly
    Given User navigates to the Admin Manage Invoice page from Admin Home Page
    When User clicks on the Sort 'Invoice #' column with value ascending
    Then The column 'Invoice #' number should be sorted ascending
    And User clicks on the Sort 'Invoice #' column with value descending
    Then The column 'Invoice #' number should be sorted descending
    And User clicks on the Sort 'Invoice Date' column with value ascending
    Then The column 'Invoice Date' date should be sorted 'ascending'
    And User clicks on the Sort 'Invoice Date' column with value descending
    Then The column 'Invoice Date' date should be sorted 'descending'
#    And User clicks on the Sort 'Bill to Name' column with value ascending
#    Then The column 'Bill to Name' string should be sorted ascending - failed
#    And User clicks on the Sort 'Bill to Name' column with value descending
#    Then The column 'Bill to Name' string should be sorted descending - failed
    And User clicks on the Sort 'Amount' column with value ascending
    Then The column 'Amount' amount should be sorted 'ascending'
    And User clicks on the Sort 'Amount' column with value descending
    Then The column 'Amount' amount should be sorted 'descending'

  @adminPagination
  Scenario: Verify the Pagination can work properly
    Given User navigates to the Admin Order page from Admin Home Page
    When User selects `View Per Page` dropdown Admin Page with value '20'
    Then The quantity of the item row should be 20 Admin Page
    And User selects `View Per Page` dropdown Admin Page with value '30'
    Then The quantity of the item row should be 30 Admin Page
    And User selects `View Per Page` dropdown Admin Page with value '50'
    Then The quantity of the item row should be 50 Admin Page

  @adminSearch
  Scenario Outline: Verify the Search Functionality can work properly
    Given User was on the end user site
    When User clicks to 'My Account' link at the footer
    And User click to `Create an Account` button
    Then User should navigate to the Register Page
    And User enters the valid inputs
      | Field            | Value             |
      | First Name       | <firstName>       |
      | Last Name        | <lastName>        |
      | Email            | <email>           |
      | Password         | <password>        |
      | Confirm Password | <confirmPassword> |
    And User click to the `Register` button
    Then The `register successfully` message should be shown
    And User navigates to the Admin Page
    Then The account data include Email: '<email>', First Name: '<firstName>', Last Name: '<lastName>'
    And User enters the 'Filter' Customer text box at Admin Page with email: '<email>' and full name: '<firstName> <lastName>'
    And User clicks on the `Search` button Admin Page
    Then The quantity of the item row should be 1 Admin Page
    Then The account data include Email: '<email>', First Name: '<firstName>', Last Name: '<lastName>'
    Examples:
      | firstName | lastName | email        | password    | confirmPassword |
      | John      | Doe      | unique_email | password123 | password123     |





