@userSearch
Feature: User can search product properly with the range price

  Background:
    Given User was on the end user site
    And User clicks to 'Advanced Search' link at the footer

  Scenario Outline: User can search product with range price from <minPriceRange> USD to <maxPriceRange> USD
    Given User was on the User Search Page
    When User enters the Price Range from <minPriceRange> USD to <maxPriceRange> USD
    And User clicks on the `Search` button
    Then The all product should be displayed with the price from <minPriceRange> USD to <maxPriceRange> USD
    Then Crawl data from Product Search from <minPriceRange> USD to <maxPriceRange> USD into the 'dataRecord' Folder
    Examples:
      | minPriceRange | maxPriceRange |
      | 0             | 150           |

  Scenario Outline: User can search product with range price from <minPriceRange> USD to <maxPriceRange> USD
    Given User was on the User Search Page
    When User enters the Price Range from <minPriceRange> USD to <maxPriceRange> USD
    And User clicks on the `Search` button
    Then The all product should be displayed with the price from <minPriceRange> USD to <maxPriceRange> USD
    Then Crawl data from Product Search from <minPriceRange> USD to <maxPriceRange> USD into the 'dataRecord' Folder
    Examples:
      | minPriceRange | maxPriceRange |
      | 151           | 1000          |