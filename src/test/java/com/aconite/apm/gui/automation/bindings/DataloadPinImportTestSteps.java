package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinImport;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import com.aconite.apm.gui.automation.data.CoreErrorMessages;

import java.util.HashMap;


public class DataloadPinImportTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadPinImportTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("Pin Import Single")
    public void runPinImportSingle(){

        HashMap<Integer, HashMap<String, String>> responseData;
        String requestId = DataLoadDataGatherer.getIdentifier(10);
        String issuerPinId = DataLoadDataGatherer.getIdentifier(18);
        String pin = "0D899B5EF21237AB";

        String xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportSingle(requestId, issuerPinId, pin));
        responseData = WebRequests.getResponseData(xmlrsp);

        for(int i = 0;i< responseData.size();i++){
            System.out.println("PIN Import " + i + ": Response = " +
                    responseData.get(i).get("ResponseCode") +
                    " - " +
                    coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
        }

    }

    @Given("Pin Import Multiple {int} iterations {int} records")
    public void runPinImportMultiple(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportMultiple(records));

            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {
                System.out.println("PIN Import Multiple " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                System.out.println("PIN ID = " + responseData.get(i).get("PINID"));
            }
        }

    }

    @Given("Token Import OrderType 1 SMS")
    public void runTOWPD_OT1_SMS(){
        WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS(5));
    }

}


