@validation @validationpvv
#noinspection CucumberUndefinedStep

Feature: Administration - PVV

  Background: Checks Administration of PVV

  Scenario: Add a PVV
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Product Menu Additional PVVs
#    Then I validate the pvv text input fields
    Given I am on the Home Page
    Then I click on the Product Menu Additional PVVs
    Then I add a new PVV with validation data
    Then I take a screenshot "PVV Added"
    Given I am on the Home Page
    Then I click on the Product Menu Additional PVVs
    Then I delete a PVV with validation data
    Then Check pvv scenario
