package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.databasegathers.HousekeeperTokenCleanUpDatabaseGatherer;
import com.aconite.apm.gui.automation.databasegathers.HousekeeperTransactionHistoryCleanUpDatabaseGatherer;
import com.aconite.apm.gui.automation.databasegathers.PinDataDatabaseGatherer;
import com.aconite.apm.gui.automation.dataload.DataLoadHousekeeperTokenCleanUp;
import com.aconite.apm.gui.automation.dataload.DataLoadHousekeeperUnusedPinPasswordCleanUp;
import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.webpages.AdminCommon;
import com.aconite.apm.gui.automation.webpages.HomePage;
import com.aconite.apm.gui.automation.webpages.HousekeeperPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class HousekeeperTestSteps {

    public WebDriver webDriver;
    public SoftAssert softAssert = new SoftAssert();

    HousekeeperPage housekeeperPage;
    AdminCommon adminCommon;

    int tokenCleanUpHousekeeperCurrentRows1=0;
    int tokenCleanUpHousekeeperCurrentRows2=0;
    HousekeeperTokenCleanUpDatabaseGatherer tokenCleanUpLogHousekeeperExp;
    HousekeeperTokenCleanUpDatabaseGatherer tokenCleanUpLogHousekeeperAct;

    int transactionLogHousekeeperCurrentRows1 = 0;
    int transactionLogHousekeeperCurrentRows2 = 0;
    HousekeeperTransactionHistoryCleanUpDatabaseGatherer housekeeperTransactionHistoryCleanUpDatabaseGathererExp;
    HousekeeperTransactionHistoryCleanUpDatabaseGatherer housekeeperTransactionHistoryCleanUpDatabaseGathererAct;

    int unusedPinPasswordCleanUpHousekeeperCurrentRows1=0;
    int unusedPinPasswordCleanUpHousekeeperCurrentRows2=0;
    PinDataDatabaseGatherer pinsmspwdCleanUpLogHousekeeperExp;
    PinDataDatabaseGatherer pinsmspwdCleanUpLogHousekeeperAct;

    public HousekeeperTestSteps(AbstractSteps abstractSteps)
    {

        this.webDriver = abstractSteps.getDriver();

        housekeeperPage = new HousekeeperPage(webDriver);
        adminCommon = new AdminCommon(webDriver);

    }

    @Then("I click on the Operation Menu Housekeeper")
    public void clickOperationScheduler() {

        try{
            HomePage homePage = new HomePage(webDriver);
            homePage.clickOnOperationHousekeeperMenuItem();

            Logging.stepName("Click On Operation Housekeeper Menu Item");

            if(housekeeperPage.isPageOpened()){
                Logging.passMessage("Logged in and on Operation Housekeeper Page");
            }
            else{
                Logging.failMessage("Not logged in and on Operation Housekeeper Page");
                softAssert.fail("Not logged in and on Operation Housekeeper Page");

            }

        }
        catch (Exception exc) {
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @When("I run a token clean up task for institution \"([^\"]*)\"$")
    public void runTask(String institution){

        boolean rc;

        Logging.stepName("Run Housekeeper Task - tokenCleanUp");

        try{

            rc = housekeeperPage.clickRunTaskCleanUp(institution);
            if(rc){
                Logging.passMessage("tokenCleanUp ran successfully");
            }
            else{
                Logging.failMessage("tokenCleanUp run failed");
                softAssert.fail("tokenCleanUp run failed");

            }
        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @When("I run a transaction history clean up task")
    public void runTransactionHistoryCleanUpTask(){

        boolean rc;

        Logging.stepName("Run Housekeeper Task - transactionHistoryCleanUp");

        try{

            rc = housekeeperPage.clickRunTransactionHistoryCleanUp();
            if(rc){
                Logging.passMessage("transactionHistoryCleanUp ran successfully");
            }
            else{
                Logging.failMessage("transactionHistoryCleanUp run failed");
                softAssert.fail("transactionHistoryCleanUp run failed");
            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }
    }

    @When("I run an unused pin password clean up task")
    public void runUnusedPinPasswordCleanUpTask(){

        boolean rc;

        Logging.stepName("Run Housekeeper Task - unusedPinPasswordCleanUp");

        try{

            rc = housekeeperPage.clickRunUnusedPinPasswordCleanUp();
            if(rc){
                Logging.passMessage("unusedPinPasswordCleanUp ran successfully");
            }
            else{
                Logging.failMessage("unusedPinPasswordCleanUp run failed");
                softAssert.fail("unusedPinPasswordCleanUp run failed");

            }

        }
        catch(Exception exc){
            softAssert.fail(exc.toString());
            Logging.failMessage(exc.toString() + "\n" + Arrays.toString(exc.getStackTrace()));
            AbstractSteps.i_take_a_screenshot("Exception" + adminCommon.getDateTime());
            exc.printStackTrace();
        }

    }

    @Then("I create expected results for token clean up")
    public void create_expected_data_tokenCleanUp() throws Exception {

        Logging.stepName("Data Creation for Housekeeper Task - tokenCleanUp");
        Logging.dataMsg(DataLoadHousekeeperTokenCleanUp.runDataLoadHousekeeperTokenCleanUp());
        Logging.dataMsg(DataLoadHousekeeperTokenCleanUp.runDataLoadTokenImportHousekeeper());


        tokenCleanUpLogHousekeeperExp = new HousekeeperTokenCleanUpDatabaseGatherer();
        tokenCleanUpLogHousekeeperExp.gatherDataForHousekeeper();

    }

    @Then("I create expected results for transaction history clean up")
    public void create_expected_data_transactionHistoryCleanUp() throws Exception {

        Logging.stepName("Data Creation for Housekeeper Task - transactionHistoryCleanUp");

        housekeeperTransactionHistoryCleanUpDatabaseGathererExp = new HousekeeperTransactionHistoryCleanUpDatabaseGatherer();
        transactionLogHousekeeperCurrentRows1 = housekeeperTransactionHistoryCleanUpDatabaseGathererExp.getRowCountForHousekeeper();

        DataLoadDataGatherer.dataloadModifyDatesForTransactionCleanUp();

        housekeeperTransactionHistoryCleanUpDatabaseGathererExp.gatherDataForHousekeeper();

    }

    @Then("I create expected results for unused pin password clean up")
    public void create_expected_data_unusedPinPasswordCleanUp() throws Exception {

        Logging.stepName("Data Creation for Housekeeper Task - unusedPinPasswordCleanUp");

        pinsmspwdCleanUpLogHousekeeperExp = new PinDataDatabaseGatherer();
        unusedPinPasswordCleanUpHousekeeperCurrentRows1 = pinsmspwdCleanUpLogHousekeeperExp.getRowCountForHousekeeper();

        Logging.dataMsg(DataLoadHousekeeperUnusedPinPasswordCleanUp.runDataLoadHousekeeperUnusedPinPasswordCleanUp());

        pinsmspwdCleanUpLogHousekeeperExp.gatherDataForUnusedPinPasswordCleanUp();
        Logging.dataMsg("ID String: " + pinsmspwdCleanUpLogHousekeeperExp.createdPins);
    }

    @Then("I create actual results for token clean up")
    public void create_actual_data_tokenCleanUp() {

        Logging.stepName("Creating Actual Results for Housekeeper Task - tokenCleanUp");

        tokenCleanUpLogHousekeeperAct = new HousekeeperTokenCleanUpDatabaseGatherer();
        tokenCleanUpLogHousekeeperAct.gatherDataForHousekeeper();
    }

    @Then("I create actual results for transaction history clean up")
    public void create_actual_data_transactionHistoryCleanUp() {

        Logging.stepName("Creating Actual Results for Housekeeper Task - transactionHistoryCleanUp");

        housekeeperTransactionHistoryCleanUpDatabaseGathererAct = new HousekeeperTransactionHistoryCleanUpDatabaseGatherer();
        housekeeperTransactionHistoryCleanUpDatabaseGathererAct.gatherDataForHousekeeper();

    }

    @Then("I create actual results for unused pin password clean up")
    public void create_actual_data_unusedPinPasswordCleanUp() {

        Logging.stepName("Creating Actual Results for Housekeeper Task - unusedPinPasswordCleanUp");

        pinsmspwdCleanUpLogHousekeeperAct = new PinDataDatabaseGatherer();
        pinsmspwdCleanUpLogHousekeeperAct.gatherDataForUnusedPinPasswordCleanUp();

    }

    @Then("The token clean up task runs successfully and the output is correct")
    public void check_data_tokenCleanUp(){

        Logging.stepName("Checking Results for Housekeeper Scheduled Task - tokenCleanUp");

        Logging.dataMsg("tokenCleanUp: Data expected to be cleared");
        Logging.dataMsg(tokenCleanUpLogHousekeeperExp.records.toString());
        Logging.dataMsg("tokenCleanUp: Data remaining after housekeeper task");
        Logging.dataMsg(tokenCleanUpLogHousekeeperAct.records.toString());

        if(tokenCleanUpLogHousekeeperAct.records.isEmpty()) {
            Logging.passMessage("All tokens meeting criteria have been deleted successfully");
        }
        else{
            Logging.failMessage("Not all tokens meeting criteria have been deleted successfully");
            softAssert.fail("Not all tokens meeting criteria have been deleted successfully");

        }

        tokenCleanUpHousekeeperCurrentRows2 = tokenCleanUpLogHousekeeperExp.getRowCountForHousekeeper();
        if(tokenCleanUpHousekeeperCurrentRows2 == 0){
            Logging.warnMessage("No rows remaining in C_D_TOKEN_APPLICATION table\n" +
                    "There were " + tokenCleanUpHousekeeperCurrentRows1 + " at the beginning of the run.");
        }

//        TODO: Add further checks to ensure tokens and pins have been deleted form C_D_TOKEN and C_D_PIN

    }

    @Then("The transaction history clean up task runs successfully and the output is correct")
    public void check_data_transactionHistoryCleanUp(){

        Logging.stepName("Checking Results for Housekeeper Scheduled Task - transactionHistoryCleanUp");

        Logging.dataMsg("transactionHistoryCleanUp: Data expected to be cleared");
        Logging.dataMsg(housekeeperTransactionHistoryCleanUpDatabaseGathererExp.records.toString());
        Logging.dataMsg("transactionHistoryCleanUp: Data remaining after housekeeper task");
        Logging.dataMsg(housekeeperTransactionHistoryCleanUpDatabaseGathererAct.records.toString());

        if(housekeeperTransactionHistoryCleanUpDatabaseGathererAct.records.isEmpty()) {
            Logging.passMessage("All transactions meeting criteria have been deleted successfully");
        }
        else{
            Logging.failMessage("Not all transactions meeting criteria have been deleted successfully");
            softAssert.fail("Not all transactions meeting criteria have been deleted successfully");

        }

        transactionLogHousekeeperCurrentRows2 = housekeeperTransactionHistoryCleanUpDatabaseGathererExp.getRowCountForHousekeeper();
        if(transactionLogHousekeeperCurrentRows2 == 0){
            Logging.warnMessage("No rows remaining in PM_D_TRX_LOG table\n" +
                    "There were " + transactionLogHousekeeperCurrentRows1 + " at the beginning of the run.");
        }

    }

    @Then("The unused pin password clean up task runs successfully and the output is correct")
    public void check_data_unusedPinPasswordCleanUp(){

        Logging.stepName("Checking Results for Housekeeper Scheduled Task - unusedPinPasswordCleanUp");

        Logging.dataMsg("unusedPinPasswordCleanUp: Data expected to be cleared");
        Logging.dataMsg(pinsmspwdCleanUpLogHousekeeperExp.records.toString());
        Logging.dataMsg("unusedPinPasswordCleanUp: Data remaining after housekeeper task");
        Logging.dataMsg(pinsmspwdCleanUpLogHousekeeperAct.records.toString());

        if(pinsmspwdCleanUpLogHousekeeperAct.records.isEmpty()) {
            Logging.passMessage("All unused pins meeting criteria have been deleted successfully");
        }
        else{
            Logging.failMessage("Not all unused pins meeting criteria have been deleted successfully");
            softAssert.fail("Not all unused pins meeting criteria have been deleted successfully");

        }

        unusedPinPasswordCleanUpHousekeeperCurrentRows2 = pinsmspwdCleanUpLogHousekeeperExp.getRowCountForHousekeeper();
        if(unusedPinPasswordCleanUpHousekeeperCurrentRows2 == 0){
            Logging.warnMessage("No rows remaining in C_D_PIN table\n" +
                    "There were " + unusedPinPasswordCleanUpHousekeeperCurrentRows1 + " at the beginning of the run.");
        }

    }

    @Then("Check tokenCleanUp scenario")
    public void check_tokenCleanUp_scenario(){
        softAssert.assertAll();
    }

    @Then("Check transactionHistoryCleanUp scenario")
    public void check_transactionHistoryCleanUp_scenario(){
        softAssert.assertAll();
    }

    @Then("Check unusedPinPasswordCleanUp scenario")
    public void check_unusedPinPasswordCleanUp_scenario(){
        softAssert.assertAll();
    }

}
