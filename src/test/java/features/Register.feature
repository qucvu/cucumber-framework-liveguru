@register
Feature: User Register

  Background:
    Given User was on the end user site
    When User click to 'My Account' link at the footer
    And User click to `Create an Account` button
    Then User should navigate to the Register Page

  @test
  Scenario: User can register successfully and login
    Given User enters the valid inputs
      | Field            | Value        |
      | First Name       | John         |
      | Last Name        | Doe          |
      | Email            | unique_email |
      | Password         | password123  |
      | Confirm Password | password123  |
    And User click to the `Register` button
    Then The `register successfully` message should be shown

  @test2
  Scenario: User can register successfully and login
    Given User enters the valid inputs
      | Field            | Value        |
      | First Name       | John         |
      | Last Name        | Doe          |
      | Email            | unique_email |
      | Password         | password123  |
      | Confirm Password | password123  |
    And User click to the `Register` button
    Then The `register successfully` message should be shown