@validation @validationtokenapplicationprofiles
#noinspection CucumberUndefinedStep

Feature: Administration - Token Application Profiles

  Background: Checks Administration of Token Application Profiles

  Scenario: Add a Token Application Profile
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Product Menu Token App Profiles
    Then I validate the token application profile text input fields
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I add a new Token Application Profile with validation data
    Then I take a screenshot "Token Application Profile Added"
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I delete a Token Application Profile with validation data
    Then Check token application profile scenario
