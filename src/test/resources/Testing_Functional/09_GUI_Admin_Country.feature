@admin @country
#noinspection CucumberUndefinedStep

Feature: Administration - Countries

  Background: Checks Administration of Countries

  Scenario: Test Country Functionality
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Administration Countries Menu
    Then I check the country required fields
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Countries Menu
    Then I add a new country
    Then I take a screenshot "Country Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Countries Menu
    Then I edit a country
    Then I take a screenshot "Country Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Administration Countries Menu
    Then I delete a country
    Then Check Country scenario

