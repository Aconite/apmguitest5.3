package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.IssuerDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.IssuersPage;
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

public class IssuersTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    public IssuerDataRecord recordNewIss = new IssuerDataRecord();
    public IssuerDataRecord recordEditIss = new IssuerDataRecord();
    public IssuerDataRecord recordTableIss = new IssuerDataRecord();
    public IssuerDataRecord recordDbIss = new IssuerDataRecord();

    IssuersPage issuersPage = null;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<IssuerDataRecord> validationTestData = new ArrayList<>();

    String testDataPath = "";
    String testType = "";

    public IssuersTestSteps(AbstractSteps abstractSteps){
        try {

            webDriver = abstractSteps.getDriver();
            issuersPage = new IssuersPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            System.out.println("Creation: " + testDataPath);
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewIss = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationIssuer.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("Country","Country is required");
            reqFields.put("Institution","Institution is required");

            editFields.add("Name");
            editFields.add("Country");
            editFields.add("Institution");

            textFields.add("Name");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    private IssuerDataRecord getJSONData(){

        IssuerDataRecord outRecord = new IssuerDataRecord();
        String path = testDataPath + "/newIssuer.json";

        try {

            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setInstitutionName((String) inst.get("institution"));
            outRecord.setIssuerName((String) inst.get("issuerName"));
            outRecord.setCountry((String) inst.get("country"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<IssuerDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<IssuerDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                IssuerDataRecord outRecord = new IssuerDataRecord();
                JSONObject j = result.getJSONObject(i);

                System.out.println("Record " + i + " Name: " + j.getString("institution"));
                outRecord.setInstitutionName(j.getString("institution"));
                outRecord.setIssuerName(j.getString("issuerName"));
                outRecord.setCountry(j.getString("country"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu Issuers")
    public void clickAdminIssuers(){

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnProductsIssuersMenuItem();

            Logging.stepName("Click On Admin Issuers Menu Item");

            if(issuersPage.isPageOpened()){
                Logging.passMessage("Logged in and on Issuers Page");
            }
            else{
                Logging.failMessage("Not logged in and on Issuers Page");
                softAssert.fail("Not logged in and on Issuers Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then("I check the issuer required fields")
    public void checkRequiredFields(){

        try{

            issuersPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = issuersPage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new issuer")
    public void addIssuer() {
        try {

            //Enter Details and create
            recordNewIss = issuersPage.enterIssuerData(recordNewIss);

            if(recordNewIss.getIssuerName()!=null) {

                recordNewIss = issuersPage.getDbIdByIssuerName(recordNewIss);

                Logging.stepName("Add Issuer: Table Data Check");
                recordTableIss = issuersPage.getTableRecordByRecord(recordNewIss);
                checkRecords(recordNewIss, recordTableIss);

                Logging.stepName("Add Issuer: Database Data Check");
                recordDbIss = issuersPage.getDBDataById(recordNewIss.getId());
                checkRecords(recordNewIss, recordDbIss);
            }
            else{
                Logging.stepName("Add Issuer");
                softAssert.fail("Issuer not added correctly.");
                Logging.failMessage("Issuer not added correctly.");
            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an issuer")
    public void editIssuer(){

        try {

            recordEditIss = recordNewIss;

            for(String field :  editFields) {

                switch (field) {

                    /* Change the name */
                    case ("Name"):
                        recordEditIss.setIssuerName("TestIssuer_" + adminCommon.getDateTime());
                        issuersPage.searchIssuer(recordEditIss.getInstitutionName(), "");
                        break;

                    /* Change the institution */
                    case ("Institution"):
                        recordEditIss.setInstitutionName("QABank");
                        issuersPage.searchIssuer("", recordEditIss.getIssuerName());
                        break;

                    /* Change the country */
                    case ("Country"):
                        recordEditIss.setCountry("Malawi");
                        break;

                }


                recordEditIss = issuersPage.editIssuerData(recordEditIss, field);

                if(recordEditIss.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Issuer");
                    Logging.failMessage("Edit Issuer: Data Check: " + field +
                            "\n" + recordEditIss.getTestOutput());
                    softAssert.fail("Edit Issuer: Data Check: " + field +
                            "\n" + recordEditIss.getTestOutput());
                }
                else {

                    Logging.stepName("Edit Issuer: Table Data Check: " + field);
                    recordTableIss = issuersPage.getTableRecordByRecord(recordEditIss);
                    checkRecords(recordEditIss, recordTableIss);

                    Logging.stepName("Edit Issuer: Database Data Check: " + field);
                    recordDbIss = issuersPage.getDBDataById(recordEditIss.getId());
                    checkRecords(recordEditIss, recordDbIss);
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

    @Then("I delete an issuer")
    public void deleteIssuer() {

        try {

            Logging.stepName("Delete Issuer");
            issuersPage.searchIssuer(recordEditIss.getInstitutionName(), recordEditIss.getIssuerName());

            // Delete the newly created issuer
            if(adminCommon.clickDeleteByUniqueId(recordEditIss.getId())) {

                recordEditIss = issuersPage.checkAndDeleteIssuer(recordEditIss);

                    Logging.stepName("Delete Issuer: Table Deletion Check");
                    recordTableIss = issuersPage.getTableRecordByRecord(recordEditIss);
                    if (recordTableIss.getId() != null) {
                        Logging.failMessage("Issuer ID " + recordEditIss.getId() + " still exists in the table.");
                        softAssert.fail("Issuer ID " + recordEditIss.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Issuer ID " + recordEditIss.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Issuer: Database Deletion Check");
                    recordDbIss = issuersPage.getDBDataById(recordEditIss.getId());
                    if (recordDbIss.getId() != null) {
                        Logging.failMessage("Issuer ID " + recordEditIss.getId() + " still exists in the database.");
                        softAssert.fail("Issuer ID " + recordEditIss.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Issuer ID " + recordEditIss.getId() + " deleted from database");
                    }
                } else {
                    Logging.failMessage("Deletion of Issuer ID " + recordEditIss.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Issuer ID " + recordEditIss.getId() + " failed in the GUI.");
                }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(IssuerDataRecord record1, IssuerDataRecord record2) {

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

    @Then("I validate the issuer text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Issuers Text Input Fields");

        try{

            issuersPage.clickAddButton();

            for (String field : textFields){

                msg = issuersPage.validateTextInputFields(field);
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
            exc.printStackTrace();
        }

    }

    @Then("I add a new issuer with validation data")
    public void addSpecialCharacterIssuer() {

        try {

            for(int i=0; i<validationTestData.size();i++){

                //Enter Details and create
                recordNewIss = issuersPage.enterIssuerData(validationTestData.get(i));

                if(recordNewIss.getIssuerName()!=null) {

                    recordNewIss = issuersPage.getDbIdByIssuerName(recordNewIss);

                    Logging.stepName("Add Issuer: Table Data Check");
                    recordTableIss = issuersPage.getTableRecordByRecord(recordNewIss);
                    checkRecords(recordNewIss, recordTableIss);

                    Logging.stepName("Add Issuer: Database Data Check");

                    recordDbIss = issuersPage.getDBDataById(recordNewIss.getId());
                    checkRecords(recordNewIss, recordDbIss);
                }
                else{
                    Logging.stepName("Add Issuer");
                    softAssert.fail("Issuer not added correctly.");
                    Logging.failMessage("Issuer not added correctly.");
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

    @Then("I delete an issuer with validation data")
    public void deleteSpecialCharacterIssuer() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                Logging.stepName("Delete Issuer");
                recordEditIss = validationTestData.get(i);
                issuersPage.searchIssuer(recordEditIss.getInstitutionName(), recordEditIss.getIssuerName());

                if(adminCommon.clickDeleteByUniqueId(recordEditIss.getId())) {

                    recordEditIss = issuersPage.checkAndDeleteIssuer(recordEditIss);

                    Logging.stepName("Delete Issuer: Table Deletion Check");
                    recordTableIss = issuersPage.getTableRecordByRecord(recordEditIss);
                    if (recordTableIss.getId() != null) {
                        Logging.failMessage("Issuer ID " + recordEditIss.getId() + " still exists in the table.");
                        softAssert.fail("Issuer ID " + recordEditIss.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Issuer ID " + recordEditIss.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Issuer: Database Deletion Check");
                    recordDbIss = issuersPage.getDBDataById(recordEditIss.getId());
                    if (recordDbIss.getId() != null) {
                        Logging.failMessage("Issuer ID " + recordEditIss.getId() + " still exists in the database.");
                        softAssert.fail("Issuer ID " + recordEditIss.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Issuer ID " + recordEditIss.getId() + " deleted from database");
                    }
                } else {
                    Logging.failMessage("Deletion of Issuer ID " + recordEditIss.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Issuer ID " + recordEditIss.getId() + " failed in the GUI.");
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

    @Then("Check issuer scenario")
    public void checkIssuerScenario(){
        Logging.dataMsg(recordEditIss.getTestOutput());
        softAssert.assertAll();
    }

}


