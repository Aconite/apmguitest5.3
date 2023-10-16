package com.aconite.apm.gui.automation.dataload;

/*===================================================================================================================
 * DataLoadTokenApplicationDataExtract
 * This will call all the functions to put enough data in for a TokenApplicationDataExtract test
 *===================================================================================================================*/

import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTOWPD;
import com.aconite.apm.gui.automation.dataload.xmlrequests.DataLoadTokenImport;
import com.aconite.apm.gui.automation.utilities.WebRequests;

import java.util.HashMap;

public class DataLoadTokenApplicationDataExtract
{
    public static String runDataLoadTokenApplicationDataExtract() {

        StringBuilder out = new StringBuilder();
        String xmlrsp;
        String pinEncrypted;
        String pinVerificationValue;
        String pvvKeyIndex;
        String panId;
        String panClear;
        String panSeqNum;
        String panExpDate;
        String issuerPanAlias;

        HashMap<Integer, HashMap<String, String>> responseData ;

        /*--------------------------------------------------------------
         * TOWPD_OT1_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail: PANID="); 
                out.append(panId); 
                out.append(", PAN="); 
                out.append(panClear); 
                out.append(", PANSEQUENCENUMBER="); 
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }
  

        /*--------------------------------------------------------------
         * TOWPD_OT2_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT3_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT4_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_Mail FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT6_Mail
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_Mail(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_Mail FAIL: "); out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        


        /*--------------------------------------------------------------
         * Token Import
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTokenImport.dataloadTokenImport(5));
        responseData = WebRequests.getResponseData(xmlrsp);
        for (int i = 0; i < responseData.size(); i++ ){

            if(responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");

                out.append("TRX_TYPE_ID 7 - TOKEN IMPORT: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
            }
            else{
                out.append("TRX_TYPE_ID 7 - TOKEN IMPORT FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");
        }
        


        /*===============================================================
         * TOWPD - SMS
         *===============================================================*/

        /*--------------------------------------------------------------
         * TOWPD_OT1_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT1_SMS(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT1_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT1_SMS FAIL: "); out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT2_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT2_SMS(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT2_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT2_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT3_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT3_SMS(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT3_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT3_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT4_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT4_SMS(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT4_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT4_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }
        

        /*--------------------------------------------------------------
         * TOWPD_OT6_SMS
         *--------------------------------------------------------------*/
        xmlrsp = WebRequests.dataload(DataLoadTOWPD.dataloadTOWPD_OT6_SMS(5));
        responseData = WebRequests.getResponseData(xmlrsp);

        for (int i = 0; i < responseData.size(); i++ ) {

            if (responseData.get(i).get("ResponseCode").equals("1")) {
                panId = responseData.get(i).get("PANID");
                panClear = responseData.get(i).get("PAN");
                panSeqNum = responseData.get(i).get("PANSequenceNumber");
                panExpDate = responseData.get(i).get("PANExpiryDate");
                issuerPanAlias = responseData.get(i).get("IssuerPANAlias");
                pinEncrypted = responseData.get(i).get("PIN");
                pinVerificationValue = responseData.get(i).get("PINVerificationValue");
                pvvKeyIndex = responseData.get(i).get("PVVKeyIndex");

                out.append("TRX_TYPE_ID 9 - TOWPD_OT6_SMS: PANID=");
                out.append(panId);
                out.append(", PAN=");
                out.append(panClear);
                out.append(", PANSEQUENCENUMBER=");
                out.append(panSeqNum);
                out.append(", PANEXPIRYDATE=");
                out.append(panExpDate);
                out.append(", ISSUERPANALIAS=");
                out.append(issuerPanAlias);
                out.append(", PIN=");
                out.append(pinEncrypted);
                out.append(", PINVERIFICATIONVALUE=");
                out.append(pinVerificationValue);
                out.append(", PVVKEYINDEX=");
                out.append(pvvKeyIndex);

            } else {
                out.append("TOWPD_OT6_SMS FAIL: ");
                out.append(responseData.get(i).get("ErrorDescription"));
            }

            out.append("\n");

        }

        return(out.toString());

    }


}


