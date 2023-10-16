@validation @validationkeys
#noinspection CucumberUndefinedStep

Feature: Administration - Keys

  Background: Checks Administration of Keys

  Scenario: Validates inputs into text fields in the Keys Page
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Administration Keys Menu
    Then I validate the keys text input fields
    Given I am on the Home Page
    Then I click on the Administration Keys Menu
    Then I add a new Key with validation data
    Then I take a screenshot "Key Added"
    Given I am on the Home Page
    Then I click on the Administration Keys Menu
    Then I delete a Key with validation data
    Then Check Key scenario

