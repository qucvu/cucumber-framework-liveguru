@userProduct
Feature: User View product, Purchase Product

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
    Then The 'welcome' message should be shown for full name: 'John Doe' at Dashboard

  @equalProductCost
  Scenario Outline: Verify the cost of product in this page and details page are equal
    Given User was on the end user site
    When User clicks on the 'Mobile' link in the header navigation
    And User retrieves the cost of the product <productName> on the Product Category Page
    And User opens the Product Details page for the product <productName>
    And User retrieves the cost of the current product on the Product Details Page
    Then The cost of the product should be the same on both pages
    Examples:
      | productName   |
      | 'Sony Xperia' |

  @discountProduct
  Scenario Outline: Verify discount works properly
    Given User was on the end user site
    When User clicks on the 'Mobile' link in the header navigation
    And User retrieves the cost of the product <productName> on the Product Category Page
    And User opens the Product Details page for the product <productName>
    And User clicks on the 'Add to Cart' button
    Then The 'Add to cart success' message should be shown by product <productName>
    And User applies discount code: <couponCode>
    Then The discount price should be displayed with value <discountPrice>
    And The 'Grand Total' price should be displayed with value <grandTotalAfterDiscount>

    Examples:
      | productName   | couponCode | discountPrice | grandTotalAfterDiscount |
      | 'Sony Xperia' | 'GURU50'   | 5.00          | 95.00                   |

  @limitItemProduct
  Scenario Outline: Verify user can not add more 500 items of product
    Given User was on the end user site
    When User clicks on the 'Mobile' link in the header navigation
    And User retrieves the cost of the product <productName> on the Product Category Page
    And User opens the Product Details page for the product <productName>
    And User clicks on the 'Add to Cart' button
    Then The 'Add to cart success' message should be shown by product <productName>
    And User enters 'Quantity' textbox with value: <quantityValue> of product: <productName>
    And User clicks on 'Update' button by Product: <productName>
    Then The error message: <errorMessage> should be shown under the Shopping Cart Title
    Then The 'Maximum Allowed Quantity' message should be shown under product <productName>
    And User clicks on 'Empty Cart' button
    Then The 'Shopping Cart is Empty' message should be shown
    Then The 'Cart Empty' description should be shown

    Examples:
      | productName   | quantityValue | errorMessage                                                    |
      | 'Sony Xperia' | '501'         | 'Some of the products cannot be ordered in requested quantity.' |

  @compareProducts
  Scenario Outline: Verify user can compare 2 products properly
    Given User was on the end user site
    When User clicks on the 'Mobile' link in the header navigation
    And User clicks to 'Add to Compare' link for the product: <productCompare1>
    Then The `Add to comparison list success` should be displayed for the product: <productCompare1>
    And User clicks to 'Add to Compare' link for the product: <productCompare2>
    Then The `Add to comparison list success` should be displayed for the product: <productCompare2>
    And User clicks to the `Compare` button
    Then The title of the window should be 'COMPARE PRODUCTS'
    Then The product information for <productCompare1> including <productPriceCompare1>, <productSKUCompare1>, and <productImageCompare1> should be displayed properly
    Then The product information for <productCompare2> including <productPriceCompare2>, <productSKUCompare2>, and <productImageCompare2> should be displayed properly
    And User closes the `Comparison Product` window

    Examples:
      | productCompare1 | productPriceCompare1 | productSKUCompare1 | productImageCompare1 | productCompare2 | productPriceCompare2 | productImageCompare2 | productSKUCompare2 |
      | 'Sony Xperia'   | '$100.00'            | 'MOB001'           | 'xperia.jpg'         | 'IPhone'        | '$500.00'            | 'iphone.png'         | 'MOB0002'          |

  @productShareWishList
  Scenario Outline: Verify user can share Wishlist
    Given User was on the end user site
    When User clicks on the 'TV' link in the header navigation
    And User clicks on the `Add to Wishlist` button for the product: <productShareWishlist>
    Then The 'Add product to wishlist success' message should be displayed for the product: <productShareWishlist>
    Then The 'My Wishlist' tab should be active
    And User clicks on the `Share Wishlist` button
    And User enters the `Wishlist` data field with email: <emailShareWishlist> and message: <messageShareWishlist>
    And User clicks on the `Share Wishlist` button
    Then The 'Share wishlist' success message should be displayed
    Then The 'My Wishlist' page should show the product item: <productShareWishlist>
    Examples:
      | productShareWishlist | emailShareWishlist     | messageShareWishlist |
      | 'LG LCD'             | 'quocvuu4@yopmail.com' | 'test'               |

  @reviewProduct
  Scenario Outline: Verify user can share Wishlist
    Given User was on the end user site
    When User clicks on the 'TV' link in the header navigation
    And User opens the Product Details page for the product <productReview>
    And User clicks on the `Add to review` link
    And User leaves the `Review` data fields empty
    And User clicks on the `Submit Review` button
    Then The `Required field` message should be displayed under each data field: Rate, Thought, Summary, Nickname
    And User fills in the `Review` data fields with Nickname: <nicknameReview>, Thought: <thoughtReview>, Summary: <summaryReview>, Rate Quality: <qualityRateReview>
    And User clicks on the `Submit Review` button
    Then The `<reviewAcceptMessage>` message should be displayed

    Examples:
      | productReview | thoughtReview                                       | summaryReview | qualityRateReview | nicknameReview | reviewAcceptMessage                             |
      | 'Samsung LCD' | 'This product is goood,Can be used for a long time' | 'Good'        | '3'               | 'Geni'         | 'Your review has been accepted for moderation.' |

  @purchaseProduct
  Scenario Outline: Verify user can purchase a new product
    Given User was on the end user site
    When User clicks on the 'TV' link in the header navigation
    And User clicks on the `Add to Wishlist` button for the product: <productName>
    Then The 'Add product to wishlist success' message should be displayed for the product: <productName>
    Then The 'My Wishlist' tab should be active
    And User clicks on the 'Add to Cart' button for the product: <productName>
    And User enters valid Estimate Shipping data with Country: <country>, State: <state>, and Zip code: <zipCode>
    And User clicks on the `Estimate` button
    Then The Shipping Cost should be displayed with value: <shippingCost>
    And User selects the 'Flat Rate' shipping option
    And User clicks on the 'Update Total' button
    Then The Shipping Cost should be displayed in the cart total as: <shippingCost>
    Then The Grand Total price should be displayed with value: <grandTotal>
    And User clicks on the 'Proceed to Checkout' button
    And User enters new valid `Billing Address` data with First Name: <firstName>, Last Name: <lastName>, Address: <address>, City: <city>, State: <state>, Zip code: <zipCode> and Telephone: <telephone>
    And User clicks on the `Continue` button with Container Id: 'billing-buttons-container'
    And User clicks on the `Continue` button with Container Id: 'shipping-method-buttons-container'
    And User selects the 'Payment Information' radio button with value: <paymentMethod>
    And User clicks on the `Continue` button with Container Id: 'payment-buttons-container'
    And User clicks on the `Place Order` button
    Then The 'order success' message should be displayed and the order number should be generated

    Examples:
      | productName | country         | state      | zipCode | firstName | lastName | address   | city       | telephone  | shippingCost | grandTotal | paymentMethod         |
      | "LG LCD"    | "United States" | "New York" | "5000"  | 'Vu'      | 'Nguyen' | "Address" | "New York" | "15464897" | "$5.00"      | "$620.00"  | "Check / Money order" |
