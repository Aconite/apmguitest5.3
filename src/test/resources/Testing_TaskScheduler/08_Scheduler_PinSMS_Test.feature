@donotrun
#noinspection CucumberUndefinedStep
Feature: Pin SMS Data Log Extract in Scheduler Task List
  ############################################################
  # Now built in to PinSMSDataExtract
  ############################################################

  Background: Creates and runs a PIN SMS Data Extract task and checks logs and database

  Scenario: Add a new pinsms task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the pinsms extract
    When I add a new daily pinsms task
    Then I take a screenshot "Task Added"
   #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily pinsms task
    Then I take a screenshot "Task Edited"
   #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a pinsms task
    Then I take a screenshot "Task Run"
   #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the pinsms from the list of tasks
    Then I take a screenshot "Task Deleted"