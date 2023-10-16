@scheduledtasks @translatepanid
#noinspection CucumberUndefinedStep
Feature: Translate PAN ID Data Extract in Scheduler Task List
  ############################################################
  # Data: Translate PAN ID
  ############################################################

  Background: Creates and runs a Translate PAN ID Data Extract task and checks logs and database

  Scenario: Add a new translatepaniddataextract task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the translatepaniddataextract extract
    When I add a new daily translatepaniddataextract task
    Then I take a screenshot "Task Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily translatepaniddataextract task
    Then I take a screenshot "Task Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a translatepaniddataextract task
    Then I take a screenshot "Task Run"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the translatepaniddataextract from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the translatepaniddataextract task runs successfully and the output is correct
    Then Check scheduler scenario