package com.aconite.apm.gui.automation.dataload.xmlrequests;

import com.aconite.apm.gui.automation.dataload.datagatherers.DataLoadDataGatherer;
import com.aconite.apm.gui.automation.records.DataLoadPinRecord;

public class DataLoadPinChangeRollback{

    public static String dataloadPinChangeRollbackByPanId(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:RollbackPINChange_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        for(int i = 1;i<=records;i++) {

            try {
                record = DataLoadDataGatherer.dataloadGetUpdatedPins();
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
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:RollbackPINChange_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinChangeRollbackByPanSeqNoExpiryDate(int records){

        StringBuilder xml = new StringBuilder();
        DataLoadPinRecord record = new DataLoadPinRecord();

        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
        xml.append("<soapenv:Header/>");
        xml.append("<soapenv:Body>");
        xml.append("<apm:RollbackPINChange_request RequestDateTime=\"");
        xml.append(DataLoadDataGatherer.getDateTime());
        xml.append("\" RequestID=\"");
        xml.append(DataLoadDataGatherer.getIdentifier(10));
        xml.append("\">");
        for(int i = 1;i<=records;i++) {

            try {
                record = DataLoadDataGatherer.dataloadGetUpdatedPins();
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
            xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
            xml.append("</apm:record>");

        }

        xml.append("</apm:RollbackPINChange_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }

    public static String dataloadPinChangeRollbackByPanAlias(int records){

            StringBuilder xml = new StringBuilder();
            DataLoadPinRecord record = new DataLoadPinRecord();

            xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:apm=\"http://www.aconite.net/schema/apm\">");
            xml.append("<soapenv:Header/>");
            xml.append("<soapenv:Body>");
            xml.append("<apm:RollbackPINChange_request RequestDateTime=\"");
            xml.append(DataLoadDataGatherer.getDateTime());
            xml.append("\" RequestID=\"");
            xml.append(DataLoadDataGatherer.getIdentifier(10));
            xml.append("\">");
            for(int i = 1;i<=records;i++) {

                try {
                    record = DataLoadDataGatherer.dataloadGetUpdatedPins();
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
                xml.append("<apm:PINPUKFlag>1</apm:PINPUKFlag>");
                xml.append("</apm:record>");

            }

        xml.append("</apm:RollbackPINChange_request>");
        xml.append("</soapenv:Body>");
        xml.append("</soapenv:Envelope>");

        return(xml.toString());

    }
}