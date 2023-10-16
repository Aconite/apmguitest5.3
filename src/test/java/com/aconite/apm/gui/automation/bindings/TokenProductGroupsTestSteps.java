package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.TokenProductGroupDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.TokenProductGroupsPage;
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

public class TokenProductGroupsTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    static String testDataPath = "";
    String testType = "";

    public TokenProductGroupDataRecord recordNewTPG = new TokenProductGroupDataRecord();
    public TokenProductGroupDataRecord recordEditTPG = new TokenProductGroupDataRecord();
    public TokenProductGroupDataRecord recordTableTPG = new TokenProductGroupDataRecord();
    public TokenProductGroupDataRecord recordDbTPG = new TokenProductGroupDataRecord();

    TokenProductGroupsPage tokenProductGroupsPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<TokenProductGroupDataRecord> validationTestData = new ArrayList<>();

    public TokenProductGroupsTestSteps(AbstractSteps abstractSteps){

        try {

            webDriver = abstractSteps.getDriver();
            tokenProductGroupsPage = new TokenProductGroupsPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewTPG = getJSONData("newTokenProductGroup.json");
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationTokenProductGroup.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("Issuer","Issuer is required");
            reqFields.put("GroupCode","Group code is required");

            editFields.add("Name");
            editFields.add("Issuer");
            editFields.add("GroupCode");

            textFields.add("Name");
            textFields.add("GroupCode");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    static TokenProductGroupDataRecord getJSONData(String filename){

        TokenProductGroupDataRecord outRecord = new TokenProductGroupDataRecord();
        String path = TokenProductGroupsTestSteps.testDataPath + "/" + filename;

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setParentInstitution((String) inst.get("parentInstitution"));
            outRecord.setIssuer((String) inst.get("issuer"));
            outRecord.setGroupCode((String) inst.get("groupCode"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<TokenProductGroupDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<TokenProductGroupDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                TokenProductGroupDataRecord outRecord = new TokenProductGroupDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setParentInstitution(j.getString("parentInstitution"));
                outRecord.setIssuer(j.getString("issuer"));
                outRecord.setGroupCode(j.getString("groupCode"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Product Menu Token Product Groups")
    public void clickProductsTokenProductGroups() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnProductsTokenProductGroupsMenuItem();

            Logging.stepName("Click On Products Token Product Groups Menu Item");

            if(tokenProductGroupsPage.isPageOpened()){
                Logging.passMessage("Logged in and on Token Product Groups Page");
            }
            else{
                Logging.failMessage("Not logged in and on Token Product Groups Page");
                softAssert.fail("Not logged in and on Token Product Groups Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then("I check the token product group required fields")
    public void checkRequiredFields(){

        try{

            tokenProductGroupsPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = tokenProductGroupsPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new token product group \"([^\"]*)\"$")
    public void addTokenProductGroup(String newTokenProductGroupDataFile) {

        try {

            recordNewTPG = getJSONData(newTokenProductGroupDataFile);

            //Enter Details and create
            recordNewTPG = tokenProductGroupsPage.enterTokenProductGroupData(recordNewTPG);

            if(recordNewTPG.getName()!=null) {

                recordNewTPG = tokenProductGroupsPage.getDbIdByTokenProductGroupName(recordNewTPG);

                Logging.stepName("Add Token Product Group: Table Data Check");
                recordTableTPG = tokenProductGroupsPage.getTableRecordByRecord(recordNewTPG);
                checkRecords(recordNewTPG, recordTableTPG);

                Logging.stepName("Add Token Product Group: Database Data Check");
                recordDbTPG = tokenProductGroupsPage.getDBDataById(recordNewTPG);
                checkRecords(recordNewTPG, recordDbTPG);
            }
            else{
                Logging.stepName("Add Token Product Group");
                softAssert.fail("Token Product Group not added correctly.\n" + recordNewTPG.getTestOutput());
                Logging.failMessage("Token Product Group not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit a token product group")
    public void editTokenProductGroup() {

        try {

            // Create the newly created data as data class
            recordEditTPG = recordNewTPG;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Name"):
                        recordEditTPG.setName("QABank Edit " + adminCommon.getDateTime());
                         break;

                    /* Change the issuer */
                    case ("Issuer"):
                        recordEditTPG.setParentInstitution("QABank");
                        recordEditTPG.setIssuer("QABank Jersey");
                        break;

                    /* Change the Group Code */
                    case ("GroupCode"):
                        recordEditTPG.setGroupCode("TPG FTG 495");
                        break;


                }

                recordEditTPG = tokenProductGroupsPage.editTokenProductGroupData(recordEditTPG, field);

                if(recordEditTPG.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Token Product Group");
                    Logging.failMessage("Edit Token Product Group: Data Check: " + field +
                            "\n" + recordEditTPG.getTestOutput());
                    softAssert.fail("Edit Token Product Group: Data Check: " + field +
                            "\n" + recordEditTPG.getTestOutput());

                }
                else {
                    Logging.stepName("Edit Token Product Group: Table Data Check: " + field);
                    recordTableTPG = tokenProductGroupsPage.getTableRecordByRecord(recordEditTPG);
                    checkRecords(recordEditTPG, recordTableTPG);

                    Logging.stepName("Edit Token Product Group: Database Data Check: " + field);
                    recordDbTPG = tokenProductGroupsPage.getDBDataById(recordEditTPG);
                    checkRecords(recordEditTPG, recordDbTPG);
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

    @Then("I delete a token product group")
    public void deleteTokenProductGroup() {

        try {

            Logging.stepName("Delete Token Product Group");

            // Delete the newly created Token Product Group
            if(adminCommon.clickDeleteByUniqueId(recordEditTPG.getId())) {

                recordEditTPG = tokenProductGroupsPage.checkDeleteTokenProductGroupById(recordEditTPG);

                Logging.stepName("Delete Token Product Group: Table Deletion Check");
                recordTableTPG = tokenProductGroupsPage.getTableRecordByRecord(recordEditTPG);
                if (recordTableTPG.getId() != null) {
                    Logging.failMessage("Token Product Group ID " + recordEditTPG.getId() + " still exists in the table.");
                    softAssert.fail("Token Product Group ID " + recordEditTPG.getId() + " still exists in the table.");
                } else {
                    Logging.passMessage("Token Product Group ID " + recordEditTPG.getId() + " deleted from table");
                }

                Logging.stepName("Delete Token Product Group: Database Deletion Check");
                recordDbTPG = tokenProductGroupsPage.getDBDataById(recordEditTPG);
                if (recordDbTPG.getId() != null) {
                    Logging.failMessage("Token Product Group ID " + recordEditTPG.getId() + " still exists in the database.");
                    softAssert.fail("Token Product Group ID " + recordEditTPG.getId() + " still exists in the database.");
                } else {
                    Logging.passMessage("Token Product Group ID " + recordEditTPG.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Product Group ID " + recordEditTPG.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Product Group ID " + recordEditTPG.getId() + " failed in the GUI.");
            }


        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(TokenProductGroupDataRecord record1, TokenProductGroupDataRecord record2) {

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

    @Then("I validate the token product group text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Encryption Zones Text Input Fields");

        try{

            tokenProductGroupsPage.clickAddButton();

            for (String field : textFields){

                msg = tokenProductGroupsPage.validateTextInputFields(field);
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

    @Then("I add a new Token Product Group with validation data")
    public void addSpecialCharacterTokenProductGroup() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewTPG = validationTestData.get(i);

                //Enter Details and create
                recordNewTPG = tokenProductGroupsPage.enterTokenProductGroupData(recordNewTPG);

                if (recordNewTPG.getName() != null) {

                    recordNewTPG = tokenProductGroupsPage.getDbIdByTokenProductGroupName(recordNewTPG);

                    Logging.stepName("Add Token Product Group: Table Data Check");
                    recordTableTPG = tokenProductGroupsPage.getTableRecordByRecord(recordNewTPG);
                    checkRecords(recordNewTPG, recordTableTPG);

                    Logging.stepName("Add Token Product Group: Database Data Check");
                    recordDbTPG = tokenProductGroupsPage.getDBDataById(recordNewTPG);
                    checkRecords(recordNewTPG, recordDbTPG);
                } else {
                    Logging.stepName("Add Token Product Group");
                    softAssert.fail("Token Product Group not added correctly.\n" + recordNewTPG.getTestOutput());
                    Logging.failMessage("Token Product Group not added correctly.");
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

    @Then("I delete a Token Product Group with validation data")
    public void deleteSpecialCharacterTokenProductGroup() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Token Product Group");
                recordEditTPG = validationTestData.get(i);

                // Delete the newly created Token Product Group
                if(adminCommon.clickDeleteByUniqueId(recordEditTPG.getId())) {

                    recordEditTPG = tokenProductGroupsPage.checkDeleteTokenProductGroupById(recordEditTPG);

                    Logging.stepName("Delete Token Product Group: Table Deletion Check");
                    recordTableTPG = tokenProductGroupsPage.getTableRecordByRecord(recordEditTPG);
                    if (recordTableTPG.getId() != null) {
                        Logging.failMessage("Token Product Group ID " + recordEditTPG.getId() + " still exists in the table.");
                        softAssert.fail("Token Product Group ID " + recordEditTPG.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Token Product Group ID " + recordEditTPG.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Token Product Group: Database Deletion Check");
                    recordDbTPG = tokenProductGroupsPage.getDBDataById(recordEditTPG);
                    if (recordDbTPG.getId() != null) {
                        Logging.failMessage("Token Product Group ID " + recordEditTPG.getId() + " still exists in the database.");
                        softAssert.fail("Token Product Group ID " + recordEditTPG.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Token Product Group ID " + recordEditTPG.getId() + " deleted from database");
                    }

                }
                else{
                    Logging.failMessage("Deletion of Token Product Group ID " + recordEditTPG.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Token Product Group ID " + recordEditTPG.getId() + " failed in the GUI.");
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

    @Then("Check token product group scenario")
    public void checkTokenProductGroupScenario(){
        Logging.dataMsg(recordEditTPG.getTestOutput());
        softAssert.assertAll();
    }


}
