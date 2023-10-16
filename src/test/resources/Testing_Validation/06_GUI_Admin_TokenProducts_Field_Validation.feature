@validation @validationtokenproducts
#noinspection CucumberUndefinedStep

Feature: Administration - Token Products

  Background: Checks Administration of Token Products

  Scenario: Add a Token Product
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Products Menu Token Products
    Then I validate the token product text input fields
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I add a new Token Product with validation data
    Then I take a screenshot "Token Product Added"
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I delete a Token Product with validation data
    Then Check token product scenario
