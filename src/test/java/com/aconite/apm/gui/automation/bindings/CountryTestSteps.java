package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.TokenProductGroupDataRecord;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.CountryPage;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.records.CountryDataRecord;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountryTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();
    CountryPage countryPage;
    AdminCommon adminCommon;

    String testDataPath = "";
    String testType = "";

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<CountryDataRecord> validationTestData = new ArrayList<>();
    List<CountryDataRecord> newTestData = new ArrayList<>();

    public CountryDataRecord recordNewCountry = new CountryDataRecord();
    public CountryDataRecord recordEditCountry = new CountryDataRecord();
    public CountryDataRecord recordTableCountry = new CountryDataRecord();
    public CountryDataRecord recordDbCountry = new CountryDataRecord();

    public CountryTestSteps(AbstractSteps abstractSteps) {

        try{
            webDriver = abstractSteps.getDriver();
            countryPage = new CountryPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
//            if(testType.equalsIgnoreCase("Admin")) {
//                recordNewCountry = getJSONData();
//            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationCountry.json");
            }

            reqFields.put("Country code","Country code is required");
            reqFields.put("Country name","Country name is required");

            editFields.add("Country name");
            editFields.add("Country code");

            textFields.add("Country name");
            textFields.add("Country code");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

    }

    private CountryDataRecord getJSONData(String fileName){

        String path = testDataPath + "/" + fileName;

        List<CountryDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                CountryDataRecord outRecord = new CountryDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setCountryCode(j.getString("countryCode"));
                outRecord.setCountryName(j.getString("countryName"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData.get(0);

    }

    private List<CountryDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<CountryDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                CountryDataRecord outRecord = new CountryDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setCountryCode(j.getString("countryCode"));
                outRecord.setCountryName(j.getString("countryName"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Administration Countries Menu")
    public void clickAdminCountries() {

        try{
            Logging.stepName("Click On The Administration Countries Menu");

            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminCountriesMenuItem();

            if(countryPage.isPageOpened()){
                Logging.passMessage("Logged in and on Countries Page");
            }
            else{
                Logging.failMessage("Not logged in and on Countries Page");
                softAssert.fail("Not logged in and on Countries Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the country required fields")
    public void checkRequiredFields(){

        Logging.stepName("Checking Country Required Fields");

        try{

            countryPage.clickAddButton();

            for (String field : reqFields.keySet()){
                boolean rc = countryPage.checkRequiredFields(field, reqFields.get(field));
                if(rc){
                    Logging.passMessage(field + " object contains correct error message: " + reqFields.get(field));
                } else {
                    Logging.failMessage(field + " object does not contain correct error message: " + reqFields.get(field));
                    softAssert.fail(field + " object does not contain correct error message: " + reqFields.get(field));
                }
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I add a new country")
    public void addCountry() {

        try{
            recordNewCountry = getJSONData("newCountry.json");

            recordNewCountry = countryPage.enterCountryData(recordNewCountry);

            if(recordNewCountry.getCountryCode()!=null) {

                recordNewCountry = countryPage.getDbIdByCountryCode(recordNewCountry);

                Logging.stepName("Add Country: Table Data Check");
                recordTableCountry = countryPage.getTableRecordByRecord(recordNewCountry);
                recordTableCountry = countryPage.getDBDataById(recordNewCountry);
                checkRecords(recordNewCountry, recordTableCountry);

                Logging.stepName("Add Country: Database Data Check");
                recordDbCountry = countryPage.getDBDataById(recordNewCountry);
                checkRecords(recordNewCountry, recordDbCountry);

            }
            else{
                Logging.stepName("Add Country");
                Logging.failMessage("Country not added correctly.\n" + recordNewCountry.getTestOutput());
                softAssert.fail("Country not added correctly.\n" + recordNewCountry.getTestOutput());
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check new country functionality \"([^\"]*)\"$")
    public void checkCountryFunctionalityList(String filename) {

        newTestData = getJSONDataToList(filename);

        try{

            for(int i=0; i<newTestData.size();i++) {

                recordNewCountry = newTestData.get(i);

                addCountry();
                AbstractSteps.i_take_a_screenshot("Country_Added_" + adminCommon.getDateTime());

                editCountry();
                AbstractSteps.i_take_a_screenshot("Country_Edited_" + adminCommon.getDateTime());

                deleteCountry();

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit a country")
    public void editCountry(){

        try {

             recordEditCountry = recordNewCountry;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the fields */
                    case ("Country name"):
                        recordEditCountry.setCountryName("North Burpistan");
                        break;

                    case ("Country code"):
                        recordEditCountry.setCountryCode("951");
                        break;
                }

                recordEditCountry = countryPage.editCountryData(recordEditCountry, field);

                if (recordEditCountry.getTestOutput().contains("UNSUCCESSFUL")) {
                    Logging.stepName("Edit Country");
                    Logging.failMessage("Edit Country: Data Check: " + field +
                            "\n" + recordEditCountry.getTestOutput());
                    softAssert.fail("Edit Country: Data Check: " + field +
                            "\n" + recordEditCountry.getTestOutput());

                } else {
                    Logging.stepName("Edit Country: Table Data Check: " + field);
                    recordTableCountry = countryPage.getTableRecordByRecord(recordEditCountry);
                    checkRecords(recordEditCountry, recordTableCountry);

                    Logging.stepName("Edit Country: Database Data Check: " + field);
                    recordDbCountry = countryPage.getDBDataById(recordEditCountry);
                    checkRecords(recordEditCountry, recordDbCountry);
                }

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then("I delete a country")
    public void deleteCountry() {

        try {

            Logging.stepName("Delete Country");

            recordEditCountry = countryPage.getDbIdByCountryCode(recordEditCountry);

            /* Sort or Filter for records */
            adminCommon.sortTableByColumn("Country code","DESC");

            /* Delete the newly created country */
            if(adminCommon.clickDeleteByUniqueId(recordEditCountry.getDbId())) {

                /* Check for dialogs */
                recordEditCountry = countryPage.checkAndDeleteCountry(recordEditCountry);

                if (recordEditCountry.getTestOutput().contains("Delete Country: SUCCESSFUL")) {

                    Logging.stepName("Delete Country: Table Deletion Check");
                    recordTableCountry = countryPage.getTableRecordByRecord(recordEditCountry);
                    if (recordTableCountry.getCountryCode() != null || recordTableCountry.getCountryName() != null) {
                        Logging.failMessage("Country ID " + recordEditCountry.getCountryCode() + " still exists in the table.");
                        softAssert.fail("Country ID " + recordEditCountry.getCountryCode() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Country ID " + recordEditCountry.getCountryCode() + " deleted from table");
                    }

                    Logging.stepName("Delete Country: Database Deletion Check");
                    recordDbCountry = countryPage.getDBDataById(recordEditCountry);
                    if (recordDbCountry.getCountryCode() != null) {
                        Logging.failMessage("Country ID " + recordEditCountry.getCountryCode() + " still exists in the database.");
                        softAssert.fail("Country ID " + recordEditCountry.getCountryCode() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Country ID " + recordEditCountry.getCountryCode() + " deleted from database");
                    }
                }
                else{
                    Logging.failMessage("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI.\n" + recordEditCountry.getTestOutput());
                    softAssert.fail("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI.\n" + recordEditCountry.getTestOutput());
                }

            }
            else{
                Logging.failMessage("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI. Check if country code is correct.");
                softAssert.fail("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI. Check if country code is correct.");
            }


        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(CountryDataRecord record1, CountryDataRecord record2) {

        if (record1 == null) {
            softAssert.fail("Record 1 data is NULL");
            Logging.failMessage("Record 1 data is NULL");
            return;
        }

        if (record2 == null) {
            softAssert.fail("Record 2 data is NULL");
            Logging.failMessage("Record 2 data is NULL");
            return;
        }


        // Check the records match
        if (record1.equals(record2)) {
            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = " + record1 +
                    "\nRecord 2 Data = " + record2);
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = " + record1 +
                    "\nRecord 2 Data = " + record2);
        }

    }

    @Then("I validate the country text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Encryption Zones Text Input Fields");

        try{

            countryPage.clickAddButton();

            for (String field : textFields){

                msg = countryPage.validateTextInputFields(field);
                if(!msg.contains("FAIL")){
                    Logging.passMessage(field + " object contains correct error messages:\n" + msg);
                } else {
                    Logging.failMessage(field + " object does not contain correct error messages:\n" + msg);
                    softAssert.fail(field + " object does not contain correct error messages:\n" + msg);
                }
                adminCommon.hardWait(5000);
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I add a new Country with validation data")
    public void addSpecialCharacterCountry() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewCountry = countryPage.enterCountryData(validationTestData.get(i));


                if(recordNewCountry.getCountryCode()!=null) {
                    recordNewCountry = countryPage.getDbIdByCountryCode(recordNewCountry);

                    recordNewCountry = countryPage.getDbIdByCountryCode(recordNewCountry);

                    Logging.stepName("Special Characters Add Country: Table Data Check");
                    recordTableCountry = countryPage.getTableRecordByRecord(recordNewCountry);
                    recordTableCountry = countryPage.getDBDataById(recordNewCountry);
                    checkRecords(recordNewCountry, recordTableCountry);

                    Logging.stepName("Special Characters Add Country: Database Data Check");
                    recordDbCountry = countryPage.getDBDataById(recordNewCountry);
                    checkRecords(recordNewCountry, recordDbCountry);

                }
                else{
                    Logging.stepName("Special Characters Add Country");
                    Logging.failMessage("Special Characters Add Country not added correctly.\n" + recordNewCountry.getTestOutput());
                    softAssert.fail("Special Characters Add Country not added correctly.\n" + recordNewCountry.getTestOutput());
                }

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I delete a Country with validation data")
    public void deleteSpecialCharacterInterface() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Country");
                recordEditCountry = validationTestData.get(i);

                /* Sort or Filter for records */
                adminCommon.sortTableByColumn("Country code","DESC");

                /* Delete the newly created country */
                if(adminCommon.clickDeleteByColumn("Country code", recordEditCountry.getCountryCode())) {

                    /* Check for dialogs */
                    recordEditCountry = countryPage.checkAndDeleteCountry(recordEditCountry);

                    if (recordEditCountry.getTestOutput().contains("Delete Country: SUCCESSFUL")) {

                        Logging.stepName("Delete Country: Table Deletion Check");
                        recordTableCountry = countryPage.getTableRecordByRecord(recordEditCountry);
                        if (recordTableCountry.getCountryCode() != null || recordTableCountry.getCountryName() != null) {
                            Logging.failMessage("Country ID " + recordEditCountry.getCountryCode() + " still exists in the table.");
                            softAssert.fail("Country ID " + recordEditCountry.getCountryCode() + " still exists in the table.");
                        } else {
                            Logging.passMessage("Country ID " + recordEditCountry.getCountryCode() + " deleted from table");
                        }

                        Logging.stepName("Delete Country: Database Deletion Check");
                        recordDbCountry = countryPage.getDBDataById(recordEditCountry);
                        if (recordDbCountry.getCountryCode() != null) {
                            Logging.failMessage("Country ID " + recordEditCountry.getCountryCode() + " still exists in the database.");
                            softAssert.fail("Country ID " + recordEditCountry.getCountryCode() + " still exists in the database.");
                        } else {
                            Logging.passMessage("Country ID " + recordEditCountry.getCountryCode() + " deleted from database");
                        }
                    }
                    else{
                        Logging.failMessage("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI.\n" + recordEditCountry.getTestOutput());
                        softAssert.fail("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI.\n" + recordEditCountry.getTestOutput());
                    }
                }
                else{
                    Logging.failMessage("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI. Check if country code is correct.");
                    softAssert.fail("Deletion of Country ID " + recordEditCountry.getCountryCode() + " failed in the GUI. Check if country code is correct.");
                }

            }
        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    /*-----------------------------------------------------
     Permissions Tests
     -----------------------------------------------------*/
    @Then("I check the user can access the Country page")
    public void check_access_to_country_page(){

        /* */


    }



//    Then I check the user can add a Country
//    Then I check the user can edit a Country
//    Then I check the user can delete a Country
//    Then I check the user can not add a Country
//    Then I check the user can not edit a Country
//    Then I check the user can not delete a Country

    @Then("Check Country scenario")
    public void checkCountryScenario()  {
        Logging.dataMsg(recordEditCountry.getTestOutput());
        softAssert.assertAll();
    }



}
