@scheduledtasks @tokenimportfeedback
#noinspection CucumberUndefinedStep
Feature: Transaction Token Import feedback in Scheduler Task List
  ############################################################
  # Data: Token Import
  ############################################################

  Background: Creates and runs a Token Import Feedback task and checks logs and database


  Scenario: Add a new tokenimportfeedback task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the tokenimportfeedback extract
    When I add a new daily tokenimportfeedback task
    Then I take a screenshot "Task Added"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily tokenimportfeedback task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a tokenimportfeedback task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the tokenimportfeedback from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the tokenimportfeedback task runs successfully and the output is correct
    Then Check scheduler scenario