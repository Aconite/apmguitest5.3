package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.SMSTemplateDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.SMSTemplatesPage;
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

public class SMSTemplatesTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public SMSTemplateDataRecord recordNewSMSTemplate = new SMSTemplateDataRecord();
    public SMSTemplateDataRecord recordEditSMSTemplate = new SMSTemplateDataRecord();
    public SMSTemplateDataRecord recordTableSMSTemplate = new SMSTemplateDataRecord();
    public SMSTemplateDataRecord recordDbSMSTemplate = new SMSTemplateDataRecord();

    SMSTemplatesPage smsTemplatesPage = null;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<SMSTemplateDataRecord> validationTestData = new ArrayList<>();

    public SMSTemplatesTestSteps(AbstractSteps abstractSteps) {

        try {

            webDriver = abstractSteps.getDriver();
            smsTemplatesPage = new SMSTemplatesPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewSMSTemplate = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationSmsTemplate.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("InstitutionName","Institution is required");
            reqFields.put("TemplateType","Template type is required");

            editFields.add("Name");

            textFields.add("Name");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private SMSTemplateDataRecord getJSONData(){

        SMSTemplateDataRecord outRecord = new SMSTemplateDataRecord();
        String path = testDataPath + "/newSmsTemplate.json";

        try {

            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setInstitutionName((String) inst.get("institutionName"));
            outRecord.setTemplateType((String) inst.get("templateType"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<SMSTemplateDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<SMSTemplateDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                SMSTemplateDataRecord outRecord = new SMSTemplateDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setTemplateType(j.getString("templateType"));
                outRecord.setInstitutionName(j.getString("institutionName"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu SMS Templates")
    public void clickAdminSmsTemplates() {

        try{

            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminSMSTemplatesMenuItem();

            Logging.stepName("Click On Admin SMS Templates Menu Item");

            if(smsTemplatesPage.isPageOpened()){
                Logging.passMessage("Logged in and on SMS Templates Page");
            }
            else{
                Logging.failMessage("Not logged in and on SMS Templates Page");
                softAssert.fail("Not logged in and on SMS Templates Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the SMS Template required fields")
    public void checkRequiredFields(){

        try{

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                smsTemplatesPage.clickAddButton();
                boolean rc = smsTemplatesPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new SMS Template")
    public void addSmsTemplate() {

        try {

            //Enter Details and create
            recordNewSMSTemplate = smsTemplatesPage.enterSMSTemplateData(recordNewSMSTemplate);

            if(recordNewSMSTemplate.getName()!=null) {

                recordNewSMSTemplate = smsTemplatesPage.getDbIdBySmsTemplateName(recordNewSMSTemplate);

                Logging.stepName("Add SMS Template: Table Data Check");
                recordTableSMSTemplate = smsTemplatesPage.getTableRecordByRecord(recordNewSMSTemplate);
                checkRecords(recordNewSMSTemplate, recordTableSMSTemplate);

                Logging.stepName("Add SMS Template: Database Data Check");
                recordDbSMSTemplate = smsTemplatesPage.getDBDataById(recordNewSMSTemplate.getId());
                checkRecords(recordNewSMSTemplate, recordDbSMSTemplate);

            }
            else{
                Logging.stepName("Add SMS Template");
                softAssert.fail("SMS Template not added correctly.\n" + recordNewSMSTemplate.getTestOutput());
                Logging.failMessage("SMS Template not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an SMS Template")
    public void editSmsTemplate() {

        try {

            recordEditSMSTemplate = recordNewSMSTemplate;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Name"):
                        recordEditSMSTemplate.setName("TestBank PIN " + adminCommon.getDateTime());
                        break;
                  }

                recordEditSMSTemplate = smsTemplatesPage.editSMSTemplateData(recordEditSMSTemplate,field);

                if(recordEditSMSTemplate.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit SMS Template");
                    Logging.failMessage("Edit SMS Template: Data Check: " + field +
                            "\n" + recordEditSMSTemplate.getTestOutput());
                    softAssert.fail("Edit SMS Template: Data Check: " + field +
                            "\n" + recordEditSMSTemplate.getTestOutput());
                }
                else {

                    Logging.stepName("Edit SMS Template: Table Data Check: " + field);
                    recordTableSMSTemplate = smsTemplatesPage.getTableRecordByRecord(recordEditSMSTemplate);
                    checkRecords(recordEditSMSTemplate, recordTableSMSTemplate);

                    Logging.stepName("Edit SMS Template: Database Data Check: " + field);
                    recordDbSMSTemplate = smsTemplatesPage.getDBDataById(recordEditSMSTemplate.getId());
                    checkRecords(recordEditSMSTemplate, recordDbSMSTemplate);
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

    @Then("I delete an SMS Template")
    public void deleteSmsTemplate() {
        try {
            Logging.stepName("Delete SMS Template");

            smsTemplatesPage.searchSmsTemplates(recordEditSMSTemplate.getInstitutionName(), recordEditSMSTemplate.getTemplateType(), "");

            /* Delete the newly created SMS Template */
            if(adminCommon.clickDeleteByUniqueId(recordEditSMSTemplate.getId())){

                smsTemplatesPage.checkDeleteSmsTemplateById(recordEditSMSTemplate);

                Logging.stepName("Delete SMS Template: : Table Deletion Check");
                recordTableSMSTemplate = smsTemplatesPage.getTableRecordByRecord(recordEditSMSTemplate);
                if (recordTableSMSTemplate.getId() != null) {
                    Logging.failMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the table.");
                    softAssert.fail("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the table.");
                } else {
                    Logging.passMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " deleted from table");
                }

                Logging.stepName("Delete SMS Template: Database Deletion Check");
                recordDbSMSTemplate = smsTemplatesPage.getDBDataById(recordEditSMSTemplate.getId());
                if (recordDbSMSTemplate.getId() != null) {
                    Logging.failMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the database.");
                    softAssert.fail("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the database.");
                } else {
                    Logging.passMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of SMS Template ID " + recordNewSMSTemplate.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of SMS Template ID " + recordNewSMSTemplate.getId() + " failed in the GUI.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(SMSTemplateDataRecord record1, SMSTemplateDataRecord record2) {

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

    @Then("I validate the sms template text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating SMS Template Input Fields");

        try{

            smsTemplatesPage.clickAddButton();

            for (String field : textFields){

                msg = smsTemplatesPage.validateTextInputFields(field);
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

    @Then("I add a new SMS Template with validation data")
    public void addSpecialCharacterSMSTemplate() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                //Enter Details and create
                recordNewSMSTemplate = smsTemplatesPage.enterSMSTemplateData(validationTestData.get(i));

                if(recordNewSMSTemplate.getName()!=null) {

                    recordNewSMSTemplate = smsTemplatesPage.getDbIdBySmsTemplateName(recordNewSMSTemplate);

                    Logging.stepName("Add SMS Template: Table Data Check");
                    recordTableSMSTemplate = smsTemplatesPage.getTableRecordByRecord(recordNewSMSTemplate);
                    checkRecords(recordNewSMSTemplate, recordTableSMSTemplate);

                    Logging.stepName("Add SMS Template: Database Data Check");
                    recordDbSMSTemplate = smsTemplatesPage.getDBDataById(recordNewSMSTemplate.getId());
                    checkRecords(recordNewSMSTemplate, recordDbSMSTemplate);

                }
                else{
                    Logging.stepName("Add SMS Template");
                    softAssert.fail("SMS Template not added correctly.\n" + recordNewSMSTemplate.getTestOutput());
                    Logging.failMessage("SMS Template not added correctly.");
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

    @Then("I delete an SMS Template with validation data")
    public void deleteSpecialCharacterSMSTemplate() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete SMS Template");

                recordEditSMSTemplate = validationTestData.get(i);

                smsTemplatesPage.searchSmsTemplates(recordEditSMSTemplate.getInstitutionName(), recordEditSMSTemplate.getTemplateType(), "");

                /* Delete the newly created SMS Template */
                if(adminCommon.clickDeleteByUniqueId(recordEditSMSTemplate.getId())){

                    smsTemplatesPage.checkDeleteSmsTemplateById(recordEditSMSTemplate);

                    Logging.stepName("Delete SMS Template: : Table Deletion Check");
                    recordTableSMSTemplate = smsTemplatesPage.getTableRecordByRecord(recordEditSMSTemplate);
                    if (recordTableSMSTemplate.getId() != null) {
                        Logging.failMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the table.");
                        softAssert.fail("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete SMS Template: Database Deletion Check");
                    recordDbSMSTemplate = smsTemplatesPage.getDBDataById(recordEditSMSTemplate.getId());
                    if (recordDbSMSTemplate.getId() != null) {
                        Logging.failMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the database.");
                        softAssert.fail("SMS Template ID " + recordEditSMSTemplate.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("SMS Template ID " + recordEditSMSTemplate.getId() + " deleted from database");
                    }

                }
                else{
                    Logging.failMessage("Deletion of SMS Template ID " + recordEditSMSTemplate.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of SMS Template ID " + recordEditSMSTemplate.getId() + " failed in the GUI.");
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


    @Then("Check SMS Template scenario")
    public void checkSmsTemplateScenario(){
        Logging.dataMsg(recordEditSMSTemplate.getTestOutput());
        softAssert.assertAll();
    }


}
