package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinVerify;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadPinVerifyTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadPinVerifyTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("Pin Verify By PAN ID {int} iterations {int} records")
    public void runPinVerifyByPanId(int iterations, int records){
        try {

            HashMap<Integer, HashMap<String, String>> responseData;
            String xmlrsp;

            for (int it = 0; it < iterations; it++) {

                xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanId(records));

                responseData = WebRequests.getResponseData(xmlrsp);
                for (int i = 0; i < responseData.size(); i++) {
                    System.out.println("PinVerifyByPanId " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                    if (!responseData.get(i).get("ResponseCode").equals("1")) {
                        System.out.println("NEED TO QUIT HERE");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Given("Pin Verify By PanSeqNumExpiryDate {int} iterations {int} records")
    public void runPinVerifyByPanSeqNumExpiryDate(int iterations, int records){
        try {
            HashMap<Integer, HashMap<String, String>> responseData;
            String xmlrsp;

            for (int it = 0; it < iterations; it++) {

                xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanSeqNoExpiryDate(records));

                responseData = WebRequests.getResponseData(xmlrsp);
                for (int i = 0; i < responseData.size(); i++) {
                    System.out.println("PinVerifyByPanSeqNoExpiryDate " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                    if (!responseData.get(i).get("ResponseCode").equals("1")) {
                        System.out.println("NEED TO QUIT HERE");
                    }
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Given("Pin Verify By PanAlias {int} iterations {int} records")
    public void runPinVerifyByPanAlias(int iterations, int records){

        try {

            HashMap<Integer, HashMap<String, String>> responseData;
            String xmlrsp;

            for (int it = 0; it < iterations; it++) {

                xmlrsp = WebRequests.dataload(DataLoadPinVerify.dataloadPinVerifyByPanAlias(records));

                responseData = WebRequests.getResponseData(xmlrsp);

                for (int i = 0; i < responseData.size(); i++) {
                    System.out.println("PinVerifyByPanAlias " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}


