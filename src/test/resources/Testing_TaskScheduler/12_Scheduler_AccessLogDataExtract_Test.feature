@scheduledtasks @accesslog
  #noinspection CucumberUndefinedStep
Feature: Access Data Log Extract in Scheduler Task List

    Background: Creates and runs an Access Log Data Extract task and checks logs and database

    Scenario: Add a new accesslogdataextract task
      Given I am logged in
      Then I click on the Operation Menu Scheduler
      When I add a new daily accesslogdataextract task
      Then I take a screenshot "Task Added"
      #    Given I am logged in
      Given I am on the Home Page
      Then I click on the Operation Menu Scheduler
      When I edit a new daily accesslogdataextract task
      Then I take a screenshot "Task Edited"
      #    Given I am logged in
      Given I am on the Home Page
      Then I click on the Operation Menu Scheduler
      When I run a accesslogdataextract task
      Then I take a screenshot "Task Run"
      Then the accesslogdataextract task runs successfully and the output is correct
      #    Given I am logged in
      Given I am on the Home Page
      Then I click on the Operation Menu Scheduler
      Then I can delete the accesslogdataextract from the list of tasks
      Then I take a screenshot "Task Deleted"
      Then Check scheduler scenario