package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTokenDelete;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadTokenDeleteTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadTokenDeleteTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("Token Delete By PAN ID {int} iterations {int} records")
    public void runTokenDeleteByPanId(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanId(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Delete By PAN ID " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

        }

    }

    @Given("Token Delete By PanSeqNumExpiryDate {int} iterations {int} records")
    public void runTokenDeleteByPanSeqNumExpiryDate(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanSeqNoExpiryDate(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Delete By PanSeqNumExpiryDate " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

        }

    }

    @Given("Token Delete By PAN Alias {int} iterations {int} records")
    public void runTokenDeleteByPanAlias(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadTokenDelete.dataloadTokenDeleteByPanAlias(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Delete By Pan Alias " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

        }

    }

}


