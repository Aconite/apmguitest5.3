@validatetest
#noinspection CucumberUndefinedStep
Feature: Reliability of Extracts in Scheduler Task List

  Background: Creates and runs and checks logs and database

  Scenario: Check tasks run and get data

    Given I am logged in
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu Message Templates
    Then I add a new Message Template with validation data
    Then I take a screenshot "Message Templates Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Message Templates
    Then I delete a Message Template with validation data
    Then Check Message Template scenario