package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.records.DataLoadPinRecord;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadVPP{

    public static String dataloadSetVPPPINIdByPAN() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPAN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:PAN EncryptedFlag=\"N\">");
        xml.append(record.getPanClear());
        xml.append("</apm:PAN>");
        xml.append("<apm:PANSequenceNumber>");
        xml.append(record.getAppSequenceNumber());
        xml.append("</apm:PANSequenceNumber>");
        xml.append("<apm:PANExpiryDate>");
        xml.append(record.getPanExpiryDate());
        xml.append("</apm:PANExpiryDate>");
        xml.append("<apm:UniquenessFlag>N</apm:UniquenessFlag>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("</apm:SetVPPPINIdByPAN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runSetVPPPINIDByPAN() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPAN());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadSetVPPPINIdByPANId() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPANID_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:PANID>");
        xml.append(record.getTokenId());
        xml.append("</apm:PANID>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("</apm:SetVPPPINIdByPANID_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runSetVPPPINIDByPANID() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPANId());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadSetVPPPINIdByIssuerPinId() {

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByIssuerPINId_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:IssuerPINId>");
        try {
            xml.append(DataLoadDataGatherer.dataloadGetRandomIssuerPinId());
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        xml.append("</apm:IssuerPINId>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("</apm:SetVPPPINIdByIssuerPINId_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runSetVPPPINIDByIssuerPinId() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByIssuerPinId());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadSetVPPPINIdByPanAlias() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPANAlias_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:IssuerPANAlias>");
        xml.append(record.getPanAlias());
        xml.append("</apm:IssuerPANAlias>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("</apm:SetVPPPINIdByPANAlias_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runSetVPPPINIDByPanAlias() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadSetVPPPINIdByPanAlias());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadVPPSetPIN() {

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:VPPSetPIN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:VPPPINId>");
        try {
            xml.append(DataLoadDataGatherer.dataloadGetRandomVPPPinId());
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        xml.append("</apm:VPPPINId>");
        xml.append("<apm:PIN>");
        try {
            xml.append(DataLoadDataGatherer.getVPPPinBlock());
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        xml.append("</apm:PIN>");
        xml.append("</apm:VPPSetPIN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runSetVPPPIN() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPSetPIN());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadVPPGetPIN() {

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:VPPGetPIN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:VPPPINId>");
        try {
            xml.append(DataLoadDataGatherer.dataloadGetRandomVPPPinId());
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        xml.append("</apm:VPPPINId>");
        xml.append("</apm:VPPGetPIN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runGetVPPPIN() {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadVPP.dataloadVPPGetPIN());
        return WebRequests.getResponseData(xmlrsp);

    }

    public static String dataloadSetExpiredVPPPINIdByPAN() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPAN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:PAN EncryptedFlag=\"N\">");
        xml.append(record.getPanClear());
        xml.append("</apm:PAN>");
        xml.append("<apm:PANSequenceNumber>");
        xml.append(record.getAppSequenceNumber());
        xml.append("</apm:PANSequenceNumber>");
        xml.append("<apm:PANExpiryDate>");
        xml.append(record.getPanExpiryDate());
        xml.append("</apm:PANExpiryDate>");
        xml.append("<apm:UniquenessFlag>N</apm:UniquenessFlag>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("<apm:VPPPINIdExpiryTime>1640995199</apm:VPPPINIdExpiryTime>");
        xml.append("</apm:SetVPPPINIdByPAN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadSetExpiredVPPPINIdByPANId() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPANID_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:PANID>");
        xml.append(record.getTokenId());
        xml.append("</apm:PANID>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("<apm:VPPPINIdExpiryTime>1640995199</apm:VPPPINIdExpiryTime>");
        xml.append("</apm:SetVPPPINIdByPANID_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadSetExpiredVPPPINIdByIssuerPinId() {

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByIssuerPINId_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:IssuerPINId>");
        try {
            xml.append(DataLoadDataGatherer.dataloadGetRandomIssuerPinId());
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        xml.append("</apm:IssuerPINId>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("<apm:VPPPINIdExpiryTime>1640995199</apm:VPPPINIdExpiryTime>");
        xml.append("</apm:SetVPPPINIdByIssuerPINId_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadSetExpiredVPPPINIdByPanAlias() {

        DataLoadPinRecord record = new DataLoadPinRecord();
        StringBuilder xml = new StringBuilder();

        try {
            record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:SetVPPPINIdByPANAlias_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:IssuerPANAlias>");
        xml.append(record.getPanAlias());
        xml.append("</apm:IssuerPANAlias>");
        xml.append("<apm:VPPPINId>");
        xml.append(DataLoadDataGatherer.getIdentifier(15));
        xml.append("</apm:VPPPINId>");
        xml.append("<apm:VPPPINIdExpiryTime>1640995199</apm:VPPPINIdExpiryTime>");
        xml.append("</apm:SetVPPPINIdByPANAlias_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }


}