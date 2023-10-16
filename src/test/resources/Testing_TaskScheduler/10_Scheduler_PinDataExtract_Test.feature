@scheduledtasks @pindataextract
#noinspection CucumberUndefinedStep
Feature: Pin Data Log Extract in Scheduler Task List
  ############################################################
  # Data: Token Import
  #       PIN Import
  ############################################################

  Background: Creates and runs a PIN Data Extract task and checks logs and database

  Scenario: Add a new pindataextract task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the pindataextract extract
    When I add a new daily pindataextract task
    Then I take a screenshot "Task Added"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily pindataextract task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a pindataextract task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the pindataextract from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the pindataextract task runs successfully and the output is correct
    Then Check scheduler scenario