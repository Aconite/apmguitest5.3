package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.InstitutionDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.InstitutionsPage;
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

public class InstitutionsTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public InstitutionDataRecord recordNewInst = new InstitutionDataRecord();
    public InstitutionDataRecord recordEditInst = new InstitutionDataRecord();
    public InstitutionDataRecord recordTableInst = new InstitutionDataRecord();
    public InstitutionDataRecord recordDbInst = new InstitutionDataRecord();

    InstitutionsPage institutionsPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    List<String> textFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<InstitutionDataRecord> validationTestData = new ArrayList<>();

    public InstitutionsTestSteps(AbstractSteps abstractSteps) {

        try {

            webDriver = abstractSteps.getDriver();
            institutionsPage = new InstitutionsPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            // Code for getting data from json
            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            System.out.println("Creation: " + testDataPath);
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewInst = getJSONData("newInstitution.json");
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationInstitutions.json");
            }

            reqFields.put("Name","Institution name is required");
            reqFields.put("LocalZone","Local Zone is required");
            reqFields.put("InstitutionZone","Institution Zone is required");
            reqFields.put("InterfaceZone","Interface Zone is required");
            reqFields.put("MaxVppPinIdSeconds","Max VPP PIN ID seconds is required");

            editFields.add("Name");
            editFields.add("LocalZone");
            editFields.add("InstitutionZone");
            editFields.add("InterfaceZone");
            editFields.add("MaxVppPinIdSeconds");

            textFields.add("Name");
            textFields.add("Max VPP PIN ID seconds");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private InstitutionDataRecord getJSONData(String fileName){

        InstitutionDataRecord outRecord = new InstitutionDataRecord();
        String path = testDataPath + "/" + fileName;

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            System.out.println(inst.get("localZone"));
            outRecord.setInstitutionName((String) inst.get("institutionName"));
            outRecord.setLocalZone((String) inst.get("localZone"));
            outRecord.setInstitutionZone((String) inst.get("institutionZone"));
            outRecord.setInterfaceZone((String) inst.get("interfaceZone"));
            outRecord.setMaxVppPinIdSeconds((String) inst.get("maxVppPinIdSeconds"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<InstitutionDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<InstitutionDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                InstitutionDataRecord outRecord = new InstitutionDataRecord();
                JSONObject j = result.getJSONObject(i);

                System.out.println("Record " + i + " Name: " + j.getString("institutionName"));
                outRecord.setInstitutionName(j.getString("institutionName"));
                outRecord.setLocalZone(j.getString("localZone"));
                outRecord.setInstitutionZone(j.getString("institutionZone"));
                outRecord.setInterfaceZone(j.getString("interfaceZone"));
                outRecord.setMaxVppPinIdSeconds(j.getString("maxVppPinIdSeconds"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu Institutions")
    public void clickAdminInstitutions() {

        try{

            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminInstitutionsMenuItem();

            Logging.stepName("Click On Admin Institution Menu Item");

            if(institutionsPage.isPageOpened()){
                Logging.passMessage("Logged in and on Institution Page");
            }
            else{
                Logging.failMessage("Not logged in and on Institution Page");
                softAssert.fail("Not logged in and on Institution Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

     @Then("I check the institution required fields")
    public void checkRequiredFields(){

        Logging.stepName("Checking Institution Required Fields");

        try{

            institutionsPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = institutionsPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new institution")
    public void addInstitution() {

        try {

            recordNewInst = institutionsPage.enterInstitutionData(recordNewInst);

            if(recordNewInst.getInstitutionName()!=null) {

                recordNewInst = institutionsPage.getDbIdByInstitutionName(recordNewInst);

                Logging.stepName("Add Institution: Table Data Check");
                recordTableInst = institutionsPage.getTableDataById(recordNewInst);
                checkRecords(recordNewInst, recordTableInst);

                Logging.stepName("Add Institution: Database Data Check");
                recordDbInst = institutionsPage.getDBDataById(recordNewInst.getId());
                checkRecords(recordNewInst, recordDbInst);
            }
            else{
                Logging.stepName("Add Institution");
                Logging.failMessage("Institution not added correctly.\n" + recordNewInst.getTestOutput());
                softAssert.fail("Institution not added correctly.");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an institution")
    public void editInstitution() {

        try {

            recordEditInst = recordNewInst;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Name"):
                        recordEditInst.setInstitutionName("Bank of Dave " + adminCommon.getDateTime());
                        break;

                    /* Change the local zone */
                    case ("LocalZone"):
                        recordEditInst.setLocalZone("ABCBank Export");
                        break;

                    /* Change the institution zone */
                    case ("InstitutionZone"):
                        recordEditInst.setInstitutionZone("ABCBank Import");
                        break;

                    /* Change the interface zone */
                    case ("InterfaceZone"):
                        recordEditInst.setInterfaceZone("ABCBank Message");
                        break;

                    /* Change the MaxVppPinIdSeconds */
                    case ("MaxVppPinIdSeconds"):
                        recordEditInst.setMaxVppPinIdSeconds("1200");
                        break;
                }

                recordEditInst = institutionsPage.editInstitutionData(recordEditInst,field);

                if(recordEditInst.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Institution");
                    Logging.failMessage("Edit Institution: Data Check: " + field +
                            "\n" + recordEditInst.getTestOutput());
                    softAssert.fail("Edit Institution: Data Check: " + field +
                            "\n" + recordEditInst.getTestOutput());
                }
                else {

                    Logging.stepName("Edit Institution: Table Data Check: " + field);
                    recordTableInst = institutionsPage.getTableDataById(recordEditInst);
                    checkRecords(recordEditInst, recordTableInst);

                    Logging.stepName("Edit Institution: Database Data Check: " + field);
                    recordDbInst = institutionsPage.getDBDataById(recordEditInst.getId());
                    checkRecords(recordEditInst, recordDbInst);

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

    @Then("I delete an institution")
    public void deleteInstitution() {

        try {
            Logging.stepName("Delete Institution");

            /* Delete the newly created Institution */
            if(adminCommon.clickDeleteByUniqueId(recordEditInst.getId())){

                /* Check for dialogs */
                recordEditInst = institutionsPage.checkAndDeleteInstitution(recordEditInst);

                Logging.stepName("Delete Institution: Table Deletion Check");
                recordTableInst = institutionsPage.getTableDataById(recordEditInst);
                if (recordTableInst.getId() != null) {
                    Logging.failMessage("Institution ID " + recordEditInst.getId() + " still exists in the table.");
                    softAssert.fail("Institution ID " + recordEditInst.getId() + " still exists in the table.");
                } else {
                    Logging.passMessage("Institution ID " + recordEditInst.getId() + " deleted from table");
                }

                Logging.stepName("Delete Institution: Database Deletion Check");
                recordDbInst = institutionsPage.getDBDataById(recordEditInst.getId());
                if (recordDbInst.getId() != null) {
                    Logging.failMessage("Institution ID " + recordEditInst.getId() + " still exists in the database.");
                    softAssert.fail("Institution ID " + recordEditInst.getId() + " still exists in the database.");
                } else {
                    Logging.passMessage("Institution ID " + recordEditInst.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Institution ID " + recordEditInst.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Institution ID " + recordEditInst.getId() + " failed in the GUI.");
            }
        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(InstitutionDataRecord record1, InstitutionDataRecord record2) {

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

    @Then("I validate the institutions text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Institution Text Input Fields");

        try{

            institutionsPage.clickAddButton();

            for (String field : textFields){

                msg = institutionsPage.validateTextInputFields(field);
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

    @Then("I add a new institution with validation data")
    public void addSpecialCharacterInstitution() {

        try {

            for(int i=0; i<validationTestData.size();i++){

                recordNewInst = institutionsPage.enterInstitutionData(validationTestData.get(i));

                if(recordNewInst!=null) {

                    recordNewInst = institutionsPage.getDbIdByInstitutionName(recordNewInst);

                    Logging.stepName("Add Institution: Table Data Check");
                    recordTableInst = institutionsPage.getTableDataById(recordNewInst);
                    checkRecords(recordNewInst, recordTableInst);

                    Logging.stepName("Add Institution: Database Data Check");

                    recordDbInst = institutionsPage.getDBDataById(recordNewInst.getId());
                    checkRecords(recordNewInst, recordDbInst);
                }
                else{
                    Logging.stepName("Add Institution");
                    Logging.failMessage("Institution not added correctly.\n" + recordNewInst.getTestOutput());
                    softAssert.fail("Institution not added correctly.");

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

    @Then("I delete an institution with validation data")
    public void deleteSpecialCharacterInstitution() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordEditInst = validationTestData.get(i);

                /* Delete the newly created Institution */
                if(adminCommon.clickDeleteByUniqueId(recordEditInst.getId())){

                    /* Check for dialogs */
                    recordEditInst = institutionsPage.checkAndDeleteInstitution(recordEditInst);

                    Logging.stepName("Delete Institution: Table Deletion Check");
                    recordTableInst = institutionsPage.getTableDataById(recordEditInst);
                    if (recordTableInst.getId() != null) {
                        Logging.failMessage("Institution ID " + recordEditInst.getId() + " still exists in the table.");
                        softAssert.fail("Institution ID " + recordEditInst.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Institution ID " + recordEditInst.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Institution: Database Deletion Check");
                    recordDbInst = institutionsPage.getDBDataById(recordEditInst.getId());
                    if (recordDbInst.getId() != null) {
                        Logging.failMessage("Institution ID " + recordEditInst.getId() + " still exists in the database.");
                        softAssert.fail("Institution ID " + recordEditInst.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Institution ID " + recordEditInst.getId() + " deleted from database");
                    }

                }
                else{
                    Logging.failMessage("Deletion of Institution ID " + recordEditInst.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Institution ID " + recordEditInst.getId() + " failed in the GUI.");
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

    @Then("Check institution scenario")
    public void checkInstitutionScenario(){
        Logging.dataMsg(recordEditInst.getTestOutput());
        softAssert.assertAll();
    }

}