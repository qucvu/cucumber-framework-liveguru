@userLogin
Feature: User Login

  Background:
    Given User was on the end user site
    When User clicks to 'My Account' link at the footer
    And User click to `Create an Account` button
    Then User should navigate to the Register Page
    And User enters the valid inputs
      | Field            | Value        |
      | First Name       | John         |
      | Last Name        | Doe          |
      | Email            | unique_email |
      | Password         | password123  |
      | Confirm Password | password123  |
    And User click to the `Register` button
    Then The `register successfully` message should be shown
    And User clicks to 'Account' link at the header
    And User clicks to 'Log Out' link at the 'Account' header
    And User clicks to 'Account' link at the header
    And User clicks to 'Log In' link at the 'Account' header

  @loginSuccess
  Scenario: User can successfully log in with their registered data
    Given User was on the Login Page
    And User enters the login credentials
    And User clicks to 'Login' button
    Then The 'welcome' message should be shown for full name: 'John Doe' at Dashboard