@unusedpinpasswordcleanup
#noinspection CucumberUndefinedStep

Feature: Housekeeper Page Tasks - Unused Pin Password Clean Up
  ############################################################
  # Data:
  ############################################################

  Background: Runs an Unused Pin Password Clean Up task from the Housekeeper page and checks UI and database

  Scenario: Run a Unused Pin Password Clean Up task
    Given I am logged in
    Then I click on the Operation Menu Housekeeper
    Then I create expected results for unused pin password clean up
    When I run an unused pin password clean up task
    Then I take a screenshot "Task Run"
    Then I create actual results for unused pin password clean up
    Then The unused pin password clean up task runs successfully and the output is correct
    Then Check unusedPinPasswordCleanUp scenario