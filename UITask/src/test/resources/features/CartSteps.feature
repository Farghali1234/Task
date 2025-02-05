Feature: Cart Functionality


  Scenario: Add One Product and Verify Quantity
    Given I am logged in and on the products page
    When I add the "Sauce Labs Onesie" to the cart # Replace with actual product name
    And I add the "Sauce Labs Onesie" to the cart # Add the same product again
    And I click on the cart button
    Then I should be on the cart page
    And the cart should contain the product with correct quantity
      | Product Name     | Price | Quantity |
      | Sauce Labs Onesie | 9.99  | 2        |  

  Scenario: Remove Item from Cart
    Given I am logged in and have added "Sauce Labs Backpack" to the cart # Replace with actual product name
    And I click on the cart button
    When I remove the "Sauce Labs Backpack" from the cart  
    Then I should be on the cart page
    And the cart should be empty

  Scenario: Cart is Empty
    Given I am logged in and on the products page
    And I click on the cart button
    Then I should be on the cart page
    And the cart should be empty

  Scenario Outline: Add Products with Different Data
    Given I am logged in and on the products page
    When I add the "<productName>" to the cart
    And I click on the cart button
    Then I should be on the cart page
    And the cart should contain the added item
      | Product Name  | Price | Quantity |
      | <productName> | <price> | 1        |  

    Examples:
      | productName                | price |
      | Sauce Labs Bike Light      | 9.99  |
      | Sauce Labs Bolt T-Shirt   | 15.99 |
      | Sauce Labs Fleece Jacket  | 49.99 |