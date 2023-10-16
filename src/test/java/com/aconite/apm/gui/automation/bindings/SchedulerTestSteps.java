package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.databasegathers.*;
import com.aconite.apm.gui.automation.dataload.*;
import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.enums.ExtractTypes;
import com.aconite.apm.gui.automation.outputfilesplitters.*;
import com.aconite.apm.gui.automation.records.*;
import com.aconite.apm.gui.automation.utilities.ConfigUtils;
import com.aconite.apm.gui.automation.utilities.HousekeeperUtils;
import com.aconite.apm.gui.automation.utilities.SFTPFileRetriever;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.TaskSchedulerPage;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;



public class SchedulerTestSteps
{
    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    public String taskId;
    public String issuer = null;
    public ExtractLogDataExtractRecord extractLogDataExtractRecordBefore;
    public ExtractLogDataExtractRecord extractLogDataExtractRecordCurrent;

    int accessLogHousekeeperCurrentRows1=0;
    int accessLogHousekeeperCurrentRows2=0;


    /* Housekeeper row counts */
    int extractLogHousekeeperCurrentRows1=0;
    int extractLogHousekeeperCurrentRows2=0;
    int housekeeperVppClearExpiredVPPPinIdsCurrentRows1=0;
    int housekeeperVppClearExpiredVPPPinIdsCurrentRows2=0;



    AccessLogDatabaseGatherer accessLogDatabaseGatherer;
    AccessLogFileSplitter accessLogFileSplitter;
    PinDataDatabaseGatherer pinDataDatabaseGatherer;
    PinDataFileSplitter pinDataFileSplitter;
    PinMailerDatabaseGatherer pinMailerDatabaseGatherer;
    PinMailerFileSplitter pinMailerFileSplitter;
    PinSMSDataExtractDatabaseGatherer pinSMSDataExtractDatabaseGatherer;
    PinSMSDataExtractFileSplitter pinSMSDataExtractFileSplitter;
    TokenApplicationDataDatabaseGatherer tokenApplicationDataDatabaseGatherer;
    TokenApplicationDataFileSplitter tokenApplicationDataFileSplitter;
    TokenDataDatabaseGatherer tokenDataDatabaseGatherer;
    TokenDataFileSplitter tokenDataFileSplitter;
    TokenImportDatabaseGatherer tokenImportDatabaseGatherer;
    TokenImportFeedbackFileSplitter tokenImportFeedbackFileSplitter;
    TokenOrderFeedbackDatabaseGatherer tokenOrderFeedbackDatabaseGatherer;
    TokenOrderFeedbackFileSplitter tokenOrderFeedbackFileSplitter;
    TransactionLogDataExtractDatabaseGatherer transactionLogDataExtractDatabaseGatherer;
    TransactionLogDataExtractFileSplitter transactionLogDataExtractFileSplitter;
    TranslatePanIdDataExtractDatabaseGatherer translatePanIdDataExtractDatabaseGatherer;
    TranslatePanIdDataExtractFileSplitter translatePanIdDataExtractFileSplitter;
    VppDataExtractDatabaseGatherer vppDataExtractDatabaseGatherer;
    VppDataExtractFileSplitter vppDataExtractFileSplitter;

    /* Housekeeper */
    AccessLogDatabaseGatherer accessLogHousekeeperExp;
    AccessLogDatabaseGatherer accessLogHousekeeperAct;
    ExtractLogDataGatherer extractLogHousekeeperExp;
    ExtractLogDataGatherer extractLogHousekeeperAct;
    HousekeeperCardHolderDataUpdateDatabaseGatherer housekeeperCardHolderDataUpdateDatabaseGathererExp;
    HousekeeperCardHolderDataUpdateDatabaseGatherer housekeeperCardHolderDataUpdateDatabaseGathererAct;
    HousekeeperVppClearExpiredVPPPinIdsDatabaseGatherer housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp;
    HousekeeperVppClearExpiredVPPPinIdsDatabaseGatherer housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct;


    List<String> housekeeperTasks;

    String outputFilePath;
    String createDataMsg;
    boolean scenarioFail = false;
    String housekeeperCardHolderDataUpdateRows = "";

    String delFileName = "";
    String movFileName = "";

    TaskSchedulerPage taskSchedulerPage;
    AdminCommon adminCommon;

    public SchedulerTestSteps(AbstractSteps abstractSteps)
    {

        this.webDriver = abstractSteps.getDriver();
        taskSchedulerPage = new TaskSchedulerPage(webDriver);
        adminCommon = new AdminCommon(webDriver);

    }

    @Then("I click on the Operation Menu Scheduler")
    public void clickOperationScheduler() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnOperationSchedulerMenuItem();

            Logging.stepName("Click On Operation Scheduler Menu Item");

            if(taskSchedulerPage.isPageOpened()){
                Logging.passMessage("Logged in and on Operation Scheduler Page");
            }
            else{
                Logging.failMessage("Not logged in and on Operation Scheduler Page");
                softAssert.fail("Not logged in and on Operation Scheduler Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    /* ===============================================================================================================*
     * Creating data for the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @Then("I create data for the {word} extract")
    public void create_data(String word) throws Exception{

        Logging.stepName("Data Creation for " + word);

        switch (word){

            case "pindataextract":
                createDataMsg = "";
                createDataMsg = DataLoadPinDataExtract.runDataLoadPinDataExtract();
                break;

            case "pinmailer":
                createDataMsg = "";
                createDataMsg = DataLoadPinMailer.runDataLoadPinMailer();
                break;

            case "pinsms":
                createDataMsg = "";
                createDataMsg = DataLoadPinSmsDataExtract.runDataLoadPinSmsDataExtract();
                break;

            case "pinsmsdataextract":
                createDataMsg = "";
                break;

            case "tokenapplicationdataextract":
            case "tokenorderfeedback":
            case "tokendataextract":
                createDataMsg = "";
                createDataMsg = DataLoadTokenApplicationDataExtract.runDataLoadTokenApplicationDataExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadPinMailer.runDataLoadPinMailer(); //uses a different token product/pvv key index
                Logging.dataMsg(createDataMsg);
                break;

            case "tokenimportfeedback":
                createDataMsg = "";
                createDataMsg = DataLoadTokenImportFeedback.runDataLoadTokenImportFeedback(); // should be iterated
                break;

            case "transactionlogdataextract":
                createDataMsg = "";
                createDataMsg = DataLoadPinDataExtract.runDataLoadPinDataExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadPinMailer.runDataLoadPinMailer();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadPinSmsDataExtract.runDataLoadPinSmsDataExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadTokenApplicationDataExtract.runDataLoadTokenApplicationDataExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadTokenImportFeedback.runDataLoadTokenImportFeedback(); //should be iterated
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadTranslatePanIdExtract.runDataLoadTranslatePanIdExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadVppDataExtract.runDataLoadVppDataExtract();
                Logging.dataMsg(createDataMsg);

                createDataMsg = "";
                createDataMsg = DataLoadTransactionLogDataExtract.runDataLoadTransactionLogDataExtract();
                break;

            case "translatepaniddataextract":
                createDataMsg = "";
                createDataMsg = DataLoadTranslatePanIdExtract.runDataLoadTranslatePanIdExtract();
                break;

            case "vppdataextract":
                createDataMsg = "";
                createDataMsg = DataLoadVppDataExtract.runDataLoadVppDataExtract();
                break;

        }

        Logging.dataMsg(createDataMsg);

    }

    /* ===============================================================================================================*
     * Adding the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @When("I add a new daily {word} task")
    public void i_add_a_new_daily_task(String word) {

        try {

            Logging.stepName("Adding Task - " + word);

            switch (word) {

                case "accesslogdataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.ACCESSLOGDATAEXTRACT);
                    issuer = "";
                    break;

                case "housekeeper":
                    break;

                case "tokenapplicationdataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENAPPLICATIONDATAEXTRACT);
                    issuer = "";
                    break;

                case "tokendataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENDATAEXTRACT);
                    issuer = "";
                    break;

                case "transactionlogdataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TRANSACTIONLOGDATAEXTRACT);
                    issuer = "";
                    break;

                case "translatepaniddataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TRANSLATEPANIDDATAEXTRACT);
                    issuer = "";
                    break;

                case "vppdataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.VPPDATAEXTRACT);
                    issuer = "";
                    break;

                case "pindataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.PINDATAEXTRACT);
                    issuer = "";
                    break;

                case "pinmailer":
                    pinMailerDatabaseGatherer = new PinMailerDatabaseGatherer();
                    issuer = "ABCBank";
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.PINMAILER);
                    pinMailerDatabaseGatherer.gatherDataByDate(extractLogDataExtractRecordBefore); //needs to be run before the task is run
                    break;

                case "pinsms":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.PINSMS);
                    issuer = "ABCBank";
                    break;

                case "tokenorderfeedback":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENORDERFEEDBACK);
                    issuer = "ABCBank";
                    break;

                case "pinsmsdataextract":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.PINSMSDATAEXTRACT);
                    issuer = "";
                    break;

                case "tokenimportfeedback":
                    extractLogDataExtractRecordBefore = ExtractLogDataGatherer.getLastSuccessfulRun(ExtractTypes.TOKENIMPORTFEEDBACK);
                    issuer = "ABCBank";
                    break;

                default:
                    softAssert.fail("No valid task name found in Add Task.");

            }

            /* Add the task */

            taskId = taskSchedulerPage.addTask(word, issuer);
//            System.out.println("Add the task " + taskId);
            if (taskId == null) {
                softAssert.fail("Add Task failed.");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    /* ===============================================================================================================*
     * Editing the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @When("I edit a new daily {word} task")
    public void i_edit_a_Task(String word)
    {

        Logging.stepName("Editing Task - " + word);

        try {

            if(taskSchedulerPage.editTask(word, taskId, issuer)==null){
                softAssert.fail("Edit Task failed.");
            }
        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    /* ===============================================================================================================*
     * Running the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @When("I run a {word} task")
    public void i_run_a_Task(String word)
    {
        String[] res;
        Logging.stepName("Running Task - " + word);

        try{

            String taskLastRun = taskSchedulerPage.runTask(word, taskId);
            res = taskLastRun.split(">");
            if(res[0].equalsIgnoreCase("OK")) {
                Logging.passMessage(word + " ran successfully at " + res[1]);
            }
            else{
                Logging.failMessage(word + " failed: " + res[1]);
                softAssert.fail(word + " failed: " + res[1]);
            }

            switch (word) {

                case "accesslogdataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.ACCESSLOGDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "housekeeper":
                    break;

                case "tokenapplicationdataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TOKENAPPLICATIONDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "tokendataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TOKENDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "transactionlogdataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TRANSACTIONLOGDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "translatepaniddataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TRANSLATEPANIDDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "vppdataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.VPPDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "pindataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.PINDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "pinmailer":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.PINMAILER,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "pinsms":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.PINSMS,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "tokenorderfeedback":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TOKENORDERFEEDBACK,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "pinsmsdataextract":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.PINSMSDATAEXTRACT,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                case "tokenimportfeedback":
                    extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.getCurrentRun(
                            ExtractTypes.TOKENIMPORTFEEDBACK,
                            extractLogDataExtractRecordBefore.getId());
                    break;

                default:
                    softAssert.fail("No valid task name found in Run Task.");

            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }



    }

    /* ===============================================================================================================*
     * Checking the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @Then("the {word} task runs successfully and the output is correct")
    public void the_Task_Runs_Successfully_And_The_OutputIsCorrect(String word)
    {
        Logging.stepName("Checking Task - " + word);

        String output;

        /*-------------------------------
            Synchronisation for task
         -------------------------------*/
        int max_timeout = 120;
        long start = System.currentTimeMillis();
        long end = start + max_timeout * 1000;

        if(!word.equalsIgnoreCase("housekeeper")) {
            while (System.currentTimeMillis() < end) {
                extractLogDataExtractRecordCurrent = ExtractLogDataGatherer.checkCompleted(extractLogDataExtractRecordCurrent);
                if (!extractLogDataExtractRecordCurrent.getStatus().equals("C")) {
                    adminCommon.hardWait(1000);
                    System.out.println("Waiting for " + extractLogDataExtractRecordCurrent.getId() + "...");
                }
                if (extractLogDataExtractRecordCurrent.getStatus().equals("C")) {
                    System.out.println(extractLogDataExtractRecordCurrent.getId() + " completed...");
                    break;
                }
            }
        }

        /*------------------------------*/

        try{

            switch (word){

                case "accesslogdataextract":

                    accessLogDatabaseGatherer = new AccessLogDatabaseGatherer();
                    accessLogFileSplitter = new AccessLogFileSplitter();
                    accessLogFileSplitter.splitFileInRecords();
                    accessLogDatabaseGatherer.gatherData();

                    Logging.dataMsg("Database Records: " + accessLogDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(accessLogDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + accessLogFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + accessLogFileSplitter.getRecordCount());
                    Logging.dataMsg(accessLogFileSplitter.records.toString());

                    if(accessLogDatabaseGatherer.getRecordCount() != accessLogFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                accessLogDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + accessLogFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(accessLogDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Access Log Data Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Access Log Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (AccessLogDataExtractRecord temp : accessLogDatabaseGatherer.records) {
                        if(accessLogFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Access Log Data Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Access Log Data Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Access Log Data Extract.");
                        }
                    }

                    break;

                case "housekeeper":

                    String hTaskName;
                    String hTaskParam = "";

                    housekeeperTasks = HousekeeperUtils.getHousekeeperTaskList();

                    for (Object hTask : housekeeperTasks) {

                        String hTaskString = hTask.toString();

                        if (hTaskString.contains("(")) {
                            String[] hTaskDetail = hTaskString.split("\\(");
                            hTaskName = hTaskDetail[0];
                            hTaskParam = hTaskDetail[1].replace(")", "");
                        } else {
                            hTaskName = hTaskString;
                        }

                        switch (hTaskName) {

                            case "cardHolderDataUpdate":

                                Logging.stepName("Checking Results for Housekeeper Scheduled Task - cardHolderDataUpdate");

                                for (HousekeeperCardHolderDataUpdateRecord expTemp : housekeeperCardHolderDataUpdateDatabaseGathererExp.records) {

                                    String expId = expTemp.getId();

                                    for (HousekeeperCardHolderDataUpdateRecord actTemp : housekeeperCardHolderDataUpdateDatabaseGathererAct.records) {

                                        String actId = actTemp.getId();

                                        if(expId.equals(actId)){

                                            Logging.dataMsg("cardHolderDataUpdate: Checking " + expId);
                                            output = "";
                                            output = output + "Record: [ID=" + expId;

                                            if(expTemp.getTrxTypeId().equals(actTemp.getTrxTypeId())){
                                                output = output + ", TRXTYPEID=" + expTemp.getTrxTypeId();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - TRXTYPEID\n" +
                                                        expTemp + "\nDatabase Record After - TRXTYPEID\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            /* Special Case - Actual transaction status should be 5 - Purged */
                                            if(actTemp.getTrxStatus().equals("5")){
                                                output = output + ", TRXSTATUS=" + actTemp.getTrxStatus();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - TRXSTATUS\n" +
                                                        expTemp + "\nDatabase Record After - TRXSTATUS\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getSystemDateTime().equals(actTemp.getSystemDateTime())){
                                                output = output + ", SYSTEMDATETIME=" + expTemp.getSystemDateTime();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - SYSTEMDATETIME\n" +
                                                        expTemp + "\nDatabase Record After - SYSTEMDATETIME\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqDateTime().equals(actTemp.getReqDateTime())){
                                                output = output + ", REQDATETIME=" + expTemp.getReqDateTime();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQDATETIME\n" +
                                                        expTemp + "\nDatabase Record After - REQDATETIME\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqOrigId().equals(actTemp.getReqOrigId())){
                                                output = output + ", REQORIGID=" + expTemp.getReqOrigId();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQORIGID\n" +
                                                        expTemp + "\nDatabase Record After - REQORIGID\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqIssuerPinId().equals(actTemp.getReqIssuerPinId())){
                                                output = output + ", REQISSUERPINID=" + expTemp.getReqIssuerPinId();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQISSUERPINID\n" +
                                                        expTemp + "\nDatabase Record After - REQISSUERPINID\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPinExpiryDate().equals(actTemp.getReqPinExpiryDate())){
                                                output = output + ", REQPINEXPIRYDATE=" + expTemp.getReqPinExpiryDate();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPINEXPIRYDATE\n" +
                                                        expTemp + "\nDatabase Record After - REQPINEXPIRYDATE\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPinId().equals(actTemp.getReqPinId())){
                                                output = output + ", REQPINID=" + expTemp.getReqPinId();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPINID\n" +
                                                        expTemp + "\nDatabase Record After - REQPINID\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPanId().equals(actTemp.getReqPanId())){
                                                output = output + ", REQPANID=" + expTemp.getReqPanId();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPANID\n" +
                                                        expTemp + "\nDatabase Record After - REQPANID\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqIssuerPanAlias().equals(actTemp.getReqIssuerPanAlias())){
                                                output = output + ", REQISSUERPANALIAS=" + expTemp.getReqIssuerPanAlias();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQISSUERPANALIAS\n" +
                                                        expTemp + "\nDatabase Record After - REQISSUERPANALIAS\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPanSeqNumber().equals(actTemp.getReqPanSeqNumber())){
                                                output = output + ", REQPANSEQNUMBER=" + expTemp.getReqPanSeqNumber();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPANSEQNUMBER\n" +
                                                        expTemp + "\nDatabase Record After - REQPANSEQNUMBER\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPanExpiryDate().equals(actTemp.getReqPanExpiryDate())){
                                                output = output + ", REQPANEXPIRYDATE=" + expTemp.getReqPanExpiryDate();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPANEXPIRYDATE\n" +
                                                        expTemp + "\nDatabase Record After - REQPANEXPIRYDATE\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(expTemp.getReqPinDeliveryMethod().equals(actTemp.getReqPinDeliveryMethod())){
                                                output = output + ", REQPINDELIVERYMETHOD=" + expTemp.getReqPinDeliveryMethod();
                                            }
                                            else{
                                                Logging.failMessage("RECORD MISMATCH\nDatabase Record Before - REQPINDELIVERYMETHOD\n" +
                                                        expTemp + "\nDatabase Record After - REQPINDELIVERYMETHOD\n" +
                                                        actTemp);
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqMobilePhone().equals(" ")){
                                                output = output + ", REQMOBILEPHONE=" + actTemp.getReqMobilePhone();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQMOBILEPHONE " + actTemp.getReqMobilePhone() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHTitle().equals(" ")){
                                                output = output + ", REQTHTITLE=" + actTemp.getReqTHTitle();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHTITLE " + actTemp.getReqTHTitle()+ " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHFirstName().equals(" ")){
                                                output = output + ", REQTHFIRSTNAME=" + actTemp.getReqTHFirstName();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHFIRSTNAME " + actTemp.getReqTHFirstName() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHMiddleName().equals(" ")){
                                                output = output + ", REQTHMIDDLENAME=" + actTemp.getReqTHMiddleName();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHMIDDLENAME " + actTemp.getReqTHMiddleName() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHLastName().equals(" ")){
                                                output = output + ", REQTHLASTNAME=" + actTemp.getReqTHLastName();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHLASTNAME " + actTemp.getReqTHLastName() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress1().equals(" ")){
                                                output = output + ", REQTHADDRESS1=" + actTemp.getReqTHAddress1();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS1 " + actTemp.getReqTHAddress1() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress2().equals(" ")){
                                                output = output + ", REQTHADDRESS2=" + actTemp.getReqTHAddress2();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS2 " + actTemp.getReqTHAddress2() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress3().equals(" ")){
                                                output = output + ", REQTHADDRESS3=" + actTemp.getReqTHAddress3();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS3 " + actTemp.getReqTHAddress3() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress4().equals(" ")){
                                                output = output + ", REQTHADDRESS4=" + actTemp.getReqTHAddress4();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS4 " + actTemp.getReqTHAddress4() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress5().equals(" ")){
                                                output = output + ", REQTHADDRESS5=" + actTemp.getReqTHAddress5();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS5 " + actTemp.getReqTHAddress5() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHAddress6().equals(" ")){
                                                output = output + ", REQTHADDRESS6=" + actTemp.getReqTHAddress6();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHADDRESS6 " + actTemp.getReqTHAddress6() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHTown().equals(" ")){
                                                output = output + ", REQTHTOWN=" + actTemp.getReqTHTown();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHTOWN " + actTemp.getReqTHTown() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHPostCode().equals(" ")){
                                                output = output + ", REQTHPOSTCODE=" + actTemp.getReqTHPostCode();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHPOSTCODE " + actTemp.getReqTHPostCode() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqTHCountryCode().equals(" ")){
                                                output = output + ", REQTHCOUNTRYCODE=" + actTemp.getReqTHCountryCode();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQTHCOUNTRYCODE " + actTemp.getReqTHCountryCode() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqCompanyName().equals(" ")){
                                                output = output + ", REQCOMPANYNAME=" + actTemp.getReqCompanyName();
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQCOMPANYNAME " + actTemp.getReqCompanyName() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if(actTemp.getReqCompanyContact().equals(" ")){
                                                output = output + ", REQCOMPANYCONTACT=" + actTemp.getReqCompanyContact() + "]";
                                            }
                                            else{
                                                Logging.failMessage(actTemp +
                                                        "\nREQCOMPANYCONTACT " + actTemp.getReqCompanyContact() + " should be null.");
                                                scenarioFail = true;
                                                break;
                                            }

                                            if (scenarioFail){
                                                softAssert.fail("Record in database:\n" + output +
                                                        "\n not  updated correctly by cardHolderDataUpdate housekeeper task.");
                                            }
                                            else{
                                                Logging.passMessage("Record in database:\n" + output +
                                                        "\n correctly updated by cardHolderDataUpdate housekeeper task.");

                                            }

                                        }
                                    }
                                }
                                break;

                            case "clearExpiredVPPPinIds":

                                Logging.stepName("Checking Results for Housekeeper Scheduled Task - clearExpiredVPPPinIds");

                                Logging.dataMsg("clearExpiredVPPPinIds: Data expected to be cleared");
                                Logging.dataMsg(housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp.records.toString());
                                Logging.dataMsg("clearExpiredVPPPinIds: Data remaining after housekeeper task");
    //                            housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct.gatherDataForHousekeeper();
                                Logging.dataMsg(housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct.records.toString());

                                if(housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct.records.isEmpty()) {
                                    Logging.passMessage("All expired vpp pin ids have been deleted successfully");
                                }
                                else{
                                    Logging.failMessage("Not all expired vpp pin ids have been deleted successfully");
                                    softAssert.fail("Not all expired vpp pin ids have been deleted successfully");
                                }

                                housekeeperVppClearExpiredVPPPinIdsCurrentRows2 = housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp.getRowCountForHousekeeper();
                                if(housekeeperVppClearExpiredVPPPinIdsCurrentRows2 == 0){
                                    Logging.warnMessage("No rows remaining in C_D_VPP_PIN_ID Table\n" +
                                            "There were " + housekeeperVppClearExpiredVPPPinIdsCurrentRows1 + " at the beginning of the run.");
                                }

                                break;

                            case "expiredKeysNotification":

                                outputFilePath = ConfigUtils.cfgProperty("local_folder");

                                Logging.stepName("Checking Results for Housekeeper Scheduled Task - expiredKeysNotification");
                                String resfile = SFTPFileRetriever.downloadAndDeleteFile(ConfigUtils.cfgProperty("expiredkeys_filepath"), outputFilePath);

                                //Check contents of resfile
                                String outtext = null;
                                for(Scanner sc = new Scanner(new File(outputFilePath + "//" + resfile)); sc.hasNext(); ) {
                                    String line = sc.nextLine();
                                    outtext = outtext + line;
                                }

                                if(outtext != null && outtext.contains("TestBank Expired ZEK")) {
                                    Logging.passMessage("Checking " + outputFilePath + "\\" + resfile + " expiredKeysNotification found for: TestBank Expired ZEK");
                                }
                                else{
                                    Logging.failMessage("Checking " + outputFilePath + "\\" + resfile + " expiredKeysNotification not found for: TestBank Expired ZEK");
                                    softAssert.fail("Checking " + outputFilePath + "\\" + resfile + " expiredKeysNotification not found for: TestBank Expired ZEK");
                                }

                                break;

                            case "extractLogsCleanUp":

                                Logging.stepName("Checking Results for Housekeeper Scheduled Task - extractLogsCleanUp");

                                Logging.dataMsg("extractLogsCleanUp: Data expected to be cleared");
                                Logging.dataMsg(extractLogHousekeeperExp.records.toString());
                                Logging.dataMsg("extractLogsCleanUp: Data remaining after housekeeper task");
                                Logging.dataMsg(extractLogHousekeeperAct.records.toString());

                                if(extractLogHousekeeperAct.records.isEmpty()) {
                                    Logging.passMessage("All extract logs older than " + hTaskParam + " days have been deleted successfully");
                                                   }
                                else{
                                    Logging.failMessage("Not all extract logs older than " + hTaskParam + " days have been deleted successfully");
                                    softAssert.fail("Not all extract logs older than " + hTaskParam + " days have been deleted successfully");
                                }

                                extractLogHousekeeperCurrentRows2 = extractLogHousekeeperExp.getRowCountForHousekeeper();
                                Logging.dataMsg("Number of Rows in Extract Log = " + extractLogHousekeeperCurrentRows2);
                                if(extractLogHousekeeperCurrentRows2 == 0){
                                    Logging.warnMessage("No rows remaining in Extract Log\n" +
                                    "There were " + extractLogHousekeeperCurrentRows1 + " at the beginning of the run.");
                                }

                                break;

                            case "filesCleanUp":

                                String remoteFilePath = ConfigUtils.cfgProperty("extractlog_filepath");
                                String remoteTmpFilePath = ConfigUtils.cfgProperty("tmp_filepath");

                                boolean delFile = SFTPFileRetriever.sftpFileExists(remoteFilePath, delFileName);
                                Logging.stepName("Checking Results for deleted File - " + delFile);
                                if (!delFile){
                                    Logging.passMessage("File " + delFileName + " has been been deleted successfully");
                                }
                                else{
                                    Logging.failMessage("File " + delFileName + " has been not been deleted successfully");
                                    softAssert.fail("File " + delFileName + " has been not been deleted successfully");
                                }

                                Logging.dataMsg("Checking Results for moved File - " + remoteTmpFilePath + "/" + movFileName);
                                boolean moveFile = SFTPFileRetriever.sftpFileExists(remoteTmpFilePath, movFileName);
                                if(moveFile){
                                    Logging.passMessage("File " + movFileName + " has been been moved successfully");
                                }
                                else{
                                    Logging.failMessage("File " + movFileName + " has been not been moved successfully");
                                    softAssert.fail("File " + movFileName + " has been not been moved successfully");

                                }

                                break;

                            case "userAccessLogsCleanUp":

                                Logging.stepName("Checking Results for Housekeeper Scheduled Task - userAccessLogsCleanUp");

                                Logging.dataMsg("userAccessLogsCleanUp: Data expected to be cleared");
                                Logging.dataMsg(accessLogHousekeeperExp.records.toString());
                                Logging.dataMsg("userAccessLogsCleanUp: Data remaining after housekeeper task");
                                Logging.dataMsg(accessLogHousekeeperAct.records.toString());

                                if(accessLogHousekeeperAct.records.isEmpty()) {
                                    Logging.passMessage("All access log entries meeting criteria have been deleted successfully");
                                }
                                else{
                                    Logging.failMessage("Not all access log entries meeting criteria have been deleted successfully");
                                    softAssert.fail("Not all access log entries meeting criteria have been deleted successfully");

                                }

                                accessLogHousekeeperCurrentRows2 = accessLogHousekeeperExp.getRowCountForHousekeeper();
                                  if(accessLogHousekeeperCurrentRows2 == 0){
                                      Logging.warnMessage("No rows remaining in ACL_D_ACCESS_LOG table\n" +
                                            "There were " + accessLogHousekeeperCurrentRows1 + " at the beginning of the run.");
                                }

                                break;

                        }
                    }

                    break;

                case "pindataextract":

                    pinDataFileSplitter = new PinDataFileSplitter();
                    pinDataDatabaseGatherer = new PinDataDatabaseGatherer();
                    pinDataDatabaseGatherer.gatherDataByDate(extractLogDataExtractRecordBefore);
                    pinDataFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + pinDataDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(pinDataDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + pinDataFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + pinDataFileSplitter.getRecordCount());
                    Logging.dataMsg(pinDataFileSplitter.records.toString());

                    if(pinDataDatabaseGatherer.getRecordCount() != pinDataFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                pinDataDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + pinDataFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(pinDataDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for PIN Data Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for PIN Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (PinDataRecord temp : pinDataDatabaseGatherer.records) {
                        if(pinDataFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in PIN Data Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in PIN Data Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in PIN Data Extract.");

                        }
                    }
                    break;

                case "pinmailer":

                    pinMailerFileSplitter = new PinMailerFileSplitter();

    //                pinMailerDatabaseGatherer.gatherData();
                    adminCommon.hardWait(30000);
                    pinMailerFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + pinMailerDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(pinMailerDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + pinMailerFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + pinMailerFileSplitter.getRecordCount());
                    Logging.dataMsg(pinMailerFileSplitter.records.toString());

                    if(pinMailerDatabaseGatherer.getRecordCount() != pinMailerFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                pinMailerDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + pinMailerFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(pinMailerDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Pin Mailer." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Pin Mailer." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (PinMailerRecord temp : pinMailerDatabaseGatherer.records) {
                        if(pinMailerFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Pin Mailer.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Pin Mailer.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Pin Mailer.");
                        }
                    }
                    break;

                case "pinsmsdataextract":

                    pinSMSDataExtractFileSplitter = new PinSMSDataExtractFileSplitter();
                    pinSMSDataExtractDatabaseGatherer = new PinSMSDataExtractDatabaseGatherer();
                    pinSMSDataExtractDatabaseGatherer.gatherData(extractLogDataExtractRecordBefore);
                    pinSMSDataExtractFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + pinSMSDataExtractDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(pinSMSDataExtractDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + pinSMSDataExtractFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + pinSMSDataExtractFileSplitter.getRecordCount());
                    Logging.dataMsg(pinSMSDataExtractFileSplitter.records.toString());

                    if(pinSMSDataExtractDatabaseGatherer.getRecordCount() != pinSMSDataExtractFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                pinSMSDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + pinSMSDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(pinSMSDataExtractDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for PIN SMS Data Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for PIN SMS Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }


                    for (PinSMSDataExtractRecord fileTemp : pinSMSDataExtractFileSplitter.records) {

                        String fileId = fileTemp.getId();

                        for (PinSMSDataExtractRecord dbTemp : pinSMSDataExtractDatabaseGatherer.records) {

                            output = "";
                            String errorMessage = "";

                            String dbId = dbTemp.getId();
                            String dbRspSmsStatus = dbTemp.getRspSmsStatus();

                            //Records created by TOWPD have RSPSMSSTATUS = 1
                            //These match between File and Database
                            if (fileId.equals(dbId) && dbRspSmsStatus.equals("1")) {
                                if (dbTemp.equals(fileTemp)) {
                                    Logging.passMessage("Record in database:\n" + dbTemp + "\n found in PIN SMS Data Extract.");
                                    continue;
                                }
                            }

                            //If the record id matches, check all other fields.
                            //SPECIAL CASE - RSPSMSSTATUS
                            // This needs to change as
                            // RSPSMSSTATUS = 2 in the log
                            // RSPSMSSTATUS = 4 in the database
                            if (fileId.equals(dbId) && dbRspSmsStatus.equals("4")) {

                                 output = output + "Record: [ID=" + dbId;

                                if(dbTemp.getInterfaceId().equals(fileTemp.getInterfaceId())){
                                    output = output + ", INTERFACEID=" + dbTemp.getInterfaceId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nINTERFACEID:" +
                                            "\nDatabase = " + dbTemp.getInterfaceId() +
                                            "\nFile = " + fileTemp.getInterfaceId();
                                }

                                if(dbTemp.getTrxTypeId().equals(fileTemp.getTrxTypeId())){
                                    output = output + ", TRXTYPEID=" + dbTemp.getTrxTypeId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nTRXTYPEID:" +
                                            "\nDatabase = " + dbTemp.getTrxTypeId() +
                                            "\nFile = " + fileTemp.getTrxTypeId();
                                }

                                if(dbTemp.getTrxStatus().equals(fileTemp.getTrxStatus())){
                                    output = output + ", TRXSTATUS=" + dbTemp.getTrxStatus();
                                }
                                else{
                                    errorMessage = errorMessage + "\nTRXSTATUS:" +
                                            "\nDatabase = " + dbTemp.getTrxStatus() +
                                            "\nFile = " + fileTemp.getTrxStatus();
                                }

                                if(dbTemp.getSystemDateTime().equals(fileTemp.getSystemDateTime())){
                                    output = output + ", SYSTEMDATETIME=" + dbTemp.getSystemDateTime();
                                }
                                else{
                                    errorMessage = errorMessage + "\nSYSTEMDATETIME:" +
                                            "\nDatabase = " + dbTemp.getSystemDateTime() +
                                            "\nFile = " + fileTemp.getSystemDateTime();
                                }

                                if(dbTemp.getReqDateTime().equals(fileTemp.getReqDateTime())){
                                    output = output + ", REQDATETIME=" + dbTemp.getReqDateTime();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQDATETIME:" +
                                            "\nDatabase = " + dbTemp.getReqDateTime() +
                                            "\nFile = " + fileTemp.getReqDateTime();
                                }

                                if(dbTemp.getReqOrigId().equals(fileTemp.getReqOrigId())){
                                    output = output + ", REQORIGID=" + dbTemp.getReqOrigId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQORIGID:" +
                                            "\nDatabase = " + dbTemp.getReqOrigId() +
                                            "\nFile = " + fileTemp.getReqOrigId();
                                }

                                if(dbTemp.getReqRn().equals(fileTemp.getReqRn())){
                                    output = output + ", REQRN=" + dbTemp.getReqRn();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQRN:" +
                                            "\nDatabase = " + dbTemp.getReqRn() +
                                            "\nFile = " + fileTemp.getReqRn();
                                }

                                if(dbTemp.getReqIssuerPinId().equals(fileTemp.getReqIssuerPinId())){
                                    output = output + ", REQISSUERPINID=" + dbTemp.getReqIssuerPinId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQISSUERPINID:" +
                                            "\nDatabase = " + dbTemp.getReqIssuerPinId() +
                                            "\nFile = " + fileTemp.getReqIssuerPinId();
                                }

                                if(dbTemp.getReqIssuerPukId().equals(fileTemp.getReqIssuerPukId())){
                                    output = output + ", REQISSUERPUKID=" + dbTemp.getReqIssuerPukId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQISSUERPUKID:" +
                                            "\nDatabase = " + dbTemp.getReqIssuerPukId() +
                                            "\nFile = " + fileTemp.getReqIssuerPukId();
                                }

                                if(dbTemp.getReqPukId().equals(fileTemp.getReqPukId())){
                                    output = output + ", REQPUKID=" + dbTemp.getReqPukId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPUKID:" +
                                            "\nDatabase = " + dbTemp.getReqPukId() +
                                            "\nFile = " + fileTemp.getReqPukId();
                                }

                                if(dbTemp.getReqPinExpiryDate().equals(fileTemp.getReqPinExpiryDate())){
                                    output = output + ", REQPINEXPIRYDATE=" + dbTemp.getReqPinExpiryDate();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPINEXPIRYDATE:" +
                                            "\nDatabase = " + dbTemp.getReqPinExpiryDate() +
                                            "\nFile = " + fileTemp.getReqPinExpiryDate();
                                }

                                if(dbTemp.getReqPinId().equals(fileTemp.getReqPinId())){
                                    output = output + ", REQPINID=" + dbTemp.getReqPinId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPINID:" +
                                            "\nDatabase = " + dbTemp.getReqPinId() +
                                            "\nFile = " + fileTemp.getReqPinId();
                                }

                                if(dbTemp.getReqPanId().equals(fileTemp.getReqPanId())){
                                    output = output + ", REQPANID=" + dbTemp.getReqPanId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPANID:" +
                                            "\nDatabase = " + dbTemp.getReqPanId() +
                                            "\nFile = " + fileTemp.getReqPanId();
                                }

                                if(dbTemp.getReqIssuerPanAlias().equals(fileTemp.getReqIssuerPanAlias())){
                                    output = output + ", REQISSUERPANALIAS=" + dbTemp.getReqIssuerPanAlias();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQISSUERPANALIAS:" +
                                            "\nDatabase = " + dbTemp.getReqIssuerPanAlias() +
                                            "\nFile = " + fileTemp.getReqIssuerPanAlias();
                                }

                                if(dbTemp.getReqPanSeqNumber().equals(fileTemp.getReqPanSeqNumber())){
                                    output = output + ", REQPANSEQNUMBER=" + dbTemp.getReqPanSeqNumber();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPANSEQNUMBER:" +
                                            "\nDatabase = " + dbTemp.getReqPanSeqNumber() +
                                            "\nFile = " + fileTemp.getReqPanSeqNumber();
                                }

                                if(dbTemp.getReqPanExpiryDate().equals(fileTemp.getReqPanExpiryDate())){
                                    output = output + ", REQPANEXPIRYDATE=" + dbTemp.getReqPanExpiryDate();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPANEXPIRYDATE:" +
                                            "\nDatabase = " + dbTemp.getReqPanExpiryDate() +
                                            "\nFile = " + fileTemp.getReqPanExpiryDate();
                                }

                                if(dbTemp.getReqTokenAppStatusId().equals(fileTemp.getReqTokenAppStatusId())){
                                    output = output + ", REQTOKENAPPSTATUSID=" + dbTemp.getReqTokenAppStatusId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQTOKENAPPSTATUSID:" +
                                            "\nDatabase = " + dbTemp.getReqTokenAppStatusId() +
                                            "\nFile = " + fileTemp.getReqTokenAppStatusId();
                                }

                                if(dbTemp.getReqTokenAppSeqNum().equals(fileTemp.getReqTokenAppSeqNum())){
                                    output = output + ", REQTOKENAPPSEQNUM=" + dbTemp.getReqTokenAppSeqNum();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQTOKENAPPSEQNUM:" +
                                            "\nDatabase = " + dbTemp.getReqTokenAppSeqNum() +
                                            "\nFile = " + fileTemp.getReqTokenAppSeqNum();
                                }

                                if(dbTemp.getReqIssuerTokenProductCode().equals(fileTemp.getReqIssuerTokenProductCode())){
                                    output = output + ", REQISSUERTOKENPRODUCTCODE=" + dbTemp.getReqIssuerTokenProductCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQISSUERTOKENPRODUCTCODE:" +
                                            "\nDatabase = " + dbTemp.getReqIssuerTokenProductCode() +
                                            "\nFile = " + fileTemp.getReqIssuerTokenProductCode();
                                }

                                if(dbTemp.getReqPinDeliveryMethod().equals(fileTemp.getReqPinDeliveryMethod())){
                                    output = output + ", REQPINDELIVERYMETHOD=" + dbTemp.getReqPinDeliveryMethod();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPINDELIVERYMETHOD:" +
                                            "\nDatabase = " + dbTemp.getReqPinDeliveryMethod() +
                                            "\nFile = " + fileTemp.getReqPinDeliveryMethod();
                                }

                                if(dbTemp.getReqAdviceAllTokenAppFlag().equals(fileTemp.getReqAdviceAllTokenAppFlag())){
                                    output = output + ", REQADVICEALLTOKENAPPFLAG=" + dbTemp.getReqAdviceAllTokenAppFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQADVICEALLTOKENAPPFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqAdviceAllTokenAppFlag() +
                                            "\nFile = " + fileTemp.getReqAdviceAllTokenAppFlag();
                                }

                                if(dbTemp.getReqAdvicePinPukFlag().equals(fileTemp.getReqAdvicePinPukFlag())){
                                    output = output + ", REQADVICEPINPUKFLAG=" + dbTemp.getReqAdvicePinPukFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQADVICEPINPUKFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqAdvicePinPukFlag() +
                                            "\nFile = " + fileTemp.getReqAdvicePinPukFlag();
                                }

                                if(dbTemp.getReqLanguageCode().equals(fileTemp.getReqLanguageCode())){
                                    output = output + ", REQLANGUAGECODE=" + dbTemp.getReqLanguageCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQLANGUAGECODE:" +
                                            "\nDatabase = " + dbTemp.getReqLanguageCode() +
                                            "\nFile = " + fileTemp.getReqLanguageCode();
                                }

                                if(dbTemp.getReqMailingCode().equals(fileTemp.getReqMailingCode())){
                                    output = output + ", REQMAILINGCODE=" + dbTemp.getReqMailingCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQMAILINGCODE:" +
                                            "\nDatabase = " + dbTemp.getReqMailingCode() +
                                            "\nFile = " + fileTemp.getReqMailingCode();
                                }

                                if(dbTemp.getReqPriority().equals(fileTemp.getReqPriority())){
                                    output = output + ", REQPRIORITY=" + dbTemp.getReqPriority();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPRIORITY:" +
                                            "\nDatabase = " + dbTemp.getReqPriority() +
                                            "\nFile = " + fileTemp.getReqPriority();
                                }

                                if(dbTemp.getReqBranchCode().equals(fileTemp.getReqBranchCode())){
                                    output = output + ", REQBRANCHCODE=" + dbTemp.getReqBranchCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQBRANCHCODE:" +
                                            "\nDatabase = " + dbTemp.getReqBranchCode() +
                                            "\nFile = " + fileTemp.getReqBranchCode();
                                }

                                if(dbTemp.getReqBranchName().equals(fileTemp.getReqBranchName())){
                                    output = output + ", REQBRANCHNAME=" + dbTemp.getReqBranchName();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQBRANCHNAME:" +
                                            "\nDatabase = " + dbTemp.getReqBranchName() +
                                            "\nFile = " + fileTemp.getReqBranchName();
                                }

                                if(dbTemp.getReqNumberofTokens().equals(fileTemp.getReqNumberofTokens())){
                                    output = output + ", REQNUMBEROFTOKENS=" + dbTemp.getReqNumberofTokens();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQNUMBEROFTOKENS:" +
                                            "\nDatabase = " + dbTemp.getReqNumberofTokens() +
                                            "\nFile = " + fileTemp.getReqNumberofTokens();
                                }

                                if(dbTemp.getReqGeneratePanFlag().equals(fileTemp.getReqGeneratePanFlag())){
                                    output = output + ", REQGENERATEPANFLAG=" + dbTemp.getReqGeneratePanFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQGENERATEPANFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqGeneratePanFlag() +
                                            "\nFile = " + fileTemp.getReqGeneratePanFlag();
                                }

                                if(dbTemp.getReqGeneratePinFlag().equals(fileTemp.getReqGeneratePinFlag())){
                                    output = output + ", REQGENERATEPINFLAG=" + dbTemp.getReqGeneratePinFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQGENERATEPINFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqGeneratePinFlag() +
                                            "\nFile = " + fileTemp.getReqGeneratePinFlag();
                                }

                                if(dbTemp.getReqGeneratePukFlag().equals(fileTemp.getReqGeneratePukFlag())){
                                    output = output + ", REQGENERATEPUKFLAG=" + dbTemp.getReqGeneratePukFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQGENERATEPUKFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqGeneratePukFlag() +
                                            "\nFile = " + fileTemp.getReqGeneratePukFlag();
                                }

                                if(dbTemp.getReqOrderType().equals(fileTemp.getReqOrderType())){
                                    output = output + ", REQORDERTYPE=" + dbTemp.getReqOrderType();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQORDERTYPE:" +
                                            "\nDatabase = " + dbTemp.getReqOrderType() +
                                            "\nFile = " + fileTemp.getReqOrderType();
                                }

                                if(dbTemp.getReqFeedbackRequiredFlag().equals(fileTemp.getReqFeedbackRequiredFlag())){
                                    output = output + ", REQFEEDBACKREQUIREDFLAG=" + dbTemp.getReqFeedbackRequiredFlag();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQFEEDBACKREQUIREDFLAG:" +
                                            "\nDatabase = " + dbTemp.getReqFeedbackRequiredFlag() +
                                            "\nFile = " + fileTemp.getReqFeedbackRequiredFlag();
                                }

                                if(dbTemp.getReqPreviousPanId().equals(fileTemp.getReqPreviousPanId())){
                                    output = output + ", REQPREVIOUSPANID=" + dbTemp.getReqPreviousPanId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPREVIOUSPANID:" +
                                            "\nDatabase = " + dbTemp.getReqPreviousPanId() +
                                            "\nFile = " + fileTemp.getReqPreviousPanId();
                                }

                                if(dbTemp.getReqPreviousPanSeqNumber().equals(fileTemp.getReqPreviousPanSeqNumber())){
                                    output = output + ", REQPREVIOUSPANSEQNUMBER=" + dbTemp.getReqPreviousPanSeqNumber();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPREVIOUSPANSEQNUMBER:" +
                                            "\nDatabase = " + dbTemp.getReqPreviousPanSeqNumber() +
                                            "\nFile = " + fileTemp.getReqPreviousPanSeqNumber();
                                }

                                if(dbTemp.getReqPreviousPanExpiryDate().equals(fileTemp.getReqPreviousPanExpiryDate())){
                                    output = output + ", REQPREVIOUSPANEXPIRYDATE=" + dbTemp.getReqPreviousPanExpiryDate();
                                }
                                else{
                                    errorMessage = errorMessage + "\nREQPREVIOUSPANEXPIRYDATE:" +
                                            "\nDatabase = " + dbTemp.getReqPreviousPanExpiryDate() +
                                            "\nFile = " + fileTemp.getReqPreviousPanExpiryDate();
                                }

                                if(dbTemp.getRspResponseCode().equals(fileTemp.getRspResponseCode())){
                                    output = output + ", RSPRESPONSECODE=" + dbTemp.getRspResponseCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPRESPONSECODE:" +
                                            "\nDatabase = " + dbTemp.getRspResponseCode() +
                                            "\nFile = " + fileTemp.getRspResponseCode();
                                }

                                if(dbTemp.getRspErrorDescr().equals(fileTemp.getRspErrorDescr())){
                                    output = output + ", RSPERRORDESCR=" + dbTemp.getRspErrorDescr();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPERRORDESCR:" +
                                            "\nDatabase = " + dbTemp.getRspErrorDescr() +
                                            "\nFile = " + fileTemp.getRspErrorDescr();
                                }

                                if(dbTemp.getRspDateTime().equals(fileTemp.getRspDateTime())){
                                    output = output + ", RSPDATETIME=" + dbTemp.getRspDateTime();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPDATETIME:" +
                                            "\nDatabase = " + dbTemp.getRspDateTime() +
                                            "\nFile = " + fileTemp.getRspDateTime();
                                }

                                if(dbTemp.getRspPinId().equals(fileTemp.getRspPinId())){
                                    output = output + ", RSPPINID=" + dbTemp.getRspPinId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPPINID:" +
                                            "\nDatabase = " + dbTemp.getRspPinId() +
                                            "\nFile = " + fileTemp.getRspPinId();
                                }

                                if(dbTemp.getRspPukId().equals(fileTemp.getRspPukId())){
                                    output = output + ", RSPPUKID=" +  dbTemp.getRspPukId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPPUKID:" +
                                            "\nDatabase = " + dbTemp.getRspPukId() +
                                            "\nFile = " + fileTemp.getRspPukId();
                                }

                                if(dbTemp.getRspPanId().equals(fileTemp.getRspPanId())){
                                    output = output + ", RSPPANID=" + dbTemp.getRspPanId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPPANID:" +
                                            "\nDatabase = " + dbTemp.getRspPanId() +
                                            "\nFile = " + fileTemp.getRspPanId();
                                }

                                if(dbTemp.getRspPanSeqNumber().equals(fileTemp.getRspPanSeqNumber())){
                                    output = output + ", RSPPANSEQNUMBER=" + dbTemp.getRspPanSeqNumber();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPPANSEQNUMBER:" +
                                            "\nDatabase = " + dbTemp.getRspPanSeqNumber() +
                                            "\nFile = " + fileTemp.getRspPanSeqNumber();
                                }

                                if(dbTemp.getRspPanExpiryDate().equals(fileTemp.getRspPanExpiryDate())){
                                    output = output + ", RSPPANEXPIRYDATE=" + dbTemp.getRspPanExpiryDate();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPPANEXPIRYDATE:" +
                                            "\nDatabase = " + dbTemp.getRspPanExpiryDate() +
                                            "\nFile = " + fileTemp.getRspPanExpiryDate();
                                }

                                if(dbTemp.getRspIssuerTokenProductCode().equals(fileTemp.getRspIssuerTokenProductCode())){
                                    output = output + ", RSPISSUERTOKENPRODUCTCODE=" + dbTemp.getRspIssuerTokenProductCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPISSUERTOKENPRODUCTCODE:" +
                                            "\nDatabase = " + dbTemp.getRspIssuerTokenProductCode() +
                                            "\nFile = " + fileTemp.getRspIssuerTokenProductCode();
                                }

                                if(dbTemp.getRspTokenAppSeqNum().equals(fileTemp.getRspTokenAppSeqNum())){
                                    output = output + ", RSPTOKENAPPSEQNUM=" + dbTemp.getRspTokenAppSeqNum();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPTOKENAPPSEQNUM:" +
                                            "\nDatabase = " + dbTemp.getRspTokenAppSeqNum() +
                                            "\nFile = " + fileTemp.getRspTokenAppSeqNum();
                                }

                                if(dbTemp.getRspSmsStatus().equals("4") && fileTemp.getRspSmsStatus().equals("2")){
                                    output = output + ", RSPSMSSTATUS=" + dbTemp.getRspSmsStatus();
                                }
                                else{
                                    errorMessage = errorMessage + "\nRSPSMSSTATUS:" +
                                            "\nDatabase = " + dbTemp.getRspSmsStatus() +
                                            "\nFile = " + fileTemp.getRspSmsStatus();
                                }

                                if(dbTemp.getExtInstitutionId().equals(fileTemp.getExtInstitutionId())){
                                    output = output + ", EXTINSTITUTIONID=" + dbTemp.getExtInstitutionId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTINSTITUTIONID:" +
                                            "\nDatabase = " + dbTemp.getExtInstitutionId() +
                                            "\nFile = " + fileTemp.getExtInstitutionId();
                                }

                                if(dbTemp.getExtInstitutionName().equals(fileTemp.getExtInstitutionName())){
                                    output = output + ", EXTINSTITUTIONNAME=" + dbTemp.getExtInstitutionName();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTINSTITUTIONNAME:" +
                                            "\nDatabase = " + dbTemp.getExtInstitutionName() +
                                            "\nFile = " + fileTemp.getExtInstitutionName();
                                }

                                if(dbTemp.getExtIssuerId().equals(fileTemp.getExtIssuerId())){
                                    output = output + ", EXTISSUERID=" + dbTemp.getExtIssuerId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTISSUERID:" +
                                            "\nDatabase = " + dbTemp.getExtIssuerId() +
                                            "\nFile = " + fileTemp.getExtIssuerId();
                                }

                                if(dbTemp.getExtIssuerName().equals(fileTemp.getExtIssuerName())){
                                    output = output + ", EXTISSUERNAME=" + dbTemp.getExtIssuerName();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTISSUERNAME:" +
                                            "\nDatabase = " + dbTemp.getExtIssuerName() +
                                            "\nFile = " + fileTemp.getExtIssuerName();
                                }

                                if(dbTemp.getExtTokenProductId().equals(fileTemp.getExtTokenProductId())){
                                    output = output + ", EXTTOKENPRODUCTID=" + dbTemp.getExtTokenProductId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTTOKENPRODUCTID:" +
                                            "\nDatabase = " + dbTemp.getExtTokenProductId() +
                                            "\nFile = " + fileTemp.getExtTokenProductId();
                                }

                                if(dbTemp.getExtIssuerTokenproductCode().equals(fileTemp.getExtIssuerTokenproductCode())){
                                    output = output + ", EXTISSUERTOKENPRODUCTCODE=" + dbTemp.getExtIssuerTokenproductCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTISSUERTOKENPRODUCTCODE:" +
                                            "\nDatabase = " + dbTemp.getExtIssuerTokenproductCode() +
                                            "\nFile = " + fileTemp.getExtIssuerTokenproductCode();
                                }

                                if(dbTemp.getExtTokenProductName().equals(fileTemp.getExtTokenProductName())){
                                    output = output + ", EXTTOKENPRODUCTNAME=" + dbTemp.getExtTokenProductName();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTTOKENPRODUCTNAME:" +
                                            "\nDatabase = " + dbTemp.getExtTokenProductName() +
                                            "\nFile = " + fileTemp.getExtTokenProductName();
                                }

                                if(dbTemp.getExtTokenProductGroupId().equals(fileTemp.getExtTokenProductGroupId())){
                                    output = output + ", EXTTOKENPRODUCTGROUPID=" + dbTemp.getExtTokenProductGroupId();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTTOKENPRODUCTGROUPID:" +
                                            "\nDatabase = " + dbTemp.getExtTokenProductGroupId() +
                                            "\nFile = " + fileTemp.getExtTokenProductGroupId();
                                }

                                if(dbTemp.getExtTokenProductGroupName().equals(fileTemp.getExtTokenProductGroupName())){
                                    output = output + ", EXTTOKENPRODUCTGROUPNAME=" + dbTemp.getExtTokenProductGroupName();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTTOKENPRODUCTGROUPNAME:" +
                                            "\nDatabase = " + dbTemp.getExtTokenProductGroupName() +
                                            "\nFile = " + fileTemp.getExtTokenProductGroupName();
                                }

                                if(dbTemp.getExtTokenProductGroupCode().equals(fileTemp.getExtTokenProductGroupCode())){
                                    output = output + ", EXTTOKENPRODUCTGROUPCODE=" + dbTemp.getExtTokenProductGroupCode();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTTOKENPRODUCTGROUPCODE:" +
                                            "\nDatabase = " + dbTemp.getExtTokenProductGroupCode() +
                                            "\nFile = " + fileTemp.getExtTokenProductGroupCode();
                                }

                                if(dbTemp.getExtPanDisplay().equals(fileTemp.getExtPanDisplay())){
                                    output = output + ", EXTPANDISPLAY=" + dbTemp.getExtPanDisplay();
                                }
                                else{
                                    errorMessage = errorMessage + "\nEXTPANDISPLAY:" +
                                            "\nDatabase = " + dbTemp.getExtPanDisplay() +
                                            "\nFile = " + fileTemp.getExtPanDisplay();
                                }

                                if(dbTemp.getExtIssuerCountryCode().equals(fileTemp.getExtIssuerCountryCode())){
                                    output = output + ", EXTISSUERCOUNTRYCODE=" + dbTemp.getExtIssuerCountryCode() + "]";
                                }
                                else {
                                    errorMessage = errorMessage + "\nEXTISSUERCOUNTRYCODE:" +
                                            "\nDatabase = " + dbTemp.getExtIssuerCountryCode() +
                                            "\nFile = " + fileTemp.getExtIssuerCountryCode();
                                }

                                if(errorMessage.equals("")) {
                                    Logging.passMessage("Record in database:\n" + output + "\n found in Pin SMS Data Extract.");
                                }
                                else{
                                    Logging.failMessage("RECORD MISMATCH\nDatabase Record\n" + dbTemp +
                                            "\nFile Extract Record\n" + fileTemp +
                                            "\nError Message = " + errorMessage);
                                    softAssert.fail("RECORD MISMATCH\nDatabase Record\n" + dbTemp +
                                            "\nFile Extract Record\n" + fileTemp);

                                }


                            }

                            if(scenarioFail){
                                Logging.failMessage("Record in database:\n" + output + "\n not found in Pin SMS Data Extract or incorrect.");
                                softAssert.fail("Record in database:\n" + output + "\n not found in Pin SMS Data Extract or incorrect.");

                            }

                        }

                    }

                    break;

                case "tokenapplicationdataextract":

                    tokenApplicationDataFileSplitter = new TokenApplicationDataFileSplitter();
                    tokenApplicationDataDatabaseGatherer = new TokenApplicationDataDatabaseGatherer();
                    tokenApplicationDataDatabaseGatherer.gatherData();
                    tokenApplicationDataFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + tokenApplicationDataDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(tokenApplicationDataDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + tokenApplicationDataFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + tokenApplicationDataFileSplitter.getRecordCount());
                    Logging.dataMsg(tokenApplicationDataFileSplitter.records.toString());

                    if(tokenApplicationDataDatabaseGatherer.getRecordCount() != tokenApplicationDataFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                tokenApplicationDataDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + tokenApplicationDataFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(tokenApplicationDataDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Token Application Data Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Token Application Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (TokenApplicationDataRecord temp : tokenApplicationDataDatabaseGatherer.records) {
                        if(tokenApplicationDataFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Token Application Data Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Token Application Data Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Token Application Data Extract.");
                        }
                    }
                    break;

                case "tokendataextract":

                    tokenDataFileSplitter = new TokenDataFileSplitter();
                    tokenDataDatabaseGatherer = new TokenDataDatabaseGatherer();
                    tokenDataDatabaseGatherer.gatherData();
                    tokenDataFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + tokenDataDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(tokenDataDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + tokenDataFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + tokenDataFileSplitter.getRecordCount());
                    Logging.dataMsg(tokenDataFileSplitter.records.toString());

                    if(tokenDataDatabaseGatherer.getRecordCount() != tokenDataFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                tokenDataDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + tokenDataFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(tokenDataDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Token Data Extract." +
                                                                    "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Token Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    scenarioFail = false;
                    for (TokenDataRecord temp : tokenDataDatabaseGatherer.records) {
                        if (tokenDataFileSplitter.records.contains(temp)) {
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Token Data Extract.");

                        } else {
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Token Data Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Token Data Extract.");
                        }
                    }

                    break;

                case "tokenimportfeedback":

                    tokenImportFeedbackFileSplitter = new TokenImportFeedbackFileSplitter();
                    tokenImportDatabaseGatherer = new TokenImportDatabaseGatherer();
                    tokenImportDatabaseGatherer.gatherData(extractLogDataExtractRecordBefore);
                    tokenImportFeedbackFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + tokenImportDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(tokenImportDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + tokenImportFeedbackFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + tokenImportFeedbackFileSplitter.getRecordCount());
                    Logging.dataMsg(tokenImportFeedbackFileSplitter.records.toString());

                    if(tokenImportDatabaseGatherer.getRecordCount() != tokenImportFeedbackFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                tokenImportDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + tokenImportFeedbackFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(tokenImportDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Token Import Feedback Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Token Import Feedback Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    scenarioFail = false;
                    for (TokenImportFeedbackRecord temp : tokenImportDatabaseGatherer.records) {
                        if(tokenImportFeedbackFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Token Import Feedback Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Token Import Feedback Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Token Import Feedback Extract.");
                        }
                    }
                    break;

                case "tokenorderfeedback":



                    tokenOrderFeedbackFileSplitter = new TokenOrderFeedbackFileSplitter();
                    tokenOrderFeedbackDatabaseGatherer = new TokenOrderFeedbackDatabaseGatherer();

                    tokenOrderFeedbackDatabaseGatherer.gatherData();
                    tokenOrderFeedbackFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + tokenOrderFeedbackDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(tokenOrderFeedbackDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + tokenOrderFeedbackFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + tokenOrderFeedbackFileSplitter.getRecordCount());
                    Logging.dataMsg(tokenOrderFeedbackFileSplitter.records.toString());

                    if(tokenOrderFeedbackDatabaseGatherer.getRecordCount() != tokenOrderFeedbackFileSplitter.getRecordCount()) {
                        Logging.failMessage("Record count mismatch:\nDatabase Count: " +
                                tokenOrderFeedbackDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + tokenOrderFeedbackFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                        softAssert.fail("Record count mismatch:\nDatabase Count: " +
                                tokenOrderFeedbackDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + tokenOrderFeedbackFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(tokenOrderFeedbackDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Token Order Feedback Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Token Order Feedback Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (TokenOrderFeedbackRecord temp : tokenOrderFeedbackDatabaseGatherer.records) {

                        if(tokenOrderFeedbackFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Token Order Feedback Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Token Order Feedback Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Token Order Feedback Extract.");

                        }
                    }

                    break;

                case "transactionlogdataextract":

                    transactionLogDataExtractFileSplitter = new TransactionLogDataExtractFileSplitter();
                    transactionLogDataExtractDatabaseGatherer = new TransactionLogDataExtractDatabaseGatherer();
                    transactionLogDataExtractDatabaseGatherer.gatherData();
                    transactionLogDataExtractFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + transactionLogDataExtractDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(transactionLogDataExtractDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + transactionLogDataExtractFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + transactionLogDataExtractFileSplitter.getRecordCount());
                    Logging.dataMsg(transactionLogDataExtractFileSplitter.records.toString());

                    if(transactionLogDataExtractDatabaseGatherer.getRecordCount() != transactionLogDataExtractFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                transactionLogDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + transactionLogDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(transactionLogDataExtractDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Transaction Log Data Extract." +
                                "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Transaction Log Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (TransactionLogDataExtractRecord temp : transactionLogDataExtractDatabaseGatherer.records) {

                        if ((temp.getTrxTypeId().equals("9") && temp.getReqPinDeliveryMethod().equals("3") && temp.getRspSmsStatus().equals("1")) ||
                                (temp.getTrxTypeId().equals("9") && temp.getReqPinDeliveryMethod().equals("3") && temp.getRspSmsStatus().equals("4")) ||
                                (temp.getTrxTypeId().equals("9") && temp.getReqPinDeliveryMethod().equals("3") && temp.getRspSmsStatus().equals(" ")) ||
                                (temp.getTrxTypeId().equals("9") && temp.getReqPinDeliveryMethod().equals("3") && temp.getRspSmsStatus()==null)){
                            Logging.dataMsg("Ignoring Database Row " + temp.getId() + ": TOWPD Delivery Method 3 with SMSRSPSTATUS null, 1 or 4 is not included in Transaction Log Extract.");
                        }
                        else {
                            if(transactionLogDataExtractFileSplitter.records.contains(temp)){
                                Logging.passMessage("Record in database:\n" + temp + "\n found in Transaction Log Data Extract.");
                            }
                            else{
                                Logging.failMessage("Record in database:\n" + temp + "\n not found in Transaction Log Data Extract.");
                                softAssert.fail("Record in database:\n" + temp + "\n not found in Transaction Log Data Extract.");
                            }
                        }
                    }

                    break;

                case "translatepaniddataextract":

                    translatePanIdDataExtractFileSplitter = new TranslatePanIdDataExtractFileSplitter();
                    translatePanIdDataExtractDatabaseGatherer = new TranslatePanIdDataExtractDatabaseGatherer();
                    translatePanIdDataExtractDatabaseGatherer.gatherData();
                    translatePanIdDataExtractFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + translatePanIdDataExtractDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(translatePanIdDataExtractDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + translatePanIdDataExtractFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + translatePanIdDataExtractFileSplitter.getRecordCount());
                    Logging.dataMsg(translatePanIdDataExtractFileSplitter.records.toString());

                    if(translatePanIdDataExtractDatabaseGatherer.getRecordCount() != translatePanIdDataExtractFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                translatePanIdDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + translatePanIdDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                        softAssert.fail("Record count mismatch:\nDatabase Count: " +
                                translatePanIdDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + translatePanIdDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                    }

                    if(translatePanIdDataExtractDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for Translate PAN ID Data Extract." +
                                                                    "\nCheck if file used was from previous successful run.");
                        softAssert.fail("No records found in database for Translate PAN ID Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }

                    for (TranslatePanIdDataExtractRecord temp : translatePanIdDataExtractDatabaseGatherer.records) {

                        if(translatePanIdDataExtractFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in Translate PAN ID Data Extract.");
                        }
                        else{
                            Logging.failMessage("Record in database:\n" + temp + "\n not found in Translate PAN ID Data Extract.");
                            softAssert.fail("Record in database:\n" + temp + "\n not found in Translate PAN ID Data Extract.");
                        }
                    }

                    break;

                case "vppdataextract":

                    vppDataExtractFileSplitter = new VppDataExtractFileSplitter();
                    vppDataExtractDatabaseGatherer = new VppDataExtractDatabaseGatherer();
                    vppDataExtractDatabaseGatherer.gatherData();
                    vppDataExtractFileSplitter.splitFileInRecords();

                    Logging.dataMsg("Database Records: " + vppDataExtractDatabaseGatherer.getRecordCount());
                    Logging.dataMsg(vppDataExtractDatabaseGatherer.records.toString());
                    Logging.dataMsg("Extract Log File: " + vppDataExtractFileSplitter.getParsedFileName());
                    Logging.dataMsg("Extract Log Records: " + vppDataExtractFileSplitter.getRecordCount());
                    Logging.dataMsg(vppDataExtractFileSplitter.records.toString());

                    if(vppDataExtractDatabaseGatherer.getRecordCount() != vppDataExtractFileSplitter.getRecordCount()) {
                        Logging.warnMessage("Record count mismatch:\nDatabase Count: " +
                                vppDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + vppDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");
                        softAssert.fail("Record count mismatch:\nDatabase Count: " +
                                vppDataExtractDatabaseGatherer.getRecordCount() +
                                "\nExtract File Count: " + vppDataExtractFileSplitter.getRecordCount() +
                                "\nCheck records manually.");

                    }

                    if(vppDataExtractDatabaseGatherer.getRecordCount() == 0) {
                        Logging.failMessage("No records found in database for VPP Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                        softAssert.fail("No records found in database for VPP Data Extract." +
                                "\nCheck if file used was from previous successful run.");

                    }


                    for (VppDataExtractRecord temp : vppDataExtractDatabaseGatherer.records) {

                        if(vppDataExtractFileSplitter.records.contains(temp)){
                            Logging.passMessage("Record in database:\n" + temp + "\n found in VPP Data Extract.");
                        }
                        else{
                            Logging.failMessage(("Record in database:\n" + temp + "\n not found in VPP Data Extract."));
                            softAssert.fail("Record in database:\n" + temp + "\n not found in VPP Data Extract.");
                        }
                    }

                    break;

            }
        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    /* ===============================================================================================================*
     * Deleting the Task
     * Parameter: word - the name of the task
     * ===============================================================================================================*/
    @Then("I can delete the {word} from the list of tasks")
    public void i_can_delete_the_task(String word){

        try{

            if(!taskSchedulerPage.deleteTask(word, taskId)){
                softAssert.fail("Delete Task failed.");
            }

            AbstractSteps.i_take_a_screenshot("Delete Check " + adminCommon.getDateTime());

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    /* ===============================================================================================================*
     * Creating Expected Results for Housekeeper Scheduled Task
     * ===============================================================================================================*/
    @Then("I create my expected results")
    public void getExpectedResultsForHousekeeper() throws Exception {

        String hTaskName;
        String hTaskParam = "";

        housekeeperTasks = HousekeeperUtils.getHousekeeperTaskList();

        for (Object hTask : housekeeperTasks){

            String hTaskString = hTask.toString();

            if (hTaskString.contains("(")){
                String[] hTaskDetail = hTaskString.split("\\(");
                hTaskName = hTaskDetail[0];
                hTaskParam = hTaskDetail[1].replace(")", "");
                }
            else{
                hTaskName = hTaskString;
            }

            switch(hTaskName){

                case "cardHolderDataUpdate":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - cardHolderDataUpdate");

                    createDataMsg = "";
                    createDataMsg = DataLoadHousekeeperCardHolderDataUpdate.runDataLoadHousekeeperCardHolderDataUpdate();

                    housekeeperCardHolderDataUpdateDatabaseGathererExp = new HousekeeperCardHolderDataUpdateDatabaseGatherer();
                    housekeeperCardHolderDataUpdateDatabaseGathererExp.gatherDataForHousekeeper();
                    housekeeperCardHolderDataUpdateRows = housekeeperCardHolderDataUpdateDatabaseGathererExp.selectedIds;

                    Logging.dataMsg(createDataMsg);
                    Logging.dataMsg("ID String: " + housekeeperCardHolderDataUpdateRows);

                    break;

                case "clearExpiredVPPPinIds":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - clearExpiredVPPPinIds");

                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp = new HousekeeperVppClearExpiredVPPPinIdsDatabaseGatherer();
                    housekeeperVppClearExpiredVPPPinIdsCurrentRows1 = housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp.getRowCountForHousekeeper();

                    createDataMsg = "";
                    createDataMsg = DataLoadHousekeeperExpiredVppPinIds.runDataLoadHousekeeperExpiredVppPinIds();

                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp.gatherDataForHousekeeper();
//                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererExp.printRecords();

                    break;

                case "expiredKeysNotification":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - expiredKeysNotification");

                    boolean rc = DataLoadDataGatherer.dataloadCheckTestKeyExists();
                    if(rc){
                        DataLoadDataGatherer.dataloadUpdateKeyExpiryDate();
                    }
                    else{
                        DataLoadDataGatherer.dataloadInsertKey();
                    }

                    break;

                case "extractLogsCleanUp":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - extractLogsCleanUp");

                    extractLogHousekeeperExp = new ExtractLogDataGatherer();
                    extractLogHousekeeperCurrentRows1 = extractLogHousekeeperExp.getRowCountForHousekeeper();

                    createDataMsg = "";
                    createDataMsg = DataLoadHousekeeperExtractLogsCleanUp.runDataLoadHousekeeperExtractLogsCleanUp();


                    extractLogHousekeeperExp.gatherDataForHousekeeper(hTaskParam);
//                    extractLogHousekeeperExp.printRecords();
                    break;

                case "filesCleanUp":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - filesCleanUp");

                    String remoteFilePath;

                    outputFilePath = ConfigUtils.cfgProperty("local_folder");
                    remoteFilePath = ConfigUtils.cfgProperty("extractlog_filepath");

                    /* Create files to be uploaded in the local folder */
                    delFileName = "fileCleanUpTestDelete_" + adminCommon.getDateTime() + ".djr";
                    File myDjr = new File(outputFilePath + "\\" + delFileName);
                    boolean rc1 = myDjr.createNewFile();
                    movFileName = "fileCleanUpTestMove_" + adminCommon.getDateTime() + ".aco";
                    File myAco = new File(outputFilePath + "\\" + movFileName);
                    boolean rc2 = myAco.createNewFile();

                    if(rc1 && rc2){
                        Logging.dataMsg("Files for Housekeeper Scheduled Task - filesCleanUp created successfully.");
                    }

                    try {
                        Thread.sleep(5000);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    SFTPFileRetriever.uploadFile("/" + remoteFilePath + "/" + delFileName, outputFilePath + "\\" + delFileName);
                    SFTPFileRetriever.uploadFile("/" + remoteFilePath + "/" + movFileName, outputFilePath + "\\" + movFileName);

                    break;

                case "userAccessLogsCleanUp":

                    Logging.stepName("Creating Expected Results for Housekeeper Scheduled Task - userAccessLogsCleanUp");

                    accessLogHousekeeperExp = new AccessLogDatabaseGatherer();
                    accessLogHousekeeperCurrentRows1 = accessLogHousekeeperExp.getRowCountForHousekeeper();

                    DataLoadDataGatherer.dataloadModifyDatesForUserAccessLogsCleanUp();

                    accessLogHousekeeperExp.gatherDataForHousekeeper(hTaskParam);
                    break;

            }

        }

    }

    /* ===============================================================================================================*
     * Creating Actual Results for Housekeeper Scheduled Task
     * ===============================================================================================================*/
    @Then("I create my actual results")
    public void getActualResultsForHousekeeper() throws SftpException, JSchException {

        String hTaskName;
        String hTaskParam = "";

        housekeeperTasks = HousekeeperUtils.getHousekeeperTaskList();

        for (Object hTask : housekeeperTasks){

            String hTaskString = hTask.toString();

            if (hTaskString.contains("(")){
                String[] hTaskDetail = hTaskString.split("\\(");
                hTaskName = hTaskDetail[0];
                hTaskParam = hTaskDetail[1].replace(")", "");
            }
            else{
                hTaskName = hTaskString;
            }

            switch(hTaskName){

                case "cardHolderDataUpdate":

                    Logging.stepName("Creating Actual Results for Housekeeper Scheduled Task - cardHolderDataUpdate");

                    housekeeperCardHolderDataUpdateDatabaseGathererAct = new HousekeeperCardHolderDataUpdateDatabaseGatherer();
                    housekeeperCardHolderDataUpdateDatabaseGathererAct.gatherDataForHousekeeperActual(housekeeperCardHolderDataUpdateRows);
                    Logging.dataMsg(housekeeperCardHolderDataUpdateDatabaseGathererAct.records.toString());

                    break;

                case "clearExpiredVPPPinIds":

                    Logging.stepName("Creating Actual Results for Housekeeper Scheduled Task - clearExpiredVPPPinIds");

                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct = new HousekeeperVppClearExpiredVPPPinIdsDatabaseGatherer();
                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct.gatherDataForHousekeeper();
//                    housekeeperVppClearExpiredVPPPinIdsDatabaseGathererAct.printRecords();

                    break;

                case "extractLogsCleanUp":

                    Logging.stepName("Creating Actual Results for Housekeeper Scheduled Task - extractLogsCleanUp");

                    extractLogHousekeeperAct = new ExtractLogDataGatherer();
                    extractLogHousekeeperAct.gatherDataForHousekeeper(hTaskParam);

                    break;

                case "userAccessLogsCleanUp":

                    Logging.stepName("Creating Actual Results for Housekeeper Scheduled Task - userAccessLogsCleanUp");

                    accessLogHousekeeperAct = new AccessLogDatabaseGatherer();
                    accessLogHousekeeperAct.gatherDataForHousekeeper(hTaskParam);

                    break;

            }
        }
    }

    @Then("Check scheduler scenario")
    public void check_scheduler_scenario(){
        softAssert.assertAll();
    }


}
