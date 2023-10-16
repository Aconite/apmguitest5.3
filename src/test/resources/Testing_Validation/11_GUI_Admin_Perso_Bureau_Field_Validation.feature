@validation @validationpersobureau
#noinspection CucumberUndefinedStep

Feature: Administration - Interfaces - Perso Bureau

  Background: Checks Administration of Interfaces - Perso Bureau

  Scenario: Add a Perso Bureau
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Admin Menu Interfaces Perso Bureau
    Then I validate the perso bureau text input fields
    Given I am on the Home Page
    Then I click on the Admin Menu Interfaces Perso Bureau
    Then I add a new Perso Bureau with validation data
    Then I take a screenshot "Interface Added"
    Given I am on the Home Page
    Then I click on the Admin Menu Interfaces Perso Bureau
    Then I delete a Perso Bureau with validation data
    Then Check Perso Bureau scenario

