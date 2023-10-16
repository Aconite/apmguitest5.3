@validation @validationissuers
#noinspection CucumberUndefinedStep

Feature: Administration - Issuers

  Background: Checks Administration of Issuers

  Scenario: Add an Issuer
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu Issuers
    Then I validate the issuer text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu Issuers
    Then I add a new issuer with validation data
    Then I take a screenshot "Institution Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Issuers
    Then I delete an issuer with validation data
    Then Check issuer scenario
