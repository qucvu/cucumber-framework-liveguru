@userProduct
Feature: User View product, Purchase Product

  Background:
    Given User was on the end user site
    And User clicks to 'Account' link at the header
    And User clicks to 'Log In' link at the 'Account' header
    And User enters the login credentials
      | email                 | password |
      | 12345678910@gmail.com | 123456   |
    And User clicks to 'Login' button
    And The 'welcome' message should be shown for full name: 'vu nguyen' at Dashboard

  Scenario: Verify the cost of product in this page and details page are equal
    Given User was on the end user site
    And User clicks on the 'Mobile' link in the header navigation
    And User retrieves the cost of the product 'Sony Xperia' on the Product Category Page
    And User opens the Product Details page for the product 'Sony Xperia'
    And User retrieves the cost of the current product on the Product Details Page
    Then The cost of the product should be the same on both pages
