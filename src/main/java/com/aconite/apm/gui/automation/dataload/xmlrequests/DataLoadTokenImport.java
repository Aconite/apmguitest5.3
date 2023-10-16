package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.DataLoadAutomationTestData;
import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;

import java.util.List;


public class DataLoadTokenImport{

    private static final DataLoadAutomationTestData dataLoadAutomationTestDate = new DataLoadAutomationTestData();

    public static String dataloadTokenImport(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            System.out.println("dataloadTokenImport Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenImport_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:OverwriteFlag>N</apm:OverwriteFlag>");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSequenceNumber>1</apm:PANSequenceNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:ApplicationStatus>1</apm:ApplicationStatus>");
            xml.append("</apm:TokenApplication>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenImport_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        System.out.println(xml);
        return(xml.toString());

    }

    public static String dataloadTokenImportTokenCleanUp(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            System.out.println("dataloadTokenImportTokenCleanUp Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenImport_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:OverwriteFlag>N</apm:OverwriteFlag>");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSequenceNumber>1</apm:PANSequenceNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:ApplicationStatus>1</apm:ApplicationStatus>");
            xml.append("</apm:TokenApplication>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenImport_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }


}