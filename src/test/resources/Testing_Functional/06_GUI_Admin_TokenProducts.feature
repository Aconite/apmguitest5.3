@admin @tokenproducts
#noinspection CucumberUndefinedStep

Feature: Administration - Token Products

  Background: Checks Administration of Token Products

  Scenario: Add a Token Product
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Products Menu Token Products
    Then I check the token product required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I add a new token product "newTokenProduct.json"
    Then I take a screenshot "Token Product Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I edit an existing token product
    Then I take a screenshot "Token Product Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I delete a token product
    Then Check token product scenario
