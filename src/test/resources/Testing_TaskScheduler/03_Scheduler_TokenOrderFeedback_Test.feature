@scheduledtasks @tokenorderfeedback
#noinspection CucumberUndefinedStep
Feature: Transaction Token Order feedback in Scheduler Task List

  Background: Creates and runs a Token Order Feedback task and checks logs and database

  Scenario: Add a new tokenorderfeedback task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the tokenorderfeedback extract
    When I add a new daily tokenorderfeedback task
    Then I take a screenshot "Task Added"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily tokenorderfeedback task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a tokenorderfeedback task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the tokenorderfeedback from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the tokenorderfeedback task runs successfully and the output is correct
    Then Check scheduler scenario