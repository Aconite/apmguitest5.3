package com.aconite.apm.gui.automation.bindings;

import com.aconite.apm.gui.automation.data.CoreErrorMessages;
//import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinDelete;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadPinImport;
import com.aconite.apm.gui.automation.utilities.WebRequests;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataloadPinDeleteTestSteps {

    public WebDriver webDriver = null;
    public CoreErrorMessages coreErrorMessages;

    public DataloadPinDeleteTestSteps(AbstractSteps abstractSteps){
        try {
            webDriver = abstractSteps.getDriver();
            coreErrorMessages = new CoreErrorMessages();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }


    @Given("PIN Delete by PIN ID {int} iterations {int} records")
    public void runPinDeleteByPinId(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;
        String xmlrsp2;

        for(int it=0;it<iterations;it++) {

            List<String> pinIdsList = new ArrayList<>();

            for(int recs=0;recs<records;recs++) {

                xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportMultiple(records));

                responseData = WebRequests.getResponseData(xmlrsp);

                for (int i = 0; i < responseData.size(); i++) {

                    System.out.println("PIN Import " + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));

                    if (responseData.get(i).get("ResponseCode").equals("1")) {
                        pinIdsList.add(responseData.get(i).get("PINID"));
                    } else {
                        System.out.println("NEED TO QUIT HERE");
                    }

                }

            }

            System.out.println("panIdsList = " + pinIdsList);
            for(String pinId : pinIdsList) {
                xmlrsp2 = WebRequests.dataload(DataLoadPinDelete.dataloadPinDeleteByPinId(pinId));
                responseData = WebRequests.getResponseData(xmlrsp2);

                for (int i = 0; i < responseData.size(); i++) {
                    System.out.println("PIN Delete By Pin Id" + i + ": Response = " +
                            responseData.get(i).get("ResponseCode") +
                            " - " +
                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
                }
            }
        }
    }

//    @Given("PIN Delete by Issuer PIN ID {int} iterations {int} records")
//    public void runPinDeleteByIssuerPinId(int iterations, int records) {
//
//        HashMap<Integer, HashMap<String, String>> responseData;
//        String xmlrsp;
//        String xmlrsp2;
//        List<String> pinIdsList = new ArrayList<>();
//        List<String> issuerPinIdsList = new ArrayList<>();
//        String issuerPinId;
//
//        for(int it=0;it<iterations;it++) {
//
//
//
//            for(int recs=0;recs<records;recs++) {
//
//                xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportMultiple(records));
//                responseData = WebRequests.getResponseData(xmlrsp);
//
//                for (int i = 0; i < responseData.size(); i++) {
//
//                    System.out.println("PIN Import " + i + ": Response = " +
//                            responseData.get(i).get("ResponseCode") +
//                            " - " +
//                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
//
//                    if (responseData.get(i).get("ResponseCode").equals("1")) {
//                        pinIdsList.add(responseData.get(i).get("PINID"));
//                    } else {
//                        System.out.println("NEED TO QUIT HERE");
//                    }
//
//                }
//
//            }
//
//            for(String item: pinIdsList) {
//                issuerPinId = DataLoadDataGatherer.dataloadGetIssuerPinAliasByPinId(item);
//                issuerPinIdsList.add(issuerPinId);
//            }
//
//            for(String pinId : pinIdsList) {
//                xmlrsp2 = WebRequests.dataload(DataLoadPinDelete.dataloadPinDeleteByIssuerPinId(pinId));
//                responseData = WebRequests.getResponseData(xmlrsp2);
//
//                for (int i = 0; i < responseData.size(); i++) {
//                    System.out.println("PIN Delete By Issuer Pin Id" + i + ": Response = " +
//                            responseData.get(i).get("ResponseCode") +
//                            " - " +
//                            coreErrorMessages.getErrorMessageByCode(responseData.get(i).get("ResponseCode")));
//                }
//            }
//        }
//    }

}


