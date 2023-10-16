@admin @tokenapplicationprofiles
#noinspection CucumberUndefinedStep

Feature: Administration - Token Application Profiles

  Background: Checks Administration of Token Application Profiles

  Scenario: Add a Token Application Profile
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Product Menu Token App Profiles
    Then I check the Token Application Profile required fields
#    Given I am logged in
#    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I add a new token application profile "newTokenApplicationProfile.json"
    Then I take a screenshot "Token Application Profile Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I edit an existing token application profile
    Then I take a screenshot "Token Application Profile Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I delete a token application profile
    Then Check token application profile scenario
