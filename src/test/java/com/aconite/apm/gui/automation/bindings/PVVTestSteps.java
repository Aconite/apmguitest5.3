package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.*;
import com.aconite.apm.gui.automation.webpages.*;
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

public class PVVTestSteps {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    TokenProductsPage tokenProductsPage;
    TokenApplicationProfilePage tokenApplicationProfilePage;
    TokenProductGroupsPage tokenProductGroupsPage;
    TokenApplicationProfileTestSteps tokenApplicationProfileTestSteps;
    TokenProductTestSteps tokenProductTestSteps;
    TokenProductGroupsTestSteps tokenProductGroupsTestSteps;
    AdminCommon adminCommon;
    PVVPage pvvPage;
//    AbstractSteps abstractSteps;

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";
    List<PVVDataRecord> validationTestData = new ArrayList<>();

    public PVVDataRecord recordNewPVV = new PVVDataRecord();
    public PVVDataRecord recordEditPVV = new PVVDataRecord();
    public PVVDataRecord recordDbPVV = new PVVDataRecord();
    public PVVDataRecord recordTablePVV = new PVVDataRecord();

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();

    public PVVTestSteps(AbstractSteps abstractSteps) {
        try {

            webDriver = abstractSteps.getDriver();
            tokenProductGroupsPage = new TokenProductGroupsPage(webDriver);
            tokenProductsPage = new TokenProductsPage(webDriver);
            tokenApplicationProfilePage = new TokenApplicationProfilePage(webDriver);
            adminCommon = new AdminCommon(webDriver);
            pvvPage = new PVVPage(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewPVV = getJSONData("newPVV.json");
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationPvv.json");
            }

            reqFields.put("TokenProduct","Token product is required");
//            reqFields.put("AppSequenceNumber","Application sequence number must be an integer value of 1-99");
            reqFields.put("PVKIdentifier","PVK identifier is required");
            reqFields.put("PinVerificationMethod","PIN verification method is required");
            reqFields.put("PinVerificationKey","PIN verification key is required");

            editFields.add("PVKIdentifier");
            editFields.add("PinVerificationMethod");
            editFields.add("PinVerificationKey");

//            textFields.add("AppSequenceNumber");
            textFields.add("PVKIdentifier");


        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private PVVDataRecord getJSONData(String filename){

        PVVDataRecord outRecord = new PVVDataRecord();
        String path = testDataPath + "/"+ filename;

        try {

            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setParentInstitution((String) inst.get("parentInstitution"));
            outRecord.setParentIssuer((String) inst.get("parentIssuer"));
            outRecord.setParentTokenProduct((String) inst.get("parentTokenProduct"));
            outRecord.setParentTokenApplicationProfileName((String) inst.get("parentTokenApplicationProfileName"));
            outRecord.setParentTokenApplicationProfileAppSeqNum((String) inst.get("parentTokenApplicationProfileAppSeqNum"));
            outRecord.setPvkIdentifier((String) inst.get("pvkIdentifier"));
            outRecord.setPinVerificationKey((String) inst.get("pinVerificationKey"));
            outRecord.setPinVerificationMethod((String) inst.get("pinVerificationMethod"));


        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<PVVDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<PVVDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                PVVDataRecord outRecord = new PVVDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setParentInstitution(j.getString("parentInstitution"));
                outRecord.setParentIssuer(j.getString("parentIssuer"));
                outRecord.setParentTokenProduct(j.getString("parentTokenProduct"));
                outRecord.setParentTokenApplicationProfileName(j.getString("parentTokenApplicationProfileName"));
                outRecord.setParentTokenApplicationProfileAppSeqNum(j.getString("parentTokenApplicationProfileAppSeqNum"));
                outRecord.setPvkIdentifier(j.getString("pvkIdentifier"));
                outRecord.setPinVerificationKey(j.getString("pinVerificationKey"));
                outRecord.setPinVerificationMethod(j.getString("pinVerificationMethod"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Product Menu Additional PVVs")
    public void clickAdditionalPVVs() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnProductsAdditionalPVVsMenuItem();

            Logging.stepName("Click On Products Additional PVVs Menu Item");

            if(pvvPage.isPageOpened()){
                Logging.passMessage("Logged in and on Additional PVVs Page");
            }
            else{
                Logging.failMessage("Not logged in and on Additional PVVs Page");
                softAssert.fail("Not logged in and on Additional PVVs Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();

        }
    }

    @Then ("I check the pvv required fields")
    public void checkRequiredFields(){

        try{

            //click the add pvv button
            pvvPage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = pvvPage.checkRequiredFieldMessages(field, reqFields.get(field));
                if(rc){
                    Logging.passMessage(field + " object contains correct error message: " + reqFields.get(field));
                } else {
                    Logging.failMessage(field + " object does not contain correct error message: " + reqFields.get(field));
                    softAssert.fail(field + " object does not contain correct error message: " + reqFields.get(field));
                }
            }
        }
        catch(Exception exc){System.out.println("IN CATCH BLOCK");
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then ("I add a new pvv \"([^\"]*)\"$")
    public void addPvv(String newPvvDataFile)  {

        try {

            recordNewPVV = getJSONData(newPvvDataFile);

            recordNewPVV = pvvPage.enterPVVData(recordNewPVV);

            if(recordNewPVV.getPvkIdentifier() != null){

                String copyTestOutput = recordNewPVV.getTestOutput();
                recordNewPVV = pvvPage.getDBIdByPVKIdentifier(recordNewPVV.getPvkIdentifier());
                recordNewPVV.copyTestOutput(copyTestOutput);

                Logging.stepName("Add PVV: Table Data Check");
                recordTablePVV = pvvPage.getTableRecordByRecord(recordNewPVV);
                checkTableRecords(recordNewPVV, recordTablePVV);

                Logging.stepName("Add PVV: Database Data Check");
                recordDbPVV = pvvPage.getDBDataById(recordNewPVV);
                checkDBRecords(recordNewPVV, recordDbPVV);

            }
            else {

                Logging.stepName("Add PVV");
                softAssert.fail("PVV not added correctly.");
                Logging.failMessage("PVV not added correctly.");

            }

        }
        catch (Exception exc) {
            exc.printStackTrace();
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
        }


    }

    @Then ("I edit an existing pvv")
    public void editPvv(){

        try{

            recordEditPVV = recordNewPVV;

            for(String field :  editFields) {

                switch (field) {

                    case ("PVKIdentifier"):
                        recordEditPVV.setPvkIdentifier("PVV2_" + adminCommon.getDateTime());
                        break;

                    case ("PinVerificationMethod"):
                        recordEditPVV.setPinVerificationMethod("Password comparison");
                        break;

                    case ("PinVerificationKey"):
                        recordEditPVV.setPinVerificationKey("ClearBank PVK 1");
                        break;

                }

                Logging.stepName("Edit PVV: " + field);
                recordEditPVV = pvvPage.editPVVData(recordEditPVV, field);
                if(recordEditPVV.getTestOutput().contains("UNSUCCESSFUL")){
                    softAssert.fail("Edit PVV: Data change was unsuccessful - Either data value was not different or object did not trigger a change.");
                    Logging.failMessage("Edit PVV: Database Data Check: " + field +
                            "\nData change was unsuccessful - Either data value was not different or object did not trigger a change." +
                            "\nExpected values: " + recordEditPVV);

                }
                else {

                    Logging.stepName("Edit PVV: Table Data Check: " + field);
                    recordTablePVV = pvvPage.getTableRecordByRecord(recordEditPVV);
                    checkTableRecords(recordEditPVV, recordTablePVV);

                    Logging.stepName("Edit PVV: Database Data Check: " + field);
                    recordDbPVV = pvvPage.getDBDataById(recordEditPVV);
                    checkDBRecords(recordEditPVV, recordDbPVV);
                }

            }

        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then ("I delete a pvv")
    public void deletePvv(){

        try{
            Logging.stepName("Delete PVV");
            String copyTestOutput = recordEditPVV.getTestOutput();
            recordEditPVV = pvvPage.getDBDataById(recordEditPVV);
            recordEditPVV.copyTestOutput(copyTestOutput);

            if(adminCommon.clickDeleteByUniqueId(recordEditPVV.getId())){

                pvvPage.checkDeletePVV(recordEditPVV);

                Logging.stepName("Delete PVV: Table Deletion Check");
                recordTablePVV = pvvPage.getTableRecordByRecord(recordEditPVV);
                if (recordTablePVV.getId()!=null){
                    Logging.failMessage("PVV Name " + recordEditPVV.getId() + " still exists in the table.");
                    softAssert.fail("PVV Name " + recordEditPVV.getId() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("PVV Name " + recordEditPVV.getId() + " deleted from table");
                }

                Logging.stepName("Delete PVV: Database Deletion Check");
                recordDbPVV = pvvPage.getDBDataById(recordEditPVV);
                if (recordDbPVV.getId()!=null){
                    Logging.failMessage("PVV ID " + recordEditPVV.getId() + " still exists in the database.");
                    softAssert.fail("PVV ID " + recordEditPVV.getId() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("PVV ID " + recordEditPVV.getId() + " deleted from database");
                }


            }
            else{
                Logging.failMessage("Deletion of PVV ID " + recordEditPVV.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of PVV ID " + recordEditPVV.getId() + " failed in the GUI.");
            }


        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then ("I delete the pvv parent application profile")
    public void deleteTokenApplicationProfileForPvv(){

        try{

            Logging.stepName("Delete Token Application Profile for PVV");
            TokenApplicationProfileDataRecord recordEditTokenApplicationProfile;
            TokenApplicationProfileDataRecord recordTableTokenApplicationProfile;
            TokenApplicationProfileDataRecord recordDbTokenApplicationProfile;
            recordEditTokenApplicationProfile = tokenApplicationProfileTestSteps.getJSONData("pvvTokenApplicationProfile.json");
            recordEditTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());


            if(tokenApplicationProfilePage.deleteTokenApplicationProfileByName(recordEditTokenApplicationProfile)){

                Logging.stepName("Delete Token Application Profile for PVV: Table Deletion Check");

                recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordEditTokenApplicationProfile);
                if (recordTableTokenApplicationProfile.getName()!=null){
                    Logging.failMessage("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                    softAssert.fail("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " deleted from table");
                }

                Logging.stepName("Delete Token Application Profile for PVV: Database Deletion Check");
                recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                if (recordDbTokenApplicationProfile.getName()!=null){
                    Logging.failMessage("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                    softAssert.fail("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Application Profile for PVV Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
            }


        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }


    }

    @Then ("I delete the pvv parent token product")
    public void deleteTokenProductForPvv(){

        try{

            Logging.stepName("Delete Token Product for PVV");
            TokenProductDataRecord recordEditTokenProduct;
            TokenProductDataRecord recordTableTokenProduct;
            TokenProductDataRecord recordDbTokenProduct;

            recordEditTokenProduct = tokenProductTestSteps.getJSONData("pvvTokenProduct.json");
            recordEditTokenProduct = tokenProductsPage.getDbIdByTokenProductName(recordEditTokenProduct);
            recordEditTokenProduct = tokenProductsPage.getDBDataById(recordEditTokenProduct.getId());

            if(adminCommon.clickDeleteByUniqueId(recordEditTokenProduct.getId())){

                recordEditTokenProduct = tokenProductsPage.checkDeleteTokenProduct(recordEditTokenProduct);

                Logging.stepName("Delete Token Product for PVV: Table Deletion Check");
                recordTableTokenProduct = tokenProductsPage.getTableRecordByRecord(recordEditTokenProduct);
                if (recordTableTokenProduct.getName()!=null){
                    Logging.failMessage("Token Product for PVV Name " + recordEditTokenProduct.getName() + " still exists in the table.");
                    softAssert.fail("Token Product for PVV Name " + recordEditTokenProduct.getName() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Token Product for PVV Name " + recordEditTokenProduct.getName() + " deleted from table");
                }

                Logging.stepName("Delete Token Product for PVV: Database Deletion Check");
                recordDbTokenProduct = tokenProductsPage.getDbIdByTokenProductName(recordEditTokenProduct);
                if (recordDbTokenProduct.getName()!=null){
                    Logging.failMessage("Token Product for PVV Name " + recordEditTokenProduct.getName() + " still exists in the database.");
                    softAssert.fail("Token Product for PVV Name " + recordEditTokenProduct.getName() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Token Product for PVV Name " + recordEditTokenProduct.getName() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Product for PVV Name " + recordEditTokenProduct.getName() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Product for PVV Name " + recordEditTokenProduct.getName() + " failed in the GUI.");
            }


        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }


    }

    @Then ("I delete the pvv parent token product group")
    public void deleteTokenProductGroupForPvv(){

        try{

            TokenProductGroupDataRecord recordEditTokenProductGroup;
            TokenProductGroupDataRecord recordTableTokenProductGroup;
            TokenProductGroupDataRecord recordDbTokenProductGroup;

            Logging.stepName("Delete Token Product Group for PVV");

            recordDbTokenProductGroup = new TokenProductGroupDataRecord();

            recordEditTokenProductGroup = tokenProductGroupsTestSteps.getJSONData("pvvTokenProductGroup.json");
            recordEditTokenProductGroup = tokenProductGroupsPage.getDbIdByTokenProductGroupName(recordEditTokenProductGroup);
            recordEditTokenProductGroup = tokenProductGroupsPage.getDBDataById(recordEditTokenProductGroup);

            if(adminCommon.clickDeleteByUniqueId(recordEditTokenProductGroup.getId())){

                recordEditTokenProductGroup = tokenProductGroupsPage.checkDeleteTokenProductGroupById(recordEditTokenProductGroup);

                Logging.stepName("Delete Token Product Group for PVV: Table Deletion Check");
                recordTableTokenProductGroup = tokenProductGroupsPage.getTableRecordByRecord(recordEditTokenProductGroup);
                System.out.println("recordTableTokenProductGroup = " + recordTableTokenProductGroup);
                if (recordTableTokenProductGroup.getName()!=null){
                    Logging.failMessage("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " still exists in the table.");
                    softAssert.fail("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " deleted from table");
                }

                Logging.stepName("Delete Token Product Group for PVV: Database Deletion Check");
                   if (recordDbTokenProductGroup.getName()!=null){
                    Logging.failMessage("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " still exists in the database.");
                    softAssert.fail("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Product Group for PVV Name " + recordEditTokenProductGroup.getName() + " failed in the GUI.");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkTableRecords(PVVDataRecord record1, PVVDataRecord record2) {

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
        if (record1.toString().equals(record2.toString())) {
            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = " + record1 +
                    "\nRecord 2 Data = " + record2);
        }
        else{

            String[] rec1 = record1.toString().split(", ");
            String[] rec2 = record2.toString().split(", ");

            for(int i=0;i<rec1.length;i++){

                if(!rec1[i].equals(rec2[i])){
                    Logging.failMessage("Record field does not match:" +
                            "\nRecord 1 Data = " + rec1[i] +
                            "\nRecord 2 Data = " + rec2[i]);
                    softAssert.fail("Record data does not match:" +
                            "\nRecord 1 Data = " + rec1[i] +
                            "\nRecord 2 Data = " + rec2[i]);
                }

            }

        }
    }

    public void checkDBRecords(PVVDataRecord record1, PVVDataRecord record2) {


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
            String[] rec1 = record1.toString().split(", ");
            String[] rec2 = record2.toString().split(", ");

            for(int i=0;i<rec1.length;i++){

                if(!rec1[i].equals(rec2[i])){
                    Logging.failMessage("Record field does not match:" +
                            "\nRecord 1 Data = " + rec1[i] +
                            "\nRecord 2 Data = " + rec2[i]);
                    softAssert.fail("Record data does not match:" +
                            "\nRecord 1 Data = " + rec1[i] +
                            "\nRecord 2 Data = " + rec2[i]);
                }

            }

        }
    }

    @Then("I validate the pvv text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating PVV Text Input Fields");

        try{

            pvvPage.clickAddButton();

            for (String field : textFields){

                msg = pvvPage.validateTextInputFields(field);
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

    @Then("I add a new PVV with validation data")
    public void addSpecialCharacterPvv() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewPVV = validationTestData.get(i);

                recordNewPVV = pvvPage.enterPVVData(recordNewPVV);

                if(recordNewPVV.getPvkIdentifier() != null){

                    String copyTestOutput = recordNewPVV.getTestOutput();
                    recordNewPVV = pvvPage.getDBIdByPVKIdentifier(recordNewPVV.getPvkIdentifier());
                    recordNewPVV.copyTestOutput(copyTestOutput);

                    Logging.stepName("Add PVV: Table Data Check");
                    recordTablePVV = pvvPage.getTableRecordByRecord(recordNewPVV);
                    checkTableRecords(recordNewPVV, recordTablePVV);

                    Logging.stepName("Add PVV: Database Data Check");
                    recordDbPVV = pvvPage.getDBDataById(recordNewPVV);
                    checkDBRecords(recordNewPVV, recordDbPVV);

                }
                else {

                    Logging.stepName("Add PVV");
                    softAssert.fail("PVV not added correctly.");
                    Logging.failMessage("PVV not added correctly.");

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

    @Then("I delete a PVV with validation data")
    public void deleteSpecialCharacterPvv() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete PVV");
                recordEditPVV = validationTestData.get(i);
                String copyTestOutput = recordEditPVV.getTestOutput();
                recordEditPVV = pvvPage.getDBIdByPVKIdentifier(recordEditPVV.getPvkIdentifier());
                recordEditPVV.copyTestOutput(copyTestOutput);

                if(adminCommon.clickDeleteByUniqueId(recordEditPVV.getId())){

                    pvvPage.checkDeletePVV(recordEditPVV);

                    Logging.stepName("Delete PVV: Table Deletion Check");
                    recordTablePVV = pvvPage.getTableRecordByRecord(recordEditPVV);
                    if (recordTablePVV.getId()!=null){
                        Logging.failMessage("PVV Name " + recordEditPVV.getId() + " still exists in the table.");
                        softAssert.fail("PVV Name " + recordEditPVV.getId() + " still exists in the table.");
                    }
                    else{
                        Logging.passMessage("PVV Name " + recordEditPVV.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete PVV: Database Deletion Check");
                    recordDbPVV = pvvPage.getDBDataById(recordEditPVV);
                    if (recordDbPVV.getId()!=null){
                        Logging.failMessage("PVV ID " + recordEditPVV.getId() + " still exists in the database.");
                        softAssert.fail("PVV ID " + recordEditPVV.getId() + " still exists in the database.");
                    }
                    else{
                        Logging.passMessage("PVV ID " + recordEditPVV.getId() + " deleted from database");
                    }


                }
                else{
                    Logging.failMessage("Deletion of PVV ID " + recordEditPVV.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of PVV ID " + recordEditPVV.getId() + " failed in the GUI.");
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

    @Then ("Check pvv scenario")
    public void checkPvvScenario() {

        Logging.dataMsg(recordEditPVV.getTestOutput());
        softAssert.assertAll();
    }

}
