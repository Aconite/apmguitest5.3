@validation @validationsmstemplate
#noinspection CucumberUndefinedStep

Feature: Administration - SMS Templates

  Background: Checks Administration of SMS Templates

  Scenario: Add an SMS Template
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu SMS Templates
    Then I validate the sms template text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Templates
    Then I add a new SMS Template with validation data
    Then I take a screenshot "SMS Template Added"
    Given I am on the Home Page
    Then I click on the Admin Menu SMS Templates
    Then I delete an SMS Template with validation data
    Then Check SMS Template scenario

