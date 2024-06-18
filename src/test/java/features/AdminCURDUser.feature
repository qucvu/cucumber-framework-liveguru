@adminCrudUSer
Feature: Admin show data user properly

  Background:
    Given User was on the Admin site
    And User enters the valid login credentials on the Admin Login Page
    And User clicks the `Login` button on the Admin Login Page
    And User dismisses the `Incoming Message` popup if it appears
    Then The `Manage Customers` title should be displayed

  @createUser
  Scenario Outline: Registered user can show data at Admin Page
    Given User was on the end user site
    And User clicks to 'My Account' link at the footer
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

    Examples:
      | firstName | lastName | email        | password    | confirmPassword |
      | John      | Doe      | unique_email | password123 | password123     |
