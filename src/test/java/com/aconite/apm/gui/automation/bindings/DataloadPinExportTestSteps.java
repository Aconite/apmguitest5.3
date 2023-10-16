package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinExport;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadPinExportTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadPinExportTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }


    @Given("PIN Export by PAN ID {int} iterations {int} records")
    public void runPinExportByPanId(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanId(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinExportByPanId2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinExportByPanId2 FAILED");
                }

            }
        }
    }

    @Given("PIN Export by PanSeqNumExpiryDate {int} iterations {int} records")
    public void runPinExportByPanSeqNumExpiryDate(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanSeqNoExpiryDate(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinExportByPanSeqNoExpiryDate2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinExportByPanSeqNoExpiryDate2 FAILED");
                }

            }
        }
    }

    @Given("PIN Export by PAN Alias {int} iterations {int} records")
    public void runPinExportByPANAlias(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinExport.dataloadPinExportByPanAlias(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadPinExportByPanAlias2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadPinExportByPanAlias2 FAILED");
                }

            }
        }
    }
}


