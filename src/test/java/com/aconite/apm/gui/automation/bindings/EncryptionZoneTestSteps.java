package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.EncryptionZoneDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.EncryptionZonePage;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EncryptionZoneTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public EncryptionZoneDataRecord recordNewEncryptionZone = new EncryptionZoneDataRecord();
    public EncryptionZoneDataRecord recordEditEncryptionZone = new EncryptionZoneDataRecord();
    public EncryptionZoneDataRecord recordTableEncryptionZone = new EncryptionZoneDataRecord();
    public EncryptionZoneDataRecord recordDbEncryptionZone = new EncryptionZoneDataRecord();

    EncryptionZonePage encryptionZonePage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<EncryptionZoneDataRecord> validationTestData = new ArrayList<>();

    public EncryptionZoneTestSteps(AbstractSteps abstractSteps) {

        try{
            webDriver = abstractSteps.getDriver();
            encryptionZonePage = new EncryptionZonePage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewEncryptionZone = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationEncryptionZone.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("PAN encryption key","PAN encryption key is required");
            reqFields.put("PIN block format","PIN block format is required");
            reqFields.put("PIN encryption key","PIN encryption key is required");
            reqFields.put("PUK block format","PUK block format is required");
            reqFields.put("PUK encryption key","PUK encryption key is required");
            reqFields.put("PIN session","PIN session is required");
            reqFields.put("PUK session","PUK session is required");

            editFields.add("PAN encryption key");
            editFields.add("PIN block format");
            editFields.add("PIN encryption key");
            editFields.add("Excluded PINs");
            editFields.add("PUK block format");
            editFields.add("PUK encryption key");
            editFields.add("PIN session");
            editFields.add("PUK session");
            editFields.add("RSA key");
            editFields.add("PAN encrypted");
            editFields.add("PUK encrypted");

            textFields.add("Name");
            textFields.add("Excluded PINS");


        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

    }

    private EncryptionZoneDataRecord getJSONData(){

        EncryptionZoneDataRecord outRecord = new EncryptionZoneDataRecord();
        String path = testDataPath + "/newEncryptionZone.json";

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setPanEncrypted((String) inst.get("panEncrypted"));
            outRecord.setPanEncryptionKey((String) inst.get("panEncryptionKey"));
            outRecord.setPinBlockFormat((String) inst.get("pinBlockFormat"));
            outRecord.setPinEncryptionKey((String) inst.get("pinEncryptionKey"));
            outRecord.setPukEncrypted((String) inst.get("pukEncrypted"));
            outRecord.setPukBlockFormat((String) inst.get("pukBlockFormat"));
            outRecord.setPukEncryptionKey((String) inst.get("pukEncryptionKey"));
            outRecord.setPinSession((String) inst.get("pinSession"));
            outRecord.setPukSession((String) inst.get("pukSession"));
            outRecord.setExcludedPins((String) inst.get("excludedPins"));
            outRecord.setRsaKey((String) inst.get("rsaKey"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<EncryptionZoneDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<EncryptionZoneDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                EncryptionZoneDataRecord outRecord = new EncryptionZoneDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setPanEncrypted(j.getString("panEncrypted"));
                outRecord.setPanEncryptionKey(j.getString("panEncryptionKey"));
                outRecord.setPinBlockFormat(j.getString("pinBlockFormat"));
                outRecord.setPinEncryptionKey(j.getString("pinEncryptionKey"));
                outRecord.setPukEncrypted(j.getString("pukEncrypted"));
                outRecord.setPukBlockFormat(j.getString("pukBlockFormat"));
                outRecord.setPukEncryptionKey(j.getString("pukEncryptionKey"));
                outRecord.setPinSession(j.getString("pinSession"));
                outRecord.setPukSession(j.getString("pukSession"));
                outRecord.setExcludedPins(j.getString("excludedPins"));
                outRecord.setRsaKey(j.getString("rsaKey"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("testData.size() = " + testData.size());
        return testData;

    }

    @Then("I click on the Administration Encryption Zones Menu")
    public void clickAdminEncryptionZones() {

        try{
            Logging.stepName("Click On The Administration Encryption Zones Menu");

            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminEncryptionZonesMenuItem();

            if(encryptionZonePage.isPageOpened()){
                Logging.passMessage("Logged in and on Encryption Zones Page");
            }
            else{
                Logging.failMessage("Not logged in and on Encryption Zones Page");
                softAssert.fail("Not logged in and on Encryption Zones Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the encryption zone required fields")
    public void checkRequiredFields(){

        Logging.stepName("Checking Encryption Zones Required Fields");

        try{

            encryptionZonePage.clickAddButton();

            for (String field : reqFields.keySet()){
                boolean rc = encryptionZonePage.checkRequiredFields(field, reqFields.get(field));
                if(rc){
                    Logging.passMessage(field + " object contains correct error message: " + reqFields.get(field));
                } else {
                    Logging.failMessage(field + " object does not contain correct error message: " + reqFields.get(field));
                    softAssert.fail(field + " object does not contain correct error message: " + reqFields.get(field));
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

    @Then("I add a new encryption zone")
    public void addEncryptionZone(){

        try{

            recordNewEncryptionZone = encryptionZonePage.enterEncryptionZoneData(recordNewEncryptionZone);

            if(recordNewEncryptionZone.getName()!=null) {

                recordNewEncryptionZone = encryptionZonePage.getDbIdByEncryptionZoneName(recordNewEncryptionZone);

                Logging.stepName("Add Encryption Zone: Table Data Check");
                recordTableEncryptionZone = encryptionZonePage.getTableRecordByRecord(recordNewEncryptionZone);
                checkTableRecords(recordNewEncryptionZone, recordTableEncryptionZone);

                Logging.stepName("Add Encryption Zone: Database Data Check");
                recordDbEncryptionZone = encryptionZonePage.getDBDataById(recordNewEncryptionZone.getDbId());
                checkRecords(recordNewEncryptionZone, recordDbEncryptionZone);

            }
            else{
                Logging.stepName("Add Encryption Zone");
                Logging.failMessage("Encryption Zone not added correctly.\n" + recordNewEncryptionZone.getTestOutput());
                softAssert.fail("Encryption Zone not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an encryption zone")
    public void editEncryptionZone(){

        try {

            recordEditEncryptionZone = recordNewEncryptionZone;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the fields */
                    case ("PAN encrypted"):
                        recordEditEncryptionZone.setPanEncrypted("false");
                        break;

                    case ("PAN encryption key"):
                        recordEditEncryptionZone.setPanEncryptionKey("Gemalto ZEK");
                        break;

                    case ("PIN block format"):
                        recordEditEncryptionZone.setPinBlockFormat("ISO 9564-1 Format 4");
                        break;

                    case ("PIN encryption key"):
                        recordEditEncryptionZone.setPinEncryptionKey("Gemalto ZPK");
                        break;

                    case ("Excluded PINs"):
                        recordEditEncryptionZone.setExcludedPins("1111,2222");
                        break;

                    case ("PUK encrypted"):
                        recordEditEncryptionZone.setPukEncrypted("false");
                        break;

                    case ("PUK block format"):
                        recordEditEncryptionZone.setPukBlockFormat("ISO 9564-1 Format 3");
                        break;

                    case ("PUK encryption key"):
                        recordEditEncryptionZone.setPukEncryptionKey("Gemalto PUK");
                        break;

                    case ("PIN session"):
                        recordEditEncryptionZone.setPinSession("Dynamic");
                        break;

                    case ("PUK session"):
                        recordEditEncryptionZone.setPukSession("Dynamic");
                        break;

                    case ("RSA key"):
                        recordEditEncryptionZone.setRsaKey("LastBank session RSA private");
                        break;

                }

                recordEditEncryptionZone = encryptionZonePage.editEncryptionZoneData(recordEditEncryptionZone, field);

                if (recordEditEncryptionZone.getTestOutput().contains("UNSUCCESSFUL")) {
                    Logging.stepName("Edit Encryption Zone");
                    Logging.failMessage("Edit Encryption Zone: Data Check: " + field +
                            "\n" + recordEditEncryptionZone.getTestOutput());
                    softAssert.fail("Edit Encryption Zone: Data Check: " + field +
                            "\n" + recordEditEncryptionZone.getTestOutput());

                } else {
                    Logging.stepName("Edit Encryption Zone: Table Data Check: " + field);
                    recordTableEncryptionZone = encryptionZonePage.getTableRecordByRecord(recordEditEncryptionZone);
                    checkTableRecords(recordEditEncryptionZone, recordTableEncryptionZone);

                    Logging.stepName("Edit Encryption Zone: Database Data Check: " + field);
                    recordDbEncryptionZone = encryptionZonePage.getDBDataById(recordEditEncryptionZone.getDbId());
                    checkRecords(recordEditEncryptionZone, recordDbEncryptionZone);
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

    @Then("I delete an encryption zone")
    public void deleteEncryptionZone() {

        try {

            Logging.stepName("Delete Encryption Zone");

            /* Delete the newly created Encryption Zone */
            if(adminCommon.clickDeleteByUniqueId(recordEditEncryptionZone.getDbId())) {

                /* Check for dialogs */
                recordEditEncryptionZone = encryptionZonePage.checkAndDeleteEncryptionZone(recordEditEncryptionZone);

                Logging.stepName("Delete Encryption Zone: Table Deletion Check");
                recordTableEncryptionZone = encryptionZonePage.getTableRecordByRecord(recordEditEncryptionZone);
                if (recordTableEncryptionZone.getDbId() != null || recordTableEncryptionZone.getName() != null) {
                    Logging.failMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the table.");
                    softAssert.fail("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the table.");
                } else {
                    Logging.passMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " deleted from table");
                }

                Logging.stepName("Delete Encryption Zone: Database Deletion Check");
                recordDbEncryptionZone = encryptionZonePage.getDBDataById(recordEditEncryptionZone.getDbId());
                if (recordDbEncryptionZone.getDbId() != null) {
                    Logging.failMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the database.");
                    softAssert.fail("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the database.");
                } else {
                    Logging.passMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " deleted from database");
                }

            }
            else{

                Logging.failMessage("Deletion of Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " failed in the GUI.");
                softAssert.fail("Deletion of Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " failed in the GUI.");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkTableRecords(EncryptionZoneDataRecord record1, EncryptionZoneDataRecord record2) {

        if (record1.getDbId() == null) {
            softAssert.fail("Record 1 data is NULL");
            Logging.failMessage("Record 1 data is NULL");
            return;
        }

        if (record2.getDbId() == null) {
            softAssert.fail("Record 2 data is NULL");
            Logging.failMessage("Record 2 data is NULL");
            return;
        }


        // Check the records match
        if(record1.getDbId().equalsIgnoreCase(record2.getDbId()) &&
                record1.getName().equalsIgnoreCase(record2.getName()) &&
                record1.getPanEncrypted().equalsIgnoreCase(record2.getPanEncrypted()) &&
                record1.getPanEncryptionKey().equalsIgnoreCase(record2.getPanEncryptionKey()) &&
                record1.getPinBlockFormat().equalsIgnoreCase(record2.getPinBlockFormat()) &&
                record1.getPinEncryptionKey().equalsIgnoreCase(record2.getPinEncryptionKey()) &&
                record1.getPukEncrypted().equalsIgnoreCase(record2.getPukEncrypted())) {
//                record1.getPukBlockFormat().equalsIgnoreCase(record2.getPukBlockFormat()) &&
//                record1.getPukEncryptionKey().equalsIgnoreCase(record2.getPukEncryptionKey())){

                Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = DBID=" + record1.getDbId() +
                    ", NAME=" + record1.getName() +
                    ", PANENCRYPTED=" + record1.getPanEncrypted() +
                    ", PANENCRYPTIONKEY=" + record1.getPanEncryptionKey() +
                    ", PINBLOCKFORMAT=" + record1.getPinBlockFormat() +
                    ", PINENCRYPTIONKEY=" + record1.getPinEncryptionKey() +
                    ", PUKENCRYPTED=" + record1.getPukEncrypted() +
//                    ", PUKBLOCKFORMAT=" + record1.getPukBlockFormat() +
//                    ", PUKENCRYPTIONKEY=" + record1.getPukEncryptionKey() +
                    "\nRecord 2 Data = DBID=" + record2.getDbId() +
                        ", NAME=" + record2.getName() +
                        ", PANENCRYPTED=" + record2.getPanEncrypted() +
                        ", PANENCRYPTIONKEY=" + record2.getPanEncryptionKey() +
                        ", PINBLOCKFORMAT=" + record2.getPinBlockFormat() +
                        ", PINENCRYPTIONKEY=" + record2.getPinEncryptionKey() +
                        ", PUKENCRYPTED=" + record2.getPukEncrypted());
//                        ", PUKBLOCKFORMAT=" + record2.getPukBlockFormat() +
//                        ", PUKENCRYPTIONKEY=" + record2.getPukEncryptionKey());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    ", NAME=" + record1.getName() +
                    ", PANENCRYPTED=" + record1.getPanEncrypted() +
                    ", PANENCRYPTIONKEY=" + record1.getPanEncryptionKey() +
                    ", PINBLOCKFORMAT=" + record1.getPinBlockFormat() +
                    ", PINENCRYPTIONKEY=" + record1.getPinEncryptionKey() +
                    ", PUKENCRYPTED=" + record1.getPukEncrypted() +
//                    ", PUKBLOCKFORMAT=" + record1.getPukBlockFormat() +
//                    ", PUKENCRYPTIONKEY=" + record1.getPukEncryptionKey() +
                    "\nRecord 2 Data = DBID=" + record2.getDbId() +
                    ", NAME=" + record2.getName() +
                    ", PANENCRYPTED=" + record2.getPanEncrypted() +
                    ", PANENCRYPTIONKEY=" + record2.getPanEncryptionKey() +
                    ", PINBLOCKFORMAT=" + record2.getPinBlockFormat() +
                    ", PINENCRYPTIONKEY=" + record2.getPinEncryptionKey() +
                    ", PUKENCRYPTED=" + record2.getPukEncrypted());
//                    ", PUKBLOCKFORMAT=" + record2.getPukBlockFormat() +
//                    ", PUKENCRYPTIONKEY=" + record2.getPukEncryptionKey());
        }

    }

    public void checkRecords(EncryptionZoneDataRecord record1, EncryptionZoneDataRecord record2) {

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

    @Then("I validate the encryption zones text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Encryption Zones Text Input Fields");

        try{

            encryptionZonePage.clickAddButton();

            for (String field : textFields){

                msg = encryptionZonePage.validateTextInputFields(field);
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

    @Then("I add a new Encryption Zone with validation data")
    public void addSpecialCharacterEncryptionZone() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewEncryptionZone = encryptionZonePage.enterEncryptionZoneData(validationTestData.get(i));

                if(recordNewEncryptionZone.getName()!=null) {

                    recordNewEncryptionZone = encryptionZonePage.getDbIdByEncryptionZoneName(recordNewEncryptionZone);

                    Logging.stepName("Add Encryption Zone: Table Data Check");
                    recordTableEncryptionZone = encryptionZonePage.getTableRecordByRecord(recordNewEncryptionZone);
                    checkTableRecords(recordNewEncryptionZone, recordTableEncryptionZone);

                    Logging.stepName("Add Encryption Zone: Database Data Check");
                    recordDbEncryptionZone = encryptionZonePage.getDBDataById(recordNewEncryptionZone.getDbId());
                    checkRecords(recordNewEncryptionZone, recordDbEncryptionZone);

                }
                else{
                    Logging.stepName("Add Encryption Zone");
                    Logging.failMessage("Encryption Zone not added correctly.\n" + recordNewEncryptionZone.getTestOutput());
                    Assert.fail("Encryption Zone not added correctly.");
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

    @Then("I delete an Encryption Zone with validation data")
    public void deleteSpecialCharacterEncryptionZone() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Encryption Zone");
                recordEditEncryptionZone = validationTestData.get(i);

                /* Delete the newly created Encryption Zone */
                if(adminCommon.clickDeleteByUniqueId(recordEditEncryptionZone.getDbId())) {

                    /* Check for dialogs */
                    recordEditEncryptionZone = encryptionZonePage.checkAndDeleteEncryptionZone(recordEditEncryptionZone);

                    Logging.stepName("Delete Encryption Zone: Table Deletion Check");
                    recordTableEncryptionZone = encryptionZonePage.getTableRecordByRecord(recordEditEncryptionZone);
                    if (recordTableEncryptionZone.getDbId() != null || recordTableEncryptionZone.getName() != null) {
                        Logging.failMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the table.");
                        softAssert.fail("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " deleted from table");
                    }

                    Logging.stepName("Delete Encryption Zone: Database Deletion Check");
                    recordDbEncryptionZone = encryptionZonePage.getDBDataById(recordEditEncryptionZone.getDbId());
                    if (recordDbEncryptionZone.getDbId() != null) {
                        Logging.failMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the database.");
                        softAssert.fail("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " deleted from database");
                    }

                }
                else{

                    Logging.failMessage("Deletion of Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Encryption Zone ID " + recordEditEncryptionZone.getDbId() + " failed in the GUI.");

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

    @Then("Check Encryption Zone scenario")
    public void checkEncryptionZoneScenario()  {
        Logging.dataMsg(recordEditEncryptionZone.getTestOutput());
        softAssert.assertAll();
    }

}
