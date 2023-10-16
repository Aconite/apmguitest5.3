@admin @messagetemplate
#noinspection CucumberUndefinedStep

Feature: Administration - Message Templates

  Background: Checks Administration of Message Templates

  Scenario: Add a Message Template
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Admin Menu Message Templates
    Then I check the Message Template required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I add a new Message Template
    Then I take a screenshot "Message Template Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I edit a Message Template
    Then I take a screenshot "Message Template Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I delete a Message Template
    Then Check Message Template scenario

