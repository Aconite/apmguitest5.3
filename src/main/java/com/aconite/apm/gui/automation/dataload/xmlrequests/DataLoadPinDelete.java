package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;

public class DataLoadPinDelete
{

    public static String dataloadPinDeleteByPinId(String pinId) {

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINDelete_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:record record_number=\"");
        xml.append("1");
        xml.append("\">");
        xml.append("<apm:PINID>");
        xml.append(pinId);
        xml.append("</apm:PINID>");
        xml.append("</apm:record>");
        xml.append("</apm:PINDelete_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinDeleteByIssuerPinId(String issuerPinId){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINDelete_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:record record_number=\"");
        xml.append("1");
        xml.append("\">");
        xml.append("<apm:IssuerPINID>");
        xml.append(issuerPinId);
        xml.append("</apm:IssuerPINID>");
        xml.append("</apm:record>");
        xml.append("</apm:PINDelete_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }


}