package com.aconite.apm.gui.automation.bindings;


import com.aconite.apm.gui.automation.records.KeyDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.KeyPage;
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

public class KeyTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public KeyDataRecord recordNewKey = new KeyDataRecord();
    public KeyDataRecord recordEditKey = new KeyDataRecord();
    public KeyDataRecord recordTableKey = new KeyDataRecord();
    public KeyDataRecord recordDbKey = new KeyDataRecord();

    KeyPage keyPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    List<String> textFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<KeyDataRecord> validationTestData = new ArrayList<>();

    public KeyTestSteps(AbstractSteps abstractSteps) {

        try{
            webDriver = abstractSteps.getDriver();
            keyPage = new KeyPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewKey = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationKey.json");
            }

            reqFields.put("Status","Status is required");
            reqFields.put("Key type","Key type is required");
            reqFields.put("Name","Name is required");
            reqFields.put("HSM type","HSM type is required");
            reqFields.put("MK check value","MK check value is required");
            reqFields.put("Key algorithm","Key algorithm is required");
            reqFields.put("Block size","Block size is required");
            reqFields.put("Key value","Key value is required");

            editFields.add("Name");
            editFields.add("Status");
            editFields.add("Key type");
            editFields.add("Index");
            editFields.add("Decimalisation table");
            editFields.add("Validation pattern");
            editFields.add("Decimalisation table encryption");
            editFields.add("HSM type");
            editFields.add("MK check value");
            editFields.add("SCI key type");
            editFields.add("Key algorithm");
            editFields.add("Block size");
            editFields.add("Key value");
            editFields.add("Key check value");
            editFields.add("Expiry date");
            editFields.add("Extension");

            textFields.add("Index");
            textFields.add("Decimalisation table");
            textFields.add("Validation pattern");
            textFields.add("Name");
            textFields.add("MK check value");
            textFields.add("SCI key type");
            textFields.add("Block size");
            textFields.add("Key check value");


        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

    }

    private KeyDataRecord getJSONData(){

        KeyDataRecord outRecord = new KeyDataRecord();
        String path = testDataPath + "/newKey.json";

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setStatus((String) inst.get("status"));
            outRecord.setKeyType((String) inst.get("keyType"));
            outRecord.setName((String) inst.get("name"));
            outRecord.setHsmType((String) inst.get("hsmType"));
            outRecord.setMkCheckValue((String) inst.get("mkCheckValue"));
            outRecord.setKeyAlgorithm((String) inst.get("keyAlgorithm"));
            outRecord.setBlockSize((String) inst.get("blockSize"));
            outRecord.setKeyValue((String) inst.get("keyValue"));
            outRecord.setExpiryDate((String) inst.get("expiryDate"));
            outRecord.setDecimalisationTableEncryption((String) inst.get("decimalisationTableEncryption"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<KeyDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<KeyDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                KeyDataRecord outRecord = new KeyDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setStatus(j.getString("status"));
                outRecord.setKeyType(j.getString("keyType"));
                outRecord.setHsmType(j.getString("hsmType"));
                outRecord.setExpiryDate(j.getString("expiryDate"));
                outRecord.setMkCheckValue(j.getString("mkCheckValue"));
                outRecord.setKeyAlgorithm(j.getString("keyAlgorithm"));
                outRecord.setBlockSize(j.getString("blockSize"));
                outRecord.setDecimalisationTableEncryption(j.getString("decimalisationTableEncryption"));
                outRecord.setKeyValue(j.getString("keyValue"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Administration Keys Menu")
    public void clickAdminKeys() {

        try{
            Logging.stepName("Click On The Administration Keys Menu");

            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminKeysMenuItem();

            if(keyPage.isPageOpened()){
                Logging.passMessage("Logged in and on Keys Page");
            }
            else{
                Logging.failMessage("Not logged in and on Keys Page");
                softAssert.fail("Not logged in and on Keys Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the keys required fields")
    public void checkRequiredFields(){

        Logging.stepName("Checking Keys Required Fields");

        try{

            keyPage.clickAddButton();

            for (String field : reqFields.keySet()){
                boolean rc = keyPage.checkRequiredFields(field, reqFields.get(field));
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

    @Then("I add a new key")
    public void addKey(){

        try{
            recordNewKey = keyPage.enterKeyData(recordNewKey);

            if(recordNewKey.getName()!=null) {

                recordNewKey = keyPage.getDbIdByKeyName(recordNewKey);

                Logging.stepName("Add Key: Table Data Check");
                recordTableKey = keyPage.getTableRecordByRecord(recordNewKey);
                checkTableRecords(recordNewKey, recordTableKey);

                Logging.stepName("Add Key: Database Data Check");
                recordDbKey = keyPage.getDBDataById(recordNewKey.getDbId());
                checkRecords(recordNewKey, recordDbKey);

            }
            else{
                Logging.stepName("Add Key");
                Logging.failMessage("Key not added correctly.\n" + recordNewKey.getTestOutput());
                softAssert.fail("Key not added correctly.\n" + recordNewKey.getTestOutput());
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit a key")
    public void editKey(){

        try {

            recordEditKey = recordNewKey;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the fields */
                    case ("Name"):
                        recordEditKey.setName("Updated Test Zone PIN Key");
                        break;

                    case ("HSM type"):
                        recordEditKey.setHsmType("Thales payShield (keyblock)");
                        break;

                    case ("Status"):
                        recordEditKey.setStatus("Inactive");
                        break;

                    case ("Key type"):
                        recordEditKey.setKeyType("PIN Generation Key");
                        break;

                    case ("Index"):
                        recordEditKey.setIndex("1");
                        break;

                    case ("Decimalisation table"):
                        recordEditKey.setDecimalisationTable("6E4AFE23");
                        break;

                    case ("Decimalisation table encryption"):
                        recordEditKey.setDecimalisationTableEncryption("true");
                        break;

                    case ("Validation pattern"):
                        recordEditKey.setValidationPattern("2233");
                        break;

                    case ("MK check value"):
                        recordEditKey.setMkCheckValue("2F5A7E");
                        break;

                    case ("SCI key type"):
                        recordEditKey.setSciKeytype("SCI Key");
                        break;

                    case ("Key algorithm"):
                        recordEditKey.setKeyAlgorithm("DES1E");
                        break;

                    case ("Block size"):
                        recordEditKey.setBlockSize("32");
                        break;

                    case ("Key value"):
                        recordEditKey.setKeyValue("C38C6145274DAE32145CF3761FDC3E4025298CA22FD96AC4B");
                        break;

                    case ("Key check value"):
                        recordEditKey.setKeyCheckValue("1234");
                        break;

                    case ("Expiry date"):
                        recordEditKey.setExpiryDate("31/12/2099");
                        break;

                    case ("Extension"):
                        recordEditKey.setExtension(".aco");
                        break;


                }

                recordEditKey = keyPage.editKeyData(recordEditKey, field);

                if (recordEditKey.getTestOutput().contains("UNSUCCESSFUL")) {
                    Logging.stepName("Edit Key");
                    Logging.failMessage("Edit Key: Data Check: " + field +
                            "\n" + recordEditKey.getTestOutput());
                    softAssert.fail("Edit Key: Data Check: " + field +
                            "\n" + recordEditKey.getTestOutput());
                } else {
                    Logging.stepName("Edit Key: Table Data Check: " + field);
                    recordTableKey = keyPage.getTableRecordByRecord(recordEditKey);
                    checkTableRecords(recordEditKey, recordTableKey);

                    Logging.stepName("Edit Key: Database Data Check: " + field);
                    recordDbKey = keyPage.getDBDataById(recordEditKey.getDbId());
                    checkRecords(recordEditKey, recordDbKey);
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

    @Then("I delete a key")
    public void deleteKey() {

        try {

            Logging.stepName("Delete Key");

            /* Sort or Filter for records */
            keyPage.searchKey(recordEditKey.getKeyType(), recordEditKey.getName());

            /* Delete the newly created key */
            if(adminCommon.clickDeleteByUniqueId(recordEditKey.getDbId())) {

                /* Check for dialogs */
                recordEditKey = keyPage.checkAndDeleteKey(recordEditKey);

                if (recordEditKey.getTestOutput().contains("Delete Key: SUCCESSFUL")) {

                    Logging.stepName("Delete Key: Table Deletion Check");
                    recordTableKey = keyPage.getTableRecordByRecord(recordEditKey);
                    if (recordTableKey.getDbId() != null || recordTableKey.getName() != null) {
                        Logging.failMessage("Key ID " + recordEditKey.getDbId() + " still exists in the table.");
                        softAssert.fail("Key ID " + recordEditKey.getDbId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Key ID " + recordEditKey.getDbId() + " deleted from table");
                    }

                    Logging.stepName("Delete Key: Database Deletion Check");
                    recordDbKey = keyPage.getDBDataById(recordEditKey.getDbId());
                    if (recordDbKey.getDbId() != null) {
                        Logging.failMessage("Key ID " + recordEditKey.getDbId() + " still exists in the database.");
                        softAssert.fail("Key ID " + recordEditKey.getDbId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Key ID " + recordEditKey.getDbId() + " deleted from database");
                    }

                }
                else{
                    Logging.failMessage("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI.\n" + recordEditKey.getTestOutput());
                    softAssert.fail("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI.\n" + recordEditKey.getTestOutput());
                }

            }
            else{

                Logging.failMessage("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI. Check if Key ID is correct.");
                softAssert.fail("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI. Check if Key ID is correct.");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }


    public void checkTableRecords(KeyDataRecord record1, KeyDataRecord record2) {

        String tempdate1 = "";
        String tempdate2;

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

//        System.out.println("record1.getExpiryDate() = >" + record1.getExpiryDate() + "<");
//        System.out.println("record2.getExpiryDate() = >" + record2.getExpiryDate() + "<");
        if(record1.getExpiryDate()==null || record1.getExpiryDate().equalsIgnoreCase("null")){
            tempdate1 = "";
        }
        else if(record1.getExpiryDate().equalsIgnoreCase("1970-01-01")){
            record1.setExpiryDate(null);
        }
        else{
            tempdate1 = record1.getExpiryDate();
        }
        if(record2.getExpiryDate().equalsIgnoreCase("1970-01-01")){
            tempdate2 = "";
        }
        else{
            tempdate2 = record2.getExpiryDate();
        }
//        System.out.println("tempdate1 = " + tempdate1);
//        System.out.println("tempdate2 = " + tempdate2);
        // Check the records match
        if(record1.getDbId().equalsIgnoreCase(record2.getDbId()) &&
                record1.getName().equalsIgnoreCase(record2.getName()) &&
                record1.getKeyType().equalsIgnoreCase(record2.getKeyType()) &&
                record1.getStatus().equalsIgnoreCase(record2.getStatus()) &&
                tempdate1.equalsIgnoreCase(tempdate2)){

                Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = DBID=" + record1.getDbId() +
                    ", NAME=" + record1.getName() +
                    ", KEYTYPE=" + record1.getKeyType() +
                    ", STATUS=" + record1.getStatus() +
                    ", EXPIRYDATE=" + tempdate1 +
                    "\nRecord 2 Data = DBID=" + record2.getDbId() +
                            ", NAME=" + record2.getName() +
                            ", KEYTYPE=" + record2.getKeyType() +
                            ", STATUS=" + record2.getStatus() +
                            ", EXPIRYDATE=" + tempdate2);
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = DBID=" + record1.getDbId() +
                    ", NAME=" + record1.getName() +
                    ", KEYTYPE=" + record1.getKeyType() +
                    ", STATUS=" + record1.getStatus() +
//                    ", EXPIRYDATE=" + record1.getExpiryDate() +
                    ", EXPIRYDATE=" + tempdate1 +
                    "\nRecord 2 Data = DBID=" + record2.getDbId() +
                    ", NAME=" + record2.getName() +
                    ", KEYTYPE=" + record2.getKeyType() +
                    ", STATUS=" + record2.getStatus() +
//                    ", EXPIRYDATE=" + record2.getExpiryDate());
                    ", EXPIRYDATE=" + tempdate2);
        }


    }

    public void checkRecords(KeyDataRecord record1, KeyDataRecord record2) {

        if(record1.getExpiryDate()==null || record1.getExpiryDate().equalsIgnoreCase("null")){
            record1.setExpiryDate(null);
        }
        if(record2.getExpiryDate()==null || record2.getExpiryDate().equalsIgnoreCase("null")){
            record2.setExpiryDate(null);
        }

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
            softAssert.fail("Record data does not match");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = " + record1 +
                    "\nRecord 2 Data = " + record2);
        }

    }

    @Then("I validate the keys text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Keys Text Input Fields");

        try{

            keyPage.clickAddButton();

            for (String field : textFields){

                msg = keyPage.validateTextInputFields(field);
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

    @Then("I add a new Key with validation data")
    public void addSpecialCharacterKey() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewKey = keyPage.enterKeyData(validationTestData.get(i));

                if(recordNewKey.getName()!=null) {

                    recordNewKey = keyPage.getDbIdByKeyName(recordNewKey);

                    Logging.stepName("Add Key: Table Data Check");
                    recordTableKey = keyPage.getTableRecordByRecord(recordNewKey);
                    checkTableRecords(recordNewKey, recordTableKey);

                    Logging.stepName("Add Key: Database Data Check");
                    recordDbKey = keyPage.getDBDataById(recordNewKey.getDbId());
                    checkRecords(recordNewKey, recordDbKey);

                }
                else{
                    Logging.stepName("Add Key");
                    Logging.failMessage("Key not added correctly.\n" + recordNewKey.getTestOutput());
                    Assert.fail("Key not added correctly.\n" + recordNewKey.getTestOutput());
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

    @Then("I delete a Key with validation data")
    public void deleteSpecialCharacterKey() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                try {

                    Logging.stepName("Delete Key");
                    recordEditKey = validationTestData.get(i);

                    /* Sort or Filter for records */
                    keyPage.searchKey(recordEditKey.getKeyType(), recordEditKey.getName());

                    /* Delete the newly created key */
                    if(adminCommon.clickDeleteByUniqueId(recordEditKey.getDbId())) {

                        /* Check for dialogs */
                        recordEditKey = keyPage.checkAndDeleteKey(recordEditKey);

                        if (!recordEditKey.getTestOutput().contains("Delete Key: UNSUCCESSFUL")) {

                            Logging.stepName("Delete Key: Table Deletion Check");
                            recordTableKey = keyPage.getTableRecordByRecord(recordEditKey);
                            if (recordTableKey.getDbId() != null || recordTableKey.getName() != null) {
                                Logging.failMessage("Key ID " + recordEditKey.getDbId() + " still exists in the table.");
                                softAssert.fail("Key ID " + recordEditKey.getDbId() + " still exists in the table.");
                            } else {
                                Logging.passMessage("Key ID " + recordEditKey.getDbId() + " deleted from table");
                            }

                            Logging.stepName("Delete Key: Database Deletion Check");
                            recordDbKey = keyPage.getDBDataById(recordEditKey.getDbId());
                            if (recordDbKey.getDbId() != null) {
                                Logging.failMessage("Key ID " + recordEditKey.getDbId() + " still exists in the database.");
                                softAssert.fail("Key ID " + recordEditKey.getDbId() + " still exists in the database.");
                            } else {
                                Logging.passMessage("Key ID " + recordEditKey.getDbId() + " deleted from database");
                            }

                        }
                        else{
                            Logging.failMessage("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI.\n" + recordEditKey.getTestOutput());
                            softAssert.fail("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI.\n" + recordEditKey.getTestOutput());
                        }

                    }
                    else{

                        Logging.failMessage("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI. Check if Key ID is correct.");
                        softAssert.fail("Deletion of Key ID " + recordEditKey.getDbId() + " failed in the GUI. Check if Key ID is correct.");

                    }

                }
                catch (Exception exc) {
                    exc.printStackTrace();
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

    @Then("Check Key scenario")
    public void checkKeyScenario()  {
        Logging.dataMsg(recordEditKey.getTestOutput());
        softAssert.assertAll();
    }

}
