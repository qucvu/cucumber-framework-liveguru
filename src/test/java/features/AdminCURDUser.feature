@adminCrudUSer
Feature: Admin show data user properly

  Background: User navigate to the Manage Customer Page
    Given User was on the Admin site
    When User enters the valid login credentials on the Admin Login Page
    And User clicks the `Login` button on the Admin Login Page
    And User dismisses the `Incoming Message` popup if it appears
    Then The `Manage Customers` title should be displayed

  @adminCreatedUser
  Scenario Outline: Registered user can show data at Admin Page
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

    Examples:
      | firstName | lastName | email        | password    | confirmPassword |
      | John      | Doe      | unique_email | password123 | password123     |

  @adminUpdatedUser
  Scenario Outline: Updated user can show data at Admin Page
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
    And User click to 'Account Information' link at the left sidebar
    And User enters the Account Information with Email '<editedEmail>', First Name '<editedFirstName>', Last Name '<editedLastName>'
    And User clicks on the `Save` button
    Then The "The account information has been saved." message should be displayed under the Page Title
    And User navigates to the Admin Page
    Then The account data include Email: '<editedEmail>', First Name: '<editedFirstName>', Last Name: '<editedLastName>'
    Examples:
      | firstName | lastName | email        | password    | confirmPassword | editedFirstName | editedLastName | editedEmail   |
      | John      | Doe      | unique_email | password123 | password123     | Vu              | Nguyen         | quocvu@b3.com |

  @adminReviewUser
  Scenario Outline: User add a review can show da
  ta at Admin Page
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
    And User clicks on the 'TV' link in the header navigation
    And User opens the Product Details page for the product '<productReview>'
    And User clicks on the `Add to review` link
    And User fills in the `Review` data fields with Nickname: '<nicknameReview>', Thought: '<thoughtReview>', Summary: '<summaryReview>', Rate Quality: '<qualityRateReview>'
    And User clicks on the `Submit Review` button
    Then The `<reviewAcceptMessage>` message should be displayed
    And User navigates to the Admin Page
    And User navigates to the Admin Manage Review page from Admin Home Page
    Then the Review data should be displayed properly with Nickname "<nicknameReview>", Summary "<summaryReview>", and Thought "<thoughtReview>"

    Examples:
      | firstName | lastName | email        | password    | confirmPassword | productReview | thoughtReview                                     | summaryReview | qualityRateReview | nicknameReview | reviewAcceptMessage                             |
      | John      | Doe      | unique_email | password123 | password123     | Samsung LCD   | This product is goood,Can be used for a long time | Good          | 3                 | Geni           | 'Your review has been accepted for moderation.' |

  @adminDeleteUser
  Scenario Outline: User add a review can show data at Admin Page
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
    And User navigates to the Admin Page
    And User navigates to the Admin Manage Customer page from the Admin Home Page
    And User selects the Customer checkbox by email "<email>"
    Then The selected current Customer count in the table should be 1
    And User selects 'Delete' in the Action dropdown
    And User clicks on the Submit button Admin Page
    And User accepts the Delete Alert
    Then the "Total of 1 record(s) were deleted." message should be displayed above the Admin Page header
    Then The account is undisplayed At the Customer Table
      | firstName | lastName | email        |
      | John      | Doe      | unique_email |
    And User navigates to the End User Page
    And User clicks to 'My Account' link at the footer
    And User enters the login credentials
    And User clicks to 'Login' button
    Then The 'Invalid login or password.' message should be displayed under the Login Page Title
    Examples:
      | firstName | lastName | email        | password    | confirmPassword |
      | John      | Doe      | unique_email | password123 | password123     |


