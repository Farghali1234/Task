Feature: Checkout Functionality

  Scenario: Successful Checkout
    Given I have added products to the cart and am on the checkout page
    When I fill in the checkout information with valid data
      | First Name | Last Name | Postal Code |
      | John       | Doe       | 12345       |  # Example data - Replace with your test data
    And I click the continue button
    Then I should be on the overview page
    And the overview page should display the correct items and totals
      | Product Name             | Price | Quantity |  # Example - Replace with your actual data
      | Sauce Labs Backpack      | 29.99 | 1        |
      | Sauce Labs Fleece Jacket | 49.99 | 1        |
    And I click the finish button
    Then I should see a success message  # Or verify order confirmation page

  Scenario: Invalid Checkout - Missing First Name
    Given I have added products to the cart and am on the checkout page
    When I fill in the checkout information with invalid data
      | First Name | Last Name | Postal Code |
      |          | Doe       | 12345       |  # Missing first name
    And I click the continue button
    Then I should see an error message "First Name is required"  # Replace with the actual error message

  Scenario: Invalid Checkout - Missing Last Name
    Given I have added products to the cart and am on the checkout page
    When I fill in the checkout information with invalid data
      | First Name | Last Name | Postal Code |
      | John       |          | 12345       |  # Missing last name
    And I click the continue button
    Then I should see an error message "Last Name is required"  # Replace with the actual error message

  Scenario: Invalid Checkout - Missing Postal Code
    Given I have added products to the cart and am on the checkout page
    When I fill in the checkout information with invalid data
      | First Name | Last Name | Postal Code |
      | John       | Doe       |          |  # Missing postal code
    And I click the continue button
    Then I should see an error message "Postal Code is required"  # Replace with the actual error message

  Scenario Outline: Invalid Checkout - Data-Driven
    Given I have added products to the cart and am on the checkout page
    When I fill in the checkout information with invalid data
      | First Name | Last Name | Postal Code |
      | <firstName> | <lastName> | <postalCode> |
    And I click the continue button
    Then I should see an error message "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage                   |
      |          | Doe       | 12345       | First Name is required          |
      | John       |          | 12345       | Last Name is required           |
      | John       | Doe       |          | Postal Code is required         |
      | Invalid    | User      | ABCDE       | Postal Code is invalid (Example) | # Example - Replace with actual error message