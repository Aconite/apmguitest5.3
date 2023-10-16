package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.records.InterfaceDataRecord;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.InterfacePage;
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


public class InterfaceTestSteps {

    public WebDriver webDriver = null;
    public SoftAssert softAssert = new SoftAssert();

    // Code for getting data from json
    String testDataPath = "";
    String testType = "";

    public InterfaceDataRecord recordNewInterface = new InterfaceDataRecord();
    public InterfaceDataRecord recordEditInterface = new InterfaceDataRecord();
    public InterfaceDataRecord recordTableInterface = new InterfaceDataRecord();
    public InterfaceDataRecord recordDBInterface = new InterfaceDataRecord();

    InterfacePage interfacePage;
    AdminCommon adminCommon;

    List<String> editFields = new ArrayList<>();
    List<String> textFields = new ArrayList<>();
    HashMap<String, String> reqFields = new HashMap<>();
    List<InterfaceDataRecord> validationTestData = new ArrayList<>();

    public InterfaceTestSteps(AbstractSteps abstractSteps) {

        try

        {
            webDriver = abstractSteps.getDriver();
            interfacePage = new InterfacePage(webDriver);
            adminCommon = new AdminCommon(webDriver);

            testDataPath = abstractSteps.getDataPath();
            testType = abstractSteps.getTestType();
            if(testType.equalsIgnoreCase("Admin")) {
                recordNewInterface = getJSONData();
            }
            if(testType.equalsIgnoreCase("Validation")) {

                validationTestData = getJSONDataToList("validationInterface.json");
            }

            reqFields.put("name","Name is required");
            reqFields.put("type","Type is required");
            reqFields.put("institution","Institution is required");
            reqFields.put("encryptionZone","Encryption zone is required");
            reqFields.put("host","Host is required");
            reqFields.put("port","Port is required");
            reqFields.put("timeout","Timeout is required");

            // TODO: Ability to check password fields in database is required
            // Need to find out about encryption/decryption
            editFields.add("name");
            editFields.add("type");
            editFields.add("institution");
            editFields.add("encryptionZone");
            editFields.add("host");
            editFields.add("port");
            editFields.add("timeout");
            editFields.add("ssl");
            editFields.add("keystorePath");
            editFields.add("keystoreFormat");
            editFields.add("certificateAlias");
            editFields.add("context");

            textFields.add("Name");
            textFields.add("Host");
            textFields.add("Port");
            textFields.add("Timeout");
            textFields.add("Keystore path");
//            textFields.add("Keystore password");
//            textFields.add("Keystore password confirmation");
            textFields.add("Certificate alias");
//            textFields.add("New key password");
//            textFields.add("New key password confirmation");
            textFields.add("Context");
            textFields.add("Username");
//            textFields.add("New user password");
//            textFields.add("New user password confirmation");

        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }

    }

    private InterfaceDataRecord getJSONData(){

        InterfaceDataRecord outRecord = new InterfaceDataRecord();
        String path = testDataPath + "/newInterface.json";

        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            outRecord.setName((String) inst.get("name"));
            outRecord.setType((String) inst.get("type"));
            outRecord.setInstitution((String) inst.get("institution"));
            outRecord.setEncryptionZone((String) inst.get("encryptionZone"));
            outRecord.setHost((String) inst.get("host"));
            outRecord.setPort((String) inst.get("port"));
            outRecord.setTimeout((String) inst.get("timeout"));
            outRecord.setSsl((String) inst.get("ssl"));
            outRecord.setKeystorePath((String) inst.get("keystorePath"));
            outRecord.setCertificateAlias((String) inst.get("certificateAlias"));
            outRecord.setContext((String) inst.get("context"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return outRecord;

    }

    private List<InterfaceDataRecord> getJSONDataToList(String fileName){

        String path = testDataPath + "/" + fileName;

        List<InterfaceDataRecord> testData = new ArrayList<>();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject inst = new JSONObject(contents);
            JSONArray result = inst.getJSONArray("testData");

            for(int i =0; i<result.length();i++){
                InterfaceDataRecord outRecord = new InterfaceDataRecord();
                JSONObject j = result.getJSONObject(i);

                outRecord.setName(j.getString("name"));
                outRecord.setType(j.getString("type"));
                outRecord.setInstitution(j.getString("institution"));
                outRecord.setEncryptionZone(j.getString("encryptionZone"));
                outRecord.setHost(j.getString("host"));
                outRecord.setPort(j.getString("port"));
                outRecord.setTimeout(j.getString("timeout"));
                outRecord.setSsl(j.getString("ssl"));
                outRecord.setKeystorePath(j.getString("keystorePath"));
                outRecord.setCertificateAlias(j.getString("certificateAlias"));
                outRecord.setContext(j.getString("context"));
                outRecord.setUsername(j.getString("username"));

                testData.add(outRecord);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return testData;

    }

    @Then("I click on the Admin Menu Interfaces Interfaces")
    public void clickAdminInterfaces() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnAdminInterfacesMenuItem();

            Logging.stepName("Click On Admin > Interfaces Menu Item");

            if(interfacePage.isPageOpened()){
                Logging.passMessage("Logged in and on Interfaces Page");
            }
            else{
                Logging.failMessage("Not logged in and on Interfaces Page");
                softAssert.fail("Not logged in and on Interfaces Page");
            }
        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I check the interface required fields")
    public void checkRequiredFields(){

        try{

            interfacePage.clickAddButton();

            //Check that all required fields have the correct error message
            for (String field : reqFields.keySet()){

                boolean rc = interfacePage.checkRequiredFieldMessages(field, reqFields.get(field));
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

    @Then("I add a new interface")
    public void addInterface(){

        try{

            recordNewInterface = interfacePage.enterInterfaceData(recordNewInterface);
            adminCommon.hardWait(500);

            if(recordNewInterface.getName()!=null) {

                recordNewInterface = interfacePage.getDbIdByInterfaceName(recordNewInterface);

                Logging.stepName("Add Interface: Table Data Check");
                recordTableInterface = interfacePage.getTableRecordByRecord(recordNewInterface);
                checkTableRecords(recordNewInterface, recordTableInterface);

                Logging.stepName("Add Interface: Database Data Check");
                recordDBInterface = interfacePage.getDBDataById(recordNewInterface.getId());
                checkRecords(recordNewInterface, recordDBInterface);

            }
            else{
                Logging.stepName("Add Interface");
                softAssert.fail("Interface not added correctly.\n" + recordNewInterface.getTestOutput());
                Logging.failMessage("Interface not added correctly.");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I edit an interface")
    public void editInterface(){

        try {

            recordEditInterface = recordNewInterface;

            for(String field :  editFields) {

                switch (field) {


                    /* Change the fields */
                    case ("name"):
                        recordEditInterface.setName("QABank Test Interface");
                        break;

                    case ("type"):
                        recordEditInterface.setType("Return interface");
                        break;

                    case ("institution"):
                        recordEditInterface.setInstitution("QABank");
                        break;

                    case ("encryptionZone"):
                        recordEditInterface.setEncryptionZone("QABank Local");
                        break;

                    case ("host"):
                        recordEditInterface.setHost("ip-172-31-34-84.eu-west-1.qabank.internal");
                        break;

                    case ("port"):
                        recordEditInterface.setPort("3448");
                        break;

                    case ("timeout"):
                        recordEditInterface.setTimeout("600");
                        break;

                    case ("ssl"):
                        recordEditInterface.setSsl("true");
                        break;

                    case ("keystorePath"):
                        recordEditInterface.setKeystorePath("/home/pojo/pinmanager/keys/.keystore");
                        System.out.println("recordEditInterface.setKeystorePath = " + recordEditInterface.getKeystorePath());
                        break;

                    case ("keystoreFormat"):
                        recordEditInterface.setKeystoreFormat("JKS");
                        break;

                    case ("keystorePassword"):
                        recordEditInterface.setKeystorePassword("password");
                        break;

                    case ("certificateAlias"):
                        recordEditInterface.setCertificateAlias("wls");
                        break;

                    case ("keyPassword"):
                        recordEditInterface.setKeyPassword("password");
                        break;

                    case ("context"):
                        recordEditInterface.setContext("PINAdviceConfirmServer/ApmRemoteService");
                        break;

                    case ("username"):
                        recordEditInterface.setUsername("wsuser");
                        break;

                    case ("userPassword"):
                        recordEditInterface.setPassword("password");
                        break;

                }

                recordEditInterface = interfacePage.editInterfaceData(recordEditInterface,field);

                if(recordEditInterface.getTestOutput().contains("UNSUCCESSFUL")){
                    Logging.stepName("Edit Interface");
                    Logging.failMessage("Edit Interface: Data Check: " + field +
                            "\n" + recordEditInterface.getTestOutput());
                    softAssert.fail("Edit Interface: Data Check: " + field +
                            "\n" + recordEditInterface.getTestOutput());

                }
                else {
                    Logging.stepName("Edit Interface: Table Data Check: " + field);
                    recordTableInterface = interfacePage.getTableRecordByRecord(recordEditInterface);
                    checkTableRecords(recordEditInterface, recordTableInterface);

                    Logging.stepName("Edit Interface: Database Data Check: " + field);
                    recordDBInterface = interfacePage.getDBDataById(recordEditInterface.getId());
                    checkRecords(recordEditInterface, recordDBInterface);
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

    @Then("I delete an interface")
    public void deleteInterface(){
        try {

            Logging.stepName("Delete Interface");

            /* Delete the newly created Interface */
            if(adminCommon.clickDeleteByUniqueId(recordEditInterface.getId())) {

                /* Check for dialogs */
                recordEditInterface = interfacePage.checkDeleteInterfaceById(recordEditInterface);

                Logging.stepName("Delete Interface: Table Deletion Check");
                recordTableInterface = interfacePage.getTableRecordByRecord(recordEditInterface);
                if (recordTableInterface.getId() != null) {
                    Logging.failMessage("Interface ID " + recordEditInterface.getId() + " still exists in the table.");
                    softAssert.fail("Interface ID " + recordEditInterface.getId() + " still exists in the table.");
                } else {
                    Logging.passMessage("Interface ID " + recordEditInterface.getId() + " deleted from table");
                }

                Logging.stepName("Delete Interface: Database Deletion Check");
                recordDBInterface = interfacePage.getDBDataById(recordEditInterface.getId());
                if (recordDBInterface.getId() != null) {
                    Logging.failMessage("Interface ID " + recordEditInterface.getId() + " still exists in the database.");
                    softAssert.fail("Interface " + recordEditInterface.getId() + " still exists in the database.");
                } else {
                    Logging.passMessage("Interface ID " + recordEditInterface.getId() + " deleted from database");
                }
            }
            else{
                Logging.failMessage("Deletion of Interface ID " + recordEditInterface.getId() + " failed in the GUI.");
                softAssert.fail("Deletion of Interface ID " + recordEditInterface.getId() + " failed in the GUI.");
            }


        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    public void checkRecords(InterfaceDataRecord record1, InterfaceDataRecord record2) {

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

    public void checkTableRecords(InterfaceDataRecord record1, InterfaceDataRecord record2) {

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
                record1.getType().equalsIgnoreCase(record2.getType()) &&
                record1.getInstitution().equalsIgnoreCase(record2.getInstitution()) &&
                record1.getEncryptionZone().equalsIgnoreCase(record2.getEncryptionZone())){

            Logging.passMessage("Record data matches:" +
                    "\nRecord 1 Data = ID=" + record1.getId() +
                    ", NAME=" + record1.getName() +
                    ", TYPE=" + record1.getType() +
                    ", INSTITUTION=" + record1.getInstitution() +
                    ", ENCRYPTIONZONE=" + record1.getEncryptionZone() +
                    "\nRecord 2 Data = ID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", CODE=" + record2.getType() +
                    ", INSTITUTION=" + record2.getInstitution() +
                    ", ENCRYPTIONZONE=" + record2.getEncryptionZone());
        }
        else{
            softAssert.fail("Record data does not match:");
            Logging.failMessage("Record data does not match:" +
                    "\nRecord 1 Data = ID=" + record1.getId() +
                    ", NAME=" + record1.getName() +
                    ", TYPE=" + record1.getType() +
                    ", INSTITUTION=" + record1.getInstitution() +
                    ", ENCRYPTIONZONE=" + record1.getEncryptionZone() +
                    "\nRecord 2 Data = ID=" + record2.getId() +
                    ", NAME=" + record2.getName() +
                    ", CODE=" + record2.getType() +
                    ", INSTITUTION=" + record2.getInstitution() +
                    ", ENCRYPTIONZONE=" + record2.getEncryptionZone());
        }

    }

    @Then("I validate the interfaces text input fields")
    public void validateTextInputFields(){

        String msg;

        Logging.stepName("Validating Interface Text Input Fields");

        try{

            interfacePage.clickAddButton();

            for (String field : textFields){

                msg = interfacePage.validateTextInputFields(field);
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

    @Then("I add a new Interface with validation data")
    public void addSpecialCharacterInterface() {

        try {

            for(int i=0; i<validationTestData.size();i++) {

                recordNewInterface = interfacePage.enterInterfaceData(validationTestData.get(i));
                adminCommon.hardWait(500);

                if(recordNewInterface.getName()!=null) {

                    recordNewInterface = interfacePage.getDbIdByInterfaceName(recordNewInterface);

                    Logging.stepName("Add Interface: Table Data Check");
                    recordTableInterface = interfacePage.getTableRecordByRecord(recordNewInterface);
                    checkTableRecords(recordNewInterface, recordTableInterface);

                    Logging.stepName("Add Interface: Database Data Check");
                    recordDBInterface = interfacePage.getDBDataById(recordNewInterface.getId());
                    checkRecords(recordNewInterface, recordDBInterface);

                }
                else{
                    Logging.stepName("Add Interface");
                    softAssert.fail("Interface not added correctly.\n" + recordNewInterface.getTestOutput());
                    Logging.failMessage("Interface not added correctly.");
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

    @Then("I delete an Interface with validation data")
    public void deleteSpecialCharacterInterface() {

        try {

            for (int i = 0; i < validationTestData.size(); i++) {

                Logging.stepName("Delete Interface");
                recordEditInterface = validationTestData.get(i);

                /* Delete the newly created Interface */
                if(adminCommon.clickDeleteByUniqueId(recordEditInterface.getId())) {

                    /* Check for dialogs */
                    recordEditInterface = interfacePage.checkDeleteInterfaceById(recordEditInterface);

                    Logging.stepName("Delete Interface: Table Deletion Check");
                    recordTableInterface = interfacePage.getTableRecordByRecord(recordEditInterface);
                    if (recordTableInterface.getId() != null) {
                        Logging.failMessage("Interface ID " + recordEditInterface.getId() + " still exists in the table.");
                        softAssert.fail("Interface ID " + recordEditInterface.getId() + " still exists in the table.");
                    } else {
                        Logging.passMessage("Interface ID " + recordEditInterface.getId() + " deleted from table");
                    }

                    Logging.stepName("Delete Interface: Database Deletion Check");
                    recordDBInterface = interfacePage.getDBDataById(recordEditInterface.getId());
                    if (recordDBInterface.getId() != null) {
                        Logging.failMessage("Interface ID " + recordEditInterface.getId() + " still exists in the database.");
                        softAssert.fail("Interface " + recordEditInterface.getId() + " still exists in the database.");
                    } else {
                        Logging.passMessage("Interface ID " + recordEditInterface.getId() + " deleted from database");
                    }
                }
                else{
                    Logging.failMessage("Deletion of Interface ID " + recordEditInterface.getId() + " failed in the GUI.");
                    softAssert.fail("Deletion of Interface ID " + recordEditInterface.getId() + " failed in the GUI.");
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

    @Then("Check Interface scenario")
    public void checkInterfaceScenario()  {
        Logging.dataMsg(recordEditInterface.getTestOutput());
        softAssert.assertAll();
    }

}
