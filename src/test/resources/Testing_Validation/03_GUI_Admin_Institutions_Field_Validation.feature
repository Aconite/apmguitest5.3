@validation @validationinstitutions
#noinspection CucumberUndefinedStep

Feature: Administration - Institutions

  Background: Checks Administration of Institutions

  Scenario: Validates inputs into text fields in the Institutions Page
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu Institutions
    Then I validate the institutions text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu Institutions
    Then I add a new institution with validation data
    Then I take a screenshot "Institution Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Institutions
    Then I delete an institution with validation data
    Then Check institution scenario
