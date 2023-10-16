package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;

import java.util.HashMap;
import java.util.List;

public class DataLoadPinAdvice
{

    public static String dataloadPinAdviceByPanIdSMS(List<String> panIdsList){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPANID_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 0;i<panIdsList.size();i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i+1);
            xml.append("\">");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PANID>");
            xml.append(panIdsList.get(i));
            xml.append("</apm:PANID>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:PINAdviceByPANID_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinAdviceByPanIdMail(String inPanId){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPANID_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:record record_number=\"");
        xml.append("1");
        xml.append("\">");
        xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
        xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
        xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
        xml.append("<apm:PANID>");
        xml.append(inPanId);
        xml.append("</apm:PANID>");
        xml.append("<apm:PINMailerData>");
        xml.append("<apm:MailingCode>2</apm:MailingCode>");
        xml.append("<apm:TokenholderName>");
        xml.append("<apm:Title>Mr</apm:Title>");
        xml.append("<apm:FirstName>PINAdvice</apm:FirstName>");
        xml.append("<apm:MiddleName>");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("</apm:MiddleName>");
        xml.append("<apm:LastName>ByPANIDMail</apm:LastName>");
        xml.append("</apm:TokenholderName>");
        xml.append("<apm:TokenholderAddress>");
        xml.append("<apm:AddressLine1>Pin Advice By PAN ID - Mail</apm:AddressLine1>");
        xml.append("<apm:Town>PAN ID:");
        xml.append(inPanId);
        xml.append("</apm:Town>");
        xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
        xml.append("<apm:CountryCode>826</apm:CountryCode>");
        xml.append("</apm:TokenholderAddress>");
        xml.append("</apm:PINMailerData>");
        xml.append("</apm:record>");


        xml.append("</apm:PINAdviceByPANID_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinAdviceByPanAliasSMS(List<String> panAliasList){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPANAlias_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 0;i<panAliasList.size();i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i+1);
            xml.append("\">");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:IssuerPANAlias>");
            xml.append(panAliasList.get(i));
            xml.append("</apm:IssuerPANAlias>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:PINAdviceByPANAlias_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

      }

    public static String dataloadPinAdviceByPanAliasMail(String issuerPanAlias){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPANAlias_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:record record_number=\"");
        xml.append("1");
        xml.append("\">");
        xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
        xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
        xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
        xml.append("<apm:IssuerPANAlias>");
        xml.append(issuerPanAlias);
        xml.append("</apm:IssuerPANAlias>");
        xml.append("<apm:PINMailerData>");
        xml.append("<apm:MailingCode>2</apm:MailingCode>");
        xml.append("<apm:TokenholderName>");
        xml.append("<apm:Title>Mr</apm:Title>");
        xml.append("<apm:FirstName>PINAdvice</apm:FirstName>");
        xml.append("<apm:MiddleName>");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("</apm:MiddleName>");
        xml.append("<apm:LastName>ByPANAliasMail</apm:LastName>");
        xml.append("</apm:TokenholderName>");
        xml.append("<apm:TokenholderAddress>");
        xml.append("<apm:AddressLine1>Pin Advice By PAN Alias Mail</apm:AddressLine1>");
        xml.append("<apm:Town>PAN Alias:");
        xml.append(issuerPanAlias);
        xml.append("</apm:Town>");
        xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
        xml.append("<apm:CountryCode>826</apm:CountryCode>");
        xml.append("</apm:TokenholderAddress>");
        xml.append("</apm:PINMailerData>");
        xml.append("</apm:record>");
        xml.append("</apm:PINAdviceByPANAlias_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinAdviceByPanSeqNumExpDateSMS(List<HashMap> panDetailsList){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPAN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");

        for(int i = 0;i<panDetailsList.size();i++) {

            xml.append("<apm:record record_number=\"");
            xml.append(i+1);
            xml.append("\">");
            xml.append("<apm:PINDeliveryMethod>3</apm:PINDeliveryMethod>");
            xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("<apm:PAN EncryptedFlag=\"N\">");
            xml.append(panDetailsList.get(i).get("PAN"));
            xml.append("</apm:PAN>");
            xml.append("<apm:PANSeqNumber>");
            xml.append(panDetailsList.get(i).get("SeqNum"));
            xml.append("</apm:PANSeqNumber>");
            xml.append("<apm:PANExpiryDate>");
            xml.append(panDetailsList.get(i).get("ExpDate"));
            xml.append("</apm:PANExpiryDate>");
            xml.append("<apm:SMSData>");
            xml.append("<apm:MobilePhoneNumber>070");
            xml.append(DataLoadDataGatherer.getNewPin(8));
            xml.append("</apm:MobilePhoneNumber>");
            xml.append("<apm:LanguageCode>ENG</apm:LanguageCode>");
            xml.append("</apm:SMSData>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:PINAdviceByPAN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinAdviceByPanSeqNumExpDateMail(String panClear, String panSeqNum, String panExpDate){

        StringBuilder xml = new StringBuilder();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:PINAdviceByPAN_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        xml.append("<apm:record record_number=\"");
        xml.append("1");
        xml.append("\">");
        xml.append("<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>");
        xml.append("<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>");
        xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
        xml.append("<apm:PAN EncryptedFlag=\"N\">");
        xml.append(panClear);
        xml.append("</apm:PAN>");
        xml.append("<apm:PANSeqNumber>");
        xml.append(panSeqNum);
        xml.append("</apm:PANSeqNumber>");
        xml.append("<apm:PANExpiryDate>");
        xml.append(panExpDate);
        xml.append("</apm:PANExpiryDate>");
        xml.append("<apm:PINMailerData>");
        xml.append("<apm:MailingCode>2</apm:MailingCode>");
        xml.append("<apm:TokenholderName>");
        xml.append("<apm:Title>Mr</apm:Title>");
        xml.append("<apm:FirstName>PINAdvice</apm:FirstName>");
        xml.append("<apm:MiddleName>");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("</apm:MiddleName>");
        xml.append("<apm:LastName>ByPanSeqNumExpDate</apm:LastName>");
        xml.append("</apm:TokenholderName>");
        xml.append("<apm:TokenholderAddress>");
        xml.append("<apm:AddressLine1>By Pan SeqNum ExpDate- Mail</apm:AddressLine1>");
        xml.append("<apm:Town>PAN:");
        xml.append(panClear);
        xml.append("</apm:Town>");
        xml.append("<apm:PostCode>NE45 8GB</apm:PostCode>");
        xml.append("<apm:CountryCode>826</apm:CountryCode>");
        xml.append("</apm:TokenholderAddress>");
        xml.append("</apm:PINMailerData>");
        xml.append("</apm:record>");
        xml.append("</apm:PINAdviceByPAN_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinAdviceByPanIdMailHousekeeper(String panId){

        return("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:PINAdvice_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PANID>" + panId + "</apm:PANID>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>2</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>PINAdvice</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>ByPANIDMail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>Pin Advice By PAN ID - Mail</apm:AddressLine1>" +
                "<apm:Town>PAN ID:" + panId + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:record>" +
                "</apm:PINAdvice_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>");

    }

    public static String dataloadPinAdviceByPanAliasMailHousekeeper(String issuerPanAlias){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:PINAdvice_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:IssuerPANAlias>" + issuerPanAlias + "</apm:IssuerPANAlias>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>2</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>PINAdvice</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>ByPANAliasMail</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>Pin Advice By PAN Alias - Mail</apm:AddressLine1>" +
                "<apm:Town>PAN Alias:" + issuerPanAlias + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:record>" +
                "</apm:PINAdvice_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

    }

    public static String dataloadPinAdviceByPanSeqNumExpDateMailHousekeeper(String panClear, String panSeqNum, String panExpDate){

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<apm:PINAdvice_request RequestDateTime=\"" + DataLoadDataGatherer.getDateTime() + "\" RequestID=\"" + DataLoadDataGatherer.getIdentifier(10) + "\">" +
                "<apm:record record_number=\"1\">" +
                "<apm:PINDeliveryMethod>2</apm:PINDeliveryMethod>" +
                "<apm:PINAdviceForAllTokenApplicationsFlag>N</apm:PINAdviceForAllTokenApplicationsFlag>" +
                "<apm:PINPUKFlag>1</apm:PINPUKFlag>" +
                "<apm:PAN EncryptedFlag=\"N\">" + panClear + "</apm:PAN>" +
                "<apm:PANSeqNumber>" + panSeqNum + "</apm:PANSeqNumber>" +
                "<apm:PANExpiryDate>" + panExpDate + "</apm:PANExpiryDate>" +
                "<apm:PINMailerData>" +
                "<apm:MailingCode>2</apm:MailingCode>" +
                "<apm:TokenholderName>" +
                "<apm:Title>Mr</apm:Title>" +
                "<apm:FirstName>PINAdvice</apm:FirstName>" +
                "<apm:MiddleName>cardHolderDataUpdate</apm:MiddleName>" +
                "<apm:LastName>ByPanSeqNumExpDate</apm:LastName>" +
                "</apm:TokenholderName>" +
                "<apm:TokenholderAddress>" +
                "<apm:AddressLine1>By Pan SeqNum ExpDate- Mail</apm:AddressLine1>" +
                "<apm:Town>PAN:" + panClear + "</apm:Town>" +
                "<apm:PostCode>NE45 8GB</apm:PostCode>" +
                "<apm:CountryCode>826</apm:CountryCode>" +
                "</apm:TokenholderAddress>" +
                "</apm:PINMailerData>" +
                "</apm:record>" +
                "</apm:PINAdvice_request>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

    }


}