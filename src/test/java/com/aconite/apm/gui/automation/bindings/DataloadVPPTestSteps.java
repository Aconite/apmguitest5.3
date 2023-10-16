package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadVPP;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DataloadVPPTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadVPPTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("Set VPP PIN ID By PAN {int} iterations")
    public void runSetVPPPINIDByPAN(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPAN());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadSetVPPPINIdByPAN " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadSetVPPPINIdByPAN FAILED");
                }

            }
        }
    }

    @Given("Set VPP PIN ID By PAN ID {int} iterations")
    public void runSetVPPPINIDByPANID(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPANId());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadSetVPPPINIdByPANID " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadSetVPPPINIdByPANID FAILED");
                }

            }
        }
    }

    @Given("Set VPP PIN ID By Issuer PIN ID {int} iterations")
    public void runSetVPPPINIDByIssuerPINID(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByIssuerPinId());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadSetVPPPINIdByPANID " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadSetVPPPINIdByPANID FAILED");
                }

            }
        }
    }

    @Given("Set VPP PIN ID By PAN Alias {int} iterations")
    public void runSetVPPPINIDByPANAlias(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPanAlias());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadSetVPPPINIdByPanAlias " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadSetVPPPINIdByPanAlias FAILED");
                }

            }
        }
    }

    @Given("VPP Set PIN {int} iterations")
    public void runSetVPPPIN(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPSetPIN());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadVPPSetPIN " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadVPPSetPIN FAILED");
                }

            }
        }
    }

    @Given("VPP Get PIN {int} iterations")
    public void runGetVPPPIN(Integer iterations) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;


        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPGetPIN());
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("dataloadVPPGetPIN " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (!responseData.get(i).get("ResponseCode").equals("1")) {
                    System.out.println("NEED TO QUIT HERE - dataloadVPPGetPIN FAILED");
                }

            }
        }
    }



}



