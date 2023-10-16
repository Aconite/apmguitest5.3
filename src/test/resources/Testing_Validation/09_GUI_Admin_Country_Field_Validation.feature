@validation @validationcountry
#noinspection CucumberUndefinedStep

Feature: Administration - Countries

  Background: Checks Administration of Countries

  Scenario: Test Country Functionality
    Given I set test type "Validation"
    Given I am logged in
    Then I click on the Administration Countries Menu
    Then I validate the country text input fields
    Given I am on the Home Page
    Then I click on the Administration Countries Menu
    Then I add a new Country with validation data
    Then I take a screenshot "Country Added"
    Given I am on the Home Page
    Then I click on the Administration Countries Menu
    Then I delete a Country with validation data
    Then Check Country scenario

