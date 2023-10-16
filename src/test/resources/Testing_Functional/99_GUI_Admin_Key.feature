@admin @keys
#noinspection CucumberUndefinedStep

Feature: Administration - Keys

  Background: Checks Administration of Keys

  Scenario: Test Keys Functionality
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Administration Keys Menu
    Then I check the keys required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Keys Menu
    Then I add a new key
    Then I take a screenshot "Key Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Keys Menu
    Then I edit a key
    Then I take a screenshot "Key Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Keys Menu
    Then I delete a key
    Then Check Key scenario

