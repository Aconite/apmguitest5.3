@validation @validationzones
#noinspection CucumberUndefinedStep

Feature: Administration - Encryption Zones

  Background: Checks Administration of Encryption Zones

  Scenario: Validates inputs into text fields in the Encryption Zones Page
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Administration Encryption Zones Menu
    Then I validate the encryption zones text input fields
    Given I am on the Home Page
    Then I click on the Administration Encryption Zones Menu
    Then I add a new Encryption Zone with validation data
    Then I take a screenshot "Encryption Zone Added"
    Given I am on the Home Page
    Then I click on the Administration Encryption Zones Menu
    Then I delete an Encryption Zone with validation data
    Then Check Encryption Zone scenario

