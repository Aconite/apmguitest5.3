package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.dataload.DataLoadAutomationTestData;
import com.aconite.apm.gui.automation.records.DataLoadPinRecord;
import com.aconite.apm.gui.automation.utilities.WebRequests;


import java.util.HashMap;
import java.util.List;

public class DataLoadTOWPD{

    private static final DataLoadAutomationTestData dataLoadAutomationTestDate = new DataLoadAutomationTestData();

    public static String dataloadTOWPD_OT1_SMS(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>1</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadTOWPD_OT1_Mail(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            System.out.println("dataloadTOWPD_OT1_Mail Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>1</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT1_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT1_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT1_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        ////System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static HashMap<Integer, HashMap<String, String>> runTOWPD_OT1_Mail(Integer records) {

        String xmlrsp;

        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail(records));
        return WebRequests.getResponseData(xmlrsp);


    }

    public static String dataloadTOWPD_OT1_Web2(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>1</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>5</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:WebData>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:WebData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT1_SMS_DM1(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>1</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT1_Mail_DM1(int records){

        StringBuilder xml = new StringBuilder();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 1;i<=records;i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i);
            xml.append("\">");
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>1</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT1_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT1_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT1_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT2_SMS(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }


        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>2</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT2_Mail(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>2</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT2_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT2_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT2_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append( "</apm:PINDeliveryData>");
            xml.append( "</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT2_Web2(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>2</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>5</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:WebData>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:WebData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT2_SMS_DM1(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }


        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>2</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT2_Mail_DM1(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>2</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PANID>");
            xml.append(record.getTokenId());
            xml.append("</apm:PANID>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT2_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT2_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT2_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append( "</apm:PINDeliveryData>");
            xml.append( "</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());

    }

    public static String dataloadTOWPD_OT3_SMS(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append( "<apm:OrderType>3</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousPAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PreviousPAN>");
            xml.append("<apm:PreviousPANSequenceNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PreviousPANSequenceNumber>");
            xml.append("<apm:PreviousPANExpirydate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PreviousPANExpirydate>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT3_Mail(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>3</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT3_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT3_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT3_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousPAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PreviousPAN>");
            xml.append("<apm:PreviousPANSequenceNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PreviousPANSequenceNumber>");
            xml.append("<apm:PreviousPANExpirydate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PreviousPANExpirydate>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT3_Web(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>3</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>5</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:WebData>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:WebData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousPAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PreviousPAN>");
            xml.append("<apm:PreviousPANSequenceNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PreviousPANSequenceNumber>");
            xml.append("<apm:PreviousPANExpirydate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PreviousPANExpirydate>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT3_SMS_DM1(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }


        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append( "<apm:OrderType>3</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousPAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PreviousPAN>");
            xml.append("<apm:PreviousPANSequenceNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PreviousPANSequenceNumber>");
            xml.append("<apm:PreviousPANExpirydate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PreviousPANExpirydate>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT3_Mail_DM1(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>3</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT3_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT3_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT3_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousPAN EncryptedFlag=\"N\">");
            xml.append(record.getPanClear());
            xml.append("</apm:PreviousPAN>");
            xml.append("<apm:PreviousPANSequenceNumber>");
            xml.append(record.getAppSequenceNumber());
            xml.append("</apm:PreviousPANSequenceNumber>");
            xml.append("<apm:PreviousPANExpirydate>");
            xml.append(record.getPanExpiryDate());
            xml.append("</apm:PreviousPANExpirydate>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT4_SMS(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>4</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PINID>");
            xml.append(record.getPinId());
            xml.append("</apm:PINID>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT4_Mail(int records)  {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>4</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT4_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT4_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT4_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append( "</apm:TokenholderAddress>");
            xml.append( "</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PINID>");
            xml.append(record.getPinId());
            xml.append("</apm:PINID>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT4_Web(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>4</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>5</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:WebData>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:WebData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PINID>");
            xml.append(record.getPinId());
            xml.append("</apm:PINID>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT4_SMS_DM1(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>4</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PINID>");
            xml.append(record.getPinId());
            xml.append("</apm:PINID>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT4_Mail_DM1(int records)  {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>4</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT4_Mail</apm:FirstName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT4_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT4_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append( "</apm:TokenholderAddress>");
            xml.append( "</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PINID>");
            xml.append(record.getPinId());
            xml.append("</apm:PINID>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT6_SMS(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>6</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousIssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:PreviousIssuerPANAlias>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT6_Mail(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>6</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT6_Mail</apm:FirstName>");
            xml.append("<apm:MiddleName>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:MiddleName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT6_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT6_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousIssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:PreviousIssuerPANAlias>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT6_Web(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>6</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>5</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:WebData>");
            xml.append("<apm:CustomerCode>");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("</apm:CustomerCode>");
            xml.append("</apm:WebData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousIssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:PreviousIssuerPANAlias>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT6_SMS_DM1(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>6</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousIssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:PreviousIssuerPANAlias>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    public static String dataloadTOWPD_OT6_Mail_DM1(int records) {

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"");
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
            xml.append("<apm:IssuerTokenProductCode>");
            xml.append(selTokenProductCode);
            xml.append("</apm:IssuerTokenProductCode>");
            xml.append("<apm:OrderType>6</apm:OrderType>");
            xml.append("<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>");
            xml.append("<apm:TokenApplication SequenceNumber=\"1\">");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(DataLoadDataGatherer.getPan());
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>1</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(DataLoadDataGatherer.createNewExpiryDate());
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(DataLoadDataGatherer.getIdentifier(18));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:PINDeliveryData>");
            xml.append("<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PINMailerData>");
            xml.append("<apm:MailingCode>1</apm:MailingCode>");
            xml.append("<apm:TokenholderName>");
            xml.append("<apm:Title>Mr</apm:Title>");
            xml.append("<apm:FirstName>dataloadTOWPD_OT6_Mail</apm:FirstName>");
            xml.append("<apm:LastName>dataloadTOWPD_OT6_Mail</apm:LastName>");
            xml.append("</apm:TokenholderName>");
            xml.append("<apm:TokenholderAddress>");
            xml.append("<apm:AddressLine1>dataloadTOWPD_OT6_Mail</apm:AddressLine1>");
            xml.append("<apm:Town>Camberwick Green</apm:Town>");
            xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
            xml.append("<apm:CountryCode>826</apm:CountryCode>");
            xml.append("</apm:TokenholderAddress>");
            xml.append("</apm:PINMailerData>");
            xml.append("</apm:PINDeliveryData>");
            xml.append("<apm:PreviousIssuerPANAlias>");
            xml.append(record.getPanAlias());
            xml.append("</apm:PreviousIssuerPANAlias>");
            xml.append("</apm:TokenApplication>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:TokenOrderWithPINDelivery_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        //System.out.println("XML = \n" + xml);
        return(xml.toString());
    }

    /*****************************************************************
     * Requests for cardHolderDataUpdate
     *****************************************************************/
    public static String dataloadTOWPD_OT1_Mail_Housekeeper(){

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        return("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>1</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT1_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT1_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT1_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>");


    }

    public static String dataloadTOWPD_OT2_Mail_Housekeeper() throws Exception{

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>2</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PANID>" + record.getTokenId() + "</apm:PANID>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT2_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT2_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT2_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT3_Mail_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>3</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT3_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT3_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT3_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousPAN EncryptedFlag=\"N\">" + record.getPanClear() + "</apm:PreviousPAN>" +
                "<apm:PreviousPANSequenceNumber>" + record.getAppSequenceNumber() + "</apm:PreviousPANSequenceNumber>" +
                "<apm:PreviousPANExpirydate>" + record.getPanExpiryDate() + "</apm:PreviousPANExpirydate>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT4_Mail_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>4</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT4_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT4_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT4_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PINID>" + record.getPinId() + "</apm:PINID>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT6_Mail_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>6</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT6_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT6_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT6_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousIssuerPANAlias>" + record.getPanAlias() + "</apm:PreviousIssuerPANAlias>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT1_Mail_DM1_Housekeeper(){

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>1</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT1_Mail_DM1</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT1_Mail_DM1</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT1_Mail_DM1</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);

    }

    public static String dataloadTOWPD_OT2_Mail_DM1_Housekeeper() throws Exception{

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>2</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PANID>" + record.getTokenId() + "</apm:PANID>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT2_Mail_DM1</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT2_Mail_DM1</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT2_Mail_DM1</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT3_Mail_DM1_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>3</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT3_Mail_DM1</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT3_Mail_DM1</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT3_Mail_DM1</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousPAN EncryptedFlag=\"N\">" + record.getPanClear() + "</apm:PreviousPAN>" +
                "<apm:PreviousPANSequenceNumber>" + record.getAppSequenceNumber() + "</apm:PreviousPANSequenceNumber>" +
                "<apm:PreviousPANExpirydate>" + record.getPanExpiryDate() + "</apm:PreviousPANExpirydate>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT4_Mail_DM1_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>4</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT4_Mail_DM1</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT4_Mail_DM1</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT4_Mail_DM1</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PINID>" + record.getPinId() + "</apm:PINID>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT6_Mail_DM1_Housekeeper() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>0){
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = "ABCC"; //try a known value
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>6</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>1</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT6_Mail</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>dataloadTOWPD_OT6_Mail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT6_Mail</apm:AddressLine1>" +
                "<apm:Town>Camberwick Green</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousIssuerPANAlias>" + record.getPanAlias() + "</apm:PreviousIssuerPANAlias>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT1_DM6(){

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>1</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>6</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT1_DM6</apm:FirstName>" +
                "<apm:LastName>dataloadTOWPD_OT1_DM6</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT1_DM6</apm:AddressLine1>" +
                "<apm:Town>" + DataLoadDataGatherer.getIdentifier(6) + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);

    }

    public static String dataloadTOWPD_OT2_DM6() throws Exception{

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>2</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PANID>" + record.getTokenId() + "</apm:PANID>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>6</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT2_DM6</apm:FirstName>" +
                "<apm:LastName>dataloadTOWPD_OT2_DM6</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT2_DM6</apm:AddressLine1>" +
                "<apm:Town>" + DataLoadDataGatherer.getIdentifier(6) + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT3_DM6() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>3</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>6</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT3_DM6</apm:FirstName>" +
                "<apm:LastName>dataloadTOWPD_OT3_DM6</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT3_DM6</apm:AddressLine1>" +
                "<apm:Town>" + DataLoadDataGatherer.getIdentifier(6) + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousPAN EncryptedFlag=\"N\">" + record.getPanClear() + "</apm:PreviousPAN>" +
                "<apm:PreviousPANSequenceNumber>" + record.getAppSequenceNumber() + "</apm:PreviousPANSequenceNumber>" +
                "<apm:PreviousPANExpirydate>" + record.getPanExpiryDate() + "</apm:PreviousPANExpirydate>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT4_DM6() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>4</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>6</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT4_DM6</apm:FirstName>" +
                "<apm:LastName>dataloadTOWPD_OT4_DM6</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT4_DM6</apm:AddressLine1>" +
                "<apm:Town>" + DataLoadDataGatherer.getIdentifier(6) + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PINID>" + record.getPinId() + "</apm:PINID>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }

    public static String dataloadTOWPD_OT6_DM6() throws Exception {

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>1){
            selTokenProductCode = issuerTokenProductCode.get(1);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>" +
                "<apm:OrderType>6</apm:OrderType>" +
                "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                "<apm:TokenApplication SequenceNumber=\"1\">" +
                "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>" +
                "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>6</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>1</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>dataloadTOWPD_OT6_DM6</apm:FirstName>" +
                "<apm:LastName>dataloadTOWPD_OT6_DM6</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>dataloadTOWPD_OT6_DM6</apm:AddressLine1>" +
                "<apm:Town>" + DataLoadDataGatherer.getIdentifier(6) + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:PINDeliveryData>" +
                "<apm:PreviousIssuerPANAlias>" + record.getPanAlias() + "</apm:PreviousIssuerPANAlias>" +
                "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        return(xml);
    }


    public static String dataloadTOWPD(String orderType, String deliveryMethodInt, String deliveryMethodString, String requestPurpose) throws Exception{

//        deliveryMethodInt = number to put in
//        deliveryMethodString = Delivery data block to include

        String headerXml;
        String orderTypeXml;
        String appSeqXml;
        String panDetailsXml;
        String expDateXml;
        String pinDelMethodXml;
        String pinOutXml="";
        String copyFromXml="";
        String endXml;

        DataLoadPinRecord record = DataLoadDataGatherer.dataloadGetPinRecordForVerify();

        String selTokenProductCode;
        List<String> issuerTokenProductCode = dataLoadAutomationTestDate.getTokenProductsForIssuerOne();
        if(issuerTokenProductCode.size()>2){
            selTokenProductCode = issuerTokenProductCode.get(2);
            //System.out.println("Taken from list: " + selTokenProductCode);
        }
        else{
            selTokenProductCode = issuerTokenProductCode.get(0);
            //System.out.println("Defaulted: " + selTokenProductCode);
        }

//        Assumes a single record for each request
//        TODO: create function for multiple records
        headerXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:TokenOrderWithPINDelivery_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:IssuerTokenProductCode>" + selTokenProductCode + "</apm:IssuerTokenProductCode>";

        orderTypeXml = "<apm:OrderType>" + orderType + "</apm:OrderType>";

        appSeqXml = "<apm:FeedbackRequiredFlag>Y</apm:FeedbackRequiredFlag>" +
                            "<apm:TokenApplication SequenceNumber=\"1\">";

        if(orderType.equals("2")){
            panDetailsXml = "<apm:PANID>" + record.getTokenId() + "</apm:PANID>" +
                    "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>" +
                    "<apm:PANSeqNumber>1</apm:PANSeqNumber>";

        }
        else{
            panDetailsXml = "<apm:PAN EncryptedFlag=\"N\">" + DataLoadDataGatherer.getPan() + "</apm:PAN>" +
                    "<apm:PANSeqNumber>1</apm:PANSeqNumber>" +
                    "<apm:IssuerPANAlias>" + DataLoadDataGatherer.getIdentifier(18) + "</apm:IssuerPANAlias>";
        }

        if(requestPurpose.equalsIgnoreCase("TOKENCLEANUP")) {
            expDateXml = "<apm:PANExpiryDate>" + DataLoadDataGatherer.createExpiredExpiryDate(7,12) + "</apm:PANExpiryDate>";
        }
        else{
            expDateXml = "<apm:PANExpiryDate>" + DataLoadDataGatherer.createNewExpiryDate() + "</apm:PANExpiryDate>";
        }

        pinDelMethodXml = "<apm:PINDeliveryData>" +
                "<apm:PINDeliveryMethod>" + deliveryMethodInt + "</apm:PINDeliveryMethod>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>";

//        System.out.println("Delivery Method String: " + deliveryMethodString);
        if(deliveryMethodString.equalsIgnoreCase("MAIL")){

            pinOutXml = "<apm:PINMailerData>" +
                    "<apm:MailingCode>1</apm:MailingCode>" +
                    "<apm:TokenholderName>" +
                    "<apm:Title>Mr</apm:Title>" +
                    "<apm:FirstName>dataloadTOWPD_OT" + deliveryMethodInt + "_" + deliveryMethodString + "</apm:FirstName>" +
                    "<apm:MiddleName>dataloadTOWPD_OT" + requestPurpose + "</apm:MiddleName>" +
                    "<apm:LastName>dataloadTOWPD_OT" + deliveryMethodInt + "_" + deliveryMethodString + "</apm:LastName>" +
                    "</apm:TokenholderName>" +
                    "<apm:TokenholderAddress>" +
                    "<apm:AddressLine1>dataloadTOWPD_OT" + deliveryMethodInt + "_" + deliveryMethodString + "</apm:AddressLine1>" +
                    "<apm:Town>Camberwick Green</apm:Town>" +
                    "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                    "<apm:CountryCode>826</apm:CountryCode>" +
                    "</apm:TokenholderAddress>" +
                    "</apm:PINMailerData>" +
                    "</apm:PINDeliveryData>";

        }

        if(deliveryMethodString.equalsIgnoreCase("SMS")){

            pinOutXml = "<apm:SMSData>" +
                    "<apm:MobilePhoneNumber>070" + DataLoadDataGatherer.getNewPin(8) + "</apm:MobilePhoneNumber>" +
                    "<apm:LanguageCode>ENG</apm:LanguageCode>" +
                    "</apm:SMSData>" +
                    "</apm:PINDeliveryData>";
        }

        if(deliveryMethodString.equalsIgnoreCase("WEB")) {

            pinOutXml = "<apm:WebData>" +
                    "<apm:CustomerCode>" + DataLoadDataGatherer.getIdentifier(10) + "</apm:CustomerCode>" +
                    "</apm:WebData>" +
                    "</apm:PINDeliveryData>";

        }

//        System.out.println("Order Type: " + orderType);

        if(orderType.equals("1") || orderType.equals("2")) {
            copyFromXml = "";
        }

        if(orderType.equals("3")){
            copyFromXml = "<apm:PreviousPAN EncryptedFlag=\"N\">" + record.getPanClear() + "</apm:PreviousPAN>" +
                            "<apm:PreviousPANSequenceNumber>" + record.getAppSequenceNumber() + "</apm:PreviousPANSequenceNumber>" +
                            "<apm:PreviousPANExpirydate>" + record.getPanExpiryDate() + "</apm:PreviousPANExpirydate>";
        }

        if(orderType.equals("4")){
            copyFromXml = "<apm:PINID>" + record.getPinId() + "</apm:PINID>";
        }

        if(orderType.equals("6")){
            copyFromXml = "<apm:PreviousIssuerPANAlias>" + record.getPanAlias() + "</apm:PreviousIssuerPANAlias>";
        }

        endXml = "</apm:TokenApplication>" +
                "</apm:record>" +
                "</apm:TokenOrderWithPINDelivery_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        String xml = headerXml + orderTypeXml + appSeqXml + panDetailsXml + expDateXml + pinDelMethodXml + pinOutXml + copyFromXml + endXml;
        System.out.println(xml);
        return(xml);
    }

}