@userRegister
Feature: User Register

  Background:
    Given User was on the end user site
    When User clicks to 'My Account' link at the footer
    And User click to `Create an Account` button
    Then User should navigate to the Register Page

  @registerSuccess
  Scenario: User can register successfully
    Given User enters the valid inputs
      | Field            | Value        |
      | First Name       | John         |
      | Last Name        | Doe          |
      | Email            | unique_email |
      | Password         | password123  |
      | Confirm Password | password123  |
    And User click to the `Register` button
    Then The `register successfully` message should be shown

  @registerSuccessWithData
  Scenario Outline: User can register successfully with correct data
    Given User enters the valid inputs
      | Field            | Value             |
      | First Name       | <firstName>       |
      | Last Name        | <lastName>        |
      | Email            | <email>           |
      | Password         | <password>        |
      | Confirm Password | <confirmPassword> |
    And User click to the `Register` button
    Then The `register successfully` message should be shown
    And User click to 'Account Information' link at the left sidebar
    Then User should navigate to the 'Account Information' link
    Then User info is displayed properly on the My Account page
      | firstName   | lastName   | email   |
      | <firstName> | <lastName> | <email> |
    Examples:
      | firstName | lastName | email        | password    | confirmPassword |
      | John      | Doe      | unique_email | password123 | password123     |