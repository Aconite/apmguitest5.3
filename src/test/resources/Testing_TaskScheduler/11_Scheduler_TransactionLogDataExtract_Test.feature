@scheduledtasks @transactionlog
#noinspection CucumberUndefinedStep
Feature: Transaction Data Log Extract in Scheduler Task List
  ############################################################
  # Data: Any
  ############################################################

  Background: Creates and runs a Transaction Data Log Extract task and checks logs and database

  Scenario: Add a new transactionlogdataextract task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the transactionlogdataextract extract
    When I add a new daily transactionlogdataextract task
    Then I take a screenshot "Task Added"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily transactionlogdataextract task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a transactionlogdataextract task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the transactionlogdataextract from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the transactionlogdataextract task runs successfully and the output is correct
    Then Check scheduler scenario