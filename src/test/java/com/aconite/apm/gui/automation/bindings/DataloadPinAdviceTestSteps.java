package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinAdvice;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTokenImport;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataloadPinAdviceTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadPinAdviceTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    @Given("PIN Advice By PAN ID SMS {int} iterations {int} records")
    public void runPinAdviceByPanIdSMS(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<String> panIdsList = new ArrayList<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    panIdsList.add(responseData.get(i).get("PANID"));
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

            xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdSMS(panIdsList));
            responseData = WebRequests.getResponseData(xmlrsp2);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Pin Advice By Pan Id SMS " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
            }

        }

    }

    @Given("PIN Advice By PAN ID Mail {int} iterations {int} records")
    public void runPinAdviceByPanIdMail(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<String> panIdsList = new ArrayList<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    panIdsList.add(responseData.get(i).get("PANID"));
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

            for(String panId : panIdsList) {
                xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanIdMail(panId));
                responseData = WebRequests.getResponseData(xmlrsp2);

                for (int i = 0; i < responseData.size(); i++) {

                    System.out.println("Pin Advice By Pan Id Mail " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                }
            }
        }

    }

    @Given("PIN Advice By PAN Alias SMS {int} iterations {int} records")
    public void runPinAdviceByPanAliasSMS(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<String> panAliasList = new ArrayList<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    panAliasList.add(responseData.get(i).get("IssuerPANAlias"));
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

            xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasSMS(panAliasList));
            responseData = WebRequests.getResponseData(xmlrsp2);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Pin Advice By Pan Alias SMS " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
            }

        }

    }

    @Given("PIN Advice By PAN Alias Mail {int} iterations {int} records")
    public void runPinAdviceByPanAliasMail(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<String> panAliasList = new ArrayList<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    panAliasList.add(responseData.get(i).get("IssuerPANAlias"));
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

            System.out.println("panIdsList = " + panAliasList);

            for(String panAlias : panAliasList) {
                xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanAliasMail(panAlias));
                responseData = WebRequests.getResponseData(xmlrsp2);

                for (int i = 0; i < responseData.size(); i++) {

                    System.out.println("Pin Advice By Pan Alias Mail " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                }
            }
        }

    }

    @Given("PIN Advice By PanSeqNumExpiryDate SMS {int} iterations {int} records")
    public void runPinAdviceByPanSeqNumExpiryDateSMS(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<HashMap> panDetailsList = new ArrayList<>();
            HashMap<String, String> tokenDetails = new HashMap<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    tokenDetails.put("PAN",responseData.get(i).get("PAN"));
                    tokenDetails.put("SeqNum",responseData.get(i).get("PANSequenceNumber"));
                    tokenDetails.put("ExpDate",responseData.get(i).get("PANExpiryDate"));
                    panDetailsList.add(tokenDetails);
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }

            xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateSMS(panDetailsList));
            responseData = WebRequests.getResponseData(xmlrsp2);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Pin Advice By PanSeqNumExpiryDate SMS " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
            }

        }

    }

    @Given("PIN Advice By PanSeqNumExpiryDate Mail {int} iterations {int} records")
    public void runPinAdviceByPanSeqNumExpiryDateMail(Integer iterations, Integer records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for (int it = 0; it < iterations; it++) {

            List<HashMap> panDetailsList = new ArrayList<>();
            HashMap<String, String> tokenDetails = new HashMap<>();

            xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(records));
            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {

                System.out.println("Token Import " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode") +
                        " - " +
                        coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                if (responseData.get(i).get("ResponseCode").equals("1")) {
                    tokenDetails.put("PAN",responseData.get(i).get("PAN"));
                    tokenDetails.put("SeqNum",responseData.get(i).get("PANSequenceNumber"));
                    tokenDetails.put("ExpDate",responseData.get(i).get("PANExpiryDate"));
                    panDetailsList.add(tokenDetails);
                }
                else {
                    System.out.println("NEED TO QUIT HERE");
                }

            }
            for(HashMap<String, String> panDetail : panDetailsList) {
                xmlrsp2 = WebRequests.dataload(DataLoadPinAdvice.dataloadPinAdviceByPanSeqNumExpDateMail(panDetail.get("PAN"), panDetail.get("SeqNum"), panDetail.get("ExpDate")));
                responseData = WebRequests.getResponseData(xmlrsp2);

                for (int i = 0; i < responseData.size(); i++) {

                    System.out.println("Pin Advice By PanSeqNumExpiryDate Mail " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                }
            }
        }

    }





}








