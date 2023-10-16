@validation @validationsmstemplatetext
#noinspection CucumberUndefinedStep

Feature: Administration - SMS Template Text

  Background: Checks Administration of SMS Template Text

  Scenario: Add an SMS Template Text
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu SMS Template Text
    Then I validate the sms template text text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Template Text
    Then I add a new SMS Template Text with validation data
    Then I take a screenshot "SMS Template Text Added"
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Template Text
    Then I delete an SMS Template Text with validation data
    Then Check SMS Template Text scenario

