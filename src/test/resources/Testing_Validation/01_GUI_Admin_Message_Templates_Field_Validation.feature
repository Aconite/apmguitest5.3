@validation @validationmessagetemplate
#noinspection CucumberUndefinedStep

Feature: Administration - Message Templates

  Background: Checks Administration of Message Templates

  Scenario: Add a Message Template
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu Message Templates
    Then I validate the message template text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I add a new Message Template with validation data
    Then I take a screenshot "Message Template Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I delete a Message Template with validation data
    Then Check Message Template scenario

