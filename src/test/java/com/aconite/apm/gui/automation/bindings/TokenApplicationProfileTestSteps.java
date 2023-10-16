package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.TokenApplicationProfileDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.TokenApplicationProfilePage;
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

public class TokenApplicationProfileTestSteps {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    static String testDataPath = "";
    String testType = "";

    TokenProductsPage tokenProductsPage;
    TokenApplicationProfilePage tokenApplicationProfilePage;
    AdminCommon adminCommon;

    public TokenApplicationProfileDataRecord recordNewTokenApplicationProfile = new TokenApplicationProfileDataRecord();
    public TokenApplicationProfileDataRecord recordEditTokenApplicationProfile = new TokenApplicationProfileDataRecord();
    public TokenApplicationProfileDataRecord recordTableTokenApplicationProfile = new TokenApplicationProfileDataRecord();
    public TokenApplicationProfileDataRecord recordDbTokenApplicationProfile = new TokenApplicationProfileDataRecord();

    List<String> editFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<String> textFields = new ArrayList<>();
    List<TokenApplicationProfileDataRecord> validationTestData = new ArrayList<>();

    public TokenApplicationProfileTestSteps(AbstractSteps abstractSteps) {
        try {

            webDriver = abstractSteps.getDriver();
            tokenProductsPage = new TokenProductsPage(webDriver);
            tokenApplicationProfilePage = new TokenApplicationProfilePage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewTokenApplicationProfile = getJSONData("newTokenApplicationProfile.json");
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationTokenApplicationProfile.json");
            }

            reqFields.put("Institution","Institution is required");
            reqFields.put("Issuer","Issuer is required");
            reqFields.put("TokenProduct","Token product is required");
            reqFields.put("AppSequenceNumber","App sequence number is required");
            reqFields.put("Name","Name is required");
            reqFields.put("Status","Status is required");
            reqFields.put("PinLength","PIN length is required");
            reqFields.put("ImportEncryptionZone","Import encryption zone is required");
            reqFields.put("ExportEncryptionZone","Export encryption zone is required");
            reqFields.put("PinMailerDelayHours","PIN mailer delay hours is required");
            reqFields.put("PukLength","PUK length is required");
            reqFields.put("PinVerificationKey","PIN verification key is required");
            reqFields.put("PinTemplate","PIN template is required");
            reqFields.put("PukTemplate","PUK template is required");
            reqFields.put("PasswordTemplate","Password template is required");
            reqFields.put("PinOverSMSDelayHours","PIN over SMS delay hours is required");
            reqFields.put("SmsPasswordDelayHours","SMS password delay hours is required");
            reqFields.put("SmsSender","SMS sender is required");
            reqFields.put("SmsValidityHours","SMS validity hours is required");
            reqFields.put("SmsPasswordHours","SMS password expiry hours is required");
            reqFields.put("SmsClass","SMS class is required");

            // editFields.add("Name"); // Used to identify - need to work out how to do this
            editFields.add("Status");
            editFields.add("DefaultApp");
            editFields.add("PinRequired");
            editFields.add("PinLength");
            editFields.add("ImportEncryptionZone");
            editFields.add("ExportEncryptionZone");
            editFields.add("ReturnInterface");
            editFields.add("PinMailerDelayHours");
            editFields.add("PinHeldBySeqNum");
            editFields.add("AllowPinChange");
            editFields.add("AllowOnlinePinChange");
            editFields.add("AllowOnlinePinView");
            editFields.add("IndependentTokenPins");
            editFields.add("PukRequired");
            editFields.add("PukLength");
            editFields.add("AllowPukChange");
            editFields.add("PukHeldBySeqNum");
            editFields.add("IndependentTokenPuks");
            editFields.add("PinVerificationMethod");
            editFields.add("PinVerificationKey");
            editFields.add("PinTries");
            editFields.add("VerificationBackoff");
            editFields.add("BackoffMultiplier");
            editFields.add("TokenImportPinDeliveryMethod");
            editFields.add("TokenOrderPinDeliveryMethod");
            editFields.add("ForceTokenOrderPinDelivery");
            editFields.add("PinAdvicePinDeliveryMethod");
            editFields.add("ForcePinAdvicePinDelivery");
            editFields.add("UpdatePinDeliveryMethod");
            editFields.add("VppPinDeliveryMethod");
            editFields.add("PinTemplate");
            editFields.add("SecondaryPinTemplate");
            editFields.add("PukTemplate");
            editFields.add("PasswordTemplate");
            editFields.add("MessageTemplate");
            editFields.add("MessageInterface");
            editFields.add("SmsInterface");
            editFields.add("PinOverSmsDelayHours");
            editFields.add("SmsPasswordDelayHours");
            editFields.add("SmsSender");
            editFields.add("SmsValidityHours");
            editFields.add("SmsPasswordExpiryHours");
            editFields.add("SmsClass");


            textFields.add("Name");
            textFields.add("AppSequenceNumber");
            textFields.add("PinLength");
            textFields.add("PinMailerDelayHours");
            textFields.add("PinHeldBySeqNum");
            textFields.add("PukLength");
            textFields.add("PukHeldBySeqNum");
            textFields.add("PinTries");
            textFields.add("VerificationBackoff");
            textFields.add("BackoffMultiplier");
            textFields.add("PinOverSmsDelayHours");
            textFields.add("SmsPasswordDelayHours");
            textFields.add("SmsSender");
            textFields.add("SmsValidityHours");
            textFields.add("SmsPasswordExpiryHours");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static TokenApplicationProfileDataRecord getJSONData(String filename){

        TokenApplicationProfileDataRecord outRecord = new TokenApplicationProfileDataRecord();
        String path = TokenApplicationProfileTestSteps.testDataPath + "/" + filename;

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setParentInstitution((String) inst.get("parentInstitution"));
            outRecord.setParentIssuer((String) inst.get("issuer"));
            outRecord.setParentTokenProductName((String) inst.get("tokenProduct"));
            outRecord.setParentTokenProductGroupName((String) inst.get("tokenProduct"));
            outRecord.setAppSequenceNumber((String) inst.get("appSequenceNumber"));
            outRecord.setName((String) inst.get("name"));
            outRecord.setStatus((String) inst.get("status"));
            outRecord.setPinLength((String) inst.get("pinLength"));
            outRecord.setImportEncryptionZone((String) inst.get("importEncryptionZone"));
            outRecord.setExportEncryptionZone((String) inst.get("exportEncryptionZone"));
            outRecord.setPinMailerDelayHours((String) inst.get("pinMailerDelayHours"));
            outRecord.setPinVerificationKey((String) inst.get("pinVerificationKey"));
            outRecord.setPinTemplate((String) inst.get("pinTemplate"));
            outRecord.setPukTemplate((String) inst.get("pukTemplate"));
            outRecord.setPasswordTemplate((String) inst.get("passwordTemplate"));
            outRecord.setPinOverSMSDelayHours((String) inst.get("pinOverSMSDelayHours"));
            outRecord.setSmsPasswordDelayHours((String) inst.get("smsPasswordDelayHours"));
            outRecord.setSmsSender((String) inst.get("smsSender"));
            outRecord.setSmsValidityHours((String) inst.get("smsValidityHours"));
            outRecord.setSmsPasswordExpiryHours((String) inst.get("smsPasswordHours"));
            outRecord.setSmsClass((String) inst.get("smsClass"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<TokenApplicationProfileDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<TokenApplicationProfileDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                TokenApplicationProfileDataRecord outRecord = new TokenApplicationProfileDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setParentInstitution(j.getString("parentInstitution"));
                outRecord.setParentIssuer(j.getString("issuer"));
                outRecord.setParentTokenProductName(j.getString("tokenProduct"));
                outRecord.setParentTokenProductGroupName(j.getString("tokenProduct"));
                outRecord.setAppSequenceNumber(j.getString("appSequenceNumber"));
                outRecord.setName(j.getString("name"));
                outRecord.setStatus(j.getString("status"));
                outRecord.setPinLength(j.getString("pinLength"));
                outRecord.setImportEncryptionZone(j.getString("importEncryptionZone"));
                outRecord.setExportEncryptionZone(j.getString("exportEncryptionZone"));
                outRecord.setPinMailerDelayHours(j.getString("pinMailerDelayHours"));
                outRecord.setPinVerificationKey(j.getString("pinVerificationKey"));
                outRecord.setPinTemplate(j.getString("pinTemplate"));
                outRecord.setPukTemplate(j.getString("pukTemplate"));
                outRecord.setPasswordTemplate(j.getString("passwordTemplate"));
                outRecord.setPinOverSMSDelayHours(j.getString("pinOverSMSDelayHours"));
                outRecord.setSmsPasswordDelayHours(j.getString("smsPasswordDelayHours"));
                outRecord.setSmsSender(j.getString("smsSender"));
                outRecord.setSmsValidityHours(j.getString("smsValidityHours"));
                outRecord.setSmsPasswordExpiryHours(j.getString("smsPasswordHours"));
                outRecord.setSmsClass(j.getString("smsClass"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Product Menu Token App Profiles")
    public void clickProductTokenAppProfiles() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnProductsTokenAppProfilesMenuItem();

            Logging.stepName("Click On Products Token Product Groups Menu Item");

            if(tokenApplicationProfilePage.isPageOpened()){
                Logging.passMessage("Logged in and on Token Product Groups Page");
            }
            else{
                Logging.failMessage("Not logged in and on Token Product Groups Page");
                softAssert.fail("Not logged in and on Token Product Groups Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then ("I check the Token Application Profile required fields")
    public void checkRequiredFields(){

        try{

             // Open Token App Profiles Detail window
            tokenApplicationProfilePage.clickAddTokenApplication();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){
                boolean rc = tokenApplicationProfilePage.checkRequiredFieldMessages(field, reqFields.get(field));
                if(rc){
                    Logging.passMessage(field + " object contains correct error message: " + reqFields.get(field));
                } else {
                    Logging.failMessage(field + " object does not contain correct error message: " + reqFields.get(field));
                    softAssert.fail(field + " object does not contain correct error message: " + reqFields.get(field));
                }
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then ("I add a new token application profile \"([^\"]*)\"$")
    public void addTokenApplicationProfile(String newTokenAppProfileDataFile){

        try{

            recordNewTokenApplicationProfile = getJSONData(newTokenAppProfileDataFile);

            recordNewTokenApplicationProfile = tokenApplicationProfilePage.enterTokenApplicationProfileData(recordNewTokenApplicationProfile);


            if(recordNewTokenApplicationProfile.getName() != null){

                //getDBDataByName clears Test Output field
                String copyTestOutput = recordNewTokenApplicationProfile.getTestOutput();
                recordNewTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordNewTokenApplicationProfile.getName());
                recordNewTokenApplicationProfile.copyTestOutput(copyTestOutput);


                Logging.stepName("Add Token Application Profile: Table Data Check");
                recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordNewTokenApplicationProfile);
                checkTableRecords(recordNewTokenApplicationProfile, recordTableTokenApplicationProfile);

                Logging.stepName("Add Token Application Profile: Database Data Check");
                recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordNewTokenApplicationProfile.getName());
                checkRecords(recordNewTokenApplicationProfile, recordDbTokenApplicationProfile);


            }
            else{
                Logging.stepName("Add Token Application Profile");
                softAssert.fail("Token Application Profile not added correctly.\n" + recordNewTokenApplicationProfile.getTestOutput());
                Logging.failMessage("Token Application Profile not added correctly.");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then ("I edit an existing token application profile")
    public void editTokenApplicationProfile(){

        recordEditTokenApplicationProfile = recordNewTokenApplicationProfile;

        try{


            for(String field :  editFields) {

                switch (field) {

                    /* Currently, not done - name and token product code used to id token application profile */
                    case ("Name"):
                        recordEditTokenApplicationProfile.setName("TAP" + adminCommon.getDateTime());
                        break;

                    case ("Status"):
                        recordEditTokenApplicationProfile.setStatus("Active");
                        break;

                    case ("DefaultApp"):
                        recordEditTokenApplicationProfile.setDefaultApp("true");
                        break;

                    case ("PinRequired"):
                        recordEditTokenApplicationProfile.setPinRequired("true");
                        break;

                    case ("PinLength"):
                        recordEditTokenApplicationProfile.setPinLength("6");
                        break;

                    case ("ImportEncryptionZone"):
                        recordEditTokenApplicationProfile.setImportEncryptionZone("LastBank Import");
                        break;

                    case ("ExportEncryptionZone"):
                        recordEditTokenApplicationProfile.setExportEncryptionZone("QABank Export");
                        break;

                    case ("ReturnInterface"):
                        recordEditTokenApplicationProfile.setReturnInterface("ClearBank Return interface");
                        break;

                    case ("PinMailerDelayHours"):
                        recordEditTokenApplicationProfile.setPinMailerDelayHours("12");
                        break;

                    case ("PinHeldBySeqNum"):
                        recordEditTokenApplicationProfile.setPinHeldBySeqNum("1");
                        break;

                    case ("AllowPinChange"):
                        recordEditTokenApplicationProfile.setAllowPinChange("true");
                        break;

                    case ("AllowOnlinePinChange"):
                        recordEditTokenApplicationProfile.setAllowOnlinePinChange("true");
                        break;

                    case ("AllowOnlinePinView"):
                        recordEditTokenApplicationProfile.setAllowOnlinePinView("true");
                        break;

                    case ("IndependentTokenPins"):
                        recordEditTokenApplicationProfile.setIndependentTokenPins("false");
                        break;

                    case ("PukRequired"):
                        recordEditTokenApplicationProfile.setPukRequired("true");
                        recordEditTokenApplicationProfile.setPukLength("5");
                        break;

                    case ("PukLength"):
                        recordEditTokenApplicationProfile.setPukLength("6");
                        break;


                    case ("AllowPukChange"):
                        recordEditTokenApplicationProfile.setAllowPukChange("true");
                        break;

                    case ("PukHeldBySeqNum"):
                        recordEditTokenApplicationProfile.setPukHeldBySeqNum("6");
                        break;

                    case ("IndependentTokenPuks"):
                        recordEditTokenApplicationProfile.setIndependentTokenPuks("false");
                        break;

                    case ("PinVerificationMethod"):
                        recordEditTokenApplicationProfile.setPinVerificationMethod("CVV");
                        break;

                    case ("PinVerificationKey"):
                        recordEditTokenApplicationProfile.setPinVerificationKey("ClearBank PVK 1");
                        break;

                    case ("PinTries"):
                        recordEditTokenApplicationProfile.setPinTries("3");
                        break;

                    case ("VerificationBackoff"):
                        recordEditTokenApplicationProfile.setVerificationBackoff("3");
                        break;

                    case ("BackoffMultiplier"):
                        recordEditTokenApplicationProfile.setBackoffMultiplier("2");
                        break;

                    case ("TokenImportPinDeliveryMethod"):
                        recordEditTokenApplicationProfile.setTokenImportPinDeliveryMethod("Message");
                        break;

                    case ("TokenOrderPinDeliveryMethod"):
                        recordEditTokenApplicationProfile.setTokenOrderPinDeliveryMethod("Message");
                        break;

                    case ("ForceTokenOrderPinDelivery"):
                        recordEditTokenApplicationProfile.setForceTokenOrderPinDelivery("true");
                        break;

                    case ("PinAdvicePinDeliveryMethod"):
                        recordEditTokenApplicationProfile.setPinAdvicePinDeliveryMethod("Message");
                        break;

                    case ("ForcePinAdvicePinDelivery"):
                        recordEditTokenApplicationProfile.setForcePinAdvicePinDelivery("true");
                        break;

                    case ("UpdatePinDeliveryMethod"):
                        recordEditTokenApplicationProfile.setUpdatePinDeliveryMethod("Message");
                        break;

                    case ("VppPinDeliveryMethod"):
                        recordEditTokenApplicationProfile.setVppPinDeliveryMethod("Message");
                        break;

                    case ("PinTemplate"):
                        recordEditTokenApplicationProfile.setPinTemplate("TestBank PIN template 2");
                        break;

                    case ("SecondaryPinTemplate"):
                        recordEditTokenApplicationProfile.setSecondaryPinTemplate("TestBank PIN template");
                        break;

                    case ("PukTemplate"):
                        recordEditTokenApplicationProfile.setPukTemplate("TestBank PUK template 2");
                        break;

                    case ("PasswordTemplate"):
                        recordEditTokenApplicationProfile.setPasswordTemplate("TestBank Password template 2");
                        break;

                    case ("MessageTemplate"):
                        recordEditTokenApplicationProfile.setMessageTemplate("TestBank Message template");
                        break;

                    case ("MessageInterface"):
                        recordEditTokenApplicationProfile.setMessageInterface("TestBank Message interface");
                        break;

                    case ("SmsInterface"):
                        recordEditTokenApplicationProfile.setSmsInterface("ClearBank PDG");
                        break;

                    case ("PinOverSMSDelayHours"):
                        recordEditTokenApplicationProfile.setPinOverSMSDelayHours("4");
                        break;

                    case ("SmsPasswordDelayHours"):
                        recordEditTokenApplicationProfile.setSmsPasswordDelayHours("4");
                        break;

                    case ("SmsSender"):
                        recordEditTokenApplicationProfile.setSmsSender("SMS Sender");
                        break;

                    case ("SmsValidityHours"):
                        recordEditTokenApplicationProfile.setSmsValidityHours("6");
                        break;

                    case ("SmsPasswordExpiryHours"):
                        recordEditTokenApplicationProfile.setSmsPasswordExpiryHours("8");
                        break;

                    case ("SmsClass"):
                        recordEditTokenApplicationProfile.setSmsClass("Class 1 (normal)");
                        break;

                }

                recordEditTokenApplicationProfile = tokenApplicationProfilePage.editTokenApplicationProfileData(recordEditTokenApplicationProfile, field);

                if(recordEditTokenApplicationProfile.getTestOutput().contains("UNSUCCESSFUL")){
                    recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                    Logging.failMessage("Edit Token Application Profile: Database Data Check: " + field +
                            "\nData change was unsuccessful - Either data value was not different or object did not trigger a change.");
                }
                else {
                    Logging.stepName("Edit Token Application Profile: Database Data Check: " + field);
                    recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                    checkRecords(recordEditTokenApplicationProfile, recordDbTokenApplicationProfile);

                    Logging.stepName("Edit Token Application Profile: Table Data Check: " + field);
                    recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordEditTokenApplicationProfile);
                    checkTableRecords(recordEditTokenApplicationProfile, recordTableTokenApplicationProfile);

                }

            }

        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @Then ("I delete a token application profile")
    public void deleteTokenApplicationProfile(){

        try{
            Logging.stepName("Delete Token Application Profile");

            //getDBDataByName clears Test Output field
            String copyTestOutput = recordEditTokenApplicationProfile.getTestOutput();
            recordEditTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
            recordEditTokenApplicationProfile.copyTestOutput(copyTestOutput);

            tokenApplicationProfilePage.filterByRecord(recordEditTokenApplicationProfile);

            if(adminCommon.clickDeleteByUniqueId(recordEditTokenApplicationProfile.getParentTokenProductId() + "_" + recordEditTokenApplicationProfile.getAppSequenceNumber())){
//            if(tokenApplicationProfilePage.deleteTokenApplicationProfileByName(recordEditTokenApplicationProfile)){

                tokenApplicationProfilePage.checkDeleteTokenAppProfile(recordEditTokenApplicationProfile);

                Logging.stepName("Delete Token Application Profile: Table Deletion Check");
                recordTableTokenApplicationProfile = new TokenApplicationProfileDataRecord();
                recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordEditTokenApplicationProfile);
                if (recordTableTokenApplicationProfile.getName()!=null){
                    Logging.failMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                    softAssert.fail("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                }
                else{
                    Logging.passMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " deleted from table");
                }

                Logging.stepName("Delete Token Application Profile: Database Deletion Check");
                recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                if (recordDbTokenApplicationProfile.getName()!=null){
                    Logging.failMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                    softAssert.fail("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                }
                else{
                    Logging.passMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " deleted from database");
                }

            }
            else{
                Logging.failMessage("Deletion of Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
                softAssert.fail("Deletion of Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
            }


        }catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(TokenApplicationProfileDataRecord record1, TokenApplicationProfileDataRecord record2) {

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

    public void checkTableRecords(TokenApplicationProfileDataRecord record1, TokenApplicationProfileDataRecord record2) {

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

        /// Check the records match
        if(record1.getName().equalsIgnoreCase(record2.getName()) &&
                record1.getParentInstitution().equalsIgnoreCase(record2.getParentInstitution()) &&
                record1.getParentIssuer().equalsIgnoreCase(record2.getParentIssuer()) &&
                record1.getParentTokenProductName().equalsIgnoreCase(record2.getParentTokenProductName()) &&
//                record1.getParentTokenProductGroupName().equalsIgnoreCase(record2.getParentTokenProductGroupName()) &&
                record1.getAppSequenceNumber().equalsIgnoreCase(record2.getAppSequenceNumber())){

            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = NAME=" + record1.getName() +
                    ", INSTITUTION=" + record1.getParentInstitution() +
                    ", ISSUER=" + record1.getParentIssuer() +
//                    ", TOKENPRODUCTGROUP=" + record1.getParentTokenProductGroupName() +
                    ", TOKENPRODUCT=" + record1.getParentTokenProductName() +
                    ", APPSEQUENCENUMBER=" + record1.getAppSequenceNumber() +
                    "\nRecord 2 Data = NAME=" + record2.getName() +
                    ", INSTITUTION=" + record2.getParentInstitution() +
                    ", ISSUER=" + record2.getParentIssuer() +
//                    ", TOKENPRODUCTGROUP=" + record2.getParentTokenProductName() +
                    ", TOKENPRODUCT=" + record2.getParentTokenProductName() +
                    ", APPSEQUENCENUMBER=" + record2.getAppSequenceNumber());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = NAME=" + record1.getName() +
                    ", INSTITUTION=" + record1.getParentInstitution() +
                    ", ISSUER=" + record1.getParentIssuer() +
//                    ", TOKENPRODUCTGROUP=" + record1.getParentTokenProductGroupName() +
                    ", TOKENPRODUCT=" + record1.getParentTokenProductName() +
                    ", APPSEQUENCENUMBER=" + record1.getAppSequenceNumber() +
                    "\nRecord 2 Data = NAME=" + record2.getName() +
                    ", INSTITUTION=" + record2.getParentInstitution() +
                    ", ISSUER=" + record2.getParentIssuer() +
//                    ", TOKENPRODUCTGROUP=" + record2.getParentTokenProductGroupName() +
                    ", TOKENPRODUCT=" + record2.getParentTokenProductName() +
                    ", APPSEQUENCENUMBER=" + record2.getAppSequenceNumber());
        }

    }

    @Then("I validate the token application profile text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Encryption Zones Text Input Fields");

        try{

            tokenApplicationProfilePage.clickAddTokenApplication();

            for (String field : textFields){

                msg = tokenApplicationProfilePage.validateTextInputFields(field);
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

    @Then("I add a new Token Application Profile with validation data")
    public void addSpecialCharacterTokenApplicationProfile() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewTokenApplicationProfile = validationTestData.get(i);

                recordNewTokenApplicationProfile = tokenApplicationProfilePage.enterTokenApplicationProfileData(recordNewTokenApplicationProfile);

                if(recordNewTokenApplicationProfile.getName() != null){

                    recordNewTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordNewTokenApplicationProfile.getName());

                    Logging.stepName("Add Token Application Profile: Table Data Check");
                    recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordNewTokenApplicationProfile);
                    checkTableRecords(recordNewTokenApplicationProfile, recordTableTokenApplicationProfile);

                    Logging.stepName("Add Token Application Profile: Database Data Check");
                    recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordNewTokenApplicationProfile.getName());
                    checkRecords(recordNewTokenApplicationProfile, recordDbTokenApplicationProfile);


                }
                else{
                    Logging.stepName("Add Token Application Profile");
                    softAssert.fail("Token Application Profile not added correctly.\n" + recordNewTokenApplicationProfile.getTestOutput());
                    Logging.failMessage("Token Application Profile not added correctly.");
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

    @Then("I delete a Token Application Profile with validation data")
    public void deleteSpecialCharacterTokenApplicationProfile() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Token Application Profile");
                recordEditTokenApplicationProfile = validationTestData.get(i);
                recordEditTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                tokenApplicationProfilePage.filterByRecord(recordEditTokenApplicationProfile);

                if (adminCommon.clickDeleteByUniqueId(recordEditTokenApplicationProfile.getParentTokenProductId() + "_" + recordEditTokenApplicationProfile.getAppSequenceNumber())) {
//            if(tokenApplicationProfilePage.deleteTokenApplicationProfileByName(recordEditTokenApplicationProfile)){

                    tokenApplicationProfilePage.checkDeleteTokenAppProfile(recordEditTokenApplicationProfile);

                    Logging.stepName("Delete Token Application Profile: Table Deletion Check");
                    recordTableTokenApplicationProfile = new TokenApplicationProfileDataRecord();
                    recordTableTokenApplicationProfile = tokenApplicationProfilePage.getTableRecordByRecord(recordEditTokenApplicationProfile);
                    if (recordTableTokenApplicationProfile.getName() != null) {
                        Logging.failMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                        softAssert.fail("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " deleted from table");
                    }

                    Logging.stepName("Delete Token Application Profile: Database Deletion Check");
                    recordDbTokenApplicationProfile = tokenApplicationProfilePage.getDBDataByName(recordEditTokenApplicationProfile.getName());
                    if (recordDbTokenApplicationProfile.getName() != null) {
                        Logging.failMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                        softAssert.fail("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " deleted from database");
                    }

                } else {
                    Logging.failMessage("Deletion of Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
                    softAssert.fail("Deletion of Token Application Profile Name " + recordEditTokenApplicationProfile.getName() + " failed in the GUI.");
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

    @Then ("Check token application profile scenario")
    public void checkTokenApplicationProfileScenario() {

        Logging.dataMsg(recordEditTokenApplicationProfile.getTestOutput());
        softAssert.assertAll();
    }

    
}
