package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinChangeRollback;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadRollbackPinChangeTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadRollbackPinChangeTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }


    @Given("Rollback PIN Change by PAN ID {int} iterations {int} records")
    public void runRollbackPINChangeByPanId(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanId(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinChangeRollbackByPanId2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinChangeRollbackByPanId2 FAILED");
                }

            }
        }
    }

    @Given("Rollback PIN Change by PanSeqNumExpiryDate {int} iterations {int} records")
    public void runRollbackPINChangeByPanSeqNumExpiryDate(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanSeqNoExpiryDate(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinChangeRollbackByPanSeqNoExpiryDate2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinChangeRollbackByPanSeqNoExpiryDate2 FAILED");
                }

            }
        }
    }

    @Given("Rollback PIN Change by PAN Alias {int} iterations {int} records")
    public void runRollbackPINChangeByPANAlias(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinChangeRollback.dataloadPinChangeRollbackByPanAlias(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinChangeRollbackByPanAlias2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinChangeRollbackByPanAlias2 FAILED");
                }

            }
        }
    }
}


