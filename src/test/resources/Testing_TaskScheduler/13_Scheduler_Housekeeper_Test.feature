@housekeeper
#noinspection CucumberUndefinedStep

Feature: Housekeeper in Scheduler Task List
  ############################################################
  # Data:
  ############################################################

  Background: Creates and runs a Housekeeper task and checks files and database

  Scenario: Add a new housekeeper task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create my expected results
    When I add a new daily housekeeper task
    Then I take a screenshot "Task Added"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily housekeeper task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a housekeeper task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the housekeeper from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then I create my actual results
    Then the housekeeper task runs successfully and the output is correct
    Then Check scheduler scenario