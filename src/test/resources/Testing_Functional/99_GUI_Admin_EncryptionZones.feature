@admin @zones
#noinspection CucumberUndefinedStep

Feature: Administration - Encryption Zones

  Background: Checks Administration of Encryption Zones

  Scenario: Test Keys Functionality
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Administration Encryption Zones Menu
    Then I check the encryption zone required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Encryption Zones Menu
    Then I add a new encryption zone
    Then I take a screenshot "Encryption Zone Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Encryption Zones Menu
    Then I edit an encryption zone
    Then I take a screenshot "Encryption Zone Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Encryption Zones Menu
    Then I delete an encryption zone
    Then Check Encryption Zone scenario

