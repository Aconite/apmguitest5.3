@tokencleanup
#noinspection CucumberUndefinedStep

Feature: Housekeeper Page Tasks - Token Clean Up
  ############################################################
  # Data:
  ############################################################

  Background: Runs a Token Clean Up task from the Housekeeper page and checks UI and database

  Scenario: Run a Token Clean Up task
    Given I am logged in
    Then I click on the Operation Menu Housekeeper
    Then I create expected results for token clean up
    When I run a token clean up task for institution "ABCBank"
    Then I take a screenshot "Task Run"
    Then I create actual results for token clean up
    Then The token clean up task runs successfully and the output is correct
    Then Check tokenCleanUp scenario