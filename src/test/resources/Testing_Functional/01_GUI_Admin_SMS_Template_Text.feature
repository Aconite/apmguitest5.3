@admin @smstemplatetext
#noinspection CucumberUndefinedStep

Feature: Administration - SMS Template Text

  Background: Checks Administration of SMS Template Text

  Scenario: Add an SMS Template Text
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Admin Menu SMS Template Text
    Then I check the SMS Template Text required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Template Text
    Then I add a new SMS Template Text
    Then I take a screenshot "SMS Template Text Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Template Text
    Then I edit an SMS Template Text
    Then I take a screenshot "SMS Template Text Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Template Text
    Then I delete an SMS Template Text
    Then Check SMS Template Text scenario

