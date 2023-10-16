@validation @validationinterface
#noinspection CucumberUndefinedStep

Feature: Administration - Interfaces - Interfaces

  Background: Checks Administration of Interfaces - Interfaces

  Scenario: Validates inputs into text fields in the Interfaces Page
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Admin Menu Interfaces Interfaces
    Then I validate the interfaces text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu Interfaces Interfaces
    Then I add a new Interface with validation data
    Then I take a screenshot "Interface Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Interfaces Interfaces
    Then I delete an Interface with validation data
    Then Check Interface scenario

