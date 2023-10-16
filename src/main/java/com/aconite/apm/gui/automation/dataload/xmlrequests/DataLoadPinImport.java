package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadPinImport
{

    public static String dataloadPinImportSingle(String requestId, String issuerPinId, String pin){

        return("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                        "<apm:PINImport_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + requestId + "\">" +
                            "<apm:record record_number=\"1\">" +
                                "<apm:IssuerPINID>" + issuerPinId + "</apm:IssuerPINID>" +
                                "<apm:PIN>" + pin + "</apm:PIN>" +
                            "</apm:record>" +
                        "</apm:PINImport_request>" +
                    "</soapenv:Body>" +
                "</soapenv:Envelope>");

    }

    public static String dataloadPinImportSingleExpired(){

        return("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                            "<soapenv:Header/>" +
                            "<soapenv:Body>" +
                                "<apm:PINImport_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                                    "<apm:record record_number=\"1\">" +
                                        "<apm:IssuerPINID>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPINID>" +
                                        "<apm:PIN>0D899B5EF21237AB</apm:PIN>" +
                                        "<apm:PINExpiryDate>2020-12-01</apm:PINExpiryDate>" +
                                    "</apm:record>" +
                                "</apm:PINImport_request>" +
                            "</soapenv:Body>" +
                        "</soapenv:Envelope>");


    }

    public static String dataloadPinImportMultiple(int records){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINImport_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++){
            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerPINID>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPINID>");
            xml.append("<apm:PIN>0D899B5EF21237AB</apm:PIN>");
            xml.append("</apm:record>");
        }

        xml.append("</apm:PINImport_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runPinImportSingle(){

        String requestId = DataLoadDataGatherer.getIdentifier(10);
        String issuerPinId = DataLoadDataGatherer.getIdentifier(18);
        String pin = "0D899B5EF21237AB";

        String xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportSingle(requestId, issuerPinId, pin));
        return WebRequests.getResponseData(xmlrsp);

    }

    public static void runPinImportMultiple(int iterations, int records) {

        HashMap<Integer, HashMap<String, String>> responseData;
        String xmlrsp;

        for (int it = 0; it < iterations; it++) {

            xmlrsp = WebRequests.dataload(DataLoadPinImport.dataloadPinImportMultiple(records));

            responseData = WebRequests.getResponseData(xmlrsp);

            for (int i = 0; i < responseData.size(); i++) {
                System.out.println("PIN Import Multiple " + i + ": Response = " +
                        responseData.get(i).get("ResponseCode"));
                System.out.println("PIN ID = " + responseData.get(i).get("PINID"));
            }
        }

    }



}