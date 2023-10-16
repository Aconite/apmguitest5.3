package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.PersoBureauDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.PersoBureauPage;
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


public class PersoBureauTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public PersoBureauDataRecord recordNewPersoBureau = new PersoBureauDataRecord();
    public PersoBureauDataRecord recordEditPersoBureau = new PersoBureauDataRecord();
    public PersoBureauDataRecord recordTablePersoBureau = new PersoBureauDataRecord();
    public PersoBureauDataRecord recordDBPersoBureau = new PersoBureauDataRecord();

    PersoBureauPage persoBureauPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<PersoBureauDataRecord> validationTestData = new ArrayList<>();

    public PersoBureauTestSteps(AbstractSteps abstractSteps) {

        try

        {
            webDriver = abstractSteps.getDriver();
            persoBureauPage = new PersoBureauPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewPersoBureau = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationPersoBureau.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("Code","Code is required");
            reqFields.put("Destination directory","Destination directory is required");
            reqFields.put("Encryption zone","Encryption zone is required");

            editFields.add("Name");
            editFields.add("Code");
            editFields.add("Destination directory");
            editFields.add("Encryption zone");
//            editFields.add("extractPAN");
            editFields.add("extractPANDisplay");

            textFields.add("Name");
            textFields.add("Code");
            textFields.add("Destination directory");

        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }

    }

    private PersoBureauDataRecord getJSONData(){

        PersoBureauDataRecord outRecord = new PersoBureauDataRecord();
        String path = testDataPath + "/newPersoBureau.json";

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setCode((String) inst.get("code"));
            outRecord.setDestinationDirectory((String) inst.get("destinationDirectory"));
            outRecord.setEncryptionZone((String) inst.get("encryptionZone"));
//            outRecord.setExtractPan((String) inst.get("extractPAN"));
            outRecord.setExtractPanDisplay((String) inst.get("extractPANDisplay"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<PersoBureauDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<PersoBureauDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                PersoBureauDataRecord outRecord = new PersoBureauDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setCode(j.getString("code"));
                outRecord.setDestinationDirectory(j.getString("destinationDirectory"));
                outRecord.setEncryptionZone(j.getString("encryptionZone"));
//                outRecord.setExtractPan(j.getString("extractPAN"));
                outRecord.setExtractPanDisplay(j.getString("extractPANDisplay"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("testData.size() = " + testData.size());
        return testData;

    }

    @Then("I click on the Admin Menu Interfaces Perso Bureau")
    public void clickAdminPersonalisationBureaus() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminPersoBureauMenuItem();

            Logging.stepName("Click On Admin > Interfaces > Personalisation Bureaus Menu Item");

            if(persoBureauPage.isPageOpened()){
                Logging.passMessage("Logged in and on Personalisation Bureaus Page");
            }
            else{
                Logging.failMessage("Not logged in and on Personalisation Bureaus Page");
                softAssert.fail("Not logged in and on Personalisation Bureaus Page");
            }
        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the perso bureau required fields")
    public void checkRequiredFields(){

        try{

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                persoBureauPage.clickAddButton();
                boolean rc = persoBureauPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new perso bureau")
    public void addPersoBureau(){

        try{

            recordNewPersoBureau = persoBureauPage.enterPersoBureauData(recordNewPersoBureau);

            if(recordNewPersoBureau.getName()!=null) {

                recordNewPersoBureau = persoBureauPage.getDbIdByPersoBureauName(recordNewPersoBureau);

                Logging.stepName("Add Personalisation Bureau: Table Data Check");
                recordTablePersoBureau = persoBureauPage.getTableRecordByRecord(recordNewPersoBureau);
                checkTableRecords(recordNewPersoBureau, recordTablePersoBureau);

                Logging.stepName("Add Personalisation Bureau: Database Data Check");
                recordDBPersoBureau = persoBureauPage.getDBDataById(recordNewPersoBureau.getId());
                checkRecords(recordNewPersoBureau, recordDBPersoBureau);

            }
            else{
                Logging.stepName("Add Personalisation Bureau");
                softAssert.fail("Personalisation Bureau not added correctly.\n" + recordNewPersoBureau.getTestOutput());
                Logging.failMessage("Personalisation Bureau not added correctly.");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit a perso bureau")
    public void editPersoBureau(){

        try {

            recordEditPersoBureau = recordNewPersoBureau;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the fields */
                    case ("Name"):
                        recordEditPersoBureau.setName("TestBank Updated Bureau");
                        break;

                    case ("Code"):
                        recordEditPersoBureau.setCode("TBUB");
                        break;

                    case ("Destination directory"):
                        recordEditPersoBureau.setDestinationDirectory("C:\\PinManager\\files\\testbank\\output");
                        break;

                    case ("Encryption zone"):
                        recordEditPersoBureau.setEncryptionZone("TestBank Import");
                        break;

                    case ("extractPANDisplay"):
                        recordEditPersoBureau.setExtractPanDisplay("true");
                        break;

                }

                recordEditPersoBureau = persoBureauPage.editPersoBureauData(recordEditPersoBureau,field);

                if(recordEditPersoBureau.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Personalisation Bureau");
                    Logging.failMessage("Edit Personalisation Bureau: Data Check: " + field +
                            "\n" + recordEditPersoBureau.getTestOutput());
                    softAssert.fail("Edit Personalisation Bureau: Data Check: " + field +
                            "\n" + recordEditPersoBureau.getTestOutput());
                }
                else {
                    Logging.stepName("Edit Personalisation Bureau: Table Data Check: " + field);
                    recordTablePersoBureau = persoBureauPage.getTableRecordByRecord(recordEditPersoBureau);
                    checkTableRecords(recordEditPersoBureau, recordTablePersoBureau);

                    Logging.stepName("Edit Personalisation Bureau: Database Data Check: " + field);
                    recordDBPersoBureau = persoBureauPage.getDBDataById(recordEditPersoBureau.getId());
                    checkRecords(recordEditPersoBureau, recordDBPersoBureau);
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

    @Then("I delete a perso bureau")
    public void deletePersonalisationBureau(){
        try {

            Logging.stepName("Delete Personalisation Bureau");

            /* Delete the newly created Personalisation Bureau */
            if(adminCommon.clickDeleteByUniqueId(recordEditPersoBureau.getId())) {

                /* Check for dialogs */
                persoBureauPage.checkDeletePersoBureauById(recordEditPersoBureau);

                Logging.stepName("Delete Personalisation Bureau: Table Deletion Check");
                recordTablePersoBureau = persoBureauPage.getTableRecordByRecord(recordEditPersoBureau);
                if (recordTablePersoBureau.getId() != null) {
                    Logging.failMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the table.");
                    softAssert.fail("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the table.");
                } else {
                    Logging.passMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " deleted from table");
                }

                Logging.stepName("Delete Personalisation Bureau: Database Deletion Check");
                recordDBPersoBureau = persoBureauPage.getDBDataById(recordEditPersoBureau.getId());
                if (recordDBPersoBureau.getId() != null) {
                    Logging.failMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the database.");
                    softAssert.fail("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the database.");
                } else {
                    Logging.passMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " deleted from database");
                }
            }
            else{
                Logging.failMessage("Deletion of Personalisation Bureau ID " + recordEditPersoBureau.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Personalisation Bureau ID " + recordEditPersoBureau.getId() + " failed in the GUI.");
            }


        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }


    }

    public void checkRecords(PersoBureauDataRecord record1, PersoBureauDataRecord record2) {

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

    public void checkTableRecords(PersoBureauDataRecord record1, PersoBureauDataRecord record2) {

        if (record1.getId() == null) {
            softAssert.fail("Record 1 data is NULL");
            Logging.failMessage("Record 1 data is NULL");
            return;
        }

        if (record2.getId() == null) {
            softAssert.fail("Record 2 data is NULL");
            Logging.failMessage("Record 2 data is NULL");
            return;
        }

        // Check the records match
        if(record1.getId().equalsIgnoreCase(record2.getId()) &&
                record1.getName().equalsIgnoreCase(record2.getName()) &&
                record1.getCode().equalsIgnoreCase(record2.getCode()) &&
                record1.getEncryptionZone().equalsIgnoreCase(record2.getEncryptionZone()) &&
                record1.getDestinationDirectory().equalsIgnoreCase(record2.getDestinationDirectory())){

            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = ID=" + record1.getId() +
                    ", NAME=" + record1.getName() +
                    ", CODE=" + record1.getCode() +
                    ", DESTINATIONDIRECTORY=" + record1.getDestinationDirectory() +
                    ", ENCRYPTIONZONE=" + record1.getEncryptionZone() +
                    "\nRecord 2 Data = ID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", CODE=" + record2.getCode() +
                    ", DESTINATIONDIRECTORY=" + record2.getDestinationDirectory() +
                    ", ENCRYPTIONZONE=" + record2.getEncryptionZone());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = ID=" + record1.getId() +
                    ", NAME=" + record1.getName() +
                    ", CODE=" + record1.getCode() +
                    ", DESTINATIONDIRECTORY=" + record1.getDestinationDirectory() +
                    ", ENCRYPTIONZONE=" + record1.getEncryptionZone() +
                    "\nRecord 2 Data = ID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", CODE=" + record2.getCode() +
                    ", DESTINATIONDIRECTORY=" + record2.getDestinationDirectory() +
                    ", ENCRYPTIONZONE=" + record2.getEncryptionZone());
        }

    }

    @Then("I validate the perso bureau text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Perso Bureau Text Input Fields");

        try{

            persoBureauPage.clickAddButton();

            for (String field : textFields){

                msg = persoBureauPage.validateTextInputFields(field);
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

    @Then("I add a new Perso Bureau with validation data")
    public void addSpecialCharacterInterface() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewPersoBureau = persoBureauPage.enterPersoBureauData(validationTestData.get(i));

                if(recordNewPersoBureau.getName()!=null) {

                    recordNewPersoBureau = persoBureauPage.getDbIdByPersoBureauName(recordNewPersoBureau);

                    Logging.stepName("Add Personalisation Bureau: Table Data Check");
                    recordTablePersoBureau = persoBureauPage.getTableRecordByRecord(recordNewPersoBureau);
                    checkTableRecords(recordNewPersoBureau, recordTablePersoBureau);

                    Logging.stepName("Add Personalisation Bureau: Database Data Check");
                    recordDBPersoBureau = persoBureauPage.getDBDataById(recordNewPersoBureau.getId());
                    checkRecords(recordNewPersoBureau, recordDBPersoBureau);

                }
                else{
                    Logging.stepName("Add Personalisation Bureau");
                    softAssert.fail("Personalisation Bureau not added correctly.\n" + recordNewPersoBureau.getTestOutput());
                    Logging.failMessage("Personalisation Bureau not added correctly.");
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

    @Then("I delete a Perso Bureau with validation data")
    public void deleteSpecialCharacterInterface() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Personalisation Bureau");
                recordEditPersoBureau = validationTestData.get(i);

                /* Delete the newly created Personalisation Bureau */
                if(adminCommon.clickDeleteByUniqueId(recordEditPersoBureau.getId())) {

                    /* Check for dialogs */
                    persoBureauPage.checkDeletePersoBureauById(recordEditPersoBureau);

                    Logging.stepName("Delete Personalisation Bureau: Table Deletion Check");
                    recordTablePersoBureau = persoBureauPage.getTableRecordByRecord(recordEditPersoBureau);
                    if (recordTablePersoBureau.getId() != null) {
                        Logging.failMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the table.");
                        softAssert.fail("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Personalisation Bureau: Database Deletion Check");
                    recordDBPersoBureau = persoBureauPage.getDBDataById(recordEditPersoBureau.getId());
                    if (recordDBPersoBureau.getId() != null) {
                        Logging.failMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the database.");
                        softAssert.fail("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Personalisation Bureau ID " + recordEditPersoBureau.getId() + " deleted from database");
                    }
                }
                else{
                    Logging.failMessage("Deletion of Personalisation Bureau ID " + recordEditPersoBureau.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Personalisation Bureau ID " + recordEditPersoBureau.getId() + " failed in the GUI.");
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

    @Then("Check Perso Bureau scenario")
    public void checkPersoBureauScenario()  {
        Logging.dataMsg(recordEditPersoBureau.getTestOutput());
        softAssert.assertAll();
    }

}
