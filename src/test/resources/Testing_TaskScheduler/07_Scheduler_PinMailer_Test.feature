@scheduledtasks @pinmailer
#noinspection CucumberUndefinedStep
Feature: Transaction PIN Mailer in Scheduler Task List
  ############################################################
  # Data: PIN Advice By PAN ID
  ############################################################

  Background: Creates and runs a PIN Mailer task and checks logs and database

  Scenario: Add a new pinmailer task
    Given I am logged in
    Then I click on the Operation Menu Scheduler
    Then I create data for the pinmailer extract
    When I add a new daily pinmailer task
    Then I take a screenshot "Task Added"
   #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I edit a new daily pinmailer task
    Then I take a screenshot "Task Edited"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    When I run a pinmailer task
    Then I take a screenshot "Task Run"
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Operation Menu Scheduler
    Then I can delete the pinmailer from the list of tasks
    Then I take a screenshot "Task Deleted"
    Then the pinmailer task runs successfully and the output is correct
    Then Check scheduler scenario