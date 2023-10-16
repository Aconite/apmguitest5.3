package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.SmsTemplateTextDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.SmsTemplateTextPage;
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

public class SmsTemplateTextTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public SmsTemplateTextDataRecord recordNewSmsTemplateText = new SmsTemplateTextDataRecord();
    public SmsTemplateTextDataRecord recordEditSmsTemplateText = new SmsTemplateTextDataRecord();
    public SmsTemplateTextDataRecord recordTableSmsTemplateText = new SmsTemplateTextDataRecord();
    public SmsTemplateTextDataRecord recordDbSmsTemplateText = new SmsTemplateTextDataRecord();

    SmsTemplateTextPage smsTemplateTextPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<SmsTemplateTextDataRecord> validationTestData = new ArrayList<>();

    public SmsTemplateTextTestSteps(AbstractSteps abstractSteps){
        try {

            webDriver = abstractSteps.getDriver();
            smsTemplateTextPage = new SmsTemplateTextPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewSmsTemplateText = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationSmsTemplateText.json");
            }

            reqFields.put("SMS template","SMS template is required");
            reqFields.put("Language code","Language code is required");
            reqFields.put("Template text","Template text is required");

            editFields.add("Template text");

            textFields.add("Template text");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private SmsTemplateTextDataRecord getJSONData(){

        SmsTemplateTextDataRecord outRecord = new SmsTemplateTextDataRecord();
        String path = testDataPath + "/newSmsTemplateText.json";

        try {

            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setTemplate((String) inst.get("smsTemplate"));
            outRecord.setLanguageCode((String) inst.get("languageCode"));
            outRecord.setAbbreviatedLanguageCode((String) inst.get("abbreviatedLanguageCode"));
            outRecord.setTemplateText((String) inst.get("templateText"));
            outRecord.setInstitutionName((String) inst.get("institutionName"));
            outRecord.setTemplateType((String) inst.get("templateType"));


        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<SmsTemplateTextDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<SmsTemplateTextDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                SmsTemplateTextDataRecord outRecord = new SmsTemplateTextDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setTemplate(j.getString("smsTemplate"));
                outRecord.setLanguageCode(j.getString("languageCode"));
                outRecord.setAbbreviatedLanguageCode(j.getString("abbreviatedLanguageCode"));
                outRecord.setTemplateText(j.getString("templateText"));
                outRecord.setInstitutionName(j.getString("institutionName"));
                outRecord.setTemplateType(j.getString("templateType"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu SMS Template Text")
    public void clickAdminSmsTemplateText() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminSmsTemplateTextMenuItem();

            Logging.stepName("Click On Admin SMS Template Text Menu Item");

            if(smsTemplateTextPage.isPageOpened()){
                Logging.passMessage("Logged in and on SMS Template Text Page");
            }
            else{
                Logging.failMessage("Not logged in and on SMS Template Text Page");
                softAssert.fail("Not logged in and on SMS Template Text Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then ("I check the SMS Template Text required fields")
    public void checkRequiredFields() {

        try{

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                smsTemplateTextPage.clickAddButton();
                boolean rc = smsTemplateTextPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new SMS Template Text")
    public void addSmsTemplateText() {

        try {

            //Enter Details and create
            recordNewSmsTemplateText = smsTemplateTextPage.enterSmsTemplateTextData(recordNewSmsTemplateText);
            adminCommon.hardWait(500);
            recordNewSmsTemplateText = smsTemplateTextPage.getDbIdBySmsTemplateTextName(recordNewSmsTemplateText);

            if(recordNewSmsTemplateText.getId()!=null) {

                Logging.stepName("Add SMS Template Text: Table Data Check");
                recordTableSmsTemplateText = smsTemplateTextPage.getTableRecordByRecord(recordNewSmsTemplateText);
                if (recordNewSmsTemplateText.getTemplate().equals(recordTableSmsTemplateText.getTemplate()) &&
                        recordNewSmsTemplateText.getAbbreviatedLanguageCode().equals(recordTableSmsTemplateText.getAbbreviatedLanguageCode())) {
                    Logging.passMessage("Record data matches");
                } else {
                    softAssert.fail("Add SMS Template Text: Record data does not match:");
                    Logging.failMessage("Record data does not match:" +
                            "\nRecord 1 Data = " + recordNewSmsTemplateText.getTemplate() + " - " + recordNewSmsTemplateText.getAbbreviatedLanguageCode() +
                            "\nRecord 2 Data = " + recordTableSmsTemplateText.getTemplate() + " - " + recordTableSmsTemplateText.getAbbreviatedLanguageCode());
                }

                Logging.stepName("Add SMS Template Text: Database Data Check");
                recordDbSmsTemplateText = smsTemplateTextPage.getDBDataById(recordNewSmsTemplateText.getId());
                System.out.println("recordDbSmsTemplateText = " + recordDbSmsTemplateText);
                checkDatabaseRecords(recordNewSmsTemplateText, recordDbSmsTemplateText);

            }
            else{
                Logging.stepName("Add SMS Template Text");
                softAssert.fail("SMS Template Text not added correctly.");
                Logging.failMessage("SMS Template Text not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an SMS Template Text")
    public void editSmsTemplateText() {

        try {

            // Create the newly created data as data class
            recordEditSmsTemplateText = recordNewSmsTemplateText;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Template text"):
                        recordEditSmsTemplateText.setTemplateText("TestBank SMS Template Text " + adminCommon.getDateTime());
                        break;

                }

                recordEditSmsTemplateText = smsTemplateTextPage.editSmsTemplateTextData(recordEditSmsTemplateText,field);

                if(recordEditSmsTemplateText.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit SMS Template Text");
                    Logging.failMessage("Edit SMS Template Text: Data Check: " + field +
                            "\n" + recordEditSmsTemplateText.getTestOutput());
                    softAssert.fail("Edit SMS Template Text: Data Check: " + field +
                            "\n" + recordEditSmsTemplateText.getTestOutput());
                }
                else {

                    Logging.stepName("Edit SMS Template Text: Database Data Check " + field);
                    recordDbSmsTemplateText = smsTemplateTextPage.getDBDataById(recordEditSmsTemplateText.getId());
                    checkDatabaseRecords(recordEditSmsTemplateText, recordDbSmsTemplateText);

                    Logging.stepName("Edit SMS Template Text: Table Data Check " + field);
                    recordTableSmsTemplateText = smsTemplateTextPage.getTableRecordByRecord(recordEditSmsTemplateText);
                    checkTableRecords(recordEditSmsTemplateText, recordTableSmsTemplateText);

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

    @Then("I delete an SMS Template Text")
    public void deleteSmsTemplateText() {

        try {

            Logging.stepName("Delete SMS Template Text");

            /* Delete the newly created SMS Template Text */
            if(adminCommon.clickDeleteByUniqueId(recordEditSmsTemplateText.getId())){

                smsTemplateTextPage.checkDeleteSmsTemplateTextById(recordEditSmsTemplateText);

                Logging.stepName("Delete SMS Template Text: Table Deletion Check");
                recordTableSmsTemplateText = smsTemplateTextPage.getTableRecordByRecord(recordEditSmsTemplateText);
                if (recordTableSmsTemplateText.getId()!=null){
                    Logging.failMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the table.");
                    softAssert.fail("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " deleted from table successfully.");
                }

                Logging.stepName("Delete SMS Template Text: Database Deletion Check");
                recordDbSmsTemplateText = smsTemplateTextPage.getDBDataById(recordEditSmsTemplateText.getId());
                if (recordDbSmsTemplateText.getId()!=null){
                    Logging.failMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the database.");
                    softAssert.fail("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of SMS Template Text ID " + recordEditSmsTemplateText.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of SMS Template Text ID " + recordEditSmsTemplateText.getId() + " failed in the GUI.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkDatabaseRecords(SmsTemplateTextDataRecord record1, SmsTemplateTextDataRecord record2) {

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

    public void checkTableRecords(SmsTemplateTextDataRecord record1, SmsTemplateTextDataRecord record2) {

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
        if (record1.equalsTable(record2)) {
            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = " + record1.toStringTable() +
                    "\nRecord 2 Data = " + record2.toStringTable());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = " + record1.toStringTable() +
                    "\nRecord 2 Data = " + record2.toStringTable());
        }

    }

    @Then("I validate the sms template text text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating SMS Template Input Fields");

        try{

            smsTemplateTextPage.clickAddButton();

            for (String field : textFields){

                msg = smsTemplateTextPage.validateTextInputFields(field);
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

    @Then("I add a new SMS Template Text with validation data")
    public void addSpecialCharacterInterface() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                //Enter Details and create
                recordNewSmsTemplateText = smsTemplateTextPage.enterSmsTemplateTextData(validationTestData.get(i));
                adminCommon.hardWait(500);
                recordNewSmsTemplateText = smsTemplateTextPage.getDbIdBySmsTemplateTextName(recordNewSmsTemplateText);

                if(recordNewSmsTemplateText.getId()!=null) {

                    Logging.stepName("Add SMS Template Text: Table Data Check");
                    recordTableSmsTemplateText = smsTemplateTextPage.getTableRecordByRecord(recordNewSmsTemplateText);
                    if (recordNewSmsTemplateText.getTemplate().equals(recordTableSmsTemplateText.getTemplate()) &&
                            recordNewSmsTemplateText.getAbbreviatedLanguageCode().equals(recordTableSmsTemplateText.getAbbreviatedLanguageCode())) {
                        Logging.passMessage("Record data matches");
                    } else {
                        softAssert.fail("Add SMS Template Text: Record data does not match:");
                        Logging.failMessage("Record data does not match:" +
                                "\nRecord 1 Data = " + recordNewSmsTemplateText.getTemplate() + " - " + recordNewSmsTemplateText.getAbbreviatedLanguageCode() +
                                "\nRecord 2 Data = " + recordTableSmsTemplateText.getTemplate() + " - " + recordTableSmsTemplateText.getAbbreviatedLanguageCode());
                    }

                    Logging.stepName("Add SMS Template Text: Database Data Check");
                    recordDbSmsTemplateText = smsTemplateTextPage.getDBDataById(recordNewSmsTemplateText.getId());
                    System.out.println("recordDbSmsTemplateText = " + recordDbSmsTemplateText);
                    checkDatabaseRecords(recordNewSmsTemplateText, recordDbSmsTemplateText);

                }
                else{
                    Logging.stepName("Add SMS Template Text");
                    softAssert.fail("SMS Template Text not added correctly.");
                    Logging.failMessage("SMS Template Text not added correctly.");
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

    @Then("I delete an SMS Template Text with validation data")
    public void deleteSpecialCharacterInterface() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete SMS Template Text");
                recordEditSmsTemplateText = validationTestData.get(i);

                /* Delete the newly created SMS Template Text */
                if (adminCommon.clickDeleteByUniqueId(recordEditSmsTemplateText.getId())) {

                    smsTemplateTextPage.checkDeleteSmsTemplateTextById(recordEditSmsTemplateText);

                    Logging.stepName("Delete SMS Template Text: Table Deletion Check");
                    recordTableSmsTemplateText = smsTemplateTextPage.getTableRecordByRecord(recordEditSmsTemplateText);
                    if (recordTableSmsTemplateText.getId() != null) {
                        Logging.failMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the table.");
                        softAssert.fail("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " deleted from table successfully.");
                    }

                    Logging.stepName("Delete SMS Template Text: Database Deletion Check");
                    recordDbSmsTemplateText = smsTemplateTextPage.getDBDataById(recordEditSmsTemplateText.getId());
                    if (recordDbSmsTemplateText.getId() != null) {
                        Logging.failMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the database.");
                        softAssert.fail("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("SMS Template Text ID " + recordEditSmsTemplateText.getId() + " deleted from database");
                    }

                } else {
                    Logging.failMessage("Deletion of SMS Template Text ID " + recordEditSmsTemplateText.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of SMS Template Text ID " + recordEditSmsTemplateText.getId() + " failed in the GUI.");
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

    @Then("Check SMS Template Text scenario")
    public void checkSmsTemplateTextsScenario(){
        Logging.dataMsg(recordEditSmsTemplateText.getTestOutput());
        softAssert.assertAll();
    }


}
