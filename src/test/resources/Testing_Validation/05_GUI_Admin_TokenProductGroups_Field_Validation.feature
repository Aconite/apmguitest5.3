@validation @validationtokenproductgroups
#noinspection CucumberUndefinedStep

Feature: Administration - Token Product Groups

  Background: Checks Administration of Token Product Groups

  Scenario: Add a Token Product Group
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Product Menu Token Product Groups
    Then I validate the token product group text input fields
    Given I am on the Home Page
    Then I click on the Product Menu Token Product Groups
    Then I add a new Token Product Group with validation data
    Then I take a screenshot "Token Product Group Added"
    Given I am on the Home Page
    Then I click on the Product Menu Token Product Groups
    Then I delete a Token Product Group with validation data
    Then Check token product group scenario
