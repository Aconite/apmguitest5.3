@admin @pvv
#noinspection CucumberUndefinedStep

Feature: Administration - PVV

  Background: Checks Administration of PVV

  Scenario: Add a PVV
    Given I set test type "Admin"
    Given I am logged in
    Then I click on the Product Menu Additional PVVs
    Then I check the pvv required fields
    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token Product Groups
    Then I add a new token product group "pvvTokenProductGroup.json"
    Then I take a screenshot "Token Product Group for PVV Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I add a new token product "pvvTokenProduct.json"
    Then I take a screenshot "Token Product Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I add a new token application profile "pvvTokenApplicationProfile.json"
    Then I take a screenshot "Token Application Profile Added"
    Given I am on the Home Page
    Then I click on the Product Menu Additional PVVs
    Then I add a new pvv "newPvv.json"
    Then I take a screenshot "PVV Added"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Additional PVVs
    Then I edit an existing pvv
    Then I take a screenshot "PVV Edited"
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Additional PVVs
    Then I delete a pvv
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token App Profiles
    Then I delete the pvv parent application profile
#    Given I am logged in
    Given I am on the Home Page
    Then I click on the Products Menu Token Products
    Then I delete the pvv parent token product
    #    Given I am logged in
    Given I am on the Home Page
    Then I click on the Product Menu Token Product Groups
    Then I delete the pvv parent token product group
    Then Check pvv scenario
