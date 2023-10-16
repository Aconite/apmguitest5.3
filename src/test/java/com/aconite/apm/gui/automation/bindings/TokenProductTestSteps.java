package com.aconite.apm.gui.automation.bindings;


import com.aconite.apm.gui.automation.records.TokenProductDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.TokenProductsPage;
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

public class TokenProductTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    static String testDataPath = "";
    String testType = "";

    private TokenProductsPage tokenProductsPage;
    private AbstractSteps abstractSteps;
    AdminCommon adminCommon;

    public TokenProductDataRecord recordNewTokenProduct = new TokenProductDataRecord();
    public TokenProductDataRecord recordEditTokenProduct = new TokenProductDataRecord();
    public TokenProductDataRecord recordTableTokenProduct = new TokenProductDataRecord();
    public TokenProductDataRecord recordDbTokenProduct = new TokenProductDataRecord();

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<TokenProductDataRecord> validationTestData = new ArrayList<>();

    public TokenProductTestSteps(AbstractSteps abstractSteps) {
        try {
            this.abstractSteps = abstractSteps;
            webDriver = abstractSteps.getDriver();
            tokenProductsPage = new TokenProductsPage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewTokenProduct = getJSONData("newTokenProduct.json");
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationTokenProduct.json");
            }

            reqFields.put("Name","Name is required");
            reqFields.put("IssuerTokenProductCode","Issuer token product code is required");
            reqFields.put("TokenProductGroup","Token product group is required");
            reqFields.put("Country","Country is required");
            reqFields.put("Language","Language is required");
            reqFields.put("PersoBureau","Perso bureau is required");

            editFields.add("Name");
            editFields.add("IssuerTokenProductCode");
            editFields.add("TokenProductGroup");
            editFields.add("RetentionDays");
            editFields.add("Country");
            editFields.add("Language");
            editFields.add("PersoBureau");
            editFields.add("FeedbackRequired");
            editFields.add("Active");
            editFields.add("ServiceCode");

            textFields.add("Name");
            textFields.add("IssuerTokenProductCode");
            textFields.add("RetentionDays");
            textFields.add("ServiceCode");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static TokenProductDataRecord getJSONData(String filename){

        TokenProductDataRecord outRecord = new TokenProductDataRecord();
        String path = TokenProductTestSteps.testDataPath + "/"+ filename;

        try {

            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setParentInstitution((String) inst.get("parentInstitution"));
            outRecord.setParentIssuer((String) inst.get("parentIssuer"));
            outRecord.setName((String) inst.get("name"));
            outRecord.setIssuer((String) inst.get("issuer"));
            outRecord.setIssuerTokenProductCode((String) inst.get("issuerTokenProductCode"));
            outRecord.setTokenProductGroup((String) inst.get("tokenProductGroup"));
            outRecord.setRetentionDays((String) inst.get("retentionDays"));
            outRecord.setCountry((String) inst.get("country"));
            outRecord.setLanguage((String) inst.get("language"));
            outRecord.setPersoBureau((String) inst.get("persoBureau"));
            outRecord.setFeedbackRequired((String) inst.get("feedbackRequired"));
            outRecord.setActive((String) inst.get("active"));
            outRecord.setServiceCode((String) inst.get("serviceCode"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<TokenProductDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<TokenProductDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                TokenProductDataRecord outRecord = new TokenProductDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setParentInstitution(j.getString("parentInstitution"));
                outRecord.setParentIssuer(j.getString("parentIssuer"));
                outRecord.setName(j.getString("name"));
                outRecord.setIssuer(j.getString("issuer"));
                outRecord.setIssuerTokenProductCode(j.getString("issuerTokenProductCode"));
                outRecord.setTokenProductGroup(j.getString("tokenProductGroup"));
                outRecord.setRetentionDays(j.getString("retentionDays"));
                outRecord.setCountry(j.getString("country"));
                outRecord.setLanguage(j.getString("language"));
                outRecord.setPersoBureau(j.getString("persoBureau"));
                outRecord.setFeedbackRequired(j.getString("feedbackRequired"));
                outRecord.setActive(j.getString("active"));
                outRecord.setServiceCode(j.getString("serviceCode"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Products Menu Token Products")
    public void clickAdminTokenProducts() {

        try {
            HomePage homePage = new HomePage(abstractSteps.getDriver());
            homePage.clickOnProductsTokenProductMenuItem();

            if (tokenProductsPage.isPageOpened()) {
                Logging.passMessage("Logged in and on Token Products Page");
            } else {
                Logging.failMessage("Not logged in and on Token Products Page");
                softAssert.fail("Not logged in and on Token Products Page");
            }

        } catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the token product required fields")
    public void checkRequiredFields(){

        try{

            tokenProductsPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = tokenProductsPage.checkRequiredFieldMessages(field, reqFields.get(field));
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
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I add a new token product \"([^\"]*)\"$")
    public void addTokenProduct(String newTokenProductDataFile){

        try {
            recordNewTokenProduct = getJSONData(newTokenProductDataFile);

            // Enter Details and create
            recordNewTokenProduct = tokenProductsPage.enterTokenProductData(recordNewTokenProduct);

            if (recordNewTokenProduct.getName() != null) {

                recordNewTokenProduct = tokenProductsPage.getDbIdByTokenProductName(recordNewTokenProduct);

                Logging.stepName("Add Token Product: Table Data Check");
                recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordNewTokenProduct);
                checkTableRecords(recordNewTokenProduct, recordTableTokenProduct);

                Logging.stepName("Add Token Product: Database Data Check");
                recordDbTokenProduct = tokenProductsPage.getDBDataById(recordNewTokenProduct.getId());
                checkRecords(recordNewTokenProduct, recordDbTokenProduct);

            }
            else{
                Logging.stepName("Add Token Product");
                softAssert.fail("Token Product not added correctly.\n" + recordNewTokenProduct.getTestOutput());
                Logging.failMessage("Token Product not added correctly.");
            }


        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an existing token product")
    public void editTokenProduct(){

        try{

            recordEditTokenProduct = recordNewTokenProduct;

            for(String field :  editFields) {

                switch (field) {

                    case ("Name"):
                        recordEditTokenProduct.setName("TestBank_" + adminCommon.getDateTime());
                        break;

                    case("IssuerTokenProductCode"):
                        recordEditTokenProduct.setIssuerTokenProductCode("TestBank_" + adminCommon.getDateTime());
                        break;

                    case("Issuer"):
                        recordEditTokenProduct.setIssuer("TestBank UK");
                        break;

                    case("TokenProductGroup"):
                        recordEditTokenProduct.setTokenProductGroup("TestBank Sapphire");
                        break;

                    case("RetentionDays"):
                        recordEditTokenProduct.setRetentionDays("300");
                        break;

                    case("Country"):
                        recordEditTokenProduct.setCountry("Antigua and Barbuda");
                        break;

                    case("Language"):
                        recordEditTokenProduct.setLanguage("Welsh");
                        break;

                    case("PersoBureau"):
                        recordEditTokenProduct.setPersoBureau("Card USA");
                        break;

                    case("FeedbackRequired"):
                        recordEditTokenProduct.setFeedbackRequired("false");
                        break;

                    case("Active"):
                        recordEditTokenProduct.setActive("false");
                        break;

                    case("ServiceCode"):
                        recordEditTokenProduct.setServiceCode("321");
                        break;

                }

                recordEditTokenProduct = tokenProductsPage.editTokenProductCodeData(recordEditTokenProduct, field);

                if(recordEditTokenProduct.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Token Product");
                    Logging.failMessage("Edit Token Product: Data Check: " + field +
                            "\n" + recordEditTokenProduct.getTestOutput());
                    softAssert.fail("Edit Interface: Data Check: " + field +
                            "\n" + recordEditTokenProduct.getTestOutput());

                }
                else {
                    Logging.stepName("Edit Token Product: Table Data Check: " + field);
                    recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordEditTokenProduct);
                    checkTableRecords(recordEditTokenProduct, recordTableTokenProduct);

                    Logging.stepName("Edit Token Product: Database Data Check: " + field);
                    recordDbTokenProduct = tokenProductsPage.getDBDataById(recordEditTokenProduct.getId());
                    checkRecords(recordEditTokenProduct, recordDbTokenProduct);
                }


            }

        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then("I delete a token product")
    public void deleteTokenProduct(){

        try{

            Logging.stepName("Delete Token Product");

            if(adminCommon.clickDeleteByUniqueId(recordEditTokenProduct.getId())){

                recordEditTokenProduct = tokenProductsPage.checkDeleteTokenProduct(recordEditTokenProduct);

                Logging.stepName("Delete Token Product: Table Deletion Check");
                recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordEditTokenProduct);
                if (recordTableTokenProduct.getId()!=null){
                    Logging.failMessage("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the table.");
                    softAssert.fail("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Token Product ID " + recordEditTokenProduct.getId() + " deleted from table");
                }

                Logging.stepName("Delete Token Product: Database Deletion Check");
                recordDbTokenProduct = tokenProductsPage.getDBDataById(recordEditTokenProduct.getId());
                if (recordDbTokenProduct.getId()!=null){
                    Logging.failMessage("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the database.");
                    softAssert.fail("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Token Product ID " + recordEditTokenProduct.getId() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Product ID " + recordEditTokenProduct.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Product ID " + recordEditTokenProduct.getId() + " failed in the GUI.");
            }

        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(TokenProductDataRecord record1, TokenProductDataRecord record2) {

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

    public void checkTableRecords(TokenProductDataRecord record1, TokenProductDataRecord record2) {

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
        if(record1.getId().equalsIgnoreCase(record2.getId()) &&
                record1.getName().equalsIgnoreCase(record2.getName()) &&
                record1.getIssuerTokenProductCode().equalsIgnoreCase(record2.getIssuerTokenProductCode()) &&
                record1.getIssuer().equalsIgnoreCase(record2.getIssuer()) &&
                record1.getTokenProductGroup().equalsIgnoreCase(record2.getTokenProductGroup()) &&
                // record1.getRetentionDays().equalsIgnoreCase(record2.getRetentionDays()) &&
                record1.getCountry().equalsIgnoreCase(record2.getCountry()) &&
                record1.getPersoBureau().equalsIgnoreCase(record2.getPersoBureau()) &&
                // record1.getFeedbackRequired().equalsIgnoreCase(record2.getFeedbackRequired()) &&
                record1.getActive().equalsIgnoreCase(record2.getActive())){

            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = DBID=" + record1.getId() +
                    ", NAME=" + record1.getName() +
                    ", ISSUERTOKENPRODUCTCODE=" + record1.getIssuerTokenProductCode() +
                    ", ISSUER=" + record1.getIssuer() +
                    ", TOKENPRODUCTGROUP=" + record1.getTokenProductGroup() +
                    // ", RETENTIONDAYS=" + record1.getRetentionDays() +
                    ", COUNTRY=" + record1.getCountry() +
                    ", PERSOBUREAU=" + record1.getPersoBureau() +
                    // ", FEEDBACKREQUIRED=" + record1.getFeedbackRequired() +
                    ", ACTIVE=" + record1.getActive() +
                    "\nRecord 2 Data = DBID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", ISSUERTOKENPRODUCTCODE=" + record2.getIssuerTokenProductCode() +
                    ", ISSUER=" + record2.getIssuer() +
                    ", TOKENPRODUCTGROUP=" + record2.getTokenProductGroup() +
                    // ", RETENTIONDAYS=" + record2.getRetentionDays() +
                    ", COUNTRY=" + record2.getCountry() +
                    ", PERSOBUREAU=" + record2.getPersoBureau() +
                    // ", FEEDBACKREQUIRED=" + record2.getFeedbackRequired() +
                    ", ACTIVE=" + record2.getActive());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    ", NAME=" + record1.getName() +
                    ", ISSUERTOKENPRODUCTCODE=" + record1.getIssuerTokenProductCode() +
                    ", ISSUER=" + record1.getIssuer() +
                    ", TOKENPRODUCTGROUP=" + record1.getTokenProductGroup() +
                    // ", RETENTIONDAYS=" + record1.getRetentionDays() +
                    ", COUNTRY=" + record1.getCountry() +
                    ", PERSOBUREAU=" + record1.getPersoBureau() +
                    // ", FEEDBACKREQUIRED=" + record1.getFeedbackRequired() +
                    ", ACTIVE=" + record1.getActive() +
                    "\nRecord 2 Data = DBID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", ISSUERTOKENPRODUCTCODE=" + record2.getIssuerTokenProductCode() +
                    ", ISSUER=" + record2.getIssuer() +
                    ", TOKENPRODUCTGROUP=" + record2.getTokenProductGroup() +
                    // ", RETENTIONDAYS=" + record2.getRetentionDays() +
                    ", COUNTRY=" + record2.getCountry() +
                    ", PERSOBUREAU=" + record2.getPersoBureau() +
                    // ", FEEDBACKREQUIRED=" + record2.getFeedbackRequired() +
                    ", ACTIVE=" + record2.getActive());
        }
        
    }

    @Then("I validate the token product text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Encryption Zones Text Input Fields");

        try{

            tokenProductsPage.clickAddButton();

            for (String field : textFields){

                msg = tokenProductsPage.validateTextInputFields(field);
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
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I add a new Token Product with validation data")
    public void addSpecialCharacterTokenProduct() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewTokenProduct = validationTestData.get(i);

                // Enter Details and create
                recordNewTokenProduct = tokenProductsPage.enterTokenProductData(recordNewTokenProduct);

                if (recordNewTokenProduct.getName() != null) {

                    recordNewTokenProduct = tokenProductsPage.getDbIdByTokenProductName(recordNewTokenProduct);

                    Logging.stepName("Add Token Product: Table Data Check");
                    recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordNewTokenProduct);
                    checkTableRecords(recordNewTokenProduct, recordTableTokenProduct);

                    Logging.stepName("Add Token Product: Database Data Check");
                    recordDbTokenProduct = tokenProductsPage.getDBDataById(recordNewTokenProduct.getId());
                    checkRecords(recordNewTokenProduct, recordDbTokenProduct);

                }
                else{
                    Logging.stepName("Add Token Product");
                    softAssert.fail("Token Product not added correctly.\n" + recordNewTokenProduct.getTestOutput());
                    Logging.failMessage("Token Product not added correctly.");
                }

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I delete a Token Product with validation data")
    public void deleteSpecialCharacterTokenProduct() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Token Product");
                recordEditTokenProduct = validationTestData.get(i);

                if(adminCommon.clickDeleteByUniqueId(recordEditTokenProduct.getId())){

                    recordEditTokenProduct = tokenProductsPage.checkDeleteTokenProduct(recordEditTokenProduct);

                    Logging.stepName("Delete Token Product: Table Deletion Check");
                    recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordEditTokenProduct);
                    if (recordTableTokenProduct.getId()!=null){
                        Logging.failMessage("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the table.");
                        softAssert.fail("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the table.");
                    }
                    else{
                        Logging.passMessage("Token Product ID " + recordEditTokenProduct.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Token Product: Database Deletion Check");
                    recordDbTokenProduct = tokenProductsPage.getDBDataById(recordEditTokenProduct.getId());
                    if (recordDbTokenProduct.getId()!=null){
                        Logging.failMessage("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the database.");
                        softAssert.fail("Token Product ID " + recordEditTokenProduct.getId() + " still exists in the database.");
                    }
                    else{
                        Logging.passMessage("Token Product ID " + recordEditTokenProduct.getId() + " deleted from database");
                    }

                }
                else{
                    Logging.failMessage("Deletion of Token Product ID " + recordEditTokenProduct.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Token Product ID " + recordEditTokenProduct.getId() + " failed in the GUI.");
                }

            }
        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + exc.getStackTrace().toString());
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the user can access the Token Products page")
    public void checkAccess(){}

    @Then("I check the user can filter data on the Token Products page")
    public void checkFilter(){}

    @Then("I check the user can not add a Token Product")
    @Then("I check the user can not edit a Token Product")
    @Then("I check the user can not delete a Token Product")


    @Then("Check token product scenario")

    public void checkTokenProductScenario() {
        Logging.dataMsg(recordEditTokenProduct.getTestOutput());
        softAssert.assertAll();
    }


}