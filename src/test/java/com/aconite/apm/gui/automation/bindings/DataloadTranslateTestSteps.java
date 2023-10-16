package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTranslate;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadTranslateTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadTranslateTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("Translate PAN ID {int} iterations {int} records")
    public void runTranslatePANID(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePanId(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadTranslatePanId " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadTranslatePanId FAILED");
                }

            }
        }
    }

    @Given("Translate PAN {int} iterations {int} records")
    public void runTranslatePAN(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePan(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadTranslatePan2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadTranslatePan2 FAILED");
                }

            }
        }
    }

    @Given("Translate PAN Alias {int} iterations {int} records")
    public void runTranslatePANAlias(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePanAlias(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadTranslatePanAlias2 " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadTranslatePanAlias2 FAILED");
                }

            }
        }
    }


}



