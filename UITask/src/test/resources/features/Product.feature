Feature: Product Functionality

  Scenario: Add Two Most Expensive Products to Cart
    Given I am logged in on the products page
    When I add the two most expensive products to the cart
    And I click on the cart button
    Then I should see the products in the cart
      | Product Name             | Price | Quantity |
      | Sauce Labs Backpack      | 29.99 | 1        |  # Example - Replace with actual
      | Sauce Labs Fleece Jacket | 49.99 | 1        |  # Example - Replace with actual

  Scenario: Add a Specific Product to Cart
    Given I am logged in on the products page
    When I add product at index 1 to the cart  # Example index - Change as needed
    And I click on the cart button
    Then I should see the product in the cart
      | Product Name        | Price | Quantity |
      | Sauce Labs Bolt T-Shirt | 15.99 | 1        |  # Example - Replace with actual

  Scenario: Add Multiple Quantities of a Product to Cart
    Given I am logged in on the products page
    When I add product at index 0 to the cart # Example index - Change as needed
    And I add product at index 0 to the cart # Add the same product again
    And I click on the cart button
    Then I should see the product in the cart with correct quantity
      | Product Name        | Price | Quantity |
      | Sauce Labs Onesie    | 9.99  | 2        |  # Example - Replace with actual

  Scenario: No Products Added
    Given I am logged in on the products page
    And I click on the cart button
    Then the cart should be empty

  Scenario Outline: Add Products with Different Data
    Given I am logged in on the products page
    When I add product at index <index> to the cart
    And I click on the cart button
    Then I should see the product in the cart
      | Product Name  | Price | Quantity |
      | <productName> | <price> | 1 |

    Examples:
      | index | productName                | price |
      | 0     | Sauce Labs Bike Light      | 9.99  |
      | 2     | Sauce Labs Bolt T-Shirt   | 15.99 |
      | 4     | Sauce Labs Fleece Jacket  | 49.99 |