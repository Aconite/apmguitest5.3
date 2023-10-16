package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.records.DataLoadPinRecord;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadTranslate{



    public static String dataloadTranslatePanId(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TranslatePANID_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        for(int i = 1;i<=records;i++) {

            try {
                record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("</apm:record>");

    }

        xml.append("</apm:TranslatePANID_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runTranslatePanId(int records) {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePanId(records));
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadTranslatePan_old() throws Exception{

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TranslatePAN_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" +  record.getPanClear() + "</apm:PAN>" +
                "<apm:PANSeqNumber>" + record.getAppSequenceNumber() +"</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + record.getPanExpiryDate() + "</apm:PANExpiryDate>" +
                "</apm:record>" +
                "</apm:TranslatePAN_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);

    }

    public static String dataloadTranslatePan(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TranslatePAN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        for(int i = 1;i<=records;i++) {

            try {
                record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TranslatePAN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());
    }

    public static HashMap<Integer, HashMap<String, String>> runTranslatePan(int records) {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePan(records));
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadTranslatePanAlias_old() throws Exception{

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TranslatePANAlias_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerPANAlias>" + record.getPanAlias() + "</apm:IssuerPANAlias>" +
                "</apm:record>" +
                "</apm:TranslatePANAlias_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);

    }

    public static String dataloadTranslatePanAlias(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TranslatePANAlias_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        for(int i = 1;i<=records;i++) {

            try {
                record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
            }
            catch (Exception exc)
            {
                exc.printStackTrace();
            }
            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:IssuerPANAlias>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TranslatePANAlias_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());
    }

    public static HashMap<Integer, HashMap<String, String>> runTranslatePanAlias(int records) {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadTranslate.dataloadTranslatePanAlias(records));
        return WebRequests.getResponseData(xmlrsp);

    }

}