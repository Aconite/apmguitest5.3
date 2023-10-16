package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.MessageTemplateDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.MessageTemplatesPage;
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

public class MessageTemplatesTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public MessageTemplateDataRecord recordNewMessageTemplate = new MessageTemplateDataRecord();
    public MessageTemplateDataRecord recordEditMessageTemplate = new MessageTemplateDataRecord();
    public MessageTemplateDataRecord recordTableMessageTemplate = new MessageTemplateDataRecord();
    public MessageTemplateDataRecord recordDbMessageTemplate = new MessageTemplateDataRecord();

    MessageTemplatesPage messageTemplatesPage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<MessageTemplateDataRecord> validationTestData = new ArrayList<>();

    public MessageTemplatesTestSteps(AbstractSteps abstractSteps){
        try {

            webDriver = abstractSteps.getDriver();
            messageTemplatesPage = new MessageTemplatesPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewMessageTemplate = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationMessageTemplate.json");
            }


            reqFields.put("Name","Name is required");
            reqFields.put("InstitutionName","Institution is required");

            editFields.add("Name");
            //editFields.add("InstitutionName");
            editFields.add("Pan");
            editFields.add("PanSequence");
            editFields.add("ExpiryDate");
            editFields.add("PanAlias");
            editFields.add("PanId");
            editFields.add("PinPukFlag");
            editFields.add("PinBlock");
            editFields.add("PukBlock");
            editFields.add("PinVerificationValue");
            editFields.add("PinVerificationMethod");
            editFields.add("PvvKeyName");
            editFields.add("PukVerificationValue");
            editFields.add("CustomerCode");
            editFields.add("Operation");
            editFields.add("TokenProductName");
            editFields.add("AppSequenceNumber");
            editFields.add("FixedData");

            textFields.add("Name");
            textFields.add("Fixed data");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private MessageTemplateDataRecord getJSONData(){

        MessageTemplateDataRecord outRecord = new MessageTemplateDataRecord();
        String path = testDataPath + "/newMessageTemplate.json";

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setInstitutionName((String) inst.get("institutionName"));
            outRecord.setPan((String) inst.get("Pan"));
            outRecord.setPanSequence((String) inst.get("panSequence"));
            outRecord.setExpiryDate((String) inst.get("expiryDate"));
            outRecord.setPanAlias((String) inst.get("panAlias"));
            outRecord.setPanId((String) inst.get("panId"));
            outRecord.setPinPukFlag((String) inst.get("pinPukFlag"));
            outRecord.setPinBlock((String) inst.get("pinBlock"));
            outRecord.setPukBlock((String) inst.get("pukBlock"));
            outRecord.setPinVerificationValue((String) inst.get("pinVerificationValue"));
            outRecord.setPinVerificationMethod((String) inst.get("pinVerificationMethod"));
            outRecord.setPvvKeyName((String) inst.get("pvvKeyName"));
            outRecord.setPukVerificationValue((String) inst.get("pukVerificationValue"));
            outRecord.setCustomerCode((String) inst.get("customerCode"));
            outRecord.setOperation((String) inst.get("operation"));
            outRecord.setTokenProductName((String) inst.get("tokenProductName"));
            outRecord.setAppSequenceNumber((String) inst.get("appSequenceNumber"));
            outRecord.setFixedData((String) inst.get("fixedData"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<MessageTemplateDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<MessageTemplateDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                MessageTemplateDataRecord outRecord = new MessageTemplateDataRecord();
                JSONObject j = result.getJSONObject(i);

                System.out.println("Record " + i + " Name: " + j.getString("institutionName"));
                outRecord.setName(j.getString("name"));
                outRecord.setInstitutionName(j.getString("institutionName"));
                outRecord.setPan(j.getString("Pan"));
                outRecord.setPanSequence(j.getString("panSequence"));
                outRecord.setExpiryDate(j.getString("expiryDate"));
                outRecord.setPanAlias(j.getString("panAlias"));
                outRecord.setPanId(j.getString("panId"));
                outRecord.setPinPukFlag(j.getString("pinPukFlag"));
                outRecord.setPinBlock(j.getString("pinBlock"));
                outRecord.setPukBlock(j.getString("pukBlock"));
                outRecord.setPinVerificationValue(j.getString("pinVerificationValue"));
                outRecord.setPinVerificationMethod(j.getString("pinVerificationMethod"));
                outRecord.setPvvKeyName(j.getString("pvvKeyName"));
                outRecord.setPukVerificationValue(j.getString("pukVerificationValue"));
                outRecord.setCustomerCode(j.getString("customerCode"));
                outRecord.setOperation(j.getString("operation"));
                outRecord.setTokenProductName(j.getString("tokenProductName"));
                outRecord.setAppSequenceNumber(j.getString("appSequenceNumber"));
                outRecord.setFixedData(j.getString("fixedData"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu Message Templates")
    public void clickAdminMessageTemplates() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminMessageTemplatesMenuItem();

            Logging.stepName("Click On Admin Message Templates Menu Item");

            if(messageTemplatesPage.isPageOpened()){
                Logging.passMessage("Logged in and on Message Templates Page");
            }
            else{
                Logging.failMessage("Not logged in and on Message Templates Page");
                softAssert.fail("Not logged in and on Message Templates Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then ("I check the Message Template required fields")
    public void checkRequiredFields() {

        try{

            messageTemplatesPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = messageTemplatesPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new Message Template")
    public void addMessageTemplate() {

        try {

            //Enter Details and create
            recordNewMessageTemplate = messageTemplatesPage.enterMessageTemplateData(recordNewMessageTemplate);
            adminCommon.hardWait(500);

            if(recordNewMessageTemplate.getName()!=null) {

                recordNewMessageTemplate = messageTemplatesPage.getDbIdByMessageTemplateName(recordNewMessageTemplate);

                Logging.stepName("Add Message Template: Table Data Check");
                recordTableMessageTemplate = messageTemplatesPage.getTableRecordByRecord(recordNewMessageTemplate);
                if (recordNewMessageTemplate.getName().equals(recordTableMessageTemplate.getName())) {
                    Logging.passMessage("Record data matches");
                } else {
                    softAssert.fail("Add Message Template: Record data does not match:");
                    Logging.failMessage("Record data does not match:" +
                            "\nRecord 1 Data = " + recordNewMessageTemplate.getName() +
                            "\nRecord 2 Data = " + recordTableMessageTemplate.getName());
                }

                Logging.stepName("Add Message Template: Database Data Check");
                recordDbMessageTemplate = messageTemplatesPage.getDBDataById(recordNewMessageTemplate.getId());
                checkDatabaseRecords(recordNewMessageTemplate, recordDbMessageTemplate);

            }
            else{
                Logging.stepName("Add Message Template");
                softAssert.fail("Message Template not added correctly.");
                Logging.failMessage("Message Template not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit a Message Template")
    public void editMessageTemplate() {

        try {

            // Create the newly created data as data class
            recordEditMessageTemplate = recordNewMessageTemplate;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Name"):
                        recordEditMessageTemplate.setName("TestBank Message Template " + adminCommon.getDateTime());
                        break;

                    case("InstitutionName"):
                        recordEditMessageTemplate.setInstitutionName("ABCBank");
                        break;

                    case("Pan"):
                        recordEditMessageTemplate.setPan("true");
                        break;

                    case("PanSequence"):
                        recordEditMessageTemplate.setPanSequence("true");
                        break;


                    case("ExpiryDate"):
                        recordEditMessageTemplate.setExpiryDate("true");
                        break;

                    case("PanAlias"):
                        recordEditMessageTemplate.setPanAlias("true");
                        break;

                    case("PanId"):
                        recordEditMessageTemplate.setPanId("true");
                        break;

                    case("PinPukFlag"):
                        recordEditMessageTemplate.setPinPukFlag("true");
                        break;

                    case("PinBlock"):
                        recordEditMessageTemplate.setPinBlock("true");
                        break;

                    case("PukBlock"):
                        recordEditMessageTemplate.setPukBlock("true");
                        break;

                    case("PinVerificationValue"):
                        recordEditMessageTemplate.setPinVerificationValue("true");
                        break;

                    case("PinVerificationMethod"):
                        recordEditMessageTemplate.setPinVerificationMethod("true");
                        break;

                    case("PvvKeyName"):
                        recordEditMessageTemplate.setPvvKeyName("true");
                        break;

                    case("PukVerificationValue"):
                        recordEditMessageTemplate.setPukVerificationValue("true");
                        break;

                    case("CustomerCode"):
                        recordEditMessageTemplate.setCustomerCode("true");
                        break;

                    case("Operation"):
                        recordEditMessageTemplate.setOperation("true");
                        break;

                    case("TokenProductName"):
                        recordEditMessageTemplate.setTokenProductName("true");
                        break;

                    case("AppSequenceNumber"):
                        recordEditMessageTemplate.setAppSequenceNumber("true");
                        break;

                    case("FixedData"):
                        recordEditMessageTemplate.setFixedData("Edited TestBank fixed data " + adminCommon.getDateTime());
                        break;
                    }

                Logging.stepName("Edit Message Template: " + field);
                recordEditMessageTemplate = messageTemplatesPage.editMessageTemplateData(recordEditMessageTemplate,field);
                if(recordEditMessageTemplate.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Message Template");
                    Logging.failMessage("Edit Message Template: Data Check: " + field +
                            "\n" + recordEditMessageTemplate.getTestOutput());
                    softAssert.fail("Edit SMS Template Text: Data Check: " + field +
                            "\n" + recordEditMessageTemplate.getTestOutput());
                }
                else {

                    Logging.stepName("Edit Message Template: Database Data Check " + field);
                    recordDbMessageTemplate = messageTemplatesPage.getDBDataById(recordEditMessageTemplate.getId());
                    checkDatabaseRecords(recordEditMessageTemplate, recordDbMessageTemplate);

                    Logging.stepName("Edit Message Template: Table Data Check " + field);
                    recordTableMessageTemplate = messageTemplatesPage.getTableRecordByRecord(recordEditMessageTemplate);
                    checkTableRecords(recordEditMessageTemplate, recordTableMessageTemplate);

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

    @Then("I delete a Message Template")
    public void deleteMessageTemplate() {

        try {

            Logging.stepName("Delete Message Template");

            messageTemplatesPage.searchInstitution(recordEditMessageTemplate);
            adminCommon.hardWait(1000);

            /* Delete the newly created Message Template */
            if(adminCommon.clickDeleteByUniqueId(recordEditMessageTemplate.getId())){

                messageTemplatesPage.checkDeleteMessageTemplate(recordEditMessageTemplate);

                Logging.stepName("Delete Message Template: Table Deletion Check");
                recordTableMessageTemplate = messageTemplatesPage.getTableRecordByRecord(recordEditMessageTemplate);
                if (recordTableMessageTemplate.getId()!=null){
                    Logging.failMessage("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the table.");
                    softAssert.fail("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Message Template ID " + recordEditMessageTemplate.getId() + " deleted from table successfully.");
                }

                Logging.stepName("Delete Message Template: Database Deletion Check");
                recordDbMessageTemplate = messageTemplatesPage.getDBDataById(recordEditMessageTemplate.getId());
                if (recordDbMessageTemplate.getId()!=null){
                    Logging.failMessage("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the database.");
                    softAssert.fail("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Message Template ID " + recordEditMessageTemplate.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Message Template ID " + recordEditMessageTemplate.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Message Template ID " + recordEditMessageTemplate.getId() + " failed in the GUI.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkDatabaseRecords(MessageTemplateDataRecord record1, MessageTemplateDataRecord record2) {

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

    public void checkTableRecords(MessageTemplateDataRecord record1, MessageTemplateDataRecord record2) {

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

    @Then("I validate the message template text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating SMS Template Input Fields");

        try{

            messageTemplatesPage.clickAddButton();

            for (String field : textFields){

                msg = messageTemplatesPage.validateTextInputFields(field);
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

    @Then("Check Message Template scenario")
    public void checkMessageTemplatesScenario(){
        softAssert.assertAll();
    }

    @Then("I add a new Message Template with validation data")
    public void addSpecialCharacterMessageTemplate() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                //Enter Details and create
                recordNewMessageTemplate = messageTemplatesPage.enterMessageTemplateData(validationTestData.get(i));
                adminCommon.hardWait(500);

                if(recordNewMessageTemplate.getName()!=null) {

                    recordNewMessageTemplate = messageTemplatesPage.getDbIdByMessageTemplateName(recordNewMessageTemplate);

                    Logging.stepName("Add Message Template: Table Data Check");
                    recordTableMessageTemplate = messageTemplatesPage.getTableRecordByRecord(recordNewMessageTemplate);
                    if (recordNewMessageTemplate.getName().equals(recordTableMessageTemplate.getName())) {
                        Logging.passMessage("Record data matches");
                    } else {
                        softAssert.fail("Add Message Template: Record data does not match:");
                        Logging.failMessage("Record data does not match:" +
                                "\nRecord 1 Data = " + recordNewMessageTemplate.getName() +
                                "\nRecord 2 Data = " + recordTableMessageTemplate.getName());
                    }

                    Logging.stepName("Add Message Template: Database Data Check");
                    recordDbMessageTemplate = messageTemplatesPage.getDBDataById(recordNewMessageTemplate.getId());
                    checkDatabaseRecords(recordNewMessageTemplate, recordDbMessageTemplate);

                }
                else{
                    Logging.stepName("Add Message Template");
                    softAssert.fail("Message Template not added correctly.");
                    Logging.failMessage("Message Template not added correctly.");
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

    @Then("I delete a Message Template with validation data")
    public void deleteSpecialCharacterMessageTemplate() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                Logging.stepName("Delete Message Template");

                recordEditMessageTemplate = validationTestData.get(i);
                messageTemplatesPage.searchInstitution(recordEditMessageTemplate);
                adminCommon.hardWait(1000);

                /* Delete the newly created Message Template */
                if (adminCommon.clickDeleteByUniqueId(recordEditMessageTemplate.getId())) {

                    messageTemplatesPage.checkDeleteMessageTemplate(recordEditMessageTemplate);

                    Logging.stepName("Delete Message Template: Table Deletion Check");
                    recordTableMessageTemplate = messageTemplatesPage.getTableRecordByRecord(recordEditMessageTemplate);
                    if (recordTableMessageTemplate.getId() != null) {
                        Logging.failMessage("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the table.");
                        softAssert.fail("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Message Template ID " + recordEditMessageTemplate.getId() + " deleted from table successfully.");
                    }

                    Logging.stepName("Delete Message Template: Database Deletion Check");
                    recordDbMessageTemplate = messageTemplatesPage.getDBDataById(recordEditMessageTemplate.getId());
                    if (recordDbMessageTemplate.getId() != null) {
                        Logging.failMessage("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the database.");
                        softAssert.fail("Message Template ID " + recordEditMessageTemplate.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Message Template ID " + recordEditMessageTemplate.getId() + " deleted from database");
                    }

                } else {
                    Logging.failMessage("Deletion of Message Template ID " + recordEditMessageTemplate.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Message Template ID " + recordEditMessageTemplate.getId() + " failed in the GUI.");
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

}
