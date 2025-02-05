Feature: Login Functionality

  Scenario: Invalid Login - Username and Password
    Given I am on the login page
    When I enter invalid username "invalid_user" and password "invalid_password"
    And I click the login button
    Then I should see the error message "Epic sadface: Username and password do not match any user in this site"

  Scenario: Invalid Login - Empty Username
    Given I am on the login page
    When I enter invalid username "" and password "secret_sauce"  # Empty username
    And I click the login button
    Then I should see the error message "Epic sadface: Username is required" # Or the appropriate error message

  Scenario: Invalid Login - Empty Password
    Given I am on the login page
    When I enter invalid username "standard_user" and password ""  # Empty password
    And I click the login button
    Then I should see the error message "Epic sadface: Password is required" # Or the appropriate error message

  Scenario: Valid Login
    Given I am on the login page
    When I enter valid username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be on the products page  # Or a more specific check (e.g., verify an element on the products page)

  Scenario Outline: Invalid Login - Data-Driven
    Given I am on the login page
    When I enter invalid username "<username>" and password "<password>"
    And I click the login button
    Then I should see the error message "<errorMessage>"

    Examples:
      | username        | password        | errorMessage                                                              |
      | locked_out_user | secret_sauce    | Epic sadface: Sorry, this user has been locked out.                         |
      | standard_user   | wrong_password  | Epic sadface: Username and password do not match any user in this site   |
      |               | secret_sauce    | Epic sadface: Username is required                                     |
      | standard_user   |                 | Epic sadface: Password is required                                     |
      | invalid_user    | invalid_password | Epic sadface: Username and password do not match any user in this site   |