@transactionhistorycleanup
#noinspection CucumberUndefinedStep

Feature: Housekeeper Page Tasks - Transaction History Clean Up
  ############################################################
  # Data:
  ############################################################

  Background: Runs a Transaction History Clean Up task from the Housekeeper page and checks UI and database

  Scenario: Run a Transaction History Clean Up task
    Given I am logged in
    Then I click on the Operation Menu Housekeeper
    Then I create expected results for transaction history clean up
    When I run a transaction history clean up task
    Then I take a screenshot "Task Run"
    Then I create actual results for transaction history clean up
    Then The transaction history clean up task runs successfully and the output is correct
    Then Check transactionHistoryCleanUp scenario